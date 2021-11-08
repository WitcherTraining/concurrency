package task5.exception;

import task5.model.Currency;

public class IllegalCurrencyException extends IllegalArgumentException {
    public IllegalCurrencyException(String s) {
        super(s);
    }
}
