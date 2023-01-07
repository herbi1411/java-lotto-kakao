package service;

import model.LastWeekWinningLotto;
import model.Lotto;
import model.LottoGroup;
import model.Money;

import static constant.LottoConstant.LOTTO_STRING_DELIMITER;

public class LottoJudgeService {

    public LottoResult judge(LottoGroup lottoGroup, String lottoString, int bonusNumber, Money money) {

        Lotto winningLotto = new Lotto(lottoString, LOTTO_STRING_DELIMITER);
        LastWeekWinningLotto lastWeekWinningLotto = new LastWeekWinningLotto(winningLotto, bonusNumber);
        return new LottoResult(lottoGroup, lastWeekWinningLotto, money);
    }
}
