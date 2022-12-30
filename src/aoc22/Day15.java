package aoc22;

import util.InputUtil;

import java.util.*;

public class Day15 {

    private final List<String> input;
    private final int scanRow = 2000000;

    public Day15() {
        input = InputUtil.getInputAsStringList(this.getClass().getSimpleName().toLowerCase() + ".txt");
    }

    public String run1 () {
        var sensors = parseInput();
        return ("Covered: " + findCoveredPointsOnRow(sensors).size());
    }

    public String run2 () {

        return "";
    }

    private List<Sensor> parseInput() {
        List<Sensor> sensors = new ArrayList<>();
        int sx, sy, bx, by;

        for (var line : input) {

            sx = Integer.parseInt(line.split(":")[0].split(",")[0].split("=")[1]);
            sy = Integer.parseInt(line.split(":")[0].split(",")[1].split("=")[1]);
            bx = Integer.parseInt(line.split(":")[1].split(",")[0].split("=")[1]);
            by = Integer.parseInt(line.split(":")[1].split(",")[1].split("=")[1]);
            var sensor = new Sensor(sx, sy, bx, by);
            sensors.add(sensor);
        }

        return sensors;
    }

    static class Coordinate {
        int x, y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate coordinate = (Coordinate) o;
            return x == coordinate.x && y == coordinate.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Coord{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    class Sensor {
        Coordinate coordinate;
        int distanceToClosestBeacon;
        int offsetXOnY;

        Set<Coordinate> covered;
        public Sensor (int x, int y, int bx, int by) {
            this.coordinate = new Coordinate(x, y);
            this.coordinate.x = x;
            this.coordinate.y = y;
            this.distanceToClosestBeacon = getDistanceToCoord(bx, by);
            this.offsetXOnY = this.distanceToClosestBeacon - Math.abs(this.coordinate.y - scanRow);
            this.covered = calculateCovered();
        }

        Set<Coordinate> calculateCovered() {
            Set<Coordinate> coordinates = new HashSet<>();
            for (int i = this.coordinate.x - this.offsetXOnY; i < this.coordinate.x + this.offsetXOnY; i++) {
                        coordinates.add(new Coordinate(i, scanRow));
                }

            return coordinates;
        }

        public int getDistanceToCoord(int x, int y) {
            return Math.abs(this.coordinate.x - x) + Math.abs(this.coordinate.y - y);
        }

        @Override
        public String toString() {
            return "Sensor{" +
                    "point=" + coordinate +
                    ", distanceToClosestBeacon=" + distanceToClosestBeacon +
                    ", coveredForRow=" + offsetXOnY +
                    '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(distanceToClosestBeacon);
        }
    }


    public Set<Coordinate> findCoveredPointsOnRow(List<Sensor> sensors) {
        Set<Coordinate> coordinates = new HashSet<>();
        for (var s : sensors) {
            coordinates.addAll(s.covered);
        }
        return coordinates;
    }

}
