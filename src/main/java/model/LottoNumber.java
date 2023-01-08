package model;

import constant.LottoConstant;
import exception.LottoException;
import exception.LottoExceptionCode;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "LottoNumber{" +
                "number=" + number +
                '}';
    }
}
