package model;

import constant.LottoConstant;
import exception.LottoException;
import exception.LottoExceptionCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import static constant.LottoConstant.LOTTO_MAX_NUMBER;
import static constant.LottoConstant.LOTTO_MIN_NUMBER;

public class LottoNumber {
    private static final Map<Integer, LottoNumber> cachedLottoNumberMap = new HashMap<>();
    private final int number;

    static {
        IntStream.rangeClosed(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)
                .forEach(number -> cachedLottoNumberMap.put(number, new LottoNumber(number)));
    }

    private LottoNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    public static LottoNumber from(int number) {
        validateNumber(number);
        return cachedLottoNumberMap.get(number);
    }

    private static void validateNumber(int number) {
        if (number < LottoConstant.LOTTO_MIN_NUMBER || number > LottoConstant.LOTTO_MAX_NUMBER)
            throw new LottoException(LottoExceptionCode.INVALID_LOTTO_NUMBER);
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
}
