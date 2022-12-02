package aoc22;

import util.InputUtil;

import java.util.List;

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

    /*
    private int calculateRowScore (String line) {
        int ownHandValue = 0;
        String ownHand = line.split(" ")[1];

        switch (ownHand) {
            case "X":
                ownHandValue = 1;
                break;
            case "Y":
                ownHandValue = 2;
                break;
            case "Z":
                ownHandValue = 3;
                break;
            default:
                break;
        }

        return winningHands.contains(line) ? SCORE_WIN + ownHandValue : (losingHands.contains(line) ? SCORE_LOSS + ownHandValue : SCORE_DRAW + ownHandValue);

    }
    */

}
