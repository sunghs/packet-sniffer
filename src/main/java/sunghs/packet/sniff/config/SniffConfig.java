package sunghs.packet.sniff.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.util.NifSelector;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sunghs.packet.sniff.constant.PacketType;
import sunghs.packet.sniff.constant.SniffConstant;
import sunghs.packet.sniff.constant.SniffType;
import sunghs.packet.sniff.exception.ExceptionCodeManager;
import sunghs.packet.sniff.exception.SniffHandlerException;

import java.util.List;

@ConfigurationProperties(prefix = "sniff.listen")
@Configuration
@Slf4j
@ToString
public class SniffConfig extends AbstractInitializer {

    @Getter
    @Setter
    private String ip;

    @Getter
    @Setter
    private boolean requiredData;

    @Getter
    @Setter
    private boolean autoScan;

    @Getter
    @Setter
    private PacketType captureLevel;

    @Getter
    @Setter
    private SniffType captureType;

    @Getter
    @Setter
    private boolean hexToString;

    @Override
    public void initialize() {
        super.check(SniffConfig.class, this);
    }

    @Bean
    public PcapHandle pcapHandle() throws Exception {
        PcapNetworkInterface pcapNetworkInterface;

        pcapNetworkInterface = isAutoScan() ?
                getAutoNetworkInterface() : new NifSelector().selectNetworkInterface();

        log.debug("select pcapNetworkInterface : {}", pcapNetworkInterface);

        return pcapNetworkInterface.openLive(
                SniffConstant.SNAPSHOT_BYTE_LENGTH,
                SniffConstant.DEFAULT_PROMISCUOUS_MODE,
                SniffConstant.READ_TIMEOUT_MILLISECOND);
    }

    private PcapNetworkInterface getAutoNetworkInterface() throws PcapNativeException {
        List<PcapNetworkInterface> list = Pcaps.findAllDevs();

        for (PcapNetworkInterface pni : list) {
            if (pni.getAddresses().stream().anyMatch(address -> ip.contains(address.getAddress().getHostAddress()))) {
                return pni;
            }
        }
        throw new SniffHandlerException(ExceptionCodeManager.FAIL_FIND_DEVICE);
    }
}
