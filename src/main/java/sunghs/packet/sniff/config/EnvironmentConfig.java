package sunghs.packet.sniff.config;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

@RequiredArgsConstructor
@Configuration
@Slf4j
@ToString
public class EnvironmentConfig extends AbstractInitializer {

    private final Environment environment;

    private final Map<String, Object> environmentMap = new HashMap<>();

    @Override
    protected void initialize() {
        for (PropertySource<?> propertySource : ((AbstractEnvironment) environment).getPropertySources()) {
            if (propertySource instanceof MapPropertySource) {
                environmentMap.putAll(((MapPropertySource) propertySource).getSource());
            }
        }
        super.check(EnvironmentConfig.class, this);
    }

    public Object getEnvironment(final String prop) {
        return environmentMap.get(prop);
    }
}
