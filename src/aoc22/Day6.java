package aoc22;

import util.InputUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day6 {

    private final String input;

    public Day6() {
        input = InputUtil.getInputAsString(this.getClass().getSimpleName().toLowerCase() + ".txt");
    }

    public String run1 () {
        return "First distinct sequence of four is at " + findFirstDistinct(4);
    }

    public String run2 () {
        return "First distinct sequence of fourteen is at " + findFirstDistinct(14);
    }

    private  int findFirstDistinct(int len) {
        List<Character> chars = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        return IntStream.rangeClosed(len, input.length())
                .filter(i -> new HashSet<>(chars.subList(i - len, i)).size() == len)
                .findFirst()
                .orElse(-1);
    }

}
