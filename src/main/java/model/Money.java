package model;

import exception.LottoException;
import exception.LottoExceptionCode;

public class Money {
    private final long money;

    public Money(long money) {
        validateMoneyisNotNegative(money);
        this.money = money;
    }

    public Money(String money) {
        long parsedMoney = Long.parseLong(money);
        validateMoneyisNotNegative(parsedMoney);
        this.money = parsedMoney;
    }

    private void validateMoneyisNotNegative(long money) {
        if (money < 0) {
            throw new LottoException(LottoExceptionCode.NEGATIVE_MONEY);
        }
    }

    public long getMoney() {
        return money;
    }
}
