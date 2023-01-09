package service;

import model.Lotto;
import model.LottoGroup;
import model.LottoPrize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoRankServiceTest {
    @ParameterizedTest
    @MethodSource("getGetScoreMethodTestData")
    @DisplayName("지난 주 당첨번호와 로또 번호를 비교해 올바른 결과값 객체를 생성한다.")
    void calculate_score_method_test(List<Integer> winningLottoNumbers, int bonusNumber, List<Integer> lottoTicketNumbers, int expectedMatchNumber, boolean expectedIsMatchBonus) {
        //given
        Lotto lottoTicket = new Lotto(lottoTicketNumbers);
        Lotto winningLotto = new Lotto(winningLottoNumbers);
        LottoGroup lottoGroup = new LottoGroup(List.of(lottoTicket));
        LottoRankService lottoRankService = new LottoRankService(lottoGroup, winningLotto, bonusNumber);

        //when
        List<LottoPrize> result = lottoRankService.calculateScores();

        //then
        assertThat(result).isEqualTo(List.of(LottoPrize.valueOf(expectedMatchNumber, expectedIsMatchBonus)));
    }

    static Stream<Arguments> getGetScoreMethodTestData() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 7, List.of(1, 2, 3, 4, 5, 6), 6, false),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 7, List.of(1, 2, 3, 4, 6, 7), 5, true),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 12, List.of(1, 2, 3, 4, 5, 45), 5, false),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 12, List.of(1, 2, 3, 4, 44, 45), 4, false),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 12, List.of(1, 2, 3, 43, 44, 45), 3, false),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 12, List.of(1, 2, 43, 43, 44, 45), 2, false),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 12, List.of(1, 41, 42, 43, 44, 45), 1, false),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 12, List.of(40, 41, 42, 43, 44, 45), 0, false)
        );
    }
}
