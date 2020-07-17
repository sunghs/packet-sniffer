package sunghs.packet.sniff.config;

import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "sniff.listen")
@Configuration
@Slf4j
@ToString
public class SniffConfig extends AbstractInitializer {

    @Setter
    private String ip;
    @Setter
    private int index;

    @Override
    public void initialize() {
        super.check(SniffConfig.class, this);
    }
}
