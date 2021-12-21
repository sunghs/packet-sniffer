package sunghs.packet.sniff.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
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
class TestBeanConfig {

    @MockBean
    private PacketSniffService packetSniffService;

    @MockBean
    private RawPacketSniffService rawPacketSniffService;

    @MockBean
    private KafkaClient kafkaClient;
}
