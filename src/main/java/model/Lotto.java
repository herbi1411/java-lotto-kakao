package model;

import exception.LottoException;
import exception.LottoExceptionCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static model.LottoConstant.*;

public class Lotto {
    private final List<LottoNumber> lottoNumbers;

    public Lotto() {
        this.lottoNumbers = convertIntegerListToLottoNumberList(generateLottoIntegerList());
    }

    public Lotto(List<Integer> lottoIntegerList) {
        if (lottoIntegerList.size() != LOTTO_COUNT) {
            throw new LottoException(LottoExceptionCode.INVALID_LOTTO_LENGTH);
        }
        this.lottoNumbers = convertIntegerListToLottoNumberList(lottoIntegerList);
    }

    private List<Integer> generateLottoIntegerList() {
        List<Integer> temp =  IntStream.rangeClosed(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)
                .boxed()
                .collect(Collectors.toList())
                .subList(0, LOTTO_COUNT);
        Collections.shuffle(temp);
        return temp;
    }

    private List<LottoNumber> convertIntegerListToLottoNumberList(List<Integer> integerList) {
        List<LottoNumber> lottoNumberList = integerList.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        Collections.sort(lottoNumberList);
        return lottoNumberList;
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

//    @Override
//    public String toString() {
//        return "[" + lottoNumbers.stream().map(i -> Integer.toString(i)).collect(Collectors.joining(", ")) + "]";
//    }
}
