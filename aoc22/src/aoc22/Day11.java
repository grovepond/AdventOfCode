package aoc22;

import util.InputUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Day11 {

    private final String input;
    private final boolean verbose = false;

    public Day11() {
        input = InputUtil.getInputAsString(this.getClass().getSimpleName().toLowerCase() + ".txt");
    }

    public String run1 () {

        return "Part 1 result is " + process(20,true);
    }

    public String run2 () {

        return "Part 2 result is " + process(10000, false);
    }

    private long process (int turns, boolean divide) {
        String [] m = input.split ("\n\n");
        long mod = 1;

        List<Monkey> monkeys = new ArrayList<>();
        for (String s : m) {
            Monkey monkey = new Monkey(s);
            mod *= monkey.test;
            monkeys.add(monkey);
        }

        for (int i = 0; i < turns; i++)
        {

            for (Monkey monkey : monkeys)
            {
                monkey.inspect(monkeys, divide, mod);
            }if (verbose) System.out.println("================");
            if (verbose) monkeys.forEach(f -> System.out.println(f.items));
            if (verbose) System.out.println("================");
        }


        monkeys.sort(Comparator.comparingLong(Monkey::getInspected));
        long monkeyBusiness = monkeys.get(monkeys.size() - 1).inspected * monkeys.get(monkeys.size() - 2).inspected;
        if (verbose) monkeys.forEach(mky -> System.out.println("Monkey " + mky.index + " inspected items " + mky.inspected + " times, holding items " + mky.items));

        return monkeyBusiness;
    }

    private class Monkey {
        int index;
        List<Long> items;
        char operator;
        String v;
        int test;
        long inspected;

        int trueMonkey, falseMonkey;

        public Monkey (String monkey) {
            this.test = 1;
            this.index = Integer.parseInt(monkey.split("\n")[0].split(" ")[1].substring(0,1));
            this.items = Arrays.stream(monkey.split("\n")[1].substring(18).split(", ")).map(Long::parseLong).collect(Collectors.toList());
            this.operator = monkey.split("\n")[2].charAt(23);
            this.v = monkey.split("\n")[2].substring(25);
            this.test *= Integer.parseInt(monkey.split("\n")[3].split(" ")[5]);
            this.trueMonkey = Integer.parseInt(monkey.split("\n")[4].substring(29));
            this.falseMonkey = Integer.parseInt(monkey.split("\n")[5].substring(30));
        }


        public long getInspected() {
            return inspected;
        }

        void inspect (List<Monkey> monkeys, boolean divide, long mod) {
            if (verbose) System.out.println("Monkey " + this.index);

            for (var item : this.items) {
                long number = ("old".equals(this.v)) ? item : Long.parseLong(this.v);
                if (verbose) System.out.println("    Monkey inspects an item with a worry level of " + item);
                switch (this.operator)
                {
                    case '+' -> item += number;
                    case '*' -> item *= number;
                }
                if (verbose) System.out.println("        Worry level is " + this.operator + " by " + number + " to " + item);
                if (divide) {
                    item /= 3;
                    if (verbose) System.out.println("        Monkey gets bored with item. Worry level is divided by 3 to " + item);
                }

                item %= mod;
                if (0 != item % this.test) {
                    if (verbose) System.out.println("        Current worry level is not divisible by " + this.test);
                    monkeys.get(falseMonkey).items.add(item);
                    if (verbose) System.out.println("        Item with worry level " + item + " is thrown to monkey " + falseMonkey);
                } else {
                    if (verbose) System.out.println("        Current worry level is divisible by " + this.test);
                    monkeys.get(trueMonkey).items.add(item);
                    if (verbose) System.out.println("        Item with worry level " + item + " is thrown to monkey " + trueMonkey);
                }
                inspected++;
            }
            this.items.clear();
        }
    }



}
