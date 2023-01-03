package model;

import constant.LottoConstant;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @ParameterizedTest
    @MethodSource("generateLottoResults")
    void lottoResultTest(List<Lotto> lottosList, WinningLotto winningLotto, int times, double expectedEarningRate) {
        LottoGroup lottoGroup = new LottoGroup(lottosList);
        LottoResult lottoResult = new LottoResult(lottoGroup, winningLotto, (long) times * LottoConstant.LOTTO_TICKET_PRICE);
        assertThat(lottoResult.getEarningRate()).isEqualTo(expectedEarningRate);
    }

    static Stream<Arguments> generateLottoResults() {
        return Stream.of(
                Arguments.of(
                        List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(7, 8, 9, 10, 11, 12))),
                        new WinningLotto("1, 2, 3, 4, 5, 6", 7),
                        2,
                        (double) (2_000_000_000L) / 2000L
                ), // 6개 일치
                Arguments.of(
                        List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 2, 3, 4, 5, 6))),
                        new WinningLotto("1, 2, 3, 4, 5, 6", 7),
                        2,
                        (double) (2_000_000_000L + 2_000_000_000L) / 2000L
                ), // 6개 일치 + 6개 일치
                Arguments.of(
                        List.of(new Lotto(List.of(5, 4, 3, 2, 1, 9)), new Lotto(List.of(3, 5, 6, 7, 2, 4))),
                        new WinningLotto("1, 2, 3, 4, 5, 6", 7),
                        2,
                        (double) (1_500_000L + 30_000_000L) / 2000L
                ), // 5개 일치 + 5개 일치, 보너스 일치
                Arguments.of(
                        List.of(new Lotto(List.of(1, 2, 3, 34, 35, 36)), new Lotto(List.of(1, 2, 3, 4, 35, 36))),
                        new WinningLotto("1, 2, 3, 4, 5, 6", 7),
                        2,
                        (double) (5_000L + 50_000L) / 2000L
                ) // 3개 일치, 4개 일치
        );
    }
}
