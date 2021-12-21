package sunghs.packet.sniff;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.ClassUtils;
import sunghs.packet.sniff.model.PacketContext;

import java.lang.reflect.InvocationTargetException;

@NoArgsConstructor
@Slf4j
class KafkaTests {

    @Test
    void classLoaderTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        PacketContext packetContext1 = new PacketContext();
        String name = packetContext1.getClass().getTypeName();
        Class<?> cz = ClassUtils.forName(name, ClassUtils.getDefaultClassLoader());

        PacketContext packetContext2 = (PacketContext) cz.getDeclaredConstructor().newInstance();
        packetContext2.setData("test");

        Assertions.assertEquals("test", packetContext2.getData());
    }
}
