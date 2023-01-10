package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGroup {
    private final List<Lotto> lottoGroup;

    public LottoGroup(List<Lotto> lottoGroup) {
        this.lottoGroup = lottoGroup;
    }

    public static LottoGroup addAll(LottoGroup group1, LottoGroup group2) {
        List<Lotto> lottoGroup1 = new ArrayList<>(group1.getLottoGroup());
        List<Lotto> lottoGroup2 = group2.getLottoGroup();
        lottoGroup1.addAll(lottoGroup2);
        return new LottoGroup(lottoGroup1);
    }

    public List<Lotto> getLottoGroup() {
        return Collections.unmodifiableList(this.lottoGroup);
    }

    public int size() {
        return this.lottoGroup.size();
    }
}
