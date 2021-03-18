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
import sunghs.packet.sniff.model.entity.PacketHistoryDto;
import sunghs.packet.sniff.repository.support.PacketRepositorySupport;

@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = Replace.NONE)
@AllConfigurationTests
@DataJpaTest
@Slf4j
public class QuerydslRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    private PacketRepositorySupport packetRepositorySupport;

    @BeforeEach
    public void beforeEach() {
        final JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        this.packetRepositorySupport = new PacketRepositorySupport(jpaQueryFactory);
    }

    @ParameterizedTest
    @ValueSource(longs = {
        200L, 300L, 400L, 500L, 600L
    })
    public void findTestSeq(Long seq) {
        PacketHistoryDto packetHistoryDto = packetRepositorySupport.findBySeq(seq);
        Assertions.assertEquals(packetHistoryDto.getSeq(), seq);
        log.info("packetHistoryDto : {}", packetHistoryDto.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0247c87b2baf44aeb6598fd7",
        "ee4088984f94473496ca11a7",
        "8ddf5b0e3f1a4be5a1b5ad27",
        "8903be9316e04c0b994992aa",
        "eb0e7aa1db6b4197a50b1b53",
        "62b149e16f1149289b6d3796"
    })
    public void findTestMessageKey(String messageKey) {
        PacketHistoryDto packetHistoryDto = packetRepositorySupport.findByMessageKey(messageKey);
        Assertions.assertEquals(packetHistoryDto.getIdx(), messageKey);
        log.info("packetHistoryDto : {}", packetHistoryDto.toString());
    }
}