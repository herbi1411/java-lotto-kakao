package model;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public List<Integer> getNumbers() {
        return lottoNumbers;
    }
}
