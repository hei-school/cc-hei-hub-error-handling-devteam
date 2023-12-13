package co.hei.exceptions;

public class SensitiveFileExecption extends ApiException {
    public SensitiveFileExecption(String message) {
        super(message, 400);
    }
}