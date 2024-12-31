package de.steffzilla.aoc.y2024;

import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Aoc2024_11 {

    private static final String DAY = "11";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;
    static final BigInteger bi2024 = new BigInteger("2024");

    static final String example = "0 1 10 99 999";

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);

        //List<String> inputLines = example.lines().toList();
        List<String> inputLines = AocUtils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        if (inputLines.size() != 1) {
            throw new IllegalStateException("Input format doesn't fit!" + inputLines);
        }
        return new Pair<>(part1(inputLines.getFirst()), part2(inputLines.getFirst()));
    }

    private static String part1(String input) {
        LinkedList<BigInteger> list =
                Arrays.stream(input.split(" ")).map(BigInteger::new).collect(Collectors.toCollection(LinkedList::new));
        System.out.println(LocalDateTime.now());
        for (int i = 0; i < 25; i++) {
            applyRules(list);
        }
        System.out.println(LocalDateTime.now());
        System.out.println("\nPart 1 > Result: " + list.size());
        return String.valueOf(list.size());
    }

    private static void applyRules(LinkedList<BigInteger> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            BigInteger number = list.get(i);
            if (number.equals(BigInteger.ZERO)) {
                list.set(i, BigInteger.ONE);
            } else if (number.toString().length() % 2 == 0) {
                String numberAsString = number.toString();
                String first = numberAsString.substring(0, numberAsString.length() / 2);
                String second = numberAsString.substring(numberAsString.length() / 2);
                list.set(i, new BigInteger(first));
                list.add(i + 1, new BigInteger(second));
                i++; // Increment the index to skip the newly added element
                size++;
            } else {
                list.set(i, number.multiply(bi2024));
            }
        }
    }


    private static String part2(String input) {
        long count = 0;

        System.out.println("\nPart 2 > Result: " + count);
        return String.valueOf(count);
    }

}