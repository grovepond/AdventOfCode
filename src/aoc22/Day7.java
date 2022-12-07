package aoc22;

import util.InputUtil;

import java.util.*;
public class Day7 {

    private final List<String> input;


    public Day7() {
        input = InputUtil.getInputAsStringList(this.getClass().getSimpleName().toLowerCase() + ".txt");
    }


    public String run1 () {
        Map<String, Long> dirs = parseInput();
        long result = dirs.values()
                .stream()
                .filter(aLong -> aLong <= 100000)
                .mapToLong(l -> l)
                .sum();

        return "" + result;
    }

    public String run2 () {


        Map<String, Long> dirs = parseInput();
        long totalOccupiedSpace = input
                .stream()
                .filter(x -> Character.isDigit(x.charAt(0)))
                .map(s -> s.split(" ")[0])
                .mapToLong(Long::parseLong)
                .sum();


        long result = dirs.values()
                .stream()
                .filter(l -> l >= totalOccupiedSpace - 40000000)
                .mapToLong(l -> l)
                .min()
                .orElseThrow();

        return "" + result;
    }
    private  Map<String, Long> parseInput () {
        Map<String, Long> dirs = new HashMap<>();
        var paths = new ArrayList<String>();

        for (var line : input) {
            if (line.equals("$ cd ..")) {
                paths.remove(paths.size() - 1);
            } else if (line.startsWith("$ cd")) {
                var dirName = line.substring(5);
                paths.add(paths.isEmpty() ? dirName : paths.get(paths.size() - 1) + dirName + "/");
            } else if (!line.startsWith("$") && !line.startsWith("dir")) {
                paths.forEach(p -> dirs.put(p, Long.valueOf(line.split(" ")[0]) + (dirs.get(p) != null ? dirs.get(p) : 0)));
            }
        }
        return dirs;
    }

}
