package util;

import constant.LottoConstant;
import exception.LottoException;
import exception.LottoExceptionCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberStringParserTest {

    private LottoNumberStringParser lottoNumberStringParser;

    @BeforeEach
    void setUp() {
        this.lottoNumberStringParser = new LottoNumberStringParser();
    }

    @ParameterizedTest
    @MethodSource("normalNumberStringData")
    @DisplayName("올바른 형태의 문자열을 파싱할 수 있다.")
    void normal_number_string_success(String source, String delimiter, List<Integer> expected) {
        //given

        //when
        List<Integer> result = lottoNumberStringParser.parse(source, delimiter);

        //then
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> normalNumberStringData() {
        return Stream.of(
                Arguments.of("1, 2, 3, 4, 5, 6", LottoConstant.LOTTO_STRING_DELIMITER, List.of(1, 2, 3, 4, 5, 6)),
                Arguments.of("1:2:3:4:5:6", ":", List.of(1, 2, 3, 4, 5, 6))
        );
    }

    @ParameterizedTest
    @MethodSource("abnormalNumberStringData")
    @DisplayName("문자열을 숫자로 변환할 수 없는 경우 오류가 발생한다.")
    void abnormal_number_string_success(String source, String delimiter) {
        //given
        //when

        //then
        assertThatThrownBy(
                () -> lottoNumberStringParser.parse(source, delimiter))
                .isInstanceOf(LottoException.class)
                .hasMessage(LottoExceptionCode.INVALID_LOTTO_STRING.getMessage());
    }

    static Stream<Arguments> abnormalNumberStringData() {
        return Stream.of(
                Arguments.of("1, a, 3, 4, 5, 6", LottoConstant.LOTTO_STRING_DELIMITER),
                Arguments.of("1:2:3:4:5:,6", ":"),
                Arguments.of("", ":")
        );
    }
}
