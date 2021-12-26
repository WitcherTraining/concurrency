package task5.dao.impl;

import task5.dao.api.AccountInfoDAO;
import task5.exception.CurrencyNotFoundException;
import task5.model.Account;
import task5.model.Currency;
import task5.util.AccountValidator;
import task5.util.LogUtil;
import task5.util.ResourceUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;

public class AccountInfoDAOIml implements AccountInfoDAO {

    private static final Logger log = Logger.getLogger(AccountInfoDAOIml.class.getName());
    public static final String USERS_INFO_PROPERTIES = "users_info.properties";

    @Override
    public Account findOne(String userName) {
        AccountValidator.verifyAccountExist(userName);

        String propsFileName = userName.toLowerCase() + ResourceUtil.PROPERTIES_POSTFIX;
        Properties accountProperties = ResourceUtil.loadProperties(propsFileName);
        return ResourceUtil.getAccountFromProperties(accountProperties);
    }

    @Override
    public List<Account> findAll() {
        List<Account> allAccounts = new ArrayList<>();
        Properties accountProperties = ResourceUtil.loadProperties(USERS_INFO_PROPERTIES);

        Collection<Object> userNames = accountProperties.values();
        userNames.forEach(value -> {
            String propsFileName = value + ResourceUtil.PROPERTIES_POSTFIX;
            try (InputStream propsStream = ClassLoader.getSystemResourceAsStream(propsFileName)) {
                Properties singleAccount = new Properties();
                singleAccount.load(propsStream);
                allAccounts.add(ResourceUtil.getAccountFromProperties(singleAccount));
            } catch (IllegalArgumentException | IOException ex) {
                LogUtil.logRegularExceptions(AccountInfoDAOIml.class.getName(), ex);
            }
        });

        return allAccounts;
    }

    @Override
    public void createAccount(String userName) {
        AccountValidator.verifyAccountExist(userName);
        ResourceUtil.createNewFile(userName);
    }

    @Override
    public void updateAccount(Account account) {
        AccountValidator.verifyAccountExist(account.getUserName());
        ResourceUtil.updatePropertyFile(account);
    }

    @Override
    public boolean removeAccount(String userName) {
        AccountValidator.verifyAccountExist(userName);
        String propsFileName = userName.toLowerCase() + ResourceUtil.PROPERTIES_POSTFIX;
        File fileToDelete = new File(ResourceUtil.PATH_PREFIX + propsFileName);
        return fileToDelete.delete();
    }

    @Override
    public void depositCurrency(String userName, Currency currency, BigDecimal amount) {
        AccountValidator.verifyAccountExist(userName);
        String propsFileName = userName.toLowerCase() + ResourceUtil.PROPERTIES_POSTFIX;
        Properties accountProperties = ResourceUtil.loadProperties(propsFileName);

        setCurrencyValue(currency, amount, accountProperties);

        try {
            accountProperties.store(new FileOutputStream(ResourceUtil.PATH_PREFIX + propsFileName), null);
        } catch (IOException e) {
            LogUtil.logRegularExceptions(AccountInfoDAOIml.class.getName(), e);
        }

    }

    private void setCurrencyValue(Currency currency, BigDecimal amount, Properties accountProperties) {
        switch (currency) {
            case KZT:
                accountProperties.setProperty(ResourceUtil.KZT_CURRENCY_AMOUNT, String.valueOf(amount));
                break;
            case RUB:
                accountProperties.setProperty(ResourceUtil.RUB_CURRENCY_AMOUNT, String.valueOf(amount));
                break;
            case USD:
                accountProperties.setProperty(ResourceUtil.USD_CURRENCY_AMOUNT, String.valueOf(amount));
                break;
            default:
                throw new CurrencyNotFoundException(LogUtil.logBusinessException(AccountInfoDAOIml.class.getName(),
                        "Currency with such name not found: " + currency));
        }
    }
}
