package aoc22;

import util.InputUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {

    private final List<String> input;
    private final Map<String, Integer> priority = Stream.of(new Object[][] {
            { "a", 1 }, {"A", 27},
            { "b", 2 }, {"B", 28},
            { "c", 3 }, {"C", 29},
            { "d", 4 }, {"D", 30},
            { "e", 5 }, {"E", 31},
            { "f", 6 }, {"F", 32},
            { "g", 7 }, {"G", 33},
            { "h", 8 }, {"H", 34},
            { "i", 9 }, {"I", 35},
            { "j", 10 }, {"J", 36},
            { "k", 11 }, {"K", 37},
            { "l", 12 }, {"L", 38 },
            { "m", 13 }, {"M", 39},
            { "n", 14 }, {"N", 40},
            { "o", 15 }, {"O", 41},
            { "p", 16 }, {"P", 42},
            { "q", 17 }, {"Q", 43},
            { "r", 18 }, {"R", 44},
            { "s", 19 }, {"S", 45},
            { "t", 20 }, {"T", 46},
            { "u", 21 }, {"U", 47},
            { "v", 22 }, {"V", 48},
            { "w", 23 }, {"W", 49},
            { "x", 24 }, {"X", 50},
            { "y", 25 }, {"Y", 51},
            { "z", 26 }, {"Z", 52}
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

    public Day3() {
        input = InputUtil.getInputAsStringList(this.getClass().getSimpleName().toLowerCase() + ".txt");

    }



    public String run1() {
        String firstHalf;
        String secondHalf;
        int sum = 0;
        for (String line : input) {
            firstHalf = line.substring(0, line.length() / 2);
            secondHalf = line.substring(line.length() / 2);
            for (Character c : firstHalf.toCharArray()) {
                if (secondHalf.contains(c.toString())) {
                    sum += priority.get(c.toString());
                    break;
                }
            }
        }


        return "Total sum is " + sum;
    }

    public String run2() {

        Set<Character> badges;
        int sum = 0;
        for (int i = 0; i < input.size(); i += 3) {
            badges = new HashSet<>();
            String line = input.get(i);
            for (char c : line.toCharArray()) {
                badges.add(c);
            }
            for (int j = 0; j < 3; j++) {
                String lines = input.get(i + j);
                for (Object c : badges.toArray()) {
                    if (!lines.contains("" + c)) {
                        badges.remove(c);
                    }
                }
            }
            Character c = badges.stream()
                    .findFirst()
                    .get();
            sum += priority.get(c.toString());
        }
        return "Total score is " + sum;
    }


}
