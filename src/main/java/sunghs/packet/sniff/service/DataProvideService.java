package sunghs.packet.sniff.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sunghs.packet.sniff.model.PacketContext;
import sunghs.packet.sniff.repository.EthernetHistoryRepository;
import sunghs.packet.sniff.repository.Ipv4HistoryRepository;
import sunghs.packet.sniff.repository.PacketDataInfoRepository;
import sunghs.packet.sniff.repository.PacketHistoryRepository;
import sunghs.packet.sniff.repository.TcpHistoryRepository;

@RequiredArgsConstructor
@Service
public class DataProvideService {

    private final PacketHistoryRepository packetHistoryRepository;

    private final PacketDataInfoRepository packetDataInfoRepository;

    private final EthernetHistoryRepository ethernetHistoryRepository;

    private final Ipv4HistoryRepository ipv4HistoryRepository;

    private final TcpHistoryRepository tcpHistoryRepository;

    public PacketContext getPacketContext(final Long packetHistorySeq) {
        return null;
    }

    public PacketContext getPacketContext(final String messageKey) {
        return null;
    }
}
