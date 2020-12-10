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
import sunghs.packet.sniff.model.PacketContext;
import sunghs.packet.sniff.util.CommonUtils;
import sunghs.packet.sniff.util.PacketParser;

@RequiredArgsConstructor
@Service
@Slf4j
public class PacketSniffService {

    private final PcapHandle pcapHandle;

    private final SniffConfig sniffConfig;

    private PacketContext parsePacket(Packet packet, PacketType packetType) {
        PacketContext packetContext = new PacketContext();
        // encapsulation packet header
        // EthernetPacketHeader -> IpV4PacketHeader -> TcpPacketHeader -> Data
        while (CommonUtils.isNotEmpty(packet)) {
            if (packet instanceof EthernetPacket) {
                packetContext.setEthernetInfo(PacketParser.parse((EthernetPacket) packet));
            } else if (packet instanceof IpV4Packet) {
                packetContext.setIpv4Info(PacketParser.parse((IpV4Packet) packet));
            } else if (packet instanceof TcpPacket) {
                packetContext.setTcpInfo(PacketParser.parse((TcpPacket) packet));
            } else {
                String data = ((UnknownPacket) packet).toHexString();
                packetContext.setData(data);
            }
            packet = packet.getPayload();
        }
        return StringUtils.isEmpty(packetContext.getData()) ?
            PacketContext.EMPTY_CONTEXT : packetContext;
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
            log.info("{}", packetContext);
        };
        pcapHandle.setFilter(SniffType.TCP.getTypeName(), SniffConstant.DEFAULT_FILTER_MODE);
        pcapHandle.loop(-1, packetListener);
    }
}