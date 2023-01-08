package model;

import exception.LottoException;
import exception.LottoExceptionCode;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static constant.LottoConstant.*;

public class Lotto {

    private final List<LottoNumber> numberList;

    public Lotto(List<Integer> lottoNumberList) {

        validateLottoNumberListLength(lottoNumberList);
        validateLottoNumberDuplication(lottoNumberList);
        this.numberList = lottoNumberList
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private void validateLottoNumberDuplication(List<Integer> lottoNumberList) {
        if (lottoNumberList.size() != lottoNumberList.stream().distinct().count()) {
            throw new LottoException(LottoExceptionCode.DUPLICATE_LOTTO_NUMBER);
        }
    }

    public Lotto() {

        List<Integer> possibleNumberList = IntStream.range(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(possibleNumberList);

        this.numberList = possibleNumberList
                .subList(0, LOTTO_LENGTH)
                .stream()
                .sorted()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private void validateLottoNumberListLength(List<Integer> lottoNumberList) {
        if (lottoNumberList.size() != LOTTO_LENGTH) {
            throw new LottoException(LottoExceptionCode.INVALID_LOTTO_LENGTH);
        }
    }


    public List<LottoNumber> getNumberList() {
        return Collections.unmodifiableList(this.numberList);
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "numberList=" + numberList +
                '}';
    }
}
