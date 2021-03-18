package sunghs.packet.sniff;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sunghs.packet.sniff.annotation.AllConfigurationTests;
import sunghs.packet.sniff.model.entity.PacketHistory;
import sunghs.packet.sniff.repository.support.PacketHistoryRepositorySupport;

@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = Replace.NONE)
@AllConfigurationTests
@DataJpaTest
@Slf4j
public class QuerydslRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    private PacketHistoryRepositorySupport packetHistoryRepositorySupport;

    @BeforeEach
    public void beforeEach() {
        final JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        this.packetHistoryRepositorySupport = new PacketHistoryRepositorySupport(jpaQueryFactory);
    }

    @ParameterizedTest
    @ValueSource(longs = {
        200L, 300L, 400L, 500L, 600L
    })
    public void findTest(Long seq) {
        PacketHistory packetHistory = packetHistoryRepositorySupport.findBySeq(seq);
        Assertions.assertEquals(packetHistory.getSeq(), seq);
        log.info("seq {} idx {} time {}", packetHistory.getSeq(), packetHistory.getIdx(), packetHistory.getSniffTime());
    }
}