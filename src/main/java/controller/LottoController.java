package controller;

import model.*;
import service.LottoGroupGenerateService;
import service.LottoRankService;
import service.LottoResultService;
import service.LottoValidateService;
import view.InputView;
import view.OutputView;

import java.util.List;

import static constant.LottoConstant.LOTTO_STRING_DELIMITER;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoValidateService lottoValidateService;
    private final LottoGroupGenerateService lottoGroupGenerateService;


    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoValidateService = new LottoValidateService();
        this.lottoGroupGenerateService = new LottoGroupGenerateService();
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

        LottoResultDto lottoResultDto = getLottoresult(lottoGroup, money);
        printLottoResult(lottoResultDto);
    }

    private void printLottoGroupStatus(List<Lotto> lottoGroup, Long manualLottoInputNumber) {
        outputView.putTimes(lottoGroup.size() - manualLottoInputNumber, manualLottoInputNumber);
        outputView.printLottoGroup(lottoGroup);
    }

    private LottoResultDto getLottoresult(LottoGroup lottoGroup, Money money) {
        String lottoString = inputView.getLottoString();
        int bonusNumber = inputView.getBonus();

        Lotto winningLotto = new Lotto(lottoString, LOTTO_STRING_DELIMITER);
        LottoRankService lottoRankService = new LottoRankService(lottoGroup, winningLotto, bonusNumber);

        List<LottoPrize> lottoPrizes = lottoRankService.calculateScores();
        LottoResultService lottoResultService = new LottoResultService(lottoPrizes, money);

        return lottoResultService.toLottoResultDto();
    }

    private void printLottoResult(LottoResultDto lottoResultDto) {
        outputView.printResult(lottoResultDto.getLottoPrizeMap());
        outputView.printEarningRate(lottoResultDto.getEarningRate());
    }
}
