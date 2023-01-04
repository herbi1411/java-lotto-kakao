package model;

import exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static exception.LottoExceptionCode.INVALID_LOTTO_MATCH_NUMBER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoScoreTest {

    @ParameterizedTest
    @MethodSource("getInvalidScoreExceptionTestData")
    @DisplayName("생성될 수 없는 로또 점수가 생성되면 예외를 발생시킨다.")
    void InvalidScoreExceptionTest(int matchNumber, boolean isMatchBonus) {
        assertThatThrownBy(() -> new LottoScore(matchNumber, isMatchBonus))
                .isInstanceOf(LottoException.class)
                .hasMessage(INVALID_LOTTO_MATCH_NUMBER.getErrorMessage());
    }

    static Stream<Arguments> getInvalidScoreExceptionTestData() {
        return Stream.of(
                Arguments.of(-1, true),
                Arguments.of(6, true),
                Arguments.of(7, false),
                Arguments.of(7, true)
        );
    }
}
