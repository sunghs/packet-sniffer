package sunghs.packet.sniff;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import sunghs.packet.sniff.config.BeanConfig;
import sunghs.packet.sniff.config.DBConfig;
import sunghs.packet.sniff.config.EnvironmentConfig;
import sunghs.packet.sniff.config.SniffConfig;
import sunghs.packet.sniff.config.ThreadPoolConfig;
import sunghs.packet.sniff.service.KafkaClient;
import sunghs.packet.sniff.service.PacketSniffService;
import sunghs.packet.sniff.service.RawPacketSniffService;

@TestConfiguration
@Import({
    BeanConfig.class,
    DBConfig.class,
    EnvironmentConfig.class,
    SniffConfig.class,
    ThreadPoolConfig.class
})
public class TestBeanConfig {

    @MockBean
    private PacketSniffService packetSniffService;

    @MockBean
    private RawPacketSniffService rawPacketSniffService;

    @MockBean
    private KafkaClient kafkaClient;
}
