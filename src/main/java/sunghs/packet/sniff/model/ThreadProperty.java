package sunghs.packet.sniff.model;

import lombok.Data;

@Data
public class ThreadProperty {

    private String prefix;

    private int capacity;

    private int maxPoolSize;

    private int corePoolSize;
}
