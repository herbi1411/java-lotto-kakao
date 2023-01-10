package model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoPrizeMap {
    private final Map<LottoPrize, Long> lottoPrizeMap = generateInitialLottoPrizeMap();

    public LottoPrizeMap(List<LottoPrize> lottoPrizes) {
        lottoPrizes.forEach(lottoPrize -> lottoPrizeMap.put(lottoPrize, lottoPrizeMap.get(lottoPrize) + 1));
    }

    private Map<LottoPrize, Long> generateInitialLottoPrizeMap() {
        LinkedHashMap<LottoPrize, Long> result = new LinkedHashMap<>();
        for (LottoPrize lottoPrize : LottoPrize.values()) {
            result.put(lottoPrize, 0L);
        }
        return result;
    }

    public long getTotalPrize() {
        return lottoPrizeMap.keySet()
                .stream()
                .map(lottoPrize -> lottoPrize.getPrizeAmount() * lottoPrizeMap.get(lottoPrize))
                .reduce(0L, Long::sum);
    }

    public Map<LottoPrize, Long> getLottoPrizeMap() {
        return Collections.unmodifiableMap(lottoPrizeMap);
    }
}
