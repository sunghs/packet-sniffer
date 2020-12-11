package sunghs.packet.sniff.constant;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

public enum SniffType {

    TCP("tcp", -1),
    HTTP("http", 80),
    HTTPS("https", 443);

    private static final Map<Integer, String> PORT_LOOKUP = Collections.unmodifiableMap(
        Stream.of(values()).collect(Collectors.toMap(SniffType::getPort, SniffType::name)));

    @Getter
    private final String typeName;

    @Getter
    private final int port;

    SniffType(final String typeName, final int port) {
        this.typeName = typeName;
        this.port = port;
    }

    public static SniffType of(final int port) {
        return SniffType.valueOf(PORT_LOOKUP.get(port));
    }
}
