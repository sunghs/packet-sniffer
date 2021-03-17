package sunghs.packet.sniff;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sunghs.packet.sniff.annotation.AllConfigurationTests;
import sunghs.packet.sniff.model.entity.PacketHistory;
import sunghs.packet.sniff.repository.PacketHistoryRepository;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@AllConfigurationTests
@DataJpaTest
@Slf4j
public class JpaRepositoryTest {

    @Autowired
    private PacketHistoryRepository packetHistoryRepository;

    @Test
    public void findTest() {
        List<PacketHistory> list = packetHistoryRepository.findAll();
        Assertions.assertTrue(list.size() > 0);
        log.info("packetHistoryRepository entity list size : {}", list.size());
    }
}
