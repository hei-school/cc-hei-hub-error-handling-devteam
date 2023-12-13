package co.hei.exceptions;

public class ApiException extends Exception {
    int status;

    public ApiException(String message, int status) {
        super(message);
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("""
                Code  : %s
                Error : %s
                """, status, getMessage());
    }
}
