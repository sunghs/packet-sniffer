package sunghs.packet.sniff.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;
import org.springframework.util.ObjectUtils;

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

    public static boolean isNotEmpty(final Object obj) {
        return !ObjectUtils.isEmpty(obj);
    }

    public static String hexToString(String hex) {
        hex = hex.replaceAll(" ", "");
        int size = hex.length();
        byte[] bHex = new byte[size / 2];

        for (int i = 0; i < size; i += 2) {
            bHex[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
        }
        return new String(bHex, StandardCharsets.UTF_8);
    }

    public String convertBinary(String hex) {
        StringBuilder stringBuilder = new StringBuilder();

        byte[] bt = hex.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < bt.length; ++i) {
            stringBuilder.append(shift(bt[i]));
        }
        return stringBuilder.toString();
    }

    private String shift(final byte n) {
        StringBuilder sb = new StringBuilder("00000000");
        for (int bit = 0; bit < 8; bit++) {
            if (((n >> bit) & 1) > 0) {
                sb.setCharAt(7 - bit, '1');
            }
        }
        return sb.toString();
    }
}
