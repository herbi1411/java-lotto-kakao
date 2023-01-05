package service;

import model.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoServiceTest {

    @ParameterizedTest
    @MethodSource("getLottoGroupSizeTestData")
    @DisplayName("사용자 로또 입력이 없는 case에서 금액에 대해 알맞은 로또 구매 갯수를 추출한다.")
    void lottoGroupSizeTest(Money money, int expectedResult) {
        LottoService lottoService = new LottoService(money, 0);
        lottoService.setUserInputLottoGroup(new ArrayList<>());
        long times = lottoService.getTimes();
        assertThat(times).isEqualTo(expectedResult);
    }

    static Stream<Arguments> getLottoGroupSizeTestData() {
        return Stream.of(
                Arguments.of(new Money(1000L), 1),
                Arguments.of(new Money(2345L), 2),
                Arguments.of(new Money(4999L), 4),
                Arguments.of(new Money(5000L), 5),
                Arguments.of(new Money(5001L), 5),
                Arguments.of(new Money(5500L), 5)
        );
    }
}
