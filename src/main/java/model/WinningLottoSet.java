package model;

import exception.LottoException;
import exception.LottoExceptionCode;

import java.util.Set;

public class WinningLottoSet {

    private final Lotto winningLotto;
    private final Integer bonusNumber;

    public WinningLottoSet(Lotto winningLotto, int bonusNumber) {

        validateWinningLottoSet(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateWinningLottoSet(Lotto winningLotto, int bonusNumber) {
        Set<LottoNumber> lottoNumbers = winningLotto.getNumbers();
        LottoNumber wrappedBonusNumber = LottoNumber.from(bonusNumber);

        if (lottoNumbers.contains(wrappedBonusNumber)) {
            throw new LottoException(LottoExceptionCode.WINNING_LOTTO_SET_DUPLICATE);
        }
    }

    public LottoScore getScore(Lotto lotto) {
        Set<LottoNumber> winningLottoNumbers = winningLotto.getNumbers();
        int matchNumber = (int) lotto.getNumbers()
                .stream()
                .filter(winningLottoNumbers::contains)
                .count();

        boolean isMatchBonus = lotto.getNumbers().
                stream().
                anyMatch(lottoNumber -> lottoNumber.getNumber() == bonusNumber);

        return new LottoScore(matchNumber, isMatchBonus);
    }
}
