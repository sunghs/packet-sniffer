package sunghs.packet.sniff.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class EthernetInfo {

    private final String sourceAddress;

    private final String destAddress;

    private final String etherType;

    private final int headerLength;
}
