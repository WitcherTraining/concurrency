package task5.service.impl;

import task5.dao.api.ExchangeRateDAO;
import task5.dao.impl.ExchangeRateDAOImpl;
import task5.exception.CurrencyNotFoundException;
import task5.exception.IllegalCurrencyException;
import task5.model.Account;
import task5.model.Currency;
import task5.model.ExchangeRate;
import task5.service.api.ExchangeOperationService;
import task5.util.BalanceValidator;
import task5.util.LogUtil;
import task5.util.ResourceUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Logger;

public class ExchangeOperationServiceImpl implements ExchangeOperationService {

    private static final Logger log = Logger.getLogger(ExchangeOperationServiceImpl.class.getName());

    private final ExchangeRateDAO exchangeRateDAO = new ExchangeRateDAOImpl();

    public ExchangeOperationServiceImpl() {
    }

    @Override
    public void exchange(Account account, Currency fromCurrency, BigDecimal convertingAmount, Currency toCurrency) {
        final BigDecimal zero = BigDecimal.valueOf(0);
        BigDecimal newValueTo;
        BalanceValidator.checkBalanceNotZero(account, zero);

        setAppropriatedCurrencyFrom(account, fromCurrency, convertingAmount);

        newValueTo = this.convertCurrencies(fromCurrency, toCurrency, convertingAmount);

        setAppropriatedCurrencyTo(account, toCurrency, newValueTo);

        ResourceUtil.updatePropertyFile(account);

        LogUtil.logInfo(ExchangeOperationServiceImpl.class.getName(),
                String.format("Exchange between [%s] and [%s] done successfully", fromCurrency.name(), toCurrency.name()));

        LogUtil.logInfo(ExchangeOperationServiceImpl.class.getName(),
                "Thread with name " + Thread.currentThread().getName() + " finished job for user: " + account.getUserName());
    }

    @Override
    public BigDecimal convertCurrencies(Currency fromCurrency, Currency toCurrency, BigDecimal moneyAmount) {

        final ExchangeRate exchangeRate = this.exchangeRateDAO.get();

        if (fromCurrency == toCurrency) {
            throw new IllegalCurrencyException(LogUtil.logBusinessException(ExchangeOperationServiceImpl.class.getName(),
                    String.format("Cannot convert equal type of currency: [%s, %s]", fromCurrency, toCurrency)));
        }

        LogUtil.logInfo(ExchangeOperationServiceImpl.class.getName(),
                String.format("Converting from [%s] to [%s] currency...", fromCurrency.name(), toCurrency.name()));

        return getConvertedValue(fromCurrency, toCurrency, moneyAmount, exchangeRate);
    }

    private BigDecimal getConvertedValue(Currency fromCurrency, Currency toCurrency, BigDecimal moneyAmount, ExchangeRate exchangeRate) {
        switch (fromCurrency) {
            case USD:
                BalanceValidator.checkOperationRule(toCurrency);
                return moneyAmount.multiply(exchangeRate.getUsdKztExchangeRate());
            case RUB:
                BalanceValidator.checkOperationRule(toCurrency);
                return moneyAmount.multiply(exchangeRate.getRubKztExchangeRate());
            case KZT:
                switch (toCurrency) {
                    case USD:
                        return moneyAmount.divide(exchangeRate.getUsdKztExchangeRate(), RoundingMode.DOWN);
                    case RUB:
                        return moneyAmount.divide(exchangeRate.getRubKztExchangeRate(), RoundingMode.DOWN);
                }
            default:
                throw new CurrencyNotFoundException(LogUtil.logBusinessException(ExchangeOperationServiceImpl.class.getName(),
                        "Currency with such name not found: " + fromCurrency));
        }
    }

    private void setAppropriatedCurrencyTo(Account account, Currency toCurrency, BigDecimal newValueTo) {
        switch (toCurrency) {
            case USD:
                account.setUsdCurrencyAmount(account.getUsdCurrencyAmount().add(newValueTo));
                break;
            case KZT:
                account.setKztCurrencyAmount(account.getKztCurrencyAmount().add(newValueTo));
                break;
            case RUB:
                account.setRubCurrencyAmount(account.getRubCurrencyAmount().add(newValueTo));
                break;
            default:
                throw new CurrencyNotFoundException(LogUtil.logBusinessException(ExchangeOperationServiceImpl.class.getName(),
                        "Currency with such name not found: " + toCurrency));
        }
    }

    private void setAppropriatedCurrencyFrom(Account account, Currency fromCurrency, BigDecimal convertingAmount) {
        switch (fromCurrency) {
            case USD:
                BigDecimal amountUsdOnAcc = account.getUsdCurrencyAmount();
                BalanceValidator.checkAmountOnAccount(amountUsdOnAcc, convertingAmount);
                account.setUsdCurrencyAmount(amountUsdOnAcc.subtract(convertingAmount));
                break;
            case KZT:
                BigDecimal amountKztOnAcc = account.getKztCurrencyAmount();
                BalanceValidator.checkAmountOnAccount(amountKztOnAcc, convertingAmount);
                account.setKztCurrencyAmount(amountKztOnAcc.subtract(convertingAmount));
                break;
            case RUB:
                BigDecimal amountRubOnAcc = account.getRubCurrencyAmount();
                BalanceValidator.checkAmountOnAccount(amountRubOnAcc, convertingAmount);
                account.setRubCurrencyAmount(amountRubOnAcc.subtract(convertingAmount));
                break;
            default:
                throw new CurrencyNotFoundException(LogUtil.logBusinessException(ExchangeOperationServiceImpl.class.getName(),
                        "Currency with such name not found: " + fromCurrency));
        }
    }

}
