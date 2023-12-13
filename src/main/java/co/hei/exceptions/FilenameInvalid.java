package co.hei.exceptions;

public class FilenameInvalid extends ApiException {
    public FilenameInvalid(String message) {
        super(message, 400);
    }
}