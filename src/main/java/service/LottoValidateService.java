package service;

import exception.LottoException;
import exception.LottoExceptionCode;
import model.Money;

import static constant.LottoConstant.LOTTO_TICKET_PRICE;

public class LottoValidateService {
    public void validateManualInputNumber(Money money, Long manualLottoInputNumber) {

        if (manualLottoInputNumber < 0) {
            throw new LottoException(LottoExceptionCode.NEGATIVE_MANUAL_LOTTO_COUNT);
        }

        long times = money.getMoney() / LOTTO_TICKET_PRICE;
        if (times < manualLottoInputNumber) {
            throw new LottoException(LottoExceptionCode.INVALID_MANUAL_LOTTO_COUNT);
        }
    }

    public boolean canProceedGameWithInputMoney(Money money) {
        return money.getMoney() >= LOTTO_TICKET_PRICE;
    }
}
