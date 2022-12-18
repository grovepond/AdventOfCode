package aoc22;

import util.InputUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day9 {

    private final List<String> input;
    private static final String _LEFT = "L";
    private static final String _RIGHT = "R";
    private static final String _UP = "U";
    private static final String _DOWN = "D";



    public Day9() {
        input = InputUtil.getInputAsStringList(this.getClass().getSimpleName().toLowerCase() + ".txt");
    }
    //5930
    //2443

    public String run1 () {
        Set<String> visited = new HashSet<>();
        Position head = new Position(0,0);
        Position tail = new Position(0,0);
        visited.add("0|0");
        for (var line : input) {
            var direction = line.substring(0, 1);
            var steps = Integer.parseInt(line.substring(2));
            for (int i = 0; i < steps; i++) {
                move(head, direction);
                if (!isAdjacent(head, tail)) {
                    placeNextTo(head, tail, direction);
                }
                visited.add(tail.getPosition());
            }

        }

        return visited.size() + "";
    }

    public String run2 () { // Couldn't fix this yet. Doesn't work.

        return "";
    }

    boolean isAdjacent (Position head, Position tail) {
        return (Math.abs(head.getX() - tail.getX()) <= 1) && (Math.abs(head.getY() - tail.getY()) <= 1);
    }





    static class Position {
        int x;
        int y;

        public Position(int x, int y) {this.x = x; this.y = y;}

        public String getPosition () {
            return this.x + "|" + this.y;
        }
        public void setCoordinates (int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private void placeNextTo(Position head, Position tail, String direction) {
        switch (direction) {
            case _LEFT -> tail.setCoordinates(head.getX() + 1, head.getY());
            case _RIGHT -> tail.setCoordinates(head.getX() - 1, head.getY());
            case _UP -> tail.setCoordinates(head.getX(), head.getY() - 1);
            case _DOWN -> tail.setCoordinates(head.getX(), head.getY() + 1);
        }

    }

    private void move (Position pos, String direction) {
        switch (direction) {
            case _LEFT -> pos.setX(pos.getX() - 1);
            case _RIGHT -> pos.setX(pos.getX() + 1);
            case _UP -> pos.setY(pos.getY() + 1);
            case _DOWN -> pos.setY(pos.getY() - 1);
        }

    }

}
