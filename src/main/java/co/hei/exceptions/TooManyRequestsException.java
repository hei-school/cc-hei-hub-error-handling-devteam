package co.hei.exceptions;

public class TooManyRequestsException extends ApiException {
    public TooManyRequestsException(String message) {
        super(message, 429);
    }
}
