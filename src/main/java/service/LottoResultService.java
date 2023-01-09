package service;

import model.LottoPrize;
import model.LottoPrizeMap;
import model.LottoResultDto;
import model.Money;

import java.util.List;

public class LottoResultService {
    private final LottoPrizeMap lottoPrizeMap;
    private final double earningRate;

    public LottoResultService(List<LottoPrize> lottoPrizes, Money money) {
        this.lottoPrizeMap = new LottoPrizeMap(lottoPrizes);
        this.earningRate = (double) getTotalPrize() / money.getMoney();
    }

    private long getTotalPrize() {
        return lottoPrizeMap.getTotalPrize();
    }

    public LottoResultDto toLottoResultDto() {
        return new LottoResultDto(lottoPrizeMap, earningRate);
    }
}
