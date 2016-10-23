package org.moita.keep.exception;

public class KeepException extends RuntimeException {

    private static final long serialVersionUID = 5355009901612076232L;

    public KeepException(Exception cause) {
        this.initCause(cause);
    }

}
