package sunghs.packet.sniff.model;

import lombok.Data;

@Data
public class PacketContext {

    public static final PacketContext EMPTY_CONTEXT = new PacketContext();

    private EthernetInfo ethernetInfo;

    private Ipv4Info ipv4Info;

    private TcpInfo tcpInfo;

    private String data;
}
