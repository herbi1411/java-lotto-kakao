package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Collections;
import java.util.List;

public class LottoTest {
    @RepeatedTest(100)
    @DisplayName("랜덤 생성한 숫자를 기준으로 로또를 생성했을 때 정상적으로 생성된다.")
    void generateNumbersTest() {
        RandomNumbers randomNumbers = new RandomNumbers();
        List<Integer> generatedNumbers = randomNumbers.generate();
        Lotto lotto = new Lotto(generatedNumbers);
        Collections.sort(generatedNumbers);
        Assertions.assertThat(lotto.getNumbers()).isEqualTo(generatedNumbers);
    }
}
