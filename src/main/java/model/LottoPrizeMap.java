package model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoPrizeMap {
    private final Map<LottoPrize, Long> lottoPrizeMap;

    public LottoPrizeMap(List<LottoPrize> lottoPrizes) {
        this.lottoPrizeMap = generateInitialLottoPrizeMap();
        setLottoPrizeMap(lottoPrizes);
    }

    private Map<LottoPrize, Long> generateInitialLottoPrizeMap() {
        LinkedHashMap<LottoPrize, Long> result = new LinkedHashMap<>();
        for (LottoPrize lottoPrize : LottoPrize.values()) {
            result.put(lottoPrize, 0L);
        }
        return result;
    }

    private void setLottoPrizeMap(List<LottoPrize> lottoPrizes) {
        lottoPrizes.forEach(lottoPrize -> lottoPrizeMap.put(lottoPrize, lottoPrizeMap.get(lottoPrize) + 1));
    }

    public long getTotalPrize() {
        long totalPrize = 0L;
        for (LottoPrize lottoPrize : lottoPrizeMap.keySet()) {
            totalPrize += lottoPrize.getPrizeAmount() * lottoPrizeMap.get(lottoPrize);
        }
        return totalPrize;
    }

    public Map<LottoPrize, Long> getLottoPrizeMap() {
        return Collections.unmodifiableMap(lottoPrizeMap);
    }
}
