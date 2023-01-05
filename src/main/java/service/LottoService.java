package service;

import exception.LottoException;
import exception.LottoExceptionCode;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static constant.LottoConstant.LOTTO_NUMBER_DELIMITER;
import static constant.LottoConstant.LOTTO_TICKET_PRICE;

public class LottoService {
    private final Money money;
    private final long times;
    private final long manualLottoInputNumber;
    private LottoGroup lottoGroup;
    private WinningLottoSet winningLottoSet;
    private LottoResult lottoResult;

    public LottoService(Money money, long manualLottoInputNumber) {
        this.money = money;
        this.times = money.getMoney() / LOTTO_TICKET_PRICE;
        this.manualLottoInputNumber = manualLottoInputNumber;

        validateManualTimes();
        this.lottoGroup = new LottoGroup(this.times);
    }

    private void validateManualTimes() {
        if (this.times < manualLottoInputNumber) {
            throw new LottoException(LottoExceptionCode.INVALID_MANUAL_LOTTO_COUNT);
        }
    }

    public void setUserInputLottoGroup(List<String> userInputLottoStringGroup) {
        List<String> copiedUserInputLottoStringGroup = new ArrayList<>(userInputLottoStringGroup);
        List<Lotto> userInputLottoGroup = copiedUserInputLottoStringGroup.stream()
                .map(lottoString -> new Lotto(lottoString, LOTTO_NUMBER_DELIMITER))
                .collect(Collectors.toList());
        this.lottoGroup = new LottoGroup(times - manualLottoInputNumber, userInputLottoGroup);
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

    public LottoGroup getLottoGroup() {
        return this.lottoGroup;
    }

    public LottoResult getLottoResult() {
        return this.lottoResult;
    }

    public long getTimes() {
        return this.times;
    }
}
