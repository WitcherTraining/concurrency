package task5.dao.api;

import task5.model.ExchangeRate;

public interface ExchangeRateDAO {

    ExchangeRate get();

    void updateRates(ExchangeRate rate);

    boolean removeRates();

    void createRates(ExchangeRate rate);
}
