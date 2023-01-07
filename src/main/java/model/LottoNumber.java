package model;

import constant.LottoConstant;
import exception.LottoException;
import exception.LottoExceptionCode;

public class LottoNumber {
    private final int number;

    public LottoNumber(int num) {
        validateLottoNumber(num);
        this.number = num;
    }

    private void validateLottoNumber(int num) {
        if (num < LottoConstant.LOTTO_MIN_NUMBER || num > LottoConstant.LOTTO_MAX_NUMBER) {
            throw new LottoException(LottoExceptionCode.INVALID_LOTTO_NUMBER);
        }
    }

    public int getNumber() {
        return this.number;
    }
}
