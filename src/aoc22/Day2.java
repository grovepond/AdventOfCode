package aoc22;

import util.InputUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 {

    private final List<String> input;

    public Day2 () {
        input = InputUtil.getInputAsStringList(this.getClass().getSimpleName().toLowerCase() + ".txt");

    }

    public String run1() {
        int totalScore = 0;

        for (String line : input) {

            switch (line.charAt(2)) {
                case 'X' -> { //Rock
                    totalScore += 1;
                    switch (line.charAt(0)) {
                        case 'A': //Rock
                            totalScore += 3;
                            break;
                        case 'B': //Paper
                            break;
                        case 'C': //Scissors
                            totalScore += 6;
                            break;
                    }
                }
                case 'Y' -> {//Paper
                    totalScore += 2;
                    switch (line.charAt(0)) {
                        case 'A' -> //Rock
                            totalScore += 6;
                        case 'B' -> //Paper
                            totalScore += 3;
                        case 'C' -> {} //Scissors

                    }
                }
                case 'Z' -> { //Scissors
                    totalScore += 3;
                    switch (line.charAt(0)) {
                        case 'A' -> {} //Rock

                        case 'B' -> //Paper
                                totalScore += 6;
                        case 'C' -> //Scissors
                                totalScore += 3;
                    }
                }
                default -> {
                }
            }
        }
        return "Total score is " + totalScore;
    }

    public String run2 () {

        int totalScore = 0;

        for (String line : input) {
            switch (line.charAt(2)) {
                case 'X' -> { //Lose
                    totalScore += 0;
                    switch (line.charAt(0)) {
                        case 'A' -> //Rock, play scissors
                                totalScore += 3;
                        case 'B' -> //Paper, play rock
                                totalScore += 1;
                        case 'C' -> //Scissors, play paper
                                totalScore += 2;
                    }
                }
                case 'Y' -> { //Draw
                    totalScore += 3;
                    switch (line.charAt(0)) {
                        case 'A' -> //Rock, play rock
                                totalScore += 1;
                        case 'B' -> //Paper, play paper
                                totalScore += 2;
                        case 'C' -> //Scissors, play scissors
                                totalScore += 3;
                    }
                }
                case 'Z' -> { //Win
                    totalScore += 6;
                    switch (line.charAt(0)) {
                        case 'A' -> //Rock, play paper
                                totalScore += 2;
                        case 'B' -> //Paper, play scissors
                                totalScore += 3;
                        case 'C' -> //Scissors, play rock
                                totalScore += 1;
                    }
                }
                default -> {
                }
            }
        }
        return "Total score is " + totalScore;
    }

    public String run1Alt () {


        int totalScore = 0;
        Map<String, Integer> scores = new HashMap<>();
        scores.put("A X", 4);
        scores.put("A Y", 8);
        scores.put("A Z", 3);
        scores.put("B X", 1);
        scores.put("B Y", 5);
        scores.put("B Z", 9);
        scores.put("C X", 7);
        scores.put("C Y", 2);
        scores.put("C Z", 6);

        for (String line : input) {
            totalScore += scores.get(line);
        }
        return "Total score is " + totalScore;
    }

    public String run2Alt () {

        int totalScore = 0;
        Map<String, Integer> scores = new HashMap<>();
        scores.put("A X", 3);
        scores.put("A Y", 4);
        scores.put("A Z", 8);
        scores.put("B X", 1);
        scores.put("B Y", 5);
        scores.put("B Z", 9);
        scores.put("C X", 2);
        scores.put("C Y", 6);
        scores.put("C Z", 7);

        for (String line : input) {
            totalScore += scores.get(line);
        }
        return "Total score is " + totalScore;
    }


}
