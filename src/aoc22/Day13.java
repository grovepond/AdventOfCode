package aoc22;

import util.InputUtil;

import java.util.List;

public class Day13 {

    private final List<String> input;

    public Day13() {
        input = InputUtil.getInputAsStringList(this.getClass().getSimpleName().toLowerCase() + ".txt");
        input.removeIf(String::isBlank);
    }

    public String run1 () {
        int count = 0;
        for (int i = 0; i < input.size() - 1; i += 2) {
            if (0 >= compare(leftPadSingleDigitWithZero(input.get(i)), leftPadSingleDigitWithZero(input.get(i + 1))))
                count += i /2 + 1;
        }


        return "" + count ;
    }

    public String run2 () { // Couldn't fix this yet. Doesn't work.
        String divPack1 = "[[2]]";
        String divPack2 = "[[6]]";
        input.add(divPack1);
        input.add(divPack2);
        input.sort((first, second) -> compare(leftPadSingleDigitWithZero(first), leftPadSingleDigitWithZero(second)));

        int ix1 = 1 + input.indexOf(divPack1);
        int ix2 = 1 + input.indexOf(divPack2);
        return "" + ix1 * ix2;
    }

    private int compare(String first, String second) {
        for (int i = 0, j = 0; i < first.length() && j < second.length(); ) {
            var ch1 = first.charAt(i);
            var ch2 = second.charAt(j);
            if (ch1 == ch2) {
                i++;
                j++;
            } else if (ch1 == ']') {
                return -1;
            } else if (ch2 == ']') {
                return 1;
            } else if (ch1 == '[') {
                second = makeListOf(j, second);
            } else if (ch2 == '[') {
                first = makeListOf(i, first);
            } else {
                return Character.compare(ch1, ch2);
            }
        }
        return 0;
    }
    private static String leftPadSingleDigitWithZero(String in) {
        return in.replaceAll("\\b(\\d)\\b", "0$1");
    }
    private static String makeListOf(int ix, String in) {
        return in.substring(0, ix) + in.substring(ix).replaceFirst("(\\d+)", "[$1]");
    }
}
