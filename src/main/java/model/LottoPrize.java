package model;

import java.util.Arrays;

import static constant.LottoConstant.*;

public enum LottoPrize {

    PRIZE_NOTHING(0, false, 0L),
    PRIZE_FIFTH(3, false, LOTTO_FIFTH_PRIZE_AMOUNT),
    PRIZE_FOURTH(4, false, LOTTO_FOURTH_PRIZE_AMOUNT),
    PRIZE_THIRD(5, false, LOTTO_THIRD_PRIZE_AMOUNT),
    PRIZE_SECOND(5, true, LOTTO_SECOND_PRIZE_AMOUNT),
    PRIZE_FIRST(6, true, LOTTO_FIRST_PRIZE_AMOUNT);

    private final int matchNumber;
    private final long prizeAmount;
    private final boolean isMatchBonus;

    LottoPrize(int matchNumber, boolean isMatchBonus, long prizeAmount) {
        this.matchNumber = matchNumber;
        this.isMatchBonus = isMatchBonus;
        this.prizeAmount = prizeAmount;
    }

    public static LottoPrize valueOf(int matchNumber, boolean isMatchBonus) {
        return Arrays.stream(LottoPrize.values())
                .filter(l -> l.isValueMatch(matchNumber, isMatchBonus))
                .findFirst()
                .orElse(LottoPrize.PRIZE_NOTHING);
    }

    private boolean isValueMatch(int matchNumber, boolean isMatchBonus) {
        if (this.matchNumber != matchNumber) {
            return false;
        }
        return matchNumber != 5 || this.isMatchBonus == isMatchBonus;
    }

    public long getPrizeAmount() {
        return prizeAmount;
    }

}
