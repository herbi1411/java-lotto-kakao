package model;

public class LottoResultDto {
    private final LottoPrizeMap lottoPrizeMap;
    private final double earningRate;

    public LottoResultDto(LottoPrizeMap lottoPrizeMap, double earningRate) {
        this.lottoPrizeMap = lottoPrizeMap;
        this.earningRate = earningRate;
    }

    public LottoPrizeMap getLottoPrizeMap() {
        return lottoPrizeMap;
    }

    public double getEarningRate() {
        return earningRate;
    }
}
