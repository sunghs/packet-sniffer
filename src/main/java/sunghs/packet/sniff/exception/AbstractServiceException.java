package sunghs.packet.sniff.exception;

public abstract class AbstractServiceException extends RuntimeException {

    protected final ExceptionCodeManager exceptionCodeManager;

    protected AbstractServiceException(ExceptionCodeManager exceptionCodeManager) {
        super(exceptionCodeManager.getCause());
        this.exceptionCodeManager = exceptionCodeManager;
    }
}
