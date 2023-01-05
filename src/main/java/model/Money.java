package model;

import exception.LottoException;
import exception.LottoExceptionCode;

public class Money {
    private final long money;

    public Money(long money) {
        validateMoneyIsNotNegative(money);
        this.money = money;
    }

    public Money(String money) {
        long parsedMoney = Long.parseLong(money);
        validateMoneyIsNotNegative(parsedMoney);
        this.money = parsedMoney;
    }

    private void validateMoneyIsNotNegative(long money) {
        if (money < 0) {
            throw new LottoException(LottoExceptionCode.NEGATIVE_MONEY);
        }
    }

    public long getMoney() {
        return money;
    }
}
