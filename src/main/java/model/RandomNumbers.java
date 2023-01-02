package model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumbers {
    private static final Integer MIN_RANGE = 1;
    private static final Integer MAX_RANGE = 45;
    private static final Integer COUNT = 6;
    private final List<Integer> intPool;

    public RandomNumbers() {
        intPool = IntStream.rangeClosed(MIN_RANGE, MAX_RANGE)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<Integer> generate() {
        Collections.shuffle(intPool);
        return intPool.subList(0, COUNT);
    }

}