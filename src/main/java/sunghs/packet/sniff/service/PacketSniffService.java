package sunghs.packet.sniff.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.UnknownPacket;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sunghs.packet.sniff.config.SniffConfig;
import sunghs.packet.sniff.constant.PacketType;
import sunghs.packet.sniff.constant.SniffConstant;
import sunghs.packet.sniff.constant.SniffType;
import sunghs.packet.sniff.constant.TransmissionDirection;
import sunghs.packet.sniff.model.Ipv4Info;
import sunghs.packet.sniff.model.PacketContext;
import sunghs.packet.sniff.util.CommonUtils;
import sunghs.packet.sniff.util.PacketParser;

@RequiredArgsConstructor
@Service
@Slf4j
public class PacketSniffService {

    private final PcapHandle pcapHandle;

    private final SniffConfig sniffConfig;

    private TransmissionDirection getDirection(final Ipv4Info ipv4Info) {
        String source = ipv4Info.getSourceIp();
        String dest = ipv4Info.getDestIp();
        String target = sniffConfig.getIp();

        if (StringUtils.isEmpty(source) || StringUtils.isEmpty(dest)) {
            return TransmissionDirection.UNKNOWN;
        }

        if (target.equalsIgnoreCase(source)) {
            return TransmissionDirection.SEND;
        } else if (target.equalsIgnoreCase(dest)) {
            return TransmissionDirection.RECEIVED;
        } else {
            return TransmissionDirection.UNKNOWN;
        }
    }

    private PacketContext parsePacket(Packet packet, PacketType packetType) {
        PacketContext packetContext = new PacketContext();
        packetContext.setPacketType(packetType);
        packetContext.setTransmissionDirection(TransmissionDirection.UNKNOWN);

        // encapsulation packet header
        // EthernetPacketHeader -> IpV4PacketHeader -> TcpPacketHeader -> Data
        while (CommonUtils.isNotEmpty(packet)) {
            if (packet instanceof EthernetPacket) {
                packetContext.setEthernetInfo(PacketParser.parse((EthernetPacket) packet));
            } else if (packet instanceof IpV4Packet) {
                Ipv4Info ipv4Info = PacketParser.parse((IpV4Packet) packet);
                packetContext.setIpv4Info(ipv4Info);
                packetContext.setTransmissionDirection(getDirection(ipv4Info));
            } else if (packet instanceof TcpPacket) {
                packetContext.setTcpInfo(PacketParser.parse((TcpPacket) packet));
            } else {
                String data = ((UnknownPacket) packet).toHexString();
                packetContext.setData(data);
            }
            packet = packet.getPayload();
        }
        return StringUtils.isEmpty(packetContext.getData()) && sniffConfig.isRequiredData() ?
            null : packetContext;
    }

    public void listen() throws PcapNativeException, NotOpenException, InterruptedException {
        PacketListener packetListener = packet -> {
            PacketContext packetContext;
            if (packet instanceof EthernetPacket) {
                log.debug("ETHERNET PACKET CAPTURE");
                packetContext = parsePacket(packet, PacketType.ETHERNET);
            } else if (packet instanceof IpV4Packet) {
                log.debug("IPV4 PACKET CAPTURE");
                packetContext = parsePacket(packet, PacketType.IPV4);
            } else if (packet instanceof TcpPacket) {
                log.debug("TCP PACKET CAPTURE");
                packetContext = parsePacket(packet, PacketType.TCP);
            } else {
                log.debug("UNKNOWN PACKET CAPTURE");
                packetContext = parsePacket(packet, PacketType.ETC);
            }

            // TODO Persistent System
            if(CommonUtils.isNotEmpty(packetContext)) {
                log.debug("{}", packetContext);
            }
        };
        pcapHandle.setFilter(SniffType.TCP.getTypeName(), SniffConstant.DEFAULT_FILTER_MODE);
        pcapHandle.loop(-1, packetListener);
    }
}