package model;

import exception.LottoException;
import exception.LottoExceptionCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTest {
    @ParameterizedTest
    @MethodSource("normalLottoNumberList")
    @DisplayName("정상범위 로또 숫자열 리스트가 생성자로 들어올 때 정상적으로 객체가 생성된다.")
    void generate_normal_Lotto_success(List<Integer> normalLottoNumberList) {
        //given

        //when
        Lotto lotto = new Lotto(normalLottoNumberList);
        //then
        Assertions.assertThat(lotto.getNumberList()).isEqualTo(normalLottoNumberList.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }

    static Stream<Arguments> normalLottoNumberList() {
        return Stream.of(
                generateIntegerListArgument(1, 2, 3, 4, 5, 6),
                generateIntegerListArgument(1, 2, 3, 43, 44, 45)
        );
    }

    @ParameterizedTest
    @MethodSource("abnormalLottoNumberList")
    @DisplayName("정상범위가 아닌 로또숫자를 포함한 숫자열 리스트가 생성자로 들어올 때 예외가 발생한다.")
    void generate_abnormal_Lotto_raise_exception(List<Integer> abnormalLottoNumberList) {
        //given
        //when

        //then
        Assertions.assertThatThrownBy(() -> new Lotto(abnormalLottoNumberList))
                .isInstanceOf(LottoException.class)
                .hasMessage(LottoExceptionCode.INVALID_LOTTO_NUMBER.getMessage());
    }

    static Stream<Arguments> abnormalLottoNumberList() {
        return Stream.of(
                generateIntegerListArgument(1, 2, 3, 4, 5, 46),
                generateIntegerListArgument(1, 2, 3, 43, 0, 45)
        );
    }

    @ParameterizedTest
    @MethodSource("abnormalLengthOfLottoNumberList")
    @DisplayName("로또 숫자 개수만큼의 길이를 가지지 않은 리스트가 생성자로 들어올 때 예외가 발생한다.")
    void generate_abnormal_length_of_Lotto_raise_exception(List<Integer> abnormalLottoNumberList) {
        //given
        //when

        //then
        Assertions.assertThatThrownBy(() -> new Lotto(abnormalLottoNumberList))
                .isInstanceOf(LottoException.class)
                .hasMessage(LottoExceptionCode.INVALID_LOTTO_LENGTH.getMessage());
    }

    static Stream<Arguments> abnormalLengthOfLottoNumberList() {
        return Stream.of(
                Arguments.of(List.of()),
                generateIntegerListArgument(1),
                generateIntegerListArgument(1, 2),
                generateIntegerListArgument(1, 2, 3),
                generateIntegerListArgument(1, 2, 3, 4),
                generateIntegerListArgument(1, 2, 3, 4, 5),
                generateIntegerListArgument(1, 2, 3, 43, 45),
                generateIntegerListArgument(1, 2, 3, 42, 43, 44, 45)
        );
    }

    static Arguments generateIntegerListArgument(Integer... ints) {
        return Arguments.of(List.of(ints));
    }
}
