package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGroupTest {
    @Test
    @DisplayName("지정한 횟수만큼 로또 객체를 배열로 생성한다.")
    void lottoGroupLengthTest() {
        LottoGroup lottoGroup = new LottoGroup(10L);
        List<Lotto> lottoList = lottoGroup.getLottoGroup();
        assertThat(lottoList.size()).isEqualTo(10);
    }

    @ParameterizedTest
    @MethodSource("userInputLottoGroupConstructorTestData")
    @DisplayName("로또 그룹의 일부요소가 생성자로 주어질때 자동 생성된 로또와 합쳐서 둘을 합친 길이를 갖는 로또그룹을 반환한다.")
    void userInputLottoGroupConstructorTest(long autoGenerateNumber, List<Lotto> userInputLottoGroup, int expectedSize) {
        LottoGroup lottoGroup = new LottoGroup(autoGenerateNumber, userInputLottoGroup);
        assertThat(lottoGroup.getLottoGroup().size()).isEqualTo(expectedSize);
    }

    static Stream<Arguments> userInputLottoGroupConstructorTestData() {
        return Stream.of(
                Arguments.of(3L, List.of(new Lotto(), new Lotto()), 5),
                Arguments.of(5L, List.of(new Lotto()), 6),
                Arguments.of(2L, List.of(new Lotto(), new Lotto(), new Lotto()), 5)
        );
    }
}
