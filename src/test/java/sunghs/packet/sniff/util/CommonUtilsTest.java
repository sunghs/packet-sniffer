package sunghs.packet.sniff.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class CommonUtilsTest {

    @Test
    public void test() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        Optional<String> str = CommonUtils.mapToString(map);
        str.ifPresent(s -> Assertions.assertEquals("{\"key1\":\"value1\"}", s));
    }
}
