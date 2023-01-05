package controller;

import model.Money;
import service.LottoService;
import view.InputView;
import view.OutputView;

import java.util.List;

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
        Long manualLottoInputNumber = inputView.getManualLottoNumber();
        lottoService = new LottoService(money, manualLottoInputNumber);

        List<String> manualLottoGroupInputList = inputView.getManualLottoGroup(manualLottoInputNumber);
        lottoService.setLottoGroupWithUserManualLottoInput(manualLottoGroupInputList);

        long times = lottoService.getTimes();
        if (times < 1) {
            return;
        }

        printLottoGroupStatus(manualLottoInputNumber, times);

        String lottoString = inputView.getLottoString();
        int bonusNumber = inputView.getBonus();
        generateLottoResult(lottoString, bonusNumber);

        printLottoResult();
    }

    private void printLottoGroupStatus(Long manualLottoInputNumber, long times) {
        outputView.putTimes(times - manualLottoInputNumber, manualLottoInputNumber);
        outputView.printLottoGroup(lottoService.getLottoGroup());
    }

    private void generateLottoResult(String lottoString, int bonusNumber) {
        lottoService.createWinningLotto(lottoString, bonusNumber);
        lottoService.generateLottoResult();
    }

    private void printLottoResult() {
        outputView.printResult(lottoService.getLottoResult());
        outputView.printEarningRate(lottoService.getEarningRate());
    }

}
