package model;

import java.util.Objects;

public class LottoScore {
    private final int matchNumber;
    private final boolean isMatchBonus;

    public LottoScore(int matchNumber, boolean isMatchBonus) {
        this.matchNumber = matchNumber;
        this.isMatchBonus = isMatchBonus;
    }

    public int getMatchNumber() {
        return matchNumber;
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


    @Override
    public String toString() {
        return "LottoScore{" +
                "matchNumber=" + matchNumber +
                ", isMatchBonus=" + isMatchBonus +
                '}';
    }
}
