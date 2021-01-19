package sunghs.packet.sniff.exception;

public class QueueServiceException extends AbstractServiceException {

    private Throwable ex;

    public QueueServiceException(ExceptionCodeManager exceptionCodeManager) {
        super(exceptionCodeManager);

    }

    public QueueServiceException(ExceptionCodeManager exceptionCodeManager, Throwable ex) {
        super(exceptionCodeManager);
        this.ex = ex;
    }
}
