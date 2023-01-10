package model;

import exception.LottoException;
import exception.LottoExceptionCode;

public class Money {
    private long money;

    public Money(long money) {
        this.money = money;
        validateMoneyIsNotNegative();
    }

    public Money(Money money) {
        this.money = money.money;
    }

    private void validateMoneyIsNotNegative() {
        if (this.money < 0) {
            throw new LottoException(LottoExceptionCode.NEGATIVE_MONEY);
        }
    }

    public void purchase(long price) {
        this.money -= price;
        validateMoneyIsNotNegative();
    }

    public long compare(long other) {
        return this.money - other;
    }

    public long dividedByResult(long target) {
        return this.money / target;
    }

    public double divideResult(double target) {
        return target / this.money;
    }
}
