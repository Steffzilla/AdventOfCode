package de.steffzilla.aoc.y2024;

import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Aoc2024_07 {

    private static final String DAY = "07";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    //public static final String FILENAME = "sample" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        return new Pair<>(part1(inputLines), part2(inputLines));
    }

    private static String part1(List<String> inputLines) {
        long count = 0;
        for (String line : inputLines) {
            String[] split = line.split(": ");
            long result = Long.parseLong(split[0]);
            List<Integer> numbers = Arrays.stream(split[1].split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            if (canBeTruePart1(result, numbers.getFirst(), numbers, 1)) {
                count += result;
            }
        }
        System.out.println("\nPart 1 > Result: " + count);
        return String.valueOf(count);
    }

    private static boolean canBeTruePart1(long expectedResult, long current, List<Integer> numbers, int index) {
        if (index == numbers.size()) {
            return current == expectedResult;
        }
        int nextNumber = numbers.get(index);
        return canBeTruePart1(expectedResult, current + nextNumber, numbers, index + 1) ||
                canBeTruePart1(expectedResult, current * nextNumber, numbers, index + 1);
    }

    private static String part2(List<String> inputLines) {
        long count = 0;
        for (String line : inputLines) {
            String[] split = line.split(": ");
            long result = Long.parseLong(split[0]);
            List<Long> numbers = Arrays.stream(split[1].split(" "))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            if (canBeTruePart2(result, numbers.getFirst(), numbers, 0)) {
                count += result;
            }
        }
        System.out.println("\nPart 2 > Result: " + count);
        return String.valueOf(count);
    }

    private static boolean canBeTruePart2(long expectedResult, long current, List<Long> numbers, int index) {
        if (index == numbers.size() - 1) {
            return current == expectedResult;
        }
        long nextNumber = numbers.get(index + 1);
        long mergedCurrent = Long.parseLong(String.valueOf(current) + nextNumber);
        LinkedList<Long> newNumbers = new LinkedList<>(numbers);
        newNumbers.set(index, mergedCurrent);
        newNumbers.remove(index + 1);
        return canBeTruePart2(expectedResult, current + nextNumber, numbers, index + 1) ||
                canBeTruePart2(expectedResult, current * nextNumber, numbers, index + 1) ||
                canBeTruePart2(expectedResult, mergedCurrent, newNumbers, index);
    }

}