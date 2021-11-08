package task5.util;

import task5.exception.IllegalCurrencyException;
import task5.model.Account;
import task5.model.Currency;

import java.math.BigDecimal;
import java.util.logging.Logger;

public class BalanceValidator {

    private static final Logger log = Logger.getLogger(BalanceValidator.class.getName());

    public static void checkBalanceNotZero(Account account, BigDecimal zero) {
        if (account.getKztCurrencyAmount().compareTo(zero) == 0 && account.getUsdCurrencyAmount().compareTo(zero) == 0
                && account.getRubCurrencyAmount().compareTo(zero) == 0) {
            throw new IllegalCurrencyException(LogUtil.logBusinessException(BalanceValidator.class.getName(),
                    "Not enough money to proceed exchange"));
        }
    }

    public static void checkAmountOnAccount(BigDecimal amountOnAcc, BigDecimal newValueFrom) {
        if (amountOnAcc.compareTo(newValueFrom) < 0) {
            throw new IllegalCurrencyException(LogUtil.logBusinessException(BalanceValidator.class.getName(),
                    String.format("Not enough money to proceed exchange. " +
                    "Tried to convert: [%s], but have on balance: [%s]", newValueFrom, amountOnAcc)));
        }
    }

    public static void checkOperationRule(Currency toCurrency) {
        if (toCurrency != Currency.KZT) {
            throw new IllegalCurrencyException(LogUtil.logBusinessException(BalanceValidator.class.getName(),
                    "In case of exchanging foreign currencies result of operations " +
                    "should keep in national currency"));
        }
    }
}
