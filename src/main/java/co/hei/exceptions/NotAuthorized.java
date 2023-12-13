package co.hei.exceptions;

public class NotAuthorized extends ApiException {
    public NotAuthorized(String message) {
        super(message, 401);
    }
}