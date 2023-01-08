package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static constant.LottoConstant.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoPrizeTest {
    @ParameterizedTest
    @MethodSource("prizeAmountTestData")
    @DisplayName("로또 점수에 따른 올바른 로또 상금을 반환한다.")
    void prize_amount_Test(LottoScore lottoScore, Long expectedPrize) {
        assertThat(LottoPrize.valueOf(lottoScore.getMatchNumber(), lottoScore.isMatchBonus()).getPrizeAmount())
                .isEqualTo(expectedPrize);
    }

    static Stream<Arguments> prizeAmountTestData() {
        return Stream.of(
                Arguments.of(new LottoScore(0, false), 0L),
                Arguments.of(new LottoScore(1, false), 0L),
                Arguments.of(new LottoScore(1, true), 0L),
                Arguments.of(new LottoScore(2, false), 0L),
                Arguments.of(new LottoScore(2, true), 0L),
                Arguments.of(new LottoScore(3, false), LOTTO_FIFTH_PRIZE_AMOUNT),
                Arguments.of(new LottoScore(3, true), LOTTO_FIFTH_PRIZE_AMOUNT),
                Arguments.of(new LottoScore(4, false), LOTTO_FOURTH_PRIZE_AMOUNT),
                Arguments.of(new LottoScore(4, true), LOTTO_FOURTH_PRIZE_AMOUNT),
                Arguments.of(new LottoScore(5, false), LOTTO_THIRD_PRIZE_AMOUNT),
                Arguments.of(new LottoScore(5, true), LOTTO_SECOND_PRIZE_AMOUNT),
                Arguments.of(new LottoScore(6, false), LOTTO_FIRST_PRIZE_AMOUNT)
        );
    }
}
