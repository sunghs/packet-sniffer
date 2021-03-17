package sunghs.packet.sniff.repository.support;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import sunghs.packet.sniff.model.entity.PacketHistory;
import sunghs.packet.sniff.model.entity.QPacketHistory;

@Repository
public class PacketHistoryRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public PacketHistoryRepositorySupport(final JPAQueryFactory jpaQueryFactory) {
        super(PacketHistory.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public PacketHistory findBySeq(final Long seq) {
        QPacketHistory qPacketHistory = QPacketHistory.packetHistory;
        return jpaQueryFactory.selectFrom(qPacketHistory).where(qPacketHistory.seq.eq(seq)).fetchOne();
    }

    public PacketHistory findByMessageKey(final String messageKey) {
        QPacketHistory qPacketHistory = QPacketHistory.packetHistory;
        return jpaQueryFactory.selectFrom(qPacketHistory).where(qPacketHistory.idx.eq(messageKey)).fetchOne();
    }

    public List<PacketHistory> findByLocalDateTime(LocalDateTime from, LocalDateTime to) {
        QPacketHistory qPacketHistory = QPacketHistory.packetHistory;
        return jpaQueryFactory.selectFrom(qPacketHistory).where(qPacketHistory.sniffTime.between(from, to)).fetch();
    }
}
