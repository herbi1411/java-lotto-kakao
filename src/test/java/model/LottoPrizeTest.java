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
    void prize_amount_Test(int matchNumber, boolean isMatchBonus, Long expectedPrize) {
        assertThat(LottoPrize.valueOf(matchNumber, isMatchBonus).getPrizeAmount())
                .isEqualTo(expectedPrize);
    }

    static Stream<Arguments> prizeAmountTestData() {
        return Stream.of(
                Arguments.of(0, false, 0L),
                Arguments.of(1, false, 0L),
                Arguments.of(1, true, 0L),
                Arguments.of(2, false, 0L),
                Arguments.of(2, true, 0L),
                Arguments.of(3, false, LOTTO_FIFTH_PRIZE_AMOUNT),
                Arguments.of(3, true, LOTTO_FIFTH_PRIZE_AMOUNT),
                Arguments.of(4, false, LOTTO_FOURTH_PRIZE_AMOUNT),
                Arguments.of(4, true, LOTTO_FOURTH_PRIZE_AMOUNT),
                Arguments.of(5, false, LOTTO_THIRD_PRIZE_AMOUNT),
                Arguments.of(5, true, LOTTO_SECOND_PRIZE_AMOUNT),
                Arguments.of(6, false, LOTTO_FIRST_PRIZE_AMOUNT)
        );
    }
}
