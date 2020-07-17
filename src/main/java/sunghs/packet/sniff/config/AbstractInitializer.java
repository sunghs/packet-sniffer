package sunghs.packet.sniff.config;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractInitializer {

    protected void check(final Class<?> cls, final Object instance) {
        String className = cls.getSimpleName();
        log.debug("{} start", className);
        log.debug(instance.toString());
        log.debug("{} end", className);
    }

    @PostConstruct
    protected abstract void initialize();
}
