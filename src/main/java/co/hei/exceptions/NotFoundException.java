package co.hei.exceptions;

public class NotFoundException extends ApiException {
    public NotFoundException(String message) {
        super(message, 404);
    }
}
