package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static constant.LottoConstant.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @ParameterizedTest
    @MethodSource("getEarningRateTestData")
    @DisplayName("로또 당첨에 따른 올바른 수익률을 계산한다.")
    void earningRateTest(List<Lotto> lottosList, WinningLottoSet winningLotto, long times, double expectedEarningRate) {
        LottoGroup lottoGroup = new LottoGroup(lottosList);
        LottoResult lottoResult = new LottoResult(lottoGroup, winningLotto, new Money(times * LOTTO_TICKET_PRICE));
        assertThat(lottoResult.getEarningRate()).isEqualTo(expectedEarningRate);
    }

    static Stream<Arguments> getEarningRateTestData() {
        return Stream.of(
                Arguments.of(
                        List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(7, 8, 9, 10, 11, 12))),
                        new WinningLottoSet(new Lotto("1, 2, 3, 4, 5, 6", LOTTO_NUMBER_DELIMITER), 7),
                        2L,
                        (double) (LOTTO_FIRST_PRIZE_AMOUNT) / (LOTTO_TICKET_PRICE * 2)
                ), // 6개 일치
                Arguments.of(
                        List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 2, 3, 4, 5, 6))),
                        new WinningLottoSet(new Lotto("1, 2, 3, 4, 5, 6", LOTTO_NUMBER_DELIMITER), 7),
                        2L,
                        (double) (LOTTO_FIRST_PRIZE_AMOUNT + LOTTO_FIRST_PRIZE_AMOUNT) / (LOTTO_TICKET_PRICE * 2)
                ), // 6개 일치 + 6개 일치
                Arguments.of(
                        List.of(new Lotto(List.of(5, 4, 3, 2, 1, 9)), new Lotto(List.of(3, 5, 6, 7, 2, 4))),
                        new WinningLottoSet(new Lotto("1, 2, 3, 4, 5, 6", LOTTO_NUMBER_DELIMITER), 7),
                        2L,
                        (double) (LOTTO_THIRD_PRIZE_AMOUNT + LOTTO_SECOND_PRIZE_AMOUNT) / (LOTTO_TICKET_PRICE * 2)
                ), // 5개 일치 + 5개 일치, 보너스 일치
                Arguments.of(
                        List.of(new Lotto(List.of(1, 2, 3, 34, 35, 36)), new Lotto(List.of(1, 2, 3, 4, 35, 36))),
                        new WinningLottoSet(new Lotto("1, 2, 3, 4, 5, 6", LOTTO_NUMBER_DELIMITER), 7),
                        2L,
                        (double) (LOTTO_FIFTH_PRIZE_AMOUNT + LOTTO_FOURTH_PRIZE_AMOUNT) / (LOTTO_TICKET_PRICE * 2)
                ) // 3개 일치, 4개 일치
        );
    }
}
