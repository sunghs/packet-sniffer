package sunghs.packet.sniff.constant;

import lombok.Getter;

public enum SniffType {

    TCP("tcp"),
    HTTP("tcp port 80"),
    HTTPS("tcp port 443"),;

    @Getter
    private final String typeName;

    SniffType(final String typeName) {
        this.typeName = typeName;
    }
}
