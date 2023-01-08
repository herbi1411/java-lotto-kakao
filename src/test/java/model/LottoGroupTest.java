package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class LottoGroupTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    @DisplayName("생성자로 자연수 하나를 넣으면 그 수 만큼의 로또를 랜덤 생성한다.")
    void name(int repeat) {

        //given
        LottoGroup lottoGroup = new LottoGroup(repeat);

        //when
        List<Lotto> lottoList = lottoGroup.getLottoList();

        //then
        Assertions.assertThat(lottoList.size()).isEqualTo(repeat);
    }
}
