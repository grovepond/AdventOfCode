package aoc22;

import util.InputUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day5 {

    private final List<String> input;

    private List[] crates;



    public Day5() {
        input = InputUtil.getInputAsStringList(this.getClass().getSimpleName().toLowerCase() + ".txt");
    }

    public String run1 () {
        List<String> lines = parseInput();
        for (String line : lines) {
            String[] split = line.split(" ");

            int numberOfMoves = Integer.parseInt(split[0]);
            int moveFrom = Integer.parseInt(split[2]) - 1;
            int moveTo = Integer.parseInt(split[4]) - 1;

            for (int i = 0; i < numberOfMoves; i++) {
                if (crates[moveFrom].size() > 0) {
                    Object c = crates[moveFrom].remove(crates[moveFrom].size() - 1);
                    crates[moveTo].add(c);
                }
            }
        }
        StringBuilder topCrates = new StringBuilder();
        for (List<Character> crate : crates) {
            topCrates.append(crate.get(crate.size() - 1));
        }

        return "The top crates are " + topCrates;
    }

    public String run2 () {
        List<String> lines = parseInput();
        for (String line : lines) {
            String[] split = line.split(" ");

            int numberOfMoves = Integer.parseInt(split[0]);
            int moveFrom = Integer.parseInt(split[2]) - 1;
            int moveTo = Integer.parseInt(split[4]) - 1;

            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < numberOfMoves; i++) {
                if (crates[moveFrom].size() > 0) {
                    buffer.append(crates[moveFrom].remove(crates[moveFrom].size() - 1));
                }
            }
            buffer.reverse();
            for (char c : buffer.toString().toCharArray()) {
                crates[moveTo].add(c);
            }
        }
        StringBuilder topCrates = new StringBuilder();
        for (List<Character> crate : crates) {
            topCrates.append(crate.get(crate.size() - 1));
        }

        return "Top crates are " + topCrates;

    }




    public List<String> parseInput() {
        List<String> moves = new ArrayList<>();

        int size = 9;

        this.crates = new ArrayList[size];

        for (String line : input) {
            if (line.isBlank()) {
                continue;
            }

            if (line.startsWith("move")) {
                moves.add(line.substring(5));

            } else if (!line.startsWith(" ")) {
                for (int i = 0; i < 9; i++) {
                    char parsedChar = line.charAt(1 + i * 4);

                    if (parsedChar != ' ') {
                        if (crates[i] == null) {
                            crates[i] = new ArrayList<>();
                        }

                        crates[i].add(parsedChar);
                    }
                }
            }
        }
        for (List<Character> crate : crates) {
            Collections.reverse(crate);
        }

        return moves;
    }
}
