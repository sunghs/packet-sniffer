package sunghs.packet.sniff.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import sunghs.packet.sniff.constant.SniffType;

@Builder
@Getter
@ToString
public class TcpInfo {

    private final SniffType tcpType;

    private final int sourcePort;

    private final int destPort;

    private final long seqNumber;

    private final long ackNumber;
}
