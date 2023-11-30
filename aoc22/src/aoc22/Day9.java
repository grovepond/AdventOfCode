package aoc22;

import util.InputUtil;

import java.util.ArrayList;
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


    public String run1 () { // Couldn't fix this yet. Doesn't work.

        return simulateMotion(2);
    }
    public String run2 () { // Couldn't fix this yet. Doesn't work.

        return simulateMotion(10);
    }
    public String simulateMotion (int numberOfKnots) {
        Set<String> visited = new HashSet<>();
        List<Knot> knots = new ArrayList<>();
        for (int i = 0; i < numberOfKnots; i++) {
            knots.add(new Knot(0,0));
        }



        visited.add("0|0");
        for (var line : input) {
            var direction = line.substring(0, 1);
            var steps = Integer.parseInt(line.substring(2));
            for (int i = 0; i < steps; i++) {
                    move(knots.get(0), direction);
                    for (int j = 1; j < knots.size(); j++) {
                        if (!isAdjacent(knots.get(j), knots.get(j - 1))) {
                            placeNextTo(knots.get(j - 1), knots.get(j));
                            if (knots.get(knots.size() - 1) == knots.get(j))
                                visited.add(knots.get(j).getPosition());
                        } else {
                            break;
                        }

                    }
                }

        }

        return visited.size() + "";
    }



    boolean isAdjacent (Knot head, Knot tail) {
        return (Math.abs(head.getX() - tail.getX()) <= 1) && (Math.abs(head.getY() - tail.getY()) <= 1);
    }





    static class Knot {
        int x;
        int y;

        public Knot(int x, int y) {this.x = x; this.y = y;}

        public String getPosition () {
            return this.x + "|" + this.y;
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

    private void placeNextTo(Knot head, Knot tail) {

        var horizontalDirection = tail.getX() < head.getX() ? _RIGHT : _LEFT;
        var verticalDirection = tail.getY() < head.getY() ? _UP : _DOWN;
        if (tail.getX() == head.getX())
            move(tail, verticalDirection);
        else if (tail.getY() == head.getY())
            move(tail, horizontalDirection);
        else {
            move(tail, horizontalDirection);
            move(tail, verticalDirection);
        }
    }

    private void move (Knot pos, String direction) {
        switch (direction) {
            case _LEFT -> pos.setX(pos.getX() - 1);
            case _RIGHT -> pos.setX(pos.getX() + 1);
            case _UP -> pos.setY(pos.getY() + 1);
            case _DOWN -> pos.setY(pos.getY() - 1);
        }

    }

}
