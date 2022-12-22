package aoc22;

import util.InputUtil;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Day12 {
    final List<String> input;

    public Day12() {
        input = InputUtil.getInputAsStringList(this.getClass().getSimpleName().toLowerCase() + ".txt");
    }

    public String run1 () {
        int[][] grid = buildGrid(input);

        return ("Run 1: " + findTrail(grid, start, input.size(), input.get(0).length(), end));

    }
    public String run2 () {
        int result = Integer.MAX_VALUE;
        int[][] grid = buildGrid(input);
        for (final Coordinate s : startCoordinates) {
            final int steps = findTrail(grid, s, input.size(), input.get(0).length(), end);
            if (steps > 0) {
                result = Math.min(result, steps);
            }
        }


        return "Run 2: " + result;
    }

    private static Coordinate start;
    private static Coordinate end;

    private static final List<Coordinate> startCoordinates = new ArrayList<>();

    public enum Course {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    private record Coordinate(int x, int y) {

    }

    private static List<Coordinate> findAdjacent (int[][]grid, Coordinate coordinate, int length, int width) {
        final List<Coordinate> adjacentCoords = new ArrayList<>();
        for (Course d : Course.values()) {
            int x = 0;
            int y = 0;

            switch (d) {
                case NORTH -> {
                    x = coordinate.x - 1;
                    y = coordinate.y;
                    if (x < 0) {
                        continue;
                    }
                }
                case EAST -> {
                    x = coordinate.x;
                    y = coordinate.y + 1;
                    if (y >= width) {
                        continue;
                    }
                }
                case SOUTH -> {
                    x = coordinate.x + 1;
                    y = coordinate.y;
                    if (x >= length) {
                        continue;
                    }
                }
                case WEST -> {
                    x = coordinate.x;
                    y = coordinate.y - 1;
                    if (y < 0) {
                        continue;
                    }
                }
            }

            if (grid[x][y] <= grid[coordinate.x][coordinate.y] + 1) {
                adjacentCoords.add(new Coordinate(x, y));
            }
        }

        return adjacentCoords;
    }

    private static int findTrail(int[][] map, Coordinate startCoordinate, int length, int depth, Coordinate endCoordinate) {
        final Queue<Coordinate> queue = new ArrayDeque<>();
        final Set<Coordinate> explored = new HashSet<>();
        final Map<Coordinate, Coordinate> prevMap = new HashMap<>();

        explored.add(startCoordinate);
        queue.add(startCoordinate);

        while (!queue.isEmpty()) {
            final Coordinate v = queue.poll();
            if (v.equals(endCoordinate)) {
                break;
            }

            final List<Coordinate> neighbours = findAdjacent(map, v, length, depth);
            for (final Coordinate n : neighbours) {
                if (!explored.contains(n)) {
                    explored.add(n);
                    prevMap.put(n, v);
                    queue.add(n);
                }
            }
        }

        return countStepsInTrail(prevMap);
    }

    private static int countStepsInTrail(Map<Coordinate, Coordinate> previousMap) {
        final List<Coordinate> trail = new ArrayList<>();
        Coordinate previous = previousMap.get(end);

        while(previous != null) {
            trail.add(previous);
            previous = previousMap.get(previous);
        }

        return trail.size();
    }


   static int[][] buildGrid(List<String> input) {
        final int[][] grid = new int[input.size()][input.get(0).length()];
        for (int i = 0; input.size() > i; i++) {
            final String row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                char c = row.charAt(j);
                if (c == 'S') {
                    start = new Coordinate(i, j);
                    grid[i][j] = 'a';
                } else if (c == 'E') {
                    end = new Coordinate(i, j);
                    grid[i][j] = 'z';
                } else grid[i][j] = c;

                if (c == 'a') {
                    startCoordinates.add(new Coordinate(i, j));
                }
            }
        }

        return grid;
    }





}