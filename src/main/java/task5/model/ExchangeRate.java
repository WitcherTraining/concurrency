package task5.model;

import java.math.BigDecimal;

public class ExchangeRate {
    private BigDecimal usdKztExchangeRate;
    private BigDecimal rubKztExchangeRate;

    public ExchangeRate() {}

    public ExchangeRate(BigDecimal usdExchangeRate, BigDecimal rubExchangeRate) {
        this.usdKztExchangeRate = usdExchangeRate;
        this.rubKztExchangeRate = rubExchangeRate;
    }

    public BigDecimal getUsdKztExchangeRate() {
        return usdKztExchangeRate;
    }

    public void setUsdKztExchangeRate(BigDecimal usdKztExchangeRate) {
        this.usdKztExchangeRate = usdKztExchangeRate;
    }

    public BigDecimal getRubKztExchangeRate() {
        return rubKztExchangeRate;
    }

    public void setRubKztExchangeRate(BigDecimal rubKztExchangeRate) {
        this.rubKztExchangeRate = rubKztExchangeRate;
    }
}
