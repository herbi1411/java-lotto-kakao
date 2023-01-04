package view;

import constant.LottoConstant;
import model.Lotto;
import model.LottoGroup;
import model.LottoNumber;
import model.LottoResult;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public void putTimes(int times) {
        System.out.println(times + "개를 구매했습니다.");
    }

    public void printLottoGroup(LottoGroup lottoGroup) {
        List<Lotto> lottoList = lottoGroup.getLottoGroup();
        lottoList.forEach(this::printLotto);
        System.out.println();
    }

    private void printLotto(Lotto lotto) {
        List<LottoNumber> lottoNumbers = lotto.getNumbers();
        String lottoNumberString = lottoNumbers
                .stream()
                .map(lottoNumber -> Integer.toString(lottoNumber.getNumber()))
                .collect(Collectors.joining(LottoConstant.LOTTO_NUMBER_DELIMITER));
        System.out.println("[" + lottoNumberString + "]");
    }

    public void printResult(LottoResult lottoResult) {
        System.out.println("당첨 통계");
        System.out.println("-------");
        System.out.println(lottoResult.getResult() + "\n");
    }

    public void printEarningRate(double earningRate) {
        System.out.printf("총 수익률은 %.2f입니다.\n", earningRate);
        if (earningRate < 1.0) {
            System.out.println("손해!");
            return;
        }
        System.out.println("이득!");
    }
}
