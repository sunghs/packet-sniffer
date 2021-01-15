package sunghs.packet.sniff.model;

import lombok.Data;
import sunghs.packet.sniff.constant.PacketType;
import sunghs.packet.sniff.constant.TransmissionDirection;
import sunghs.packet.sniff.model.marker.KafkaEntity;

@Data
public class PacketContext implements KafkaEntity {

    private TransmissionDirection transmissionDirection;

    private PacketType packetType;

    private EthernetInfo ethernetInfo;

    private Ipv4Info ipv4Info;

    private TcpInfo tcpInfo;

    private String data;
}
