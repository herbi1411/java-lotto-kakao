package controller;

import model.LottoResult;
import model.Lottos;
import model.RandomNumbers;
import model.WinningLotto;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    private static final int LOTTO_COST = 1000;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        long money = inputView.inputMoney();
        int times = (int) (money / LOTTO_COST);
        outputView.putTimes(times);

        Lottos lottos = new Lottos(new RandomNumbers());
        lottos.generate(times);

        outputView.printLottos(lottos);

        String lottoString = inputView.getLottoString();
        int bonus = inputView.getBonus();

        WinningLotto winningLotto = new WinningLotto(lottoString, bonus);
        LottoResult lottoResult = new LottoResult(lottos, winningLotto);

        outputView.printResult(lottoResult);
        outputView.printEarningRate(lottoResult.getEarningRate(money));
    }
}
