package sunghs.packet.sniff.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.*;

@ExtendWith(SpringExtension.class)
@Import(TestBeanConfig.class)
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AllConfigurationTests {

}
