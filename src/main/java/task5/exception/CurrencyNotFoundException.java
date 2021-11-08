package task5.exception;

public class CurrencyNotFoundException extends IllegalArgumentException {
    public CurrencyNotFoundException(String s) {
        super(s);
    }
}
