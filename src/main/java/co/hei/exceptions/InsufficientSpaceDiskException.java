package co.hei.exceptions;

public class InsufficientSpaceDiskException extends ApiException {
    public InsufficientSpaceDiskException(String message) {
        super(message, 507);
    }
}
