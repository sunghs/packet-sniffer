package sunghs.packet.sniff;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sunghs.packet.sniff.annotation.AllConfigurationTests;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@AllConfigurationTests
@DataJpaTest
@Slf4j
public class QuerydslRepositoryTest {

    @Test
    public void findTest() {
    }
}
