package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGroup {
    private final List<Lotto> lottoGroup;

    public LottoGroup(long repeat) {
        this.lottoGroup = new ArrayList<>();
        for (int i = 0; i < repeat; i++) {
            lottoGroup.add(new Lotto());
        }
    }

    public LottoGroup(List<Lotto> lottoGroup) {
        this.lottoGroup = lottoGroup;
    }

    public LottoGroup(long autoGenerateNumber, List<Lotto> userInputLottoGroup) {
        this.lottoGroup = new ArrayList<>(userInputLottoGroup);
        for (int i = 0; i < autoGenerateNumber; i++) {
            lottoGroup.add(new Lotto());
        }
    }

    public List<Lotto> getLottoGroup() {
        return Collections.unmodifiableList(this.lottoGroup);
    }
}
