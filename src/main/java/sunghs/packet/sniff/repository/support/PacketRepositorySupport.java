package sunghs.packet.sniff.repository.support;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import sunghs.packet.sniff.model.entity.PacketHistoryDto;
import sunghs.packet.sniff.model.entity.QEthernetHistory;
import sunghs.packet.sniff.model.entity.QIpv4History;
import sunghs.packet.sniff.model.entity.QPacketHistory;
import sunghs.packet.sniff.model.entity.QTcpHistory;

@Repository
public class PacketRepositorySupport extends QuerydslRepositorySupport {

    private static final QPacketHistory qPacketHistory = QPacketHistory.packetHistory;

    private static final QEthernetHistory qEthernetHistory = QEthernetHistory.ethernetHistory;

    private static final QIpv4History qIpv4History = QIpv4History.ipv4History;

    private static final QTcpHistory qTcpHistory = QTcpHistory.tcpHistory;

    private final JPAQueryFactory jpaQueryFactory;

    public PacketRepositorySupport(final JPAQueryFactory jpaQueryFactory) {
        super(PacketHistoryDto.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public PacketHistoryDto findByMessageKey(final String messageKey) {
        return selectPacketHistoryDto()
            .from(qPacketHistory)
            .join(qEthernetHistory).on(qPacketHistory.seq.eq(qEthernetHistory.packetHistorySeq))
            .join(qIpv4History).on(qPacketHistory.seq.eq(qIpv4History.packetHistorySeq))
            .join(qTcpHistory).on(qPacketHistory.seq.eq(qTcpHistory.packetHistorySeq))
            .where(qPacketHistory.idx.eq(messageKey))
            .fetchOne();
    }

    public PacketHistoryDto findBySeq(final Long seq) {
        return selectPacketHistoryDto()
            .from(qPacketHistory)
            .join(qEthernetHistory).on(qPacketHistory.seq.eq(qEthernetHistory.packetHistorySeq))
            .join(qIpv4History).on(qPacketHistory.seq.eq(qIpv4History.packetHistorySeq))
            .join(qTcpHistory).on(qPacketHistory.seq.eq(qTcpHistory.packetHistorySeq))
            .where(qPacketHistory.seq.eq(seq))
            .fetchOne();
    }

    private JPAQuery<PacketHistoryDto> selectPacketHistoryDto() {
        return jpaQueryFactory.select(Projections.constructor(PacketHistoryDto.class,
            qPacketHistory.seq,
            qPacketHistory.idx,
            qPacketHistory.transmissionDirection,
            qPacketHistory.packetType,
            qIpv4History.sourceIp,
            qEthernetHistory.sourceAddress,
            qTcpHistory.sourcePort,
            qIpv4History.destIp,
            qEthernetHistory.destAddress,
            qTcpHistory.destPort,
            qTcpHistory.tcpType,
            qPacketHistory.sniffTime));
    }
}
