package model;

import constant.LottoConstant;
import exception.LottoException;
import exception.LottoExceptionCode;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private final List<LottoNumber> numberList;

    public Lotto(List<Integer> lottoNumberList) {

        validateLottoNumberListLength(lottoNumberList);
        this.numberList = lottoNumberList
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private void validateLottoNumberListLength(List<Integer> lottoNumberList) {
        if (lottoNumberList.size() != LottoConstant.LOTTO_LENGTH) {
            throw new LottoException(LottoExceptionCode.INVALID_LOTTO_LENGTH);
        }
    }


    public List<LottoNumber> getNumberList() {
        return Collections.unmodifiableList(this.numberList);
    }
}
