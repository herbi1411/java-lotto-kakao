package model;

import exception.LottoException;
import exception.LottoExceptionCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 10, 24, 35, 43, 44, 45})
    @DisplayName("로또 범위의 숫자로 생성하면 에러 없이 생성된다.")
    void generate_with_normal_number_success(int num) {
        //given

        //when
        LottoNumber lottoNumber = new LottoNumber(num);

        //then
        Assertions.assertThat(lottoNumber.getNumber()).isEqualTo(num);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 47, -100, 52, 100, 1000})
    @DisplayName("로또 범위가 아닌 숫자로 생성하면 에러가 발생한다.")
    void generate_with_abnormal_number_raise_exception(int num) {
        //given

        //when

        //then
        Assertions.assertThatThrownBy(
                        () -> new LottoNumber(num))
                .isInstanceOf(LottoException.class)
                .hasMessage(LottoExceptionCode.INVALID_LOTTO_NUMBER.getMessage());
    }
}
