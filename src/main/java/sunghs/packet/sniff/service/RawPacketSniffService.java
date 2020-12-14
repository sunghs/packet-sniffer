package sunghs.packet.sniff.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.RawPacketListener;
import org.pcap4j.util.ByteArrays;
import org.springframework.stereotype.Service;
import sunghs.packet.sniff.config.SniffConfig;
import sunghs.packet.sniff.constant.SniffConstant;

@RequiredArgsConstructor
@Service
@Slf4j
public class RawPacketSniffService {

    private static final String RAW_PACKET_SEPARATOR = " ";

    private final PcapHandle pcapHandle;

    private final SniffConfig sniffConfig;

    public void listen() throws PcapNativeException, NotOpenException, InterruptedException {
        final RawPacketListener rawPacketListener = packet -> {
            String data = ByteArrays.toHexString(packet, RAW_PACKET_SEPARATOR);
            log.info(data);
        };
        pcapHandle.setFilter(sniffConfig.getCaptureType().getFilterCmd(), SniffConstant.DEFAULT_FILTER_MODE);
        pcapHandle.loop(-1, rawPacketListener);
    }
}
