package aoc22;

import util.InputUtil;

import java.util.*;

public class Day3 {

    private final List<String> input;

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
                    sum += getPriority(c);
                    break;
                }
            }
        }


        return "Total sum is " + sum;
    }


    public String run2 () {

        int sum = 0;
        for (int i = 0; i < input.size(); i += 3) {
            for (Character c : input.get(i).toCharArray()) {
                if (input.get(i + 1).contains(c.toString()) && input.get(i + 2).contains(c.toString())) {
                    sum += getPriority(c);
                    break;
                }
            }
         }
        return "Total sum is " + sum;
    }

    private int getPriority(char c) {
        return c >= 'a' && c <= 'z' ? c - 'a' + 1 : c - 'A' + 27;
    }
}
