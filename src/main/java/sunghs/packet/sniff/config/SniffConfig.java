package sunghs.packet.sniff.config;

import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.util.NifSelector;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sunghs.packet.sniff.constant.SniffConstant;

import java.util.List;

@ConfigurationProperties(prefix = "sniff.listen")
@Configuration
@Slf4j
@ToString
public class SniffConfig extends AbstractInitializer {

    private static final boolean AUTO_SCAN = true;

    @Setter
    private String ip;

    @Setter
    private int index;

    @Override
    public void initialize() {
        super.check(SniffConfig.class, this);
    }

    @Bean
    public PcapHandle pcapHandle() throws Exception {
        PcapNetworkInterface pcapNetworkInterface;

        if (AUTO_SCAN) {
            // IP를 특정할 수 없기 때문에 AUTO-SCAN 형태로 사용
            pcapNetworkInterface = new NifSelector().selectNetworkInterface();
        } else {
            List<PcapNetworkInterface> list = Pcaps.findAllDevs();
            pcapNetworkInterface = list.get(5);
        }

        log.debug("select pcapNetworkInterface : {}", pcapNetworkInterface);

        return pcapNetworkInterface.openLive(
            SniffConstant.SNAPSHOT_BYTE_LENGTH,
            SniffConstant.DEFAULT_PROMISCUOUS_MODE,
            SniffConstant.READ_TIMEOUT_MILLISECOND);
    }
}
