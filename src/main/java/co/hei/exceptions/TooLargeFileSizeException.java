package co.hei.exceptions;

import co.hei.exceptions.ApiException;

public class TooLargeFileSizeException extends ApiException {

    public TooLargeFileSizeException(String message) {
        super(message, 423);
    }
}
