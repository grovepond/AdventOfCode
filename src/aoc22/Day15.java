package aoc22;

import util.InputUtil;

import java.util.*;
import java.util.stream.IntStream;

public class Day15 {
//    Covered: 4737567
//    Frequency: 13267474686239
    private final List<String> input;
    private final static int min = 0, max = 4000000;

    public Day15() {
        input = InputUtil.getInputAsStringList(this.getClass().getSimpleName().toLowerCase() + ".txt");
    }

    public String run1 () {
        var sensors = parseInput();
        for (var s : sensors) {
            s.setCovered(2000000);
        }
        return ("Covered: " + findCoveredPointsOnRow(sensors).size());
    }

    public String run2 () {
        var sensors = parseInput();
        Range range = new Range(min, max);
        Coordinate c = null;
        for (Sensor sensor : sensors) {
            var north = new Coordinate(sensor.coordinate.x, sensor.coordinate.y - sensor.distanceToClosestBeacon - 1);
            var south = new Coordinate(sensor.coordinate.x, sensor.coordinate.y + sensor.distanceToClosestBeacon + 1);
            var west = new Coordinate(sensor.coordinate.x - sensor.distanceToClosestBeacon - 1, sensor.coordinate.y);
            var east = new Coordinate(sensor.coordinate.x + sensor.distanceToClosestBeacon + 1, sensor.coordinate.y);

            Set<Coordinate> coords = new HashSet<>();
            coords.addAll(north.lineTo(east));
            coords.addAll(east.lineTo(south));
            coords.addAll(south.lineTo(west));
            coords.addAll(west.lineTo(north));

            c = coords.stream()
                    .filter(coord -> range.contains(coord.getX()) && range.contains(coord.getY()))
                    .filter(coord -> sensors.stream().noneMatch(s -> s.isInRange(coord)))
                    .findFirst()
                    .orElse(null);
            if (c != null) {
                break;
            }
        }
        assert (c != null);
        return "Frequency: " + (c.x * 4000000L + c.y);
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

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int distanceToOther (Coordinate other) {
            return Math.abs(this.x - other.x) + Math.abs(y - other.y);
        }
        public List<Coordinate> lineTo(Coordinate other) {
            int xDelta = Integer.signum(other.getX() - this.getX());
            int yDelta = Integer.signum(other.getY() - this.getY());
            int steps = Math.max(Math.abs(this.getX() - other.getX()), Math.abs(this.getY() - other.getY()));
            return IntStream.rangeClosed(1, steps)
                    .mapToObj(i -> new Coordinate(this.getX() + xDelta * i, this.getY() + yDelta * i))
                    .toList();
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

    static class Sensor {
        Coordinate coordinate;
        int distanceToClosestBeacon;

        Set<Coordinate> covered;
        public Sensor (int x, int y, int bx, int by) {
            this.coordinate = new Coordinate(x, y);
            this.coordinate.x = x;
            this.coordinate.y = y;
            this.distanceToClosestBeacon = getDistanceToCoord(bx, by);

        }

        public boolean isInRange(Coordinate other) {
            return this.coordinate.distanceToOther(other) <= this.distanceToClosestBeacon;
        }
        public void setCovered(int row) {
            int offset = this.distanceToClosestBeacon - Math.abs(this.coordinate.y - row);
            this.covered = calculateCovered(row, offset);
        }


        public Set<Coordinate> calculateCovered(int row, int offset) {
            Set<Coordinate> coordinates = new HashSet<>();
            for (int i = this.coordinate.x - offset; i < this.coordinate.x + offset; i++) {
                        coordinates.add(new Coordinate(i, row));
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

    static class Range {
        int start, end;
        public Range (int start, int end) {
            this.start = start;
            this.end = end;
        }
        public boolean contains (int pos) {
            return this.start <= pos && pos <= this.end;
        }
    }

}
