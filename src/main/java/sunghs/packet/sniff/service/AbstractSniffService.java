package sunghs.packet.sniff.service;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;

public interface AbstractSniffService {

    void listen() throws PcapNativeException, NotOpenException, InterruptedException;
}
