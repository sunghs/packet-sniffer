package sunghs.packet.sniff.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pcap4j.core.PcapNetworkInterface;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class TcpSniffService {

    private final PcapNetworkInterface pcapNetworkInterface;

    public void sniff() {

    }
}
