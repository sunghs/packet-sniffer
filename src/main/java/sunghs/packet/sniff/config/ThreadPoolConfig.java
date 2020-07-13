package sunghs.packet.sniff.config;

import java.util.concurrent.Executor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@ToString
public class ThreadPoolConfig extends AbstractInitializer {

    public ThreadPoolConfig() {
        super.check(ThreadPoolConfig.class, this);
    }

    public Executor setProducer() {
        return null;
    }

    public Executor setConsumer() {
        return null;
    }
}
