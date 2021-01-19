package sunghs.packet.sniff;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import sunghs.packet.sniff.exception.ExceptionCodeManager;
import sunghs.packet.sniff.exception.SniffHandlerException;

@RequiredArgsConstructor
@Slf4j
public class ErrorTest {

    @Test
    public void errorTest() {
        SniffHandlerException sniffHandlerException = new SniffHandlerException(ExceptionCodeManager.FAIL_FIND_DEVICE);
        log.error("error", sniffHandlerException);
    }
}
