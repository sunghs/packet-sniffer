package sunghs.packet.sniff.exception;

public class SniffHandlerException extends RuntimeException {

    private static final String format = "sniff 서비스에 문제가 생겼습니다.";

    public SniffHandlerException() {
        super(format);
    }

    public SniffHandlerException(final String message) {
        super(format + " : " + message);
    }
}
