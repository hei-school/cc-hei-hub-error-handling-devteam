package co.hei.exceptions;

public class ServerErrorException extends ApiException{
    public ServerErrorException(String message) {
        super(message, 500);
    }
}
