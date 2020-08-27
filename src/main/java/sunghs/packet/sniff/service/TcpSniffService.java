package sunghs.packet.sniff.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class TcpSniffService {

    private final PcapHandle pcapHandle;

    public void sniff() throws PcapNativeException, NotOpenException, InterruptedException {
        PacketListener packetListener = p -> {
          log.info("packet : {}, time : {}", p, pcapHandle.getTimestamp());
        };

        pcapHandle.loop(5, packetListener);
    }
}