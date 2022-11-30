package aoc22;
import util.*;

import java.util.List;

public class Day1 {
    private List<String> inputData;

    public Day1 () {

        inputData = InputUtil.getInputAsStringList(this.getClass().getSimpleName().toLowerCase() + ".txt");

    }

    public void run1 () {

        inputData.stream().forEach(System.out::println);
    }
}
