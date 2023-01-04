package model;

public class WinningLottoSet {

    private final Lotto winningLotto;
    private final Integer bonusNumber;

    public WinningLottoSet(Lotto winningLotto, int bonusNumber) {

        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public LottoScore getScore(Lotto lotto) {
        int matchNumber = (int) lotto.getNumbers()
                .stream()
                .filter(number -> winningLotto.getNumbers().contains(number))
                .count();

        boolean isMatchBonus = lotto.getNumbers().
                stream().
                anyMatch(lottoNumber -> lottoNumber.getNumber() == bonusNumber);

        return new LottoScore(matchNumber, isMatchBonus);
    }
}
