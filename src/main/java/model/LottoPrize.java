package model;

import java.util.Arrays;

import static constant.LottoConstant.*;

public enum LottoPrize {

    PRIZE_FIRST(LOTTO_PRIZE_FIRST, 6, false),
    PRIZE_SECOND(LOTTO_PRIZE_SECOND, 5, true),
    PRIZE_THIRD(LOTTO_PRIZE_THIRD, 5, false),
    PRIZE_FOURTH(LOTTO_PRIZE_FOURTH, 4, false),
    PRIZE_FIFTH(LOTTO_PRIZE_FIFTH, 3, false),
    PRIZE_NOTHING(0L, 0, false);
    private final long prizeAmount;
    private final int matchNumber;
    private final boolean isMatchBonusNumber;


    LottoPrize(long prizeAmount, int matchNumber, boolean isMatchBonusNumber) {
        this.prizeAmount = prizeAmount;
        this.matchNumber = matchNumber;
        this.isMatchBonusNumber = isMatchBonusNumber;
    }

    public static LottoPrize of(LottoScore lottoScore) {
        return Arrays.stream(LottoPrize.values())
                .filter(lottoPrize -> lottoPrize.isMatchPrize(lottoScore))
                .findFirst()
                .orElse(PRIZE_NOTHING);
    }

    private boolean isMatchPrize(LottoScore lottoScore) {
        if (matchNumber == 5 && lottoScore.getMatchNumber() == 5) {
            return isMatchBonusNumber == lottoScore.isMatchBonusNumber();
        }
        return matchNumber == lottoScore.getMatchNumber();
    }

    public long getPrizeAmount() {
        return this.prizeAmount;
    }
}
