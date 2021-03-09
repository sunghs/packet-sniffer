package sunghs.packet.sniff.config;

import java.util.concurrent.Executor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import sunghs.packet.sniff.config.model.ThreadProperty;

@EnableAsync
@Slf4j
@ToString
@Configuration
@EnableConfigurationProperties
public class ThreadPoolConfig extends AbstractInitializer {

    @Override
    public void initialize() {
        super.check(ThreadPoolConfig.class, this);
    }

    @Primary
    @Bean(name = "producerThreadProperty")
    @ConfigurationProperties(prefix = "thread.producer")
    public ThreadProperty producerThreadProperty() {
        return new ThreadProperty();
    }

    @Primary
    @Bean(name = "consumerThreadProperty")
    @ConfigurationProperties(prefix = "thread.consumer")
    public ThreadProperty consumerThreadProperty() {
        return new ThreadProperty();
    }

    @Bean("producer")
    public Executor setProducer(@Qualifier("producerThreadProperty") final ThreadProperty threadProperty) {
        return getExecutor(threadProperty);
    }

    @Bean("consumer")
    public Executor setConsumer(@Qualifier("consumerThreadProperty") final ThreadProperty threadProperty) {
        return getExecutor(threadProperty);
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
}
