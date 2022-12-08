package aoc22;

import util.InputUtil;

import java.util.*;

public class Day8 {

    private final List<String> input;
    private static final String VP_WEST = "W";
    private static final String VP_EAST = "E";
    private static final String VP_NORTH = "N";
    private static final String VP_SOUTH = "S";



    public Day8() {
        input = InputUtil.getInputAsStringList(this.getClass().getSimpleName().toLowerCase() + ".txt");
    }
    //1812
    //315495

    public String run1 () {
        var grid = parseInput();
        int visibleCount = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).size(); j++) {
                if (isVisible(grid, i, j, VP_WEST) ||
                        isVisible(grid, i, j, VP_EAST) ||
                        isVisible(grid, i, j, VP_NORTH) ||
                        isVisible(grid, i, j, VP_SOUTH))
                    visibleCount++;
            }
        }
        return "" + visibleCount;
    }

    public String run2 () { // Couldn't fix this yet. Doesn't work.
        var grid = parseInput();
        int score, highScore = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                score = getViewingScore(grid, i, j);
                if (score > highScore)
                    highScore = score;
            }
        }
        return "" + highScore;
    }
    private boolean isVisible (List<List<Integer>> grid, int row, int col, String vantagePoint) {
        if(row == 0 || col == 0 || (row == grid.size() - 1) || (col == grid.get(0).size() - 1)) {
            return true;
        }
        switch (vantagePoint) {
            case VP_WEST -> {
                for (int i = row + 1; i < grid.size(); i++) {
                    if (grid.get(i).get(col) >= grid.get(row).get(col)) {
                        return false;
                    }
                }
            }
            case VP_EAST -> {
                for(int i = row - 1; i >= 0; i--) {
                    if(grid.get(i).get(col) >= grid.get(row).get(col)) {
                        return false;
                    }
                }
            }
            case VP_NORTH -> {
                for(int i = col + 1; i < grid.get(0).size(); i++) {
                    if(grid.get(row).get(i) >= grid.get(row).get(col)) {
                        return false;
                    }
                }
            }
            case VP_SOUTH -> {
                for(int i = col - 1; i >= 0; i--) {
                    if(grid.get(row).get(i) >= grid.get(row).get(col)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private int getViewingScore(List<List<Integer>> grid, int row, int col) {
        int westScore = 0, eastScore = 0, northScore = 0, southScore = 0;
                for (int i = row + 1; i < grid.size(); i++) {
                    if (grid.get(i).get(col) < grid.get(row).get(col))
                        eastScore++;
                    else
                        break;
                }
                for (int i = row - 1; i > 0; i--) {
                    if (grid.get(i).get(col) < grid.get(row).get(col))
                        westScore++;
                    else
                        break;
                }
                for (int i = col + 1; i < grid.get(col).size(); i++) {
                    if (grid.get(row).get(i) < grid.get(row).get(col))
                        northScore++;
                    else
                        break;
                }
                for (int i = col - 1; i > 0; i--) {
                    if (grid.get(row).get(i) < grid.get(row).get(col))
                        southScore++;
                    else
                        break;
                }
        return eastScore * westScore * northScore * southScore;
    }




    private List<List<Integer>> parseInput () {
        List<List<Integer>> grid = new ArrayList<>();
        for (String line : input) {
            List<Integer> list = line.chars().boxed().toList();
            grid.add(list);
        }
        return grid;
    }



    }
