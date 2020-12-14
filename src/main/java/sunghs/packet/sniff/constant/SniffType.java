package sunghs.packet.sniff.constant;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SniffType {

    TCP("tcp", -1, "tcp"),
    HTTP("http", 80, "tcp port 80"),
    HTTPS("https", 443, "tcp port 443");

    private static final Map<Integer, String> PORT_LOOKUP = Collections.unmodifiableMap(
        Stream.of(values()).collect(Collectors.toMap(SniffType::getPort, SniffType::name)));

    @Getter
    private final String typeName;

    @Getter
    private final int port;

    @Getter
    private final String filterCmd;

    SniffType(final String typeName, final int port, final String filterCmd) {
        this.typeName = typeName;
        this.port = port;
        this.filterCmd = filterCmd;
    }

    public static SniffType of(final int port) {
        return SniffType.valueOf(PORT_LOOKUP.get(port));
    }
}
