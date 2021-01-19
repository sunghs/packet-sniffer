package sunghs.packet.sniff.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractServiceException extends RuntimeException {

    protected final ExceptionCodeManager exceptionCodeManager;
}
