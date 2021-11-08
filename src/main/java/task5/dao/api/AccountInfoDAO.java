package task5.dao.api;

import task5.model.Account;
import task5.model.Currency;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface AccountInfoDAO {
    Account findOne(String userName) throws IOException;

    List<Account> findAll();

    void createAccount(String userName);

    void updateAccount(Account account);

    boolean removeAccount(String userName);

    void depositCurrency(String userName, Currency currency, BigDecimal amount);
}
