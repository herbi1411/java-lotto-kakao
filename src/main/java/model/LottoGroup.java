package model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGroup {
    private final List<Lotto> lottoList;

    public LottoGroup(int repeat) {
        this.lottoList = IntStream.range(0, repeat)
                .boxed()
                .map(i -> new Lotto())
                .collect(Collectors.toList());

    }

    public List<Lotto> getLottoList() {
        return Collections.unmodifiableList(lottoList);
    }
}
