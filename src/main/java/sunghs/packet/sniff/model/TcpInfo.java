package sunghs.packet.sniff.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class TcpInfo {

    private final String sourcePort;

    private final String destPort;

    private final long seqNumber;

    private final long ackNumber;
}
