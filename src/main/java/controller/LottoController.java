package controller;

import model.Money;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    private LottoService lottoService;


    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        Money money = new Money(inputView.inputMoney());
        lottoService = new LottoService(money);

        long times = lottoService.getTimes();
        outputView.putTimes(times);

        if (times < 1) {
            return;
        }

        outputView.printLottoGroup(lottoService.getLottoGroup());

        String lottoString = inputView.getLottoString();
        int bonusNumber = inputView.getBonus();

        lottoService.createWinningLotto(lottoString, bonusNumber);
        lottoService.generateLottoResult();

        outputView.printResult(lottoService.gerLottoResult());
        outputView.printEarningRate(lottoService.getEarningRate());
    }
}
