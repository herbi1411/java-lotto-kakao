package util;

import exception.LottoException;
import exception.LottoExceptionCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumberStringParser {
    public List<Integer> parse(String source, String delimiter) {
        String[] parsedNumber = source.split(delimiter);
        List<Integer> result;

        try {
            result = Arrays.stream(parsedNumber)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException numberFormatException) {
            throw new LottoException(LottoExceptionCode.INVALID_LOTTO_STRING);
        }

        return result;
    }
}
