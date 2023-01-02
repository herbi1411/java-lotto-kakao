package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinningLotto {
    private static final String LOTTO_NUMBER_DELIMITER = ", ";
    private final Lotto winningLotto;
    private final Integer bonusNumber;

    public WinningLotto(String lottoString, int bonusNumber) {
        List<Integer> parsedWinningLotto = Stream.of(lottoString.split(LOTTO_NUMBER_DELIMITER))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        this.winningLotto = new Lotto(parsedWinningLotto);
        this.bonusNumber = bonusNumber;
    }

    public LottoScore getScore(Lotto lotto) {
        int matchNumber = (int) lotto.getNumbers().stream().filter(winningLotto::contains).count();
        boolean isMatchBonus = lotto.getNumbers().stream().anyMatch(i -> i.equals(bonusNumber));
        return new LottoScore(matchNumber, isMatchBonus);
    }
}
