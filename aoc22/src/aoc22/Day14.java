package aoc22;

import util.InputUtil;

import java.util.*;


public class Day14 {

    private final List<String> input;
    int rightWall = 0;
    int leftWall = 0;
    int bottom = 0;
    int initialSize = 1000;
    final int sandEntryPointX = 500;
    final int sandEntryPointY = 0;

    public Day14() {
        input = InputUtil.getInputAsStringList(this.getClass().getSimpleName().toLowerCase() + ".txt");
    }

    public String run1() {
        int sandUnits = 0;
        boolean rest;
        boolean abyssReached = false;
        char[][] cave = buildCave();

        while(!abyssReached) {
            int sandPositionX = sandEntryPointX;
            int sandPositionY = sandEntryPointY;
            rest = false;

            while(!rest) {

                if(sandPositionX < leftWall || sandPositionX > rightWall || sandPositionY > bottom) {
                    abyssReached = true;
                    break;
                }

                if(cave[sandPositionY + 1][sandPositionX] == '.') {
                    sandPositionY++;
                } else if(cave[sandPositionY + 1][sandPositionX - 1] == '.') {
                    sandPositionY++;
                    sandPositionX--;
                } else if(cave[sandPositionY + 1][sandPositionX + 1] == '.') {
                    sandPositionY++;
                    sandPositionX++;
                } else {
                    rest = true;
                    cave[sandPositionY][sandPositionX] = 'o';
                    sandUnits++;
                }
            }
        }
        return "" +  sandUnits;
    }

    public String run2() { // Couldn't fix this yet. Doesn't work.
       char [][] cave = buildCave();
       int sand = 0;

        for(int x = 0; x < initialSize; x++) {
            cave[bottom + 2][x] = '#';
        }
        bottom += 2;


        var done = false;

        while(!done) {
            int sandPositionX = sandEntryPointX;
            int sandPositionY = sandEntryPointY;
            boolean rest = false;

            while(!rest && !done) {

                if(cave[sandPositionY + 1][sandPositionX] == '.') {
                    sandPositionY++;
                } else if(cave[sandPositionY + 1][sandPositionX - 1] == '.') {
                    sandPositionY++;
                    sandPositionX--;
                } else if(cave[sandPositionY + 1][sandPositionX + 1] == '.') {
                    sandPositionY++;
                    sandPositionX++;
                } else {
                    rest = true;
                    cave[sandPositionY][sandPositionX] = 'o';
                    sand++;

                    if(sandPositionY == 0 && sandPositionX == 500) {
                        done = true;
                    }
                }
            }
        }

        return "" + sand;
    }






    private char[][] buildCave () {
        char[][] cave = new char[initialSize][initialSize];
        for (int i = 0; i < initialSize; i++) {
            for (int j = 0; j < initialSize; j++) {
                cave[i][j] = '.';
            }
        }

        for (var line : input) {
            var coordinates = line.split(" -> ");

            for (int i = 1; i < coordinates.length; i++) {
                var start
                        = Arrays.stream(coordinates[i - 1].split(",")).mapToInt(Integer::parseInt).toArray();
                var end = Arrays.stream(coordinates[i].split(",")).mapToInt(Integer::parseInt).toArray();

                var minX = Math.min(start[0], end[0]);
                var maxX = Math.max(start[0], end[0]);

                var minY = Math.min(start[1], end[1]);
                var maxY = Math.max(start[1], end[1]);

                if (minY > bottom) {
                    bottom = minY;
                }

                if (maxX > rightWall) {
                    rightWall = maxX;
                }

                if (minX < leftWall) {
                    leftWall = minX;
                }

                for (int y = minY; y <= maxY; y++) {
                    for (int x = minX; x <= maxX; x++) {
                        cave[y][x] = '#';
                    }
                }
            }


        }
        return cave;
    }
}