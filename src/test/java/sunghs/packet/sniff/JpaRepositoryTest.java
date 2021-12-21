package sunghs.packet.sniff;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sunghs.packet.sniff.config.AllConfigurationTests;
import sunghs.packet.sniff.model.entity.PacketHistory;
import sunghs.packet.sniff.repository.PacketHistoryRepository;

import java.util.List;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@AllConfigurationTests
@DataJpaTest
@Slf4j
class JpaRepositoryTest {

    @Autowired
    private PacketHistoryRepository packetHistoryRepository;

    @Test
    void findTest() {
        List<PacketHistory> list = packetHistoryRepository.findAll();
        Assertions.assertTrue(list.size() > 0);
    }
}
