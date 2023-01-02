package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinningLotto {
    private final List<Integer> lottoNumbers;
    private final Integer bonusNumber;

    public WinningLotto(String lottoString, int bonusNumber) {
        this.lottoNumbers = Stream.of(lottoString.split(", "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        this.lottoNumbers.add(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public LottoScore getScore(Lotto lotto) {
        int matchNumber = (int) lotto.getNumbers().stream().filter(lottoNumbers::contains).count();
        boolean isMatchBonus = lotto.getNumbers().stream().anyMatch(i -> i.equals(bonusNumber));
        return new LottoScore(matchNumber, isMatchBonus);
    }
}
