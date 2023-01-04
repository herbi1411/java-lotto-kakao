package model;

import java.util.List;
import java.util.stream.Collectors;

public class LottoResult {
    private final LottoGroup lottoGroup;
    private final WinningLottoSet winningLotto;
    private final LottoPrize lottoPrize;
    private final double earningRate;

    public LottoResult(LottoGroup lottoGroup, WinningLottoSet winningLotto, long money) {
        this.lottoPrize = new LottoPrize();
        this.lottoGroup = lottoGroup;
        this.winningLotto = winningLotto;
        this.earningRate = (double) getTotalPrize() / money;
    }

    private long getTotalPrize() {
        return lottoGroup.getLottoGroup()
                .stream()
                .mapToLong(lotto -> lottoPrize.getPrize(winningLotto.getScore(lotto)))
                .sum();
    }

    public double getEarningRate() {
        return this.earningRate;
    }

    private List<LottoScore> getLottoScores() {
        return lottoGroup.getLottoGroup().stream().map(winningLotto::getScore).collect(Collectors.toList());
    }

    public String getResult() {
        return this.lottoPrize.formatPrizes(getLottoScores());
    }
}
