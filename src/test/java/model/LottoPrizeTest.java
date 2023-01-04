package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoPrizeTest {
    @ParameterizedTest
    @MethodSource("getPrizeAmountTestData")
    @DisplayName("로또 점수에 따른 올바른 로또 상금을 반환한다.")
    void prizeAmountTest(LottoScore lottoScore, Long expectedPrize) {

        assertThat(LottoPrize.valueOf(lottoScore.getMatchNumber(), lottoScore.isMatchBonus()).getPrizeAmount())
                .isEqualTo(expectedPrize);
    }

    static Stream<Arguments> getPrizeAmountTestData() {
        return Stream.of(
                Arguments.of(new LottoScore(0, false), 0L),
                Arguments.of(new LottoScore(1, false), 0L),
                Arguments.of(new LottoScore(1, true), 0L),
                Arguments.of(new LottoScore(2, false), 0L),
                Arguments.of(new LottoScore(2, true), 0L),
                Arguments.of(new LottoScore(3, false), 5_000L),
                Arguments.of(new LottoScore(3, true), 5_000L),
                Arguments.of(new LottoScore(4, false), 50_000L),
                Arguments.of(new LottoScore(4, true), 50_000L),
                Arguments.of(new LottoScore(5, false), 1_500_000L),
                Arguments.of(new LottoScore(5, true), 30_000_000L),
                Arguments.of(new LottoScore(6, false), 2_000_000_000L)
        );
    }
}
