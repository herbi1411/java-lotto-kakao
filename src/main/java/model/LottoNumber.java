package model;

import constant.LottoConstant;
import exception.LottoException;
import exception.LottoExceptionCode;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int number;

    public LottoNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(int number) {
        if (number < LottoConstant.LOTTO_MIN_NUMBER || number > LottoConstant.LOTTO_MAX_NUMBER)
            throw new LottoException(LottoExceptionCode.INVALID_LOTTO_NUMBER);
    }

    @Override
    public int compareTo(LottoNumber o) {
        int otherNumber = o.getNumber();
        if (this.number > otherNumber) {
            return 1;
        } else if (this.number < otherNumber) {
            return -1;
        }
        return 0;
    }

    public int getNumber() {
        return number;
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
}
