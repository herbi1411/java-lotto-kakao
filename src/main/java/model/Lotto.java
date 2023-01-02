package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
        Collections.sort(this.lottoNumbers);
    }

    public List<Integer> getNumbers() {
        return lottoNumbers;
    }

    @Override
    public String toString() {
        return "[" + lottoNumbers.stream().map(i -> Integer.toString(i)).collect(Collectors.joining(", ")) + "]";
    }
}
