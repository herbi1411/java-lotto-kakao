package service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoServiceTest {

    @ParameterizedTest
    @MethodSource("getLottoGroupSizeTestData")
    @DisplayName("금액에 대해 알맞은 로또 구매 갯수를 추출한다.")
    void lottoGroupSizeTest(long money, int expectedTimes) {
        LottoService lottoService = new LottoService(money);
        long times = lottoService.getTimes();
        assertThat(times).isEqualTo(expectedTimes);
    }

    static Stream<Arguments> getLottoGroupSizeTestData() {
        return Stream.of(
                Arguments.of(1000L, 1),
                Arguments.of(2345L, 2),
                Arguments.of(5500L, 5)
        );
    }
}
