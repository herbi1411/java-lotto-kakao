package model;

import exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static exception.LottoExceptionCode.INVALID_LOTTO_MATCH_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoScoreTest {

    @ParameterizedTest
    @MethodSource("generateWrongScoreData")
    @DisplayName("생성될 수 없는 로또 점수가 생성되면 예외를 발생시킨다.")
    void wrongScoreTest(int matchNumber, boolean isMatchBonus) {
        assertThatThrownBy(() -> new LottoScore(matchNumber, isMatchBonus))
                .isInstanceOf(LottoException.class)
                .hasMessage(INVALID_LOTTO_MATCH_NUMBER.getErrorMessage());
    }

    static Stream<Arguments> generateWrongScoreData() {
        return Stream.of(
                Arguments.of(-1, true),
                Arguments.of(6, true),
                Arguments.of(7, false),
                Arguments.of(7, true)
        );
    }

    @ParameterizedTest
    @MethodSource("generateCompareData")
    @DisplayName("동일한 로또 점수를 비교할때 비교결과로 true를 반환한다. (일치 개수가 5일 때만 보너스 숫자 일치 여부 판단)")
    void compareTest(LottoScore lottoScore, LottoScore that, boolean expected) {
        assertThat(lottoScore.compare(that)).isEqualTo(expected);
    }

    static Stream<Arguments> generateCompareData() {
        return Stream.of(
                Arguments.of(
                        new LottoScore(3, false),
                        new LottoScore(3, false),
                        true
                ),
                Arguments.of(
                        new LottoScore(3, false),
                        new LottoScore(3, true),
                        true
                ),
                Arguments.of(
                        new LottoScore(4, false),
                        new LottoScore(4, true),
                        true
                ),
                Arguments.of(
                        new LottoScore(5, false),
                        new LottoScore(5, true),
                        false
                ),
                Arguments.of(
                        new LottoScore(6, false),
                        new LottoScore(6, false),
                        true
                )
        );
    }
}
