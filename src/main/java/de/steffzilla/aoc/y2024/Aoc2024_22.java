package de.steffzilla.aoc.y2024;

import de.steffzilla.competitive.InputUtils;
import org.javatuples.Pair;

import java.util.List;

public class Aoc2024_22 {

    private static final String DAY = "22";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String INPUT_FILENAME = "input" + YEAR + "_" + DAY + ".txt";

    static final String example = """
            1
            10
            100
            2024
            """;

    public static void main(String[] args) {
        List<Long> inputLines = initialize(true);
        solve(inputLines);
    }

    static List<Long> initialize(boolean solvePuzzle) {
        List<Long> numbers;
        if (solvePuzzle) {
            System.out.println(DAY + ".12." + YEAR + ": Solving the PUZZLE");
            // set global settings here
            numbers = InputUtils.getListOfLongFromFile(BASEDIR + INPUT_FILENAME);
        } else {
            System.out.println(DAY + ".12." + YEAR + ": Solving the example");
            numbers = example.lines().mapToLong(Long::valueOf).boxed().toList();
            // set global settings here
        }
        return numbers;
    }

    static Pair<String, String> solve(List<Long> inputLines) {
        return new Pair<>(part1(inputLines), part2(inputLines));
    }

    private static String part1(List<Long> numbers) {
        long count = 0;
        for (Long number : numbers) {
            long result = number;
            for (int i = 0; i < 2000; i++) {
                result = calculateNextSecretNumber(result);
            }
            count+=result;
        }
        System.out.println("\nPart 1 > Result: " + count);
        return String.valueOf(count);
    }


    private static String part2(List<Long> numbers) {
        long count = 0;
        for (Long number : numbers) {

        }
        System.out.println("\nPart 2 > Result: " + count);
        return String.valueOf(count);
    }

    static long calculateNextSecretNumber(long secretNumber) {
        long temp = secretNumber * 64;
        secretNumber = mix(secretNumber, temp);
        secretNumber = prune(secretNumber);
        temp = (long) Math.floor((double) secretNumber / 32);
        secretNumber = mix(secretNumber, temp);
        secretNumber = prune(secretNumber);
        temp = secretNumber * 2048;
        secretNumber = mix(secretNumber, temp);
        secretNumber = prune(secretNumber);
        return secretNumber;
    }

    static long mix(long secretNumber, long otherNumber) {
        // bitwise XOR
        return secretNumber ^ otherNumber;
    }

    static long prune(long secretNumber) {
        return secretNumber % 16777216;
    }
}