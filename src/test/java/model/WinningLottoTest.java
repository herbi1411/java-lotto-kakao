package model;

import exception.LottoException;
import exception.LottoExceptionCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {

    @ParameterizedTest
    @MethodSource("normalWinningLottoConstructorSource")
    @DisplayName("올바른 정답로또 인자가 주어지면 정상적으로 객체가 생성된다.")
    void normal_case_success(List<Integer> source, int bonusNumber) {
        //given
        Lotto lotto = new Lotto(source);
        LottoNumber bonusLottoNumber = LottoNumber.of(bonusNumber);

        //when

        //then
        Assertions.assertThatCode(
                        () -> new WinningLotto(lotto, bonusLottoNumber))
                .doesNotThrowAnyException();
    }

    static Stream<Arguments> normalWinningLottoConstructorSource() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 7),
                Arguments.of(List.of(1, 2, 3, 43, 44, 45), 40)
        );
    }

    @ParameterizedTest
    @MethodSource("duplicateBonusNumberSource")
    @DisplayName("보너스볼 숫자와 로또 번호가 중복되면 예외가 발생한다.")
    void duplicate_bonusNumber_raise_exception(List<Integer> source, int bonusNumber) {
        //given
        Lotto lotto = new Lotto(source);
        LottoNumber lottoNumber = LottoNumber.of(bonusNumber);

        //when

        //then
        assertThatThrownBy(
                () -> new WinningLotto(lotto, lottoNumber))
                .isInstanceOf(LottoException.class)
                .hasMessage(LottoExceptionCode.DUPLICATE_BONUS_NUMBER.getMessage());

    }

    static Stream<Arguments> duplicateBonusNumberSource() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 4),
                Arguments.of(List.of(1, 2, 3, 43, 44, 45), 43)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateScoreTestSource")
    @DisplayName("로또 티켓이 주어졌을 때 스코어를 계산해 스코어 객체를 생성한다.")
    void calculate_score_test(List<Integer> winningLottoNumbers, int bonusNumber, List<Integer> lottoNumbers, int expectedMatchNumber, boolean expectedMatchBonus) {
        //given
        WinningLotto winningLotto = new WinningLotto(new Lotto(winningLottoNumbers), LottoNumber.of(bonusNumber));
        Lotto lotto = new Lotto(lottoNumbers);

        //when
        LottoScore lottoScore = winningLotto.calculateScore(lotto);

        //then
        assertThat(lottoScore).isEqualTo(new LottoScore(expectedMatchNumber, expectedMatchBonus));
    }

    static Stream<Arguments> calculateScoreTestSource() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 7, List.of(1, 2, 3, 4, 5, 6), 6, false), //6개 일치
                Arguments.of(List.of(1, 2, 3, 4, 5, 45), 7, List.of(1, 2, 3, 4, 5, 7), 5, true), //5개 일치 + 보너스볼 일치
                Arguments.of(List.of(1, 2, 3, 4, 44, 45), 7, List.of(1, 2, 3, 4, 5, 6), 4, false), //4개 일치
                Arguments.of(List.of(1, 2, 3, 43, 44, 45), 7, List.of(1, 2, 3, 4, 5, 6), 3, false), //3개 일치
                Arguments.of(List.of(1, 2, 3, 43, 44, 45), 7, List.of(1, 2, 3, 4, 5, 7), 3, true), //3개 일치 + 보너스볼 일치
                Arguments.of(List.of(1, 2, 42, 43, 44, 45), 7, List.of(1, 2, 3, 4, 5, 6), 2, false), //2개 일치
                Arguments.of(List.of(1, 41, 42, 43, 44, 45), 7, List.of(1, 2, 3, 4, 5, 6), 1, false), //1개 일치
                Arguments.of(List.of(40, 41, 42, 43, 44, 45), 7, List.of(1, 2, 3, 4, 5, 7), 0, true), //0개 일치 + 보너스볼 일치
                Arguments.of(List.of(40, 41, 42, 43, 44, 45), 7, List.of(1, 2, 3, 4, 5, 6), 0, false) //0개 일치
        );
    }
}
