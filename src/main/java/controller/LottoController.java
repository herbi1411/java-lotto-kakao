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


    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        long money = inputView.inputMoney();
        Lottos lottos = new Lottos(new RandomNumbers());

        int times = lottos.calcTimes(money);
        outputView.putTimes(times);

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
