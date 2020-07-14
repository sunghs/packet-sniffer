package sunghs.packet.sniff.config;

import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import sunghs.packet.sniff.model.ThreadProperty;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;

@ConfigurationProperties(prefix = "thread")
@Configuration
@Slf4j
@ToString
public class ThreadPoolConfig extends AbstractInitializer {

    @Setter
    private ThreadProperty producer;
    @Setter
    private ThreadProperty consumer;

    @PostConstruct
    public void initialize() {
        super.check(ThreadPoolConfig.class, this);
    }

    @Bean(name = "producer")
    public Executor setProducer() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(producer.getCorePoolSize());
        executor.setMaxPoolSize(producer.getMaxPoolSize());
        executor.setQueueCapacity(producer.getCapacity());
        executor.setThreadNamePrefix(producer.getPrefix());
        return executor;
    }

    @Bean(name = "consumer")
    public Executor setConsumer() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(consumer.getCorePoolSize());
        executor.setMaxPoolSize(consumer.getMaxPoolSize());
        executor.setQueueCapacity(consumer.getCapacity());
        executor.setThreadNamePrefix(consumer.getPrefix());
        return executor;
    }
}
