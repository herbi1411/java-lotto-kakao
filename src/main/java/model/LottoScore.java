package model;

import java.util.Objects;

public class LottoScore {
    private final int matchNumber;
    private final boolean isMatchBonusNumber;

    public LottoScore(int matchNumber, boolean isMatchBonusNumber) {
        this.matchNumber = matchNumber;
        this.isMatchBonusNumber = isMatchBonusNumber;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public boolean isMatchBonusNumber() {
        return isMatchBonusNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoScore that = (LottoScore) o;
        return matchNumber == that.matchNumber && isMatchBonusNumber == that.isMatchBonusNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchNumber, isMatchBonusNumber);
    }

    @Override
    public String toString() {
        return "LottoScore{" +
                "matchNumber=" + matchNumber +
                ", isMatchBonusNumber=" + isMatchBonusNumber +
                '}';
    }
}
