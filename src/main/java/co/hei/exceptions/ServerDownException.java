package co.hei.exceptions;

public class ServerDownException extends ApiException {
    public ServerDownException(String message) {
        super(message, 504);
    }
}
