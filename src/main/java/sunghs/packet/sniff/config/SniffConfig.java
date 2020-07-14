package sunghs.packet.sniff.config;

import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@ConfigurationProperties(prefix = "sniff.listen")
@Configuration
@Slf4j
@ToString
public class SniffConfig extends AbstractInitializer {

    @Setter
    private String ip;
    @Setter
    private int index;

    @PostConstruct
    public void initialize() {
        super.check(SniffConfig.class, this);
    }
}
