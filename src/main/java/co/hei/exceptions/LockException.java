package co.hei.exceptions;
public class LockException extends ApiException {
    public LockException(String message) {
        super(message, 0);
    }
}
