package task5.exception;

public class AccountNotFoundException extends IllegalArgumentException {
    public AccountNotFoundException(String s) {
        super(s);
    }
}
