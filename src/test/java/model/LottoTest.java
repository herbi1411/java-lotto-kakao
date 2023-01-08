package model;

import exception.LottoException;
import exception.LottoExceptionCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static constant.LottoConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @ParameterizedTest
    @MethodSource("normalLottoNumberList")
    @DisplayName("정상범위 로또 숫자열 리스트가 생성자로 들어올 때 정상적으로 객체가 생성된다.")
    void normal_Lotto_success(List<Integer> normalLottoNumberList) {
        //given

        //when
        Lotto lotto = new Lotto(normalLottoNumberList);
        //then
        assertThat(lotto.getNumberList()).isEqualTo(normalLottoNumberList.stream()
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
    void abnormal_Lotto_raise_exception(List<Integer> abnormalLottoNumberList) {
        //given
        //when

        //then
        assertThatThrownBy(() -> new Lotto(abnormalLottoNumberList))
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
    void abnormal_length_of_Lotto_raise_exception(List<Integer> abnormalLottoNumberList) {
        //given
        //when

        //then
        assertThatThrownBy(() -> new Lotto(abnormalLottoNumberList))
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

    @ParameterizedTest
    @MethodSource("duplicateLottoNumberList")
    @DisplayName("중복된 숫자를 가진 리스트가 생성자로 들어올 때 예외가 발생한다.")
    void duplicate_lotto_number_raise_exception(List<Integer> abnormalLottoNumberList) {
        //given
        //when

        //then
        assertThatThrownBy(() -> new Lotto(abnormalLottoNumberList))
                .isInstanceOf(LottoException.class)
                .hasMessage(LottoExceptionCode.DUPLICATE_LOTTO_NUMBER.getMessage());
    }

    static Stream<Arguments> duplicateLottoNumberList() {
        return Stream.of(
                generateIntegerListArgument(1, 2, 3, 4, 5, 1),
                generateIntegerListArgument(1, 2, 3, 43, 44, 44),
                generateIntegerListArgument(1, 1, 3, 43, 45, 44),
                generateIntegerListArgument(1, 2, 3, 2, 41, 44),
                generateIntegerListArgument(1, 2, 3, 43, 44, 3),
                generateIntegerListArgument(1, 2, 3, 5, 44, 5)
        );
    }

    @RepeatedTest(100)
    @DisplayName("매개변수 없는 생성자로 객체를 생성할 때 올바른 길이의 랜덤 로또를 생성한다.")
    void generate_random_lotto_success() {

        //given
        Lotto lotto = new Lotto();

        //when
        List<LottoNumber> lottoNumberList = lotto.getNumberList();

        //then
        assertThat(lottoNumberList.size()).isEqualTo(LOTTO_LENGTH); // 6자리로 생성
        assertThat(lottoNumberList.stream().distinct().count()).isEqualTo(LOTTO_LENGTH); // 중복 없음
        assertThat(lottoNumberList.stream()
                .map(LottoNumber::getNumber)
                .filter(n -> n >= LOTTO_MIN_NUMBER && n <= LOTTO_MAX_NUMBER)
                .count()).isEqualTo(LOTTO_LENGTH); // 로또 숫자 범위

    }

    static Arguments generateIntegerListArgument(Integer... ints) {
        return Arguments.of(List.of(ints));
    }
}
