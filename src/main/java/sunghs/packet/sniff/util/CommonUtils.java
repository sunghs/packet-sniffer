package sunghs.packet.sniff.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

@Slf4j
public final class CommonUtils {

    public static Optional<String> mapToString(final Map<?, ?> map) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return Optional.of(objectMapper.writeValueAsString(map));
        } catch (Exception e) {
            log.error("Map Convert Error", e);
            return Optional.empty();
        }
    }
}
