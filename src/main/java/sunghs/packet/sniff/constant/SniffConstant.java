package sunghs.packet.sniff.constant;

import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;

public interface SniffConstant {

    int SNAPSHOT_BYTE_LENGTH = 65536;

    int READ_TIMEOUT_MILLISECOND = 2 * 100;

    PromiscuousMode DEFAULT_PROMISCUOUS_MODE = PromiscuousMode.PROMISCUOUS;
}
