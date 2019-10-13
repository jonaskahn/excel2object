package com.teang.exceltransfer.exception;

/**
 * Exception when transfer, read and write data
 *
 * @author tea.ng
 * @since 10/13/2019
 */
public class TransferException extends RuntimeException {

    public TransferException(String message) {
        super(message);
    }

    public TransferException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransferException(Throwable cause) {
        super(cause);
    }
}
