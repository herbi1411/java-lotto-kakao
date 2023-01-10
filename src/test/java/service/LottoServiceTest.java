package service;

import exception.LottoException;
import exception.LottoExceptionCode;
import model.Lotto;
import model.LottoNumber;
import model.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static constant.LottoConstant.LOTTO_TICKET_PRICE;

public class LottoServiceTest {

    private LottoService lottoService;

    @BeforeEach
    void setUp() {
        this.lottoService = new LottoService();
    }

    @ParameterizedTest
    @MethodSource("manualLottoInputNumberBiggerThanMoneyExceptionTestData")
    @DisplayName("구입 금액 대비 수동 구매 로또 개수가 많으면 예외가 발생한다.")
    void manualLottoInputNumber_bigger_than_money_Exception_Test(Long manualLottoInputNumber, Long moneyLong) {
        //given
        Money money = new Money(moneyLong);
        //when

        //then
        Assertions.assertThatThrownBy(() -> lottoService.validateManualLottoInputNumber(manualLottoInputNumber, money))
                .isInstanceOf(LottoException.class)
                .hasMessage(LottoExceptionCode.INVALID_MANUAL_LOTTO_COUNT.getErrorMessage());
    }

    static Stream<Arguments> manualLottoInputNumberBiggerThanMoneyExceptionTestData() {
        return Stream.of(
                Arguments.of(3L, 2L * LOTTO_TICKET_PRICE),
                Arguments.of(2L, 1L * LOTTO_TICKET_PRICE),
                Arguments.of(1L, 0L * LOTTO_TICKET_PRICE),
                Arguments.of(6L, 5L * LOTTO_TICKET_PRICE)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeManualLottoInputNumberExceptionTestData")
    @DisplayName("수동 구매 로또 개수가 음수로 주어지면 예외가 발생한다.")
    void negative_manualLottoInputNumber_Exception_Test(Long manualLottoInputNumber) {
        //given
        Money money = new Money(3000L);
        //when

        //then
        Assertions.assertThatThrownBy(() -> lottoService.validateManualLottoInputNumber(manualLottoInputNumber, money))
                .isInstanceOf(LottoException.class)
                .hasMessage(LottoExceptionCode.INVALID_MANUAL_LOTTO_COUNT.getErrorMessage());
    }

    Stream<Arguments> negativeManualLottoInputNumberExceptionTestData() {
        return Stream.of(
                Arguments.of(-1L),
                Arguments.of(-2L),
                Arguments.of(-100L)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidBonusNumberExceptionTest")
    @DisplayName("로또 당첨번호와 보너스 번호가 중복되면 예외가 발생한다.")
    void invalid_bonusNumber_exception_test(List<Integer> winningLottoIntgerList, int bonusNumberInteger) {
        //given
        Lotto winningLotto = new Lotto(winningLottoIntgerList);
        LottoNumber bonusNumber = LottoNumber.from(bonusNumberInteger);

        //when

        //then
        Assertions.assertThatThrownBy(() -> lottoService.validateWinningLotto(winningLotto, bonusNumber))
                .isInstanceOf(LottoException.class)
                .hasMessage(LottoExceptionCode.WINNING_LOTTO_SET_DUPLICATE.getErrorMessage());
    }

    static Stream<Arguments> invalidBonusNumberExceptionTest() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 1),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 2),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 3),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 4),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 5),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 6)
        );
    }


}
