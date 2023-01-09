package service;

import exception.LottoException;
import exception.LottoExceptionCode;
import model.Lotto;
import model.LottoGroup;
import model.LottoPrize;

import java.util.ArrayList;
import java.util.List;

public class LottoRankService {

    private final Lotto winningLotto;
    private final Integer bonusNumber;
    private final LottoGroup lottoGroup;

    public LottoRankService(LottoGroup lottoGroup, Lotto winningLotto, int bonusNumber) {
        validateWinningLotto(winningLotto, bonusNumber);
        this.lottoGroup = lottoGroup;
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateWinningLotto(Lotto winningLotto, int bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new LottoException(LottoExceptionCode.WINNING_LOTTO_SET_DUPLICATE);
        }
    }

    public List<LottoPrize> calculateScores() {
        ArrayList<LottoPrize> lottoPrizes = new ArrayList<>();
        for (Lotto lotto : lottoGroup.getLottoGroup()) {
            int matchNumber = winningLotto.matchNumber(lotto);
            boolean isMatchBonus = lotto.contains(bonusNumber);
            lottoPrizes.add(LottoPrize.valueOf(matchNumber, isMatchBonus));
        }

        return lottoPrizes;
    }

}
