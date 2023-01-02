package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGroup {
    private final List<Lotto> lottoList;

    public LottoGroup(int repeat) {
        this.lottoList = new ArrayList<>();
        for (int i = 0; i < repeat; i++) {
            lottoList.add(new Lotto());
        }
    }
    public List<Lotto> getLottoGroup() {
        return Collections.unmodifiableList(this.lottoList);
    }

//    @Override
//    public String toString() {
//        return lottoList.stream().map(Lotto::toString).collect(Collectors.joining("\n"));
//    }
}
