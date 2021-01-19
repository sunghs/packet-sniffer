package sunghs.packet.sniff;

import java.lang.reflect.InvocationTargetException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.ClassUtils;
import sunghs.packet.sniff.model.PacketContext;

@NoArgsConstructor
@Slf4j
public class KafkaTests {

    @Test
    public void classLoaderTest()
        throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        PacketContext p1 = new PacketContext();
        String name = p1.getClass().getTypeName();
        Class<?> cz = ClassUtils.forName(name, ClassUtils.getDefaultClassLoader());

        PacketContext p2 = (PacketContext) cz.getDeclaredConstructor().newInstance();
        p2.setData("test");

        Assertions.assertEquals("test", p2.getData());
    }
}
