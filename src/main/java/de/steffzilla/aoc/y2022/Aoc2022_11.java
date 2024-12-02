package de.steffzilla.aoc.y2022;

import org.javatuples.Pair;

import java.math.BigInteger;
import java.util.*;

public class Aoc2022_11 {

    private static final String DAY = "11";
    private static final String YEAR = "2022";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        //part1();
        part2();
    }

    private static List<Monkey> getSampleMonkeys() {
        List<Monkey> monkeys = new ArrayList<>();

        Monkey monkey = new Monkey(2, 3, 23, (BigInteger value) -> value.multiply(BigInteger.valueOf(19)));
        monkey.worryLevels = new LinkedList<>(Arrays.asList(BigInteger.valueOf(79), BigInteger.valueOf(98)));
        monkeys.add(monkey);

        monkey = new Monkey(2, 0, 19, (BigInteger value) -> value.add(BigInteger.valueOf(6)));
        monkey.worryLevels = new LinkedList<>(Arrays.asList(
                BigInteger.valueOf(54), BigInteger.valueOf(65), BigInteger.valueOf(75), BigInteger.valueOf(74)));
        monkeys.add(monkey);

        monkey = new Monkey(1, 3, 13, (BigInteger value) -> value.multiply(value));
        monkey.worryLevels = new LinkedList<>(Arrays.asList(
                BigInteger.valueOf(79), BigInteger.valueOf(60), BigInteger.valueOf(97)));
        monkeys.add(monkey);

        monkey = new Monkey(0, 1, 17, (BigInteger value) -> value.add(BigInteger.valueOf(3)));
        monkey.worryLevels = new LinkedList<>(Arrays.asList(BigInteger.valueOf(74)));
        monkeys.add(monkey);

        return monkeys;
    }

    private static List<Monkey> getInputMonkeys() {
        List<Monkey> monkeys = new ArrayList<>();

        Monkey monkey = new Monkey(6, 7, 19, (BigInteger value) -> value.multiply(BigInteger.valueOf(7)));
        monkey.worryLevels = new LinkedList<>(Arrays.asList(
                BigInteger.valueOf(85), BigInteger.valueOf(77), BigInteger.valueOf(77)));
        monkeys.add(monkey);

        monkey = new Monkey(3, 5, 3, (BigInteger value) -> value.multiply(BigInteger.valueOf(11)));
        monkey.worryLevels = new LinkedList<>(Arrays.asList(BigInteger.valueOf(80), BigInteger.valueOf(99)));
        monkeys.add(monkey);

        monkey = new Monkey(0, 6, 13, (BigInteger value) -> value.add(BigInteger.valueOf(8)));
        monkey.worryLevels = new LinkedList<>(Arrays.asList(
                BigInteger.valueOf(74), BigInteger.valueOf(60), BigInteger.valueOf(74), BigInteger.valueOf(63),
                BigInteger.valueOf(86), BigInteger.valueOf(92), BigInteger.valueOf(80)));
        monkeys.add(monkey);

        monkey = new Monkey(2, 4, 7, (BigInteger value) -> value.add(BigInteger.valueOf(7)));
        monkey.worryLevels = new LinkedList<>(Arrays.asList(
                BigInteger.valueOf(71), BigInteger.valueOf(58), BigInteger.valueOf(93), BigInteger.valueOf(65),
                BigInteger.valueOf(80), BigInteger.valueOf(68), BigInteger.valueOf(54), BigInteger.valueOf(71)));
        monkeys.add(monkey);

        monkey = new Monkey(2, 0, 5, (BigInteger value) -> value.add(BigInteger.valueOf(5)));
        monkey.worryLevels = new LinkedList<>(Arrays.asList(
                BigInteger.valueOf(97), BigInteger.valueOf(56), BigInteger.valueOf(79),
                BigInteger.valueOf(65), BigInteger.valueOf(58)));
        monkeys.add(monkey);

        monkey = new Monkey(4, 3, 11, (BigInteger value) -> value.add(BigInteger.valueOf(4)));
        monkey.worryLevels = new LinkedList<>(Arrays.asList(BigInteger.valueOf(77)));
        monkeys.add(monkey);

        monkey = new Monkey(7, 1, 17, (BigInteger value) -> value.multiply(value));
        monkey.worryLevels = new LinkedList<>(Arrays.asList(
                BigInteger.valueOf(99), BigInteger.valueOf(90), BigInteger.valueOf(84), BigInteger.valueOf(50)));
        monkeys.add(monkey);

        monkey = new Monkey(5, 1, 2, (BigInteger value) -> value.add(BigInteger.valueOf(3)));
        monkey.worryLevels = new LinkedList<>(Arrays.asList(
                BigInteger.valueOf(50), BigInteger.valueOf(66), BigInteger.valueOf(61), BigInteger.valueOf(92),
                BigInteger.valueOf(64), BigInteger.valueOf(78)));
        monkeys.add(monkey);

        return monkeys;
    }


    private static void part1(){
        List<Monkey> monkeys = getSampleMonkeys();
        //List<Monkey> monkeys = getInputMonkeys();
        for (int i = 1; i <= 20; i++) {
            System.out.println("=========== Round "+ i +" ===========");
            for (Monkey throwingMonkey : monkeys) {
                while (!throwingMonkey.worryLevels.isEmpty()) {
                    Pair<BigInteger  , Integer> result = throwingMonkey.throwFirst();
                    Monkey catchingMonkey = monkeys.get(result.getValue1());
                    catchingMonkey.worryLevels.add(result.getValue0());
                }
            }
            for (Monkey monkey : monkeys) {
                System.out.println(monkey.worryLevels);
            }
        }
        List<Long> counters = new ArrayList<>();
        for (Monkey monkey : monkeys) {
            counters.add(monkey.getInspectionCounter());
        }
        Collections.sort(counters, Collections.reverseOrder()); // sort descending
        System.out.println("\nPart 1 > Result: " + counters.get(0)*counters.get(1));
    }

    private static void part2() {
        List<Monkey> monkeys = getSampleMonkeys();
        for (Monkey monkey : monkeys) {
            monkey.setReleaveDivisor(1L);
        }

        //List<Monkey> monkeys = getInputMonkeys();
        for (int i = 1; i <= 10000; i++) {
            System.out.println("=========== Round "+ i +" ===========");
            for (Monkey throwingMonkey : monkeys) {
                while (!throwingMonkey.worryLevels.isEmpty()) {
                    Pair<BigInteger, Integer> result = throwingMonkey.throwFirst();
                    Monkey catchingMonkey = monkeys.get(result.getValue1());
                    catchingMonkey.worryLevels.add(result.getValue0());
                }
            }
            /*for (Monkey monkey : monkeys) {
                System.out.println(monkey.worryLevels);
            }*/
            if(i % 1000 == 0) {
                List<Long> counters = new ArrayList<>();
                for (Monkey monkey : monkeys) {
                    counters.add(monkey.getInspectionCounter());
                }
                System.out.println(counters);
            }
        }
        List<Long> counters = new ArrayList<>();
        for (Monkey monkey : monkeys) {
            counters.add(monkey.getInspectionCounter());
        }
        System.out.println(counters);

        Collections.sort(counters, Collections.reverseOrder()); // sort descending

        System.out.println("\nPart 2 > Result: " + counters.get(0)*counters.get(1));
    }

}