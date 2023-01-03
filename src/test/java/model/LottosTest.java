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
        LottoGroup lottoGroup = new LottoGroup(10);
        List<Lotto> lottoList = lottoGroup.getLottoGroup();
        assertThat(lottoList.size()).isEqualTo(10);
    }

    @ParameterizedTest
    @MethodSource("generateLottosResultData")
    @DisplayName("랜덤 생성한 숫자로 로또를 생성했을 때 정렬된 상태로 생성이 된다")
    void lottosResultTest(List<Lotto> givenLottoGroup, List<Lotto> expected) {
        LottoGroup lottoGroup = new LottoGroup(givenLottoGroup);
        assertThat(lottoGroup.getLottoGroup()).isEqualTo(expected);
    }

    static Stream<Arguments> generateLottosResultData() {
        return Stream.of(
                Arguments.of(List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6))), List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)))),
                Arguments.of(List.of(new Lotto(List.of(6, 5, 4, 3, 2, 1))), List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)))),
                Arguments.of(List.of(new Lotto(List.of(10, 11, 12, 13, 14, 15))), List.of(new Lotto(List.of(10, 11, 12, 13, 14, 15))))
        );
    }
}
