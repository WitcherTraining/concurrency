package task5.util;

import task5.model.Account;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class ResourceUtil {

    private static final Logger log = Logger.getLogger(ResourceUtil.class.getName());

    public static final String USER_NAME = "user.name";
    public static final String KZT_CURRENCY_AMOUNT = "kzt.currency.amount";
    public static final String USD_CURRENCY_AMOUNT = "usd.currency.amount";
    public static final String RUB_CURRENCY_AMOUNT = "rub.currency.amount";
    public static final String PATH_PREFIX = "src/main/resources/";
    public static final String PROPERTIES_POSTFIX = "_acc.properties";

    public static Account getAccountFromProperties(Properties accountProperties) {
        String name = accountProperties.getProperty(USER_NAME);
        double kztCurrencyAmount = Double.parseDouble(accountProperties.getProperty(KZT_CURRENCY_AMOUNT));
        double usdCurrencyAmount = Double.parseDouble(accountProperties.getProperty(USD_CURRENCY_AMOUNT));
        double rubCurrencyAmount = Double.parseDouble(accountProperties.getProperty(RUB_CURRENCY_AMOUNT));

        return new Account(name, kztCurrencyAmount, usdCurrencyAmount, rubCurrencyAmount);
    }

    public static Properties loadProperties(String propsFileName) {
        Properties accountProperties = new Properties();

        try (InputStream propsStream = ClassLoader.getSystemResourceAsStream(propsFileName)) {
            accountProperties.load(propsStream);
        } catch (IllegalArgumentException | IOException e) {
            LogUtil.logRegularExceptions(ResourceUtil.class.getName(), e);
        }
        return accountProperties;
    }

    public static void updatePropertyFile(Account account) {
        String propsFileName = account.getUserName().toLowerCase() + PROPERTIES_POSTFIX;
        Properties accountProperties = ResourceUtil.loadProperties(propsFileName);

        accountProperties.setProperty(KZT_CURRENCY_AMOUNT, String.valueOf(account.getKztCurrencyAmount()));
        accountProperties.setProperty(USD_CURRENCY_AMOUNT, String.valueOf(account.getUsdCurrencyAmount()));
        accountProperties.setProperty(RUB_CURRENCY_AMOUNT, String.valueOf(account.getRubCurrencyAmount()));
        try {
            accountProperties.store(new FileOutputStream(PATH_PREFIX + propsFileName), null);
        } catch (IOException e) {
            LogUtil.logRegularExceptions(ResourceUtil.class.getName(), e);
        }
    }

    public static void createNewFile(String userName) {
        String propsFileName = userName.toLowerCase() + PROPERTIES_POSTFIX;
        Properties accountProperties = new Properties();
        accountProperties.setProperty(USER_NAME, userName);
        accountProperties.setProperty(KZT_CURRENCY_AMOUNT, String.valueOf(0));
        accountProperties.setProperty(USD_CURRENCY_AMOUNT, String.valueOf(0));
        accountProperties.setProperty(RUB_CURRENCY_AMOUNT, String.valueOf(0));

        try {
            accountProperties.store(new FileOutputStream(PATH_PREFIX + propsFileName), null);
        } catch (IOException e) {
            LogUtil.logRegularExceptions(ResourceUtil.class.getName(), e);
        }
    }
}
