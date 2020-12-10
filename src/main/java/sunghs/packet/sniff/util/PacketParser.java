package sunghs.packet.sniff.util;

import java.net.Inet4Address;
import lombok.extern.slf4j.Slf4j;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.EthernetPacket.EthernetHeader;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.IpV4Packet.IpV4Header;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.TcpPacket.TcpHeader;
import org.pcap4j.packet.namednumber.EtherType;
import org.pcap4j.packet.namednumber.IpNumber;
import org.pcap4j.packet.namednumber.IpVersion;
import org.pcap4j.packet.namednumber.TcpPort;
import org.pcap4j.util.MacAddress;
import sunghs.packet.sniff.model.EthernetInfo;
import sunghs.packet.sniff.model.Ipv4Info;
import sunghs.packet.sniff.model.TcpInfo;

@Slf4j
public class PacketParser {

    public static EthernetInfo parse(EthernetPacket ethernetPacket) {
        EthernetHeader ethernetHeader = ethernetPacket.getHeader();
        MacAddress sourceAddress = ethernetHeader.getSrcAddr();
        MacAddress destAddress = ethernetHeader.getDstAddr();
        EtherType etherType = ethernetHeader.getType();
        int headerLength = ethernetHeader.length();

        return EthernetInfo.builder()
            .sourceAddress(sourceAddress.toString())
            .destAddress(destAddress.toString())
            .etherType(etherType.name())
            .headerLength(headerLength)
            .build();
    }

    public static Ipv4Info parse(IpV4Packet ipV4Packet) {
        IpV4Header ipV4Header = ipV4Packet.getHeader();
        Inet4Address sourceAddress = ipV4Header.getSrcAddr();
        Inet4Address destAddress = ipV4Header.getDstAddr();
        IpNumber protocol = ipV4Header.getProtocol();
        IpVersion version = ipV4Header.getVersion();

        return Ipv4Info.builder()
            .sourceIp(sourceAddress.getHostAddress())
            .destIp(destAddress.getHostAddress())
            .protocol(protocol.name())
            .version(version.name())
            .build();
    }

    public static TcpInfo parse(TcpPacket tcpPacket) {
        TcpHeader tcpHeader = tcpPacket.getHeader();
        TcpPort sourcePort = tcpHeader.getSrcPort();
        TcpPort destPort = tcpHeader.getDstPort();
        long seqNumber = tcpHeader.getSequenceNumberAsLong();
        long ackNumber = tcpHeader.getAcknowledgmentNumberAsLong();

        return TcpInfo.builder()
            .sourcePort(sourcePort.name())
            .destPort(destPort.name())
            .seqNumber(seqNumber)
            .ackNumber(ackNumber)
            .build();
    }
}
