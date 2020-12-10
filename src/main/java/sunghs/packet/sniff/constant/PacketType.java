package sunghs.packet.sniff.constant;

import lombok.Getter;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.UnknownPacket;

public enum PacketType {

    ETHERNET("ethernet", EthernetPacket.class),
    IPV4("ipv4", IpV4Packet.class),
    TCP("tcp", TcpPacket.class),
    ETC("etc", UnknownPacket.class);

    @Getter
    private final String name;

    @Getter
    private final Class<?> cls;

    PacketType(final String name, Class<?> cls) {
        this.name = name;
        this.cls = cls;
    }
}
