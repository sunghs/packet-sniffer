package sunghs.packet.sniff.config;

import java.util.concurrent.Executor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import sunghs.packet.sniff.config.model.ThreadProperty;

@EnableAsync
@Slf4j
@ToString
@Configuration
@ConfigurationProperties(prefix = "thread")
public class ThreadPoolConfig extends AbstractInitializer {

    @Setter
    private ThreadProperty producer;

    @Setter
    private ThreadProperty consumer;

    @Override
    public void initialize() {
        super.check(ThreadPoolConfig.class, this);
    }

    private Executor getExecutor(final ThreadProperty producer) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(producer.getCorePoolSize());
        executor.setMaxPoolSize(producer.getMaxPoolSize());
        executor.setQueueCapacity(producer.getCapacity());
        executor.setThreadNamePrefix(producer.getPrefix());
        executor.initialize();
        return executor;
    }

    @Bean(name = "producer")
    public Executor setProducer() {
        return getExecutor(producer);
    }

    @Bean(name = "consumer")
    public Executor setConsumer() {
        return getExecutor(consumer);
    }
}
