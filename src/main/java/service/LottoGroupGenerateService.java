package service;

import model.Lotto;
import model.LottoGroup;
import model.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static constant.LottoConstant.LOTTO_STRING_DELIMITER;
import static constant.LottoConstant.LOTTO_TICKET_PRICE;

public class LottoGroupGenerateService {

    public LottoGroup generateLottoGroup(Money money, List<String> manualLottoGroupInputList) {
        long autoGenerateTimes = money.getMoney() / LOTTO_TICKET_PRICE - manualLottoGroupInputList.size();
        return generateLottoGroupWithUserManualLottoInput(autoGenerateTimes, manualLottoGroupInputList);
    }

    private LottoGroup generateLottoGroupWithUserManualLottoInput(long autoGenerateTimes, List<String> manualLottoGroupInputList) {
        List<String> copiedUserInputLottoStringGroup = new ArrayList<>(manualLottoGroupInputList);
        List<Lotto> userInputLottoList = copiedUserInputLottoStringGroup.stream()
                .map(lottoString -> new Lotto(lottoString, LOTTO_STRING_DELIMITER))
                .collect(Collectors.toList());
        return new LottoGroup(autoGenerateTimes, userInputLottoList);
    }
}
