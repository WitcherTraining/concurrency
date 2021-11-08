package task5.dao.impl;

import task5.dao.api.ExchangeRateDAO;
import task5.model.ExchangeRate;
import task5.util.ResourceUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExchangeRateDAOImpl implements ExchangeRateDAO {

    private static final Logger log = Logger.getLogger(ExchangeRateDAOImpl.class.getName());
    public static final String EXCHANGE_RATES_PROPERTIES = "exchange_rates.properties";
    public static final String USD_KZT_RATE = "usd.kzt.rate";
    public static final String RUB_KZT_RATE = "rub.kzt.rate";

    @Override
    public ExchangeRate get() {
        Properties exchangeProperty = ResourceUtil.loadProperties(EXCHANGE_RATES_PROPERTIES);
        BigDecimal usdKztExchangeRate = BigDecimal.valueOf(Double.parseDouble(exchangeProperty.getProperty(USD_KZT_RATE)));
        BigDecimal rubKztExchangeRate = BigDecimal.valueOf(Double.parseDouble(exchangeProperty.getProperty(RUB_KZT_RATE)));
        return new ExchangeRate(usdKztExchangeRate, rubKztExchangeRate);
    }

    @Override
    public void updateRates(ExchangeRate rate) {
        Properties exchangeProperty = ResourceUtil.loadProperties(EXCHANGE_RATES_PROPERTIES);
        exchangeProperty.setProperty(USD_KZT_RATE, String.valueOf(rate.getUsdKztExchangeRate()));
        exchangeProperty.setProperty(RUB_KZT_RATE, String.valueOf(rate.getRubKztExchangeRate()));

        try {
            exchangeProperty.store(new FileOutputStream(ResourceUtil.PATH_PREFIX + EXCHANGE_RATES_PROPERTIES), null);
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public void createRates(ExchangeRate rate) {

        Properties rateProperties = new Properties();
        rateProperties.setProperty(USD_KZT_RATE, String.valueOf(rate.getRubKztExchangeRate()));
        rateProperties.setProperty(RUB_KZT_RATE, String.valueOf(rate.getRubKztExchangeRate()));

        try {
            rateProperties.store(new FileOutputStream(ResourceUtil.PATH_PREFIX + EXCHANGE_RATES_PROPERTIES), null);
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public boolean removeRates() {
        File fileToDelete = new File(ResourceUtil.PATH_PREFIX + EXCHANGE_RATES_PROPERTIES);
        return fileToDelete.delete();
    }
}
