package sunghs.packet.sniff.util;

import java.util.UUID;

public class IdxGenerator {

    public static final int IDX_DIGIT = 24;

    public static String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, IDX_DIGIT);
    }
}
