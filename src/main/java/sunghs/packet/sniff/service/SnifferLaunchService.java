package sunghs.packet.sniff.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.util.NifSelector;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class SnifferLaunchService implements InitializingBean {

    private static final boolean AUTO_SCAN = false;

    private final TcpSnifferService tcpSnifferService;

    private final HttpSnifferService httpSnifferService;

    @Override
    public void afterPropertiesSet() throws Exception {
        // AUTOMATIC
        if(AUTO_SCAN) {
            PcapNetworkInterface pcapNetworkInterface = new NifSelector().selectNetworkInterface();
            log.info("pcapNetworkInterface : {}", pcapNetworkInterface);
        } else {
            List<PcapNetworkInterface> devs = Pcaps.findAllDevs();
            for(PcapNetworkInterface pcapNetworkInterface : devs) {
                log.info("{}", pcapNetworkInterface);
            }
        }
    }
}
