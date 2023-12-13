package co.hei.exceptions;

public class CorruptedFileException extends ApiException {
    public CorruptedFileException(String message) {
        super(message, 500);
    }
}
