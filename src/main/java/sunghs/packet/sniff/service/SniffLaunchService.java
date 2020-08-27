package sunghs.packet.sniff.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class SniffLaunchService implements InitializingBean {

    private static final boolean AUTO_SCAN = false;

    private final TcpSniffService tcpSniffService;

    private final HttpSniffService httpSniffService;

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
