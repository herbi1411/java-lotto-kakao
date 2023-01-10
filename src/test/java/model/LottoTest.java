package model;

import constant.LottoConstant;
import exception.LottoException;
import exception.LottoExceptionCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @RepeatedTest(100)
    @DisplayName("로또를 매개변수 없이 생성했을 때 정해진 개수만큼 랜덤 숫자를 가진 로또가 생성된다.")
    void lotto_random_generate() {
        //given
        Lotto lotto = new Lotto();

        //when
        Set<LottoNumber> generatedNumbers = lotto.getNumbers();

        //then
        assertThat(generatedNumbers.size()).isEqualTo(LottoConstant.LOTTO_COUNT);
    }

    @ParameterizedTest
    @MethodSource("identicalTestData")
    @DisplayName("로또의 숫자들 순서에 상관 없이 구성만 같다면 동일하게 처리된다.")
    void identical_lotto_regardless_of_number_order_is_equal(List<Integer> givenLottoConstructorSource, List<Integer> expectedLottoConstructorSource) {
        //given
        Lotto expectedLotto = new Lotto(expectedLottoConstructorSource);
        //when
        Lotto givenLotto = new Lotto(givenLottoConstructorSource);
        //then
        assertThat(givenLotto).isEqualTo(expectedLotto);
    }

    static Stream<Arguments> identicalTestData() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 6)),
                Arguments.of(List.of(6, 5, 4, 3, 2, 1), List.of(1, 2, 3, 4, 5, 6)),
                Arguments.of(List.of(10, 11, 12, 13, 14, 15), List.of(10, 11, 12, 13, 14, 15))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidLengthIntegerListConstructorTestData")
    @DisplayName("로또 생성자로 로또 길이가 맞지 않는 숫자 리스트가 주어졌을 때 예외가 발생한다.")
    void invalid_length_of_integer_list_constructor_throw_exception(List<Integer> source) {
        assertThatThrownBy(() -> new Lotto(source))
                .isInstanceOf(LottoException.class)
                .hasMessage(LottoExceptionCode.INVALID_LOTTO_LENGTH.getErrorMessage());
    }

    static Stream<Arguments> invalidLengthIntegerListConstructorTestData() {
        return Stream.of(
                Arguments.of(List.of()),
                Arguments.of(List.of(1)),
                Arguments.of(List.of(1, 2, 3, 4)),
                Arguments.of(List.of(1, 2, 3, 4, 5))
        );
    }
}
