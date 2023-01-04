package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static constant.LottoConstant.*;

public class LottoPrize {
    private static final LinkedHashMap<LottoScore, Long> prize = new LinkedHashMap<>() {{
        put(new LottoScore(3, false), LOTTO_5TH_PRIZE);
        put(new LottoScore(4, false), LOTTO_4TH_PRIZE);
        put(new LottoScore(5, false), LOTTO_3RD_PRIZE);
        put(new LottoScore(5, true), LOTTO_2ND_PRIZE);
        put(new LottoScore(6, false), LOTTO_1ST_PRIZE);
    }};

    private String formatPrize(LottoScore lottoScore) {
        if (lottoScore.getMatchNumber() == 5 && lottoScore.isMatchBonus()) {
            return String.format("%d개 일치, 보너스 볼 일치 (%d원) - ", lottoScore.getMatchNumber(), getPrize(lottoScore));
        }
        return String.format("%d개 일치 (%d원) - ", lottoScore.getMatchNumber(), getPrize(lottoScore));
    }

    public Long getPrize(LottoScore lottoScore) {
        if (lottoScore.getMatchNumber() == 5) {
            return prize.getOrDefault(lottoScore, 0L);
        }
        return prize.getOrDefault(new LottoScore(lottoScore.getMatchNumber(), false), 0L);
    }

    public String formatPrizes(List<LottoScore> lottoScores) {
        List<String> prizeResults = new ArrayList<>();
        prize.forEach((lottoScore, prizeMoney) -> {
            long count = lottoScores.stream().filter(l -> l.compare(lottoScore)).count();
            prizeResults.add(formatPrize(lottoScore) + count + "개");
        });

        return String.join("\n", prizeResults);
    }
}
