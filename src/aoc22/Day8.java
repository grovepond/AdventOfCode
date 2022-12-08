package aoc22;

import util.InputUtil;

import java.util.*;

public class Day8 {

    private final List<String> input;
    private static final String _LEFT = "L";
    private static final String _RIGHT = "R";
    private static final String _UP = "U";
    private static final String _DOWN = "D";



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
                if (isVisible(grid, i, j, _LEFT) ||
                        isVisible(grid, i, j, _RIGHT) ||
                        isVisible(grid, i, j, _UP) ||
                        isVisible(grid, i, j, _DOWN))
                    visibleCount++;
            }
        }
        return "" + visibleCount;
    }

    public String run2 () { // Couldn't fix this yet. Doesn't work.
        int score, highScore = 0;
        var grid = parseInput();
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                score = getScenicDistance(i, j, grid);
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
            case _LEFT -> {
                for (int i = row + 1; i < grid.size(); i++) {
                    if (grid.get(i).get(col) >= grid.get(row).get(col)) {
                        return false;
                    }
                }
            }
            case _RIGHT -> {
                for(int i = row - 1; i >= 0; i--) {
                    if(grid.get(i).get(col) >= grid.get(row).get(col)) {
                        return false;
                    }
                }
            }
            case _UP -> {
                for(int i = col + 1; i < grid.get(0).size(); i++) {
                    if(grid.get(row).get(i) >= grid.get(row).get(col)) {
                        return false;
                    }
                }
            }
            case _DOWN -> {
                for(int i = col - 1; i >= 0; i--) {
                    if(grid.get(row).get(i) >= grid.get(row).get(col)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }






    private List<List<Integer>> parseInput () {
        List<List<Integer>> grid = new ArrayList<>();
        for (String line : input) {
            List<Integer> list = line.chars().boxed().toList();
            grid.add(list);
        }
        return grid;
    }


    private static int getScenicDistance(int row, int col, List<List<Integer>> grid) {
        if (row == 0 || col == 0) {
            return 0;
        }

        int rowMax = grid.get(col).size() - 1;
        int colMax = grid.size() - 1;

        if (row == rowMax || col == colMax) {
            return 0;
        }

        int height = grid.get(col).get(row);
        int upDistance, downDistance, rightDistance, leftDistance;

        upDistance = row;
        for (int i = row - 1; i >= 0; i--) {
            if (grid.get(col).get(i) >= height) {
                upDistance = row - i;
                break;
            }
        }

        downDistance = rowMax - row;
        for (int i = row + 1; i <= rowMax; i++) {
            if (grid.get(col).get(i) >= height) {
                downDistance = i - row;
                break;
            }
        }


        rightDistance = col;
        for (int i = col - 1; i >= 0; i--) {
            if (grid.get(i).get(row) >= height) {
                rightDistance = col - i;
                break;
            }
        }

        leftDistance = colMax - col;
        for (int i = col + 1; i <= colMax; i++) {
            if (grid.get(i).get(row) >= height) {
                leftDistance = i - col;
                break;
            }
        }

        return upDistance * downDistance * rightDistance * leftDistance;
    }
}
