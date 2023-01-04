package model;

import java.util.List;

public class WinningLottoSet {

    private final Lotto winningLotto;
    private final Integer bonusNumber;

    public WinningLottoSet(Lotto winningLotto, int bonusNumber) {

        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public LottoScore getScore(Lotto lotto) {
        List<LottoNumber> winningLottoNumbers = winningLotto.getNumbers();
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
