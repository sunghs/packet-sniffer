package sunghs.packet.sniff.constant;

import lombok.Getter;

public enum SniffType {

    TCP("TCP"),
    HTTP("HTTP");

    @Getter
    private final String typeName;

    SniffType(final String typeName) {
        this.typeName = typeName;
    }
}
