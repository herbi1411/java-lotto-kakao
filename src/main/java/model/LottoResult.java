package model;

import java.util.List;
import java.util.stream.Collectors;

public class LottoResult {
    private final Lottos lottos;
    private final WinningLotto winningLotto;
    private final LottoPrize lottoPrize;

    private long getTotalPrize() {
        return lottos.getLottos().stream().mapToLong(l -> lottoPrize.getPrize(winningLotto.getScore(l))).sum();
    }

    private List<LottoScore> getLottoScores() {
        return lottos.getLottos().stream().map(winningLotto::getScore).collect(Collectors.toList());
    }

    public LottoResult(Lottos lottos, WinningLotto winningLotto) {
        this.lottos = lottos;
        this.winningLotto = winningLotto;
        this.lottoPrize = new LottoPrize();
    }

    public double getEarningRate(long money) {
        return (double) getTotalPrize() / money;
    }

    public String getResult() {
        return this.lottoPrize.formatPrizes(getLottoScores());
    }
}
