package sunghs.packet.sniff.model;

import lombok.Builder;
import lombok.Getter;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.Packet.Header;

@Builder
@Getter
public class TcpInfo {

    private final Header header;

    private final Packet packet;

    private final String time;
}
