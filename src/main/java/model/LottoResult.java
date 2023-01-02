package model;

import java.util.List;
import java.util.stream.Collectors;

public class LottoResult {
    private final LottoGroup lottos;
    private final WinningLotto winningLotto;
    private final LottoPrize lottoPrize;

    public LottoResult(LottoGroup lottos, WinningLotto winningLotto) {
        this.lottos = lottos;
        this.winningLotto = winningLotto;
        this.lottoPrize = new LottoPrize();
    }

    private long getTotalPrize() {
        return lottos.getLottoGroup().stream().mapToLong(l -> lottoPrize.getPrize(winningLotto.getScore(l))).sum();
    }

    public double getEarningRate(long money) {
        return (double) getTotalPrize() / money;
    }

    private List<LottoScore> getLottoScores() {
        return lottos.getLottoGroup().stream().map(winningLotto::getScore).collect(Collectors.toList());
    }

    public String getResult() {
        return this.lottoPrize.formatPrizes(getLottoScores());
    }
}
