package sunghs.packet.sniff.util;

import java.time.LocalDateTime;
import sunghs.packet.sniff.model.EthernetInfo;
import sunghs.packet.sniff.model.Ipv4Info;
import sunghs.packet.sniff.model.PacketContext;
import sunghs.packet.sniff.model.TcpInfo;
import sunghs.packet.sniff.model.entity.EthernetHistory;
import sunghs.packet.sniff.model.entity.Ipv4History;
import sunghs.packet.sniff.model.entity.PacketDataInfo;
import sunghs.packet.sniff.model.entity.PacketHistory;
import sunghs.packet.sniff.model.entity.TcpHistory;

public class EntityConverter {

    public static PacketHistory toHistory(String idx, PacketContext packetContext) {
        return PacketHistory.builder()
            .idx(idx)
            .packetType(packetContext.getPacketType())
            .transmissionDirection(packetContext.getTransmissionDirection())
            .sniffTime(LocalDateTime.now())
            .build();
    }

    public static EthernetHistory toHistory(Long seq, EthernetInfo ethernetInfo) {
        return EthernetHistory.builder()
            .packetHistorySeq(seq)
            .sourceAddress(ethernetInfo.getSourceAddress())
            .destAddress(ethernetInfo.getDestAddress())
            .headerLength(ethernetInfo.getHeaderLength())
            .build();
    }

    public Ipv4History toHistory(Long seq, Ipv4Info ipv4Info) {
        return Ipv4History.builder()
            .packetHistorySeq(seq)
            .sourceIp(ipv4Info.getSourceIp())
            .destIp(ipv4Info.getDestIp())
            .protocol(ipv4Info.getProtocol())
            .version(ipv4Info.getVersion())
            .build();
    }

    public TcpHistory toHistory(Long seq, TcpInfo tcpInfo) {
        return TcpHistory.builder()
            .packetHistorySeq(seq)
            .tcpType(tcpInfo.getTcpType())
            .sourcePort(tcpInfo.getSourcePort())
            .destPort(tcpInfo.getDestPort())
            .seqNumber(tcpInfo.getSeqNumber())
            .ackNumber(tcpInfo.getAckNumber())
            .build();
    }

    public PacketDataInfo toDataInfo(Long seq, PacketContext packetContext) {
        return PacketDataInfo.builder()
            .packetHistorySeq(seq)
            .packetData(packetContext.getData())
            .build();
    }
}
