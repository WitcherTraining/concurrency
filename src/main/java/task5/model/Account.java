package task5.model;

import java.math.BigDecimal;
import java.util.List;

public class Account {
    private String userName;
    private BigDecimal kztCurrencyAmount;
    private BigDecimal usdCurrencyAmount;
    private BigDecimal rubCurrencyAmount;

    public Account() {
    }

    public Account(String userName, double kztCurrencyAmount, double usdCurrencyAmount, double rubCurrencyAmount) {
        this.userName = userName;
        this.kztCurrencyAmount = BigDecimal.valueOf(kztCurrencyAmount);
        this.usdCurrencyAmount = BigDecimal.valueOf(usdCurrencyAmount);
        this.rubCurrencyAmount = BigDecimal.valueOf(rubCurrencyAmount);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getKztCurrencyAmount() {
        return kztCurrencyAmount;
    }

    public void setKztCurrencyAmount(BigDecimal kztCurrencyAmount) {
        this.kztCurrencyAmount = kztCurrencyAmount;
    }

    public BigDecimal getUsdCurrencyAmount() {
        return usdCurrencyAmount;
    }

    public void setUsdCurrencyAmount(BigDecimal usdCurrencyAmount) {
        this.usdCurrencyAmount = usdCurrencyAmount;
    }

    public BigDecimal getRubCurrencyAmount() {
        return rubCurrencyAmount;
    }

    public void setRubCurrencyAmount(BigDecimal rubCurrencyAmount) {
        this.rubCurrencyAmount = rubCurrencyAmount;
    }
}
