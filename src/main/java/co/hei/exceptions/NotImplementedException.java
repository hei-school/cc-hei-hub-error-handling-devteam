package co.hei.exceptions;

public class NotImplementedException extends ApiException{
    public NotImplementedException(String message) {
        super(message,501);
    }
}
