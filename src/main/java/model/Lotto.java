package model;

import exception.LottoException;
import exception.LottoExceptionCode;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static constant.LottoConstant.*;

public class Lotto {
    private final Set<LottoNumber> lottoNumbers;
    private static final List<Integer> possibleLottoNumberList = generatePossibleLottoNumberList();

    public Lotto() {
        this.lottoNumbers = convertIntegerListToLottoNumberSet(getLottoIntegerList());
    }

    public Lotto(List<Integer> lottoIntegerList) {
        validateLottoLength(lottoIntegerList);
        this.lottoNumbers = convertIntegerListToLottoNumberSet(lottoIntegerList);
    }

    private List<Integer> getLottoIntegerList() {
        Collections.shuffle(possibleLottoNumberList);
        return possibleLottoNumberList.subList(0, LOTTO_COUNT);
    }

    private Set<LottoNumber> convertIntegerListToLottoNumberSet(List<Integer> LottoIntegerList) {
        return LottoIntegerList.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toSet());
    }

    private void validateLottoLength(List<Integer> lottoIntegerList) {
        if (lottoIntegerList.size() != LOTTO_COUNT) {
            throw new LottoException(LottoExceptionCode.INVALID_LOTTO_LENGTH);
        }
    }

    private static List<Integer> generatePossibleLottoNumberList() {
        return IntStream.rangeClosed(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)
                .boxed()
                .collect(Collectors.toList());
    }

    public Set<LottoNumber> getNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public int matchNumber(Lotto other) {
        return (int) this.lottoNumbers.stream()
                .filter(other::contains)
                .count();
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
