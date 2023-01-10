package view;

import constant.LottoConstant;
import model.Lotto;
import model.LottoNumber;
import model.LottoPrize;
import model.LottoPrizeMap;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {
    public void putTimes(long autoGenerateNumber, long manualGenerateNumber) {
        System.out.printf("수동으로 %d개, 자동으로 %d개를 구매했습니다.%n", manualGenerateNumber, autoGenerateNumber);
    }

    public void printLottoGroup(List<Lotto> lottoGroup) {
        lottoGroup.forEach(this::printLotto);
        System.out.println();
    }

    private void printLotto(Lotto lotto) {
        Set<LottoNumber> lottoNumbers = lotto.getNumbers();
        String lottoNumberString = lottoNumbers
                .stream()
                .map(LottoNumber::getNumber)
                .sorted()
                .map(lottoNumberInt -> Integer.toString(lottoNumberInt))
                .collect(Collectors.joining(LottoConstant.LOTTO_STRING_DELIMITER));
        System.out.println("[" + lottoNumberString + "]");
    }

    public void printResult(LottoPrizeMap lottoResult) {
        Map<LottoPrize, Long> lottoPrizeMap = lottoResult.getLottoPrizeMap();

        System.out.println("당첨 통계");
        System.out.println("-------");

        System.out.printf("3개 일치 (5,000원) - %d개%n", lottoPrizeMap.get(LottoPrize.PRIZE_FIFTH));
        System.out.printf("4개 일치 (50,000원) - %d개%n", lottoPrizeMap.get(LottoPrize.PRIZE_FOURTH));
        System.out.printf("5개 일치 (1,500,000원) - %d개%n", lottoPrizeMap.get(LottoPrize.PRIZE_THIRD));
        System.out.printf("5개 일치, 보너스 볼 일치(30,000,000원) - %d개%n", lottoPrizeMap.get(LottoPrize.PRIZE_SECOND));
        System.out.printf("6개 일치 (2,000,000,000원) - %d개%n", lottoPrizeMap.get(LottoPrize.PRIZE_FIRST));
    }

    public void printEarningRate(double earningRate) {
        System.out.printf("총 수익률은 %.2f입니다.\n", earningRate);
    }

    public void printGameEndMessageWithPoorStatus() {
        System.out.println("로또를 구매할 수 없어서 게임을 종료합니다!");
    }
}
