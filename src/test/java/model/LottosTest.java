package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    @Test
    @DisplayName("지정한 횟수만큼 로또 객체를 배열로 생성한다.")
    void lottosLengthTest() {
        Lottos lottos = new Lottos(new RandomNumbers());
        lottos.generate(10);
        List<Lotto> lottoList = lottos.getLottos();
        assertThat(lottoList.size()).isEqualTo(10);
    }

    @ParameterizedTest
    @MethodSource("generateLottosResultData")
    @DisplayName("랜덤 생성한 숫자로 로또를 생성했을 때 정렬된 상태로 생성이 된다")
    void lottosResultTest(List<Integer> randomNumbers, int times, String expected) {
        Lottos lottos = new Lottos(createRandomNumbers(randomNumbers));
        lottos.generate(times);
        assertThat(lottos.toString()).isEqualTo(expected);
    }

    static Stream<Arguments> generateLottosResultData() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 1, "[1, 2, 3, 4, 5, 6]"),
                Arguments.of(List.of(6, 5, 4, 3, 2, 1), 2, "[1, 2, 3, 4, 5, 6]\n[1, 2, 3, 4, 5, 6]"),
                Arguments.of(List.of(10, 11, 12, 13, 14, 15), 1, "[10, 11, 12, 13, 14, 15]")
        );
    }

    @ParameterizedTest
    @MethodSource("generateTimesData")
    @DisplayName("랜덤 생성한 숫자로 로또를 생성했을 때 정렬된 상태로 생성이 된다")
    void lottosResultTest(long money, int expectedTimes) {
        Lottos lottos = new Lottos(new RandomNumbers());
        int times = lottos.calcTimes(money);
        assertThat(times).isEqualTo(expectedTimes);
    }

    static Stream<Arguments> generateTimesData() {
        return Stream.of(
                Arguments.of(1000L, 1),
                Arguments.of(2345L, 2),
                Arguments.of(5500L, 5)
        );
    }

    private RandomNumbers createRandomNumbers(List<Integer> returnValue) {
        return new RandomNumbers() {
            public List<Integer> generate() {
                return returnValue;
            }
        };
    }
}
