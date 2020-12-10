package sunghs.packet.sniff.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Ipv4Info {

    private final String sourceIp;

    private final String destIp;

    private final String protocol;

    private final String version;
}
