package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static constant.LottoConstant.*;

public class LottoPrizeTest {
    @ParameterizedTest
    @MethodSource("lottoPrizeTestSource")
    @DisplayName("로또 일치 결과의 등수에 따른 상금을 반환한다.")
    void return_appropriate_lottoPrize_amount(int matchNumber, boolean isMatchBonus, long expectedAmount) {
        //given
        LottoScore lottoScore = new LottoScore(matchNumber, isMatchBonus);

        //when
        LottoPrize lottoPrize = LottoPrize.of(lottoScore);

        //then
        Assertions.assertThat(lottoPrize.getPrizeAmount()).isEqualTo(expectedAmount);
    }

    static Stream<Arguments> lottoPrizeTestSource() {
        return Stream.of(
                Arguments.of(6, false, LOTTO_PRIZE_FIRST),
                Arguments.of(6, true, LOTTO_PRIZE_FIRST),
                Arguments.of(5, true, LOTTO_PRIZE_SECOND),
                Arguments.of(5, false, LOTTO_PRIZE_THIRD),
                Arguments.of(4, true, LOTTO_PRIZE_FOURTH),
                Arguments.of(4, false, LOTTO_PRIZE_FOURTH),
                Arguments.of(3, true, LOTTO_PRIZE_FIFTH),
                Arguments.of(3, false, LOTTO_PRIZE_FIFTH),
                Arguments.of(2, true, 0L),
                Arguments.of(2, false, 0L),
                Arguments.of(1, true, 0L),
                Arguments.of(1, false, 0L),
                Arguments.of(0, true, 0L),
                Arguments.of(0, false, 0L)
        );
    }
}
