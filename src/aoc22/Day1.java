package aoc22;
import util.*;

import java.util.*;
import java.util.stream.Collectors;

public class Day1 {
    private final List<String> data;

    public Day1 () {
        data = InputUtil.getInputAsStringList(this.getClass().getSimpleName().toLowerCase() + ".txt");
    }

    public String run1 () {
        Long cals = getElvesSortedDesc().get(0);
        return "The elf with most calories is carrying " + cals + " calories";
    }

    public String run2 () {
        long cals = getElvesSortedDesc()
                .stream()
                .limit(3)
                .reduce(Long::sum).get();

        return "The three elves with most calories are carrying " + cals + " calories together";
    }


    private List<Long> getElvesSortedDesc() {
        List<Long> elves = new ArrayList<>();
        long calories = 0;

        for (String d : data) {
            if (d.isBlank()) {
                elves.add(calories);
                calories = 0;
                continue;
            }
            calories += Long.parseLong(d);
        }
        elves = elves.stream().sorted().collect(Collectors.toList());
        Collections.reverse(elves);
        return elves;
    }
}
