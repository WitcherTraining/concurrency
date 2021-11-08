package task5;

import task5.dao.api.AccountInfoDAO;
import task5.dao.impl.AccountInfoDAOIml;
import task5.model.Account;
import task5.model.Currency;
import task5.service.api.ExchangeOperationService;
import task5.service.impl.ExchangeOperationServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.LogManager;

public class CurrencyExchanger {
    public static void main(String[] args) throws IOException {
        LogManager.getLogManager().readConfiguration(CurrencyExchanger.class.getResourceAsStream("/logging.properties"));

        AccountInfoDAO accountInfoDAO = new AccountInfoDAOIml();
        List<Account> allAccounts = accountInfoDAO.findAll();
        ExchangeOperationService exchangeOperationService = new ExchangeOperationServiceImpl();

        ExecutorService executorService1 = Executors.newFixedThreadPool(100);
        ExecutorService executorService2 = Executors.newFixedThreadPool(100);
        ExecutorService executorService3 = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            executorService1.execute(() -> exchangeOperationService.exchange(allAccounts.get(0), Currency.USD, BigDecimal.valueOf(10), Currency.KZT));
            executorService2.execute(() -> exchangeOperationService.exchange(allAccounts.get(1), Currency.RUB, BigDecimal.valueOf(10), Currency.KZT));
            executorService3.execute(() -> exchangeOperationService.exchange(allAccounts.get(2), Currency.KZT, BigDecimal.valueOf(10), Currency.RUB));
        }
        executorService1.shutdown();
        executorService2.shutdown();
        executorService3.shutdown();
    }
}
