package model;

import exception.LottoException;
import exception.LottoExceptionCode;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static constant.LottoConstant.*;

public class Lotto {
    private final List<LottoNumber> lottoNumbers;
    private static final List<Integer> possibleLottoNumberList = generatePossibleLottoNumberList();

    public Lotto() {
        this.lottoNumbers = convertIntegerListToLottoNumberList(getLottoIntegerList());
    }

    public Lotto(List<Integer> lottoIntegerList) {
        if (lottoIntegerList.size() != LOTTO_COUNT) {
            throw new LottoException(LottoExceptionCode.INVALID_LOTTO_LENGTH);
        }
        this.lottoNumbers = convertIntegerListToLottoNumberList(lottoIntegerList);
    }

    private List<Integer> getLottoIntegerList() {
        Collections.shuffle(possibleLottoNumberList);
        return possibleLottoNumberList.subList(0, LOTTO_COUNT);
    }

    private List<LottoNumber> convertIntegerListToLottoNumberList(List<Integer> LottoIntegerList) {
        return LottoIntegerList.stream()
                .sorted()
                .map(LottoNumber::of)
                .collect(Collectors.toList());
    }

    private static List<Integer> generatePossibleLottoNumberList() {
        return IntStream.rangeClosed(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
