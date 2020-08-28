package sunghs.packet.sniff.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.EthernetPacket.EthernetHeader;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.UnknownPacket;
import org.pcap4j.packet.namednumber.EtherType;
import org.pcap4j.util.MacAddress;
import org.springframework.stereotype.Service;
import sunghs.packet.sniff.constant.SniffConstant;
import sunghs.packet.sniff.constant.SniffType;

@RequiredArgsConstructor
@Service
@Slf4j
public class PacketSniffService {

    private final PcapHandle pcapHandle;

    private List<Packet> parsePacket(Packet packet) {
        // EthernetPacket -> IpV4Packet -> TcpPacket -> UnknownPacket
        while (!(packet instanceof UnknownPacket)) {
            log.info(packet.getClass().getCanonicalName());
            packet = packet.getPayload();
        }
        return null;
    }

    public void listen() throws PcapNativeException, NotOpenException, InterruptedException {
        PacketListener packetListener = packet -> {
            if (packet instanceof EthernetPacket) {
                log.debug("ETHERNET PACKET CAPTURE");
                EthernetPacket ethernetPacket = (EthernetPacket) packet;
                EthernetHeader ethernetHeader = ethernetPacket.getHeader();
                MacAddress sourceAddress = ethernetHeader.getSrcAddr();
                MacAddress destAddress = ethernetHeader.getSrcAddr();
                EtherType etherType = ethernetHeader.getType();
                int headerLength = ethernetHeader.length();
                parsePacket(packet);
            }

            if (packet instanceof TcpPacket) {
                log.debug("TCP PACKET CAPTURE");
            }
        };
        pcapHandle.setFilter(SniffType.TCP.getTypeName(), SniffConstant.DEFAULT_FILTER_MODE);
        pcapHandle.loop(-1, packetListener);
    }
}