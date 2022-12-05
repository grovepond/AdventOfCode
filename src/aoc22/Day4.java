package aoc22;

import util.InputUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day4 {

    private final List<String> input;

    public Day4() {
        input = InputUtil.getInputAsStringList(this.getClass().getSimpleName().toLowerCase() + ".txt");

    }



    public String run1() {

        int sum = 0;
        for (String line : input) {
            String [] pair = line.split(",");
            String first = pair [0];
            String second = pair [1];
            String [] firstRange = first.split("-");
            String [] secondRange = second.split("-");


            if (Integer.parseInt(firstRange[0]) >= Integer.parseInt(secondRange[0]) &&
                    (Integer.parseInt(firstRange[1]) <= Integer.parseInt(secondRange[1]))) {
                sum++;
            } else if (Integer.parseInt(secondRange[0]) >= Integer.parseInt(firstRange[0]) &&
                (Integer.parseInt(secondRange[1]) <= Integer.parseInt(firstRange[1])))
                    sum ++;

        }


        return "Total sum is " + sum;
    }


    public String run2 () {
        int sum = 0;
        for (String line : input) {
            String[] pair = line.split(",");
            String first = pair[0];
            String second = pair[1];
            String[] firstRange = first.split("-");
            String[] secondRange = second.split("-");

            if (Integer.parseInt(secondRange[0]) <= Integer.parseInt(firstRange[1]) &&
                    (Integer.parseInt(secondRange[1]) >= Integer.parseInt(firstRange[0]))) {
                sum++;
            } else if (Integer.parseInt(firstRange[0]) <= Integer.parseInt(secondRange[1]) &&
                    (Integer.parseInt(firstRange[1]) >= Integer.parseInt(secondRange[0])))
                sum++;
        }
        return "Total sum is " + sum;

    }
}
