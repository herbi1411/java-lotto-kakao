package service;

import model.Lotto;
import model.LottoGroup;
import model.LottoResult;
import model.WinningLottoSet;

import static constant.LottoConstant.LOTTO_NUMBER_DELIMITER;
import static constant.LottoConstant.LOTTO_TICKET_PRICE;

public class LottoService {
    private final long money;
    private final int times;
    private final LottoGroup lottoGroup;
    private WinningLottoSet winningLottoSet;

    private LottoResult lottoResult;

    public LottoService(long money) {
        this.money = money;
        this.times = (int) (money / LOTTO_TICKET_PRICE);
        this.lottoGroup = new LottoGroup(this.times);
    }

    public int getTimes() {
        return this.times;
    }

    public LottoGroup getLottoGroup() {
        return this.lottoGroup;
    }

    public void createWinningLotto(String lottoString, int bonusNumber) {
        Lotto winningLotto = new Lotto(lottoString, LOTTO_NUMBER_DELIMITER);
        this.winningLottoSet = new WinningLottoSet(winningLotto, bonusNumber);
    }

    public void generateLottoResult() {
        this.lottoResult = new LottoResult(lottoGroup, winningLottoSet, money);
    }

    public Double getEarningRate() {
        return lottoResult.getEarningRate();
    }

    public LottoResult gerLottoResult() {
        return this.lottoResult;
    }
}
