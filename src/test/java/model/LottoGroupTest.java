package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGroupTest {
    @Test
    @DisplayName("지정한 횟수만큼 로또 객체를 배열로 생성한다.")
    void generated_list_length_test() {
        //given
        int times = 10;
        List<Lotto> lottoList = autoGenerateLottoList(times);
        LottoGroup lottoGroup = new LottoGroup(lottoList);

        //when
        int size = lottoGroup.size();
        //then
        assertThat(size).isEqualTo(times);
    }


    List<Lotto> autoGenerateLottoList(int generateTimes) {
        return IntStream.range(0, generateTimes)
                .boxed()
                .map(i -> new Lotto())
                .collect(Collectors.toList());
    }
}
