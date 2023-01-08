package service;

import model.LastWeekWinningLotto;
import model.LottoGroup;
import model.LottoPrize;
import model.Money;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {
    private final Map<LottoPrize, Long> lottoPrizeMap;
    private final double earningRate;

    public LottoResult(LottoGroup lottoGroup, LastWeekWinningLotto lastWeekWinningLotto, Money money) {

        this.lottoPrizeMap = generateInitialLottoPrizeMap();
        setLottoPrizeMap(lottoGroup, lastWeekWinningLotto);

        this.earningRate = (double) getTotalPrize() / money.getMoney();
    }

    private void setLottoPrizeMap(LottoGroup lottoGroup, LastWeekWinningLotto lastWeekWinningLotto) {
        lottoGroup.getLottoGroup()
                .stream()
                .map(lastWeekWinningLotto::calculateScore)
                .map(lottoScore -> LottoPrize.valueOf(lottoScore.getMatchNumber(), lottoScore.isMatchBonus()))
                .forEach(lottoPrize -> lottoPrizeMap.put(lottoPrize, lottoPrizeMap.get(lottoPrize) + 1));
    }

    private Map<LottoPrize, Long> generateInitialLottoPrizeMap() {
        LinkedHashMap<LottoPrize, Long> result = new LinkedHashMap<>();
        for (LottoPrize lottoPrize : LottoPrize.values()) {
            result.put(lottoPrize, 0L);
        }
        return result;
    }

    private long getTotalPrize() {
        long totalPrize = 0L;
        for (LottoPrize lottoPrize : lottoPrizeMap.keySet()) {
            totalPrize += lottoPrize.getPrizeAmount() * lottoPrizeMap.get(lottoPrize);
        }
        return totalPrize;
    }

    public double getEarningRate() {
        return this.earningRate;
    }

    public Map<LottoPrize, Long> getLottoPrizeMap() {
        return Collections.unmodifiableMap(this.lottoPrizeMap);
    }
}
