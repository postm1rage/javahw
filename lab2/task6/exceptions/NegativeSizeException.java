package lab2.task6.exceptions;

public class NegativeSizeException extends IllegalArgumentException {
    public NegativeSizeException(String message) {
        super(message);
    }
}