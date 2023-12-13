package co.hei.exceptions;

public class RequestTimeOutException extends ApiException {
    public RequestTimeOutException(String message) {
        super(message, 408);
    }
}
