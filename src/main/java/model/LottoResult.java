package model;

public class LottoResult {
    private final Lottos lottos;
    private final WinningLotto winningLotto;
    private final LottoPrize lottoPrize;

    public LottoResult(Lottos lottos, WinningLotto winningLotto) {
        this.lottos = lottos;
        this.winningLotto = winningLotto;
        this.lottoPrize = new LottoPrize();
    }

    private long getTotalPrize() {
        return lottos.getLottos().stream().mapToLong(l -> lottoPrize.getPrize(winningLotto.getScore(l))).sum();
    }

    public double getEarningRate(long money) {
        return (double) getTotalPrize() / money;
    }
}
