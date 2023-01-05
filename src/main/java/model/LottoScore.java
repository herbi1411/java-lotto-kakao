package model;

import exception.LottoException;
import exception.LottoExceptionCode;

import java.util.Objects;

import static constant.LottoConstant.LOTTO_COUNT;

public class LottoScore {
    private final int matchNumber;
    private final boolean isMatchBonus;

    public LottoScore(int matchNumber, boolean isMatchBonus) {
        validateParams(matchNumber, isMatchBonus);
        this.matchNumber = matchNumber;
        this.isMatchBonus = isMatchBonus;
    }

    private void validateParams(int matchNumber, boolean isMatchBonus) {
        if (matchNumber < 0 || matchNumber > LOTTO_COUNT || (matchNumber == LOTTO_COUNT && isMatchBonus)) {
            throw new LottoException(LottoExceptionCode.INVALID_LOTTO_SCORE);
        }
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public boolean isMatchBonus() {
        return isMatchBonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoScore that = (LottoScore) o;
        return matchNumber == that.matchNumber && isMatchBonus == that.isMatchBonus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchNumber, isMatchBonus);
    }
}
