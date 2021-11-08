package task5.service.api;

import task5.model.Account;
import task5.model.Currency;

import java.math.BigDecimal;

public interface ExchangeOperationService {
    BigDecimal convertCurrencies(Currency fromCurrency, Currency toCurrency, BigDecimal moneyAmount);

    void exchange(Account account, Currency fromCurrency, BigDecimal convertingAmount, Currency toCurrency);
}
