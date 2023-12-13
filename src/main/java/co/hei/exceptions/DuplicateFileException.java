package co.hei.exceptions;

public class DuplicateFileException extends ApiException {
    public DuplicateFileException(String message) {
        super(message, 100);
    }
}
