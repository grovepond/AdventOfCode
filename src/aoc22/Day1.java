package aoc22;
import util.*;

import java.util.*;
import java.util.stream.Collectors;

public class Day1 {
    //private final List<String> data;
    private final String input;

    public Day1 () {
        //input = InputUtil.getInputAsStringList(this.getClass().getSimpleName().toLowerCase() + ".txt");
        input = InputUtil.getInputAsString(this.getClass().getSimpleName().toLowerCase() + ".txt");
    }

    public String run1 () {
        Long maxCalories = getElfCaloriesSortedDesc(input).get(0);
        return "The elf with most calories is carrying " + maxCalories + " calories";
    }

    public String run2 () {
        long cals = getElfCaloriesSortedDesc(input)
                .stream()
                .limit(3)
                .reduce(Long::sum).get();

        return "The three elves with most calories are carrying " + cals + " calories together";
    }


    private List<Long> getElfCaloriesSortedDesc(List<String> input) {
        List<Long> elves = new ArrayList<>();
        long calories = 0;

        for (String d : input) {
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

    private List<Long> getElfCaloriesSortedDesc(String input) {
        List<Long> bags = new ArrayList<>();
        List<String> elves = Arrays.stream(input.split("\n\n")).toList();

        for (String e : elves) {
            bags.add(Arrays.stream(e.split("\n"))
                    .mapToLong(Long::parseLong)
                    .sum());
        }
        bags = bags.stream().sorted().collect(Collectors.toList());
        Collections.reverse(bags);
        return bags;
    }
}
