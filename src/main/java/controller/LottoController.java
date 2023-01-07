package controller;

import model.Lotto;
import model.LottoGroup;
import model.Money;
import service.LottoGroupGenerateService;
import service.LottoJudgeService;
import service.LottoResult;
import service.LottoValidateService;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoValidateService lottoValidateService;
    private final LottoGroupGenerateService lottoGroupGenerateService;
    private final LottoJudgeService lottoJudgeService;


    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoValidateService = new LottoValidateService();
        this.lottoGroupGenerateService = new LottoGroupGenerateService();
        this.lottoJudgeService = new LottoJudgeService();
    }

    public void run() {
        Money money = new Money(inputView.inputMoney());
        if (!lottoValidateService.canProceedGameWithInputMoney(money)) {
            return;
        }

        Long manualLottoInputNumber = inputView.inputManualLottoNumber();
        lottoValidateService.validateManualInputNumber(money, manualLottoInputNumber);

        LottoGroup lottoGroup = lottoGroupGenerateService.generateLottoGroup(money, inputView.getManualLottoGroup(manualLottoInputNumber));
        printLottoGroupStatus(lottoGroup.getLottoGroup(), manualLottoInputNumber);

        LottoResult lottoResult = getLottoresult(lottoGroup, money);
        printLottoResult(lottoResult);
    }

    private void printLottoGroupStatus(List<Lotto> lottoGroup, Long manualLottoInputNumber) {
        outputView.putTimes(lottoGroup.size() - manualLottoInputNumber, manualLottoInputNumber);
        outputView.printLottoGroup(lottoGroup);
    }

    private LottoResult getLottoresult(LottoGroup lottoGroup, Money money) {
        String lottoString = inputView.getLottoString();
        int bonusNumber = inputView.getBonus();
        return lottoJudgeService.judge(lottoGroup, lottoString, bonusNumber, money);
    }

    private void printLottoResult(LottoResult lottoResult) {
        outputView.printResult(lottoResult);
        outputView.printEarningRate(lottoResult.getEarningRate());
    }
}
