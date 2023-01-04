package model;

import constant.LottoConstant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {
    @RepeatedTest(100)
    @DisplayName("로또를 생성했을 때 정해진 길이만큼 로또가 생성된다.")
    void lottoGenerateTest() {
        Lotto lotto = new Lotto();
        Set<LottoNumber> generatedNumbers = lotto.getNumbers();
        assertThat(generatedNumbers.size()).isEqualTo(LottoConstant.LOTTO_COUNT);
    }
}
