package service;

import model.LottoGroup;
import model.LottoResult;
import model.WinningLotto;

import static constant.LottoConstant.LOTTO_TICKET_PRICE;

public class LottoService {
    private final long money;
    private final int times;
    private final LottoGroup lottoGroup;
    private WinningLotto winningLotto;

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
        this.winningLotto = new WinningLotto(lottoString, bonusNumber);
    }

    public void generateLottoResult() {
        this.lottoResult = new LottoResult(lottoGroup, winningLotto, money);
    }

    public Double getEarningRate() {
        return lottoResult.getEarningRate();
    }

    public LottoResult gerLottoResult() {
        return this.lottoResult;
    }
}
