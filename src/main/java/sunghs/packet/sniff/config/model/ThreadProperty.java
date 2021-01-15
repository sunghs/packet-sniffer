package sunghs.packet.sniff.config.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ThreadProperty {

    private String prefix;

    private int capacity;

    private int maxPoolSize;

    private int corePoolSize;
}
