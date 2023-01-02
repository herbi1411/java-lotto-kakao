package model;

import exception.LottoException;
import exception.LottoExceptionCode;

public class LottoNumber implements Comparable<LottoNumber>{
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
        }
        else if (this.number < otherNumber) {
            return -1;
        }
        return 0;
    }

    public int getNumber() {
        return number;
    }
}
