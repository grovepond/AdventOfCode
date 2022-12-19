package aoc22;

import util.InputUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day10 {

    private final List<String> input;
    private static final char _ON = '#';
    private static final char _OFF = '.';

    public Day10() {
        input = InputUtil.getInputAsStringList(this.getClass().getSimpleName().toLowerCase() + ".txt");

    }

    public String run1 () {

        int x = 1;
        int cycle = 0;
        int sum = 0;
        List<String> tmp = new ArrayList<>();
        // Add noop before each addx for convenience
        for (var line : input) {
            if (line.startsWith("addx"))
                tmp.add ("noop");
            tmp.add(line);
        }

        for (var line : tmp) {
            cycle ++;
            if (0 == (cycle - 20) % 40) {
                System.out.println("Cycle: " + cycle + " x value: " + x + " total: " + cycle * x);
                sum += cycle * x;
            }
            if (line.startsWith("addx"))
                x += Integer.parseInt(line.substring(5));
        }


        return "" + sum;


    }

    public String run2 () {
        char[][] crt = new char[6][40];
        int cycle = 0;
        int crtPixelPos = 0;
        int x = 1;

        Range sprite = new Range(-1, 1);


       for (var line : input) {
           crt[cycle / 40][crtPixelPos] = sprite.contains(crtPixelPos) ? _ON : _OFF;
           crtPixelPos = (39 == crtPixelPos) ? 0 : crtPixelPos + 1;
           if (line.startsWith("noop"))
               cycle++;
           else {
               cycle++;
               crt[cycle / 40][crtPixelPos] = sprite.contains(crtPixelPos) ? _ON : _OFF;
               crtPixelPos = (39 == crtPixelPos) ? 0 : crtPixelPos + 1;
               cycle++;

               x += Integer.parseInt(line.split(" ")[1]);
               sprite = new Range(x - 1, x + 1);
           }


       }
        Arrays.stream(crt).forEach(System.out::println);
        return "";
    }

    private static class Range
    {
        private final int min;
        private final int max;

        public Range(int min, int max){
            this.min = min;
            this.max = max;
        }

        public boolean contains (int number){
            return (number >= min && number <= max);
        }
    }

}
