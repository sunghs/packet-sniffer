package sunghs.packet.sniff.service;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sunghs.packet.sniff.model.PacketContext;
import sunghs.packet.sniff.model.entity.EthernetHistory;
import sunghs.packet.sniff.model.entity.Ipv4History;
import sunghs.packet.sniff.model.entity.PacketDataInfo;
import sunghs.packet.sniff.model.entity.PacketHistory;
import sunghs.packet.sniff.model.entity.TcpHistory;
import sunghs.packet.sniff.repository.EthernetHistoryRepository;
import sunghs.packet.sniff.repository.Ipv4HistoryRepository;
import sunghs.packet.sniff.repository.PacketDataInfoRepository;
import sunghs.packet.sniff.repository.PacketHistoryRepository;
import sunghs.packet.sniff.repository.TcpHistoryRepository;
import sunghs.packet.sniff.util.EntityConverter;

@RequiredArgsConstructor
@Service
public class DataSaveService {

    private final PacketHistoryRepository packetHistoryRepository;

    private final PacketDataInfoRepository packetDataInfoRepository;

    private final EthernetHistoryRepository ethernetHistoryRepository;

    private final Ipv4HistoryRepository ipv4HistoryRepository;

    private final TcpHistoryRepository tcpHistoryRepository;

    @Transactional
    public void savePacketContext(final String messageKey, final PacketContext packetContext) {
        PacketHistory packetHistory = EntityConverter.toHistory(messageKey, packetContext);
        Long packetHistorySeq = packetHistoryRepository.save(packetHistory).getSeq();

        PacketDataInfo packetDataInfo = EntityConverter.toDataInfo(packetHistorySeq, packetContext);
        packetDataInfoRepository.save(packetDataInfo);

        EthernetHistory ethernetHistory = EntityConverter.toHistory(packetHistorySeq, packetContext.getEthernetInfo());
        ethernetHistoryRepository.save(ethernetHistory);

        Ipv4History ipv4History = EntityConverter.toHistory(packetHistorySeq, packetContext.getIpv4Info());
        ipv4HistoryRepository.save(ipv4History);

        TcpHistory tcpHistory = EntityConverter.toHistory(packetHistorySeq, packetContext.getTcpInfo());
        tcpHistoryRepository.save(tcpHistory);
    }
}
