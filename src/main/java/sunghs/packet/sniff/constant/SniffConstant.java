package sunghs.packet.sniff.constant;

import org.pcap4j.core.BpfProgram.BpfCompileMode;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;

public interface SniffConstant {

    int ONE_KILO_BYTE = 1024;

    int SNAPSHOT_BYTE_LENGTH = ONE_KILO_BYTE * 1024;

    int READ_TIMEOUT_MILLISECOND = 2 * 100;

    PromiscuousMode DEFAULT_PROMISCUOUS_MODE = PromiscuousMode.PROMISCUOUS;

    BpfCompileMode DEFAULT_FILTER_MODE = BpfCompileMode.OPTIMIZE;
}
