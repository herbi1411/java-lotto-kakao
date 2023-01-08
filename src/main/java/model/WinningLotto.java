package model;

import exception.LottoException;
import exception.LottoExceptionCode;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {

        validateDuplicateBonusNumber(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicateBonusNumber(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.getNumberList().contains(bonusNumber)) {
            throw new LottoException(LottoExceptionCode.DUPLICATE_BONUS_NUMBER);
        }
    }


    public LottoScore calculateScore(Lotto ticket) {
        int matchNumber = (int) lotto.getNumberList()
                .stream()
                .filter(lottoNumber -> ticket.getNumberList().contains(lottoNumber))
                .count();

        boolean isMatchBonusNumber = ticket.getNumberList().contains(bonusNumber);

        return new LottoScore(matchNumber, isMatchBonusNumber);
    }
}
