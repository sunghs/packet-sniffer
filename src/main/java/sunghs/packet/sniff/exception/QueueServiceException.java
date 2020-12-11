package sunghs.packet.sniff.exception;

public class QueueServiceException extends RuntimeException {

    private static final String format = "메시지 큐 서비스에 문제가 생겼습니다.";

    public QueueServiceException() {
        super(format);
    }

    public QueueServiceException(final String message) {
        super(format + " : " + message);
    }
}
