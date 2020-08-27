package sunghs.packet.sniff.config;

import java.util.List;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.util.NifSelector;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public PcapNetworkInterface pcapNetworkInterface() throws Exception {
        PcapNetworkInterface pcapNetworkInterface;

        if (AUTO_SCAN) {
            pcapNetworkInterface = new NifSelector().selectNetworkInterface();
        } else {
            List<PcapNetworkInterface> list = Pcaps.findAllDevs();
            pcapNetworkInterface = list.get(5);
        }

        log.debug("select pcapNetworkInterface : {}", pcapNetworkInterface);

        return pcapNetworkInterface;
    }
}
