package model;

import exception.LottoException;
import exception.LottoExceptionCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {-1000, -1, 0, 46, 47})
    @DisplayName("로또 범위가 아닌 숫자로 LottoNumber를 생성할 때 예외가 발생한다.")
    void invalid_LottoNumber_throw_exception(int invalidLottoNumber) {
        Assertions.assertThatThrownBy(
                        () -> LottoNumber.from(invalidLottoNumber)
                ).isInstanceOf(LottoException.class)
                .hasMessage(LottoExceptionCode.INVALID_LOTTO_NUMBER.getErrorMessage());
    }

}