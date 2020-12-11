package sunghs.packet.sniff.config;

import java.util.List;
import lombok.Getter;
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
import sunghs.packet.sniff.exception.SniffHandlerException;

@ConfigurationProperties(prefix = "sniff.listen")
@Configuration
@Slf4j
@ToString
public class SniffConfig extends AbstractInitializer {

    @Setter
    @Getter
    private String ip;

    @Setter
    @Getter
    private boolean requiredData;

    @Setter
    @Getter
    private boolean autoScan;

    @Override
    public void initialize() {
        super.check(SniffConfig.class, this);
    }

    @Bean
    public PcapHandle pcapHandle() throws Exception {
        PcapNetworkInterface pcapNetworkInterface;

        if (isAutoScan()) {
            List<PcapNetworkInterface> list = Pcaps.findAllDevs();

            int selectIdx = -1;

            for (int i = 0; i < list.size(); i++) {
                PcapNetworkInterface pni = list.get(i);
                if (pni.getAddresses().stream().anyMatch(address ->
                    ip.contains(address.getAddress().getHostAddress()))) {
                    selectIdx = i;
                    break;
                }
            }

            if (selectIdx < 0) {
                throw new SniffHandlerException("device 를 찾지 못 했습니다.");
            }
            pcapNetworkInterface = list.get(selectIdx);
        } else {
            pcapNetworkInterface = new NifSelector().selectNetworkInterface();
        }

        log.debug("select pcapNetworkInterface : {}", pcapNetworkInterface);

        return pcapNetworkInterface.openLive(
            SniffConstant.SNAPSHOT_BYTE_LENGTH,
            SniffConstant.DEFAULT_PROMISCUOUS_MODE,
            SniffConstant.READ_TIMEOUT_MILLISECOND);
    }
}
