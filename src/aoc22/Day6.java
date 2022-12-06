package aoc22;

import util.InputUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Day6 {

    private final String input;





    public Day6() {
        input = InputUtil.getInputAsString(this.getClass().getSimpleName().toLowerCase() + ".txt");
    }

    public String run1 () {


        return "First distinct sequence of four is at " + findDistinct(4);

    }

    public String run2 () {

        return "First distinct sequence of four is at " + findDistinct(14);

    }



    private int findDistinct (int length) {
        char [] chars = input.toCharArray();
        int ix = 0;

        Set<Character> set = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            for (int j = ix; j < chars.length; j++) {
                if (set.contains(chars[j])) {
                    set.clear();
                    ix++;
                    break;
                } else {
                    set.add(chars[j]);
                    if (length == set.size())
                        return (j + 1);
                }
            }
        }
        return 0;
    }

}
