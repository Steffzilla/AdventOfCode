package de.steffzilla.aoc.y2024;

import de.steffzilla.aoc.AocUtils;
import de.steffzilla.competitive.InputUtils;
import org.javatuples.Pair;

import java.math.BigInteger;
import java.util.*;

public class Aoc2024_11 {

    private static final String DAY = "11";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    //public static final String FILENAME = "sample" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;
    static final BigInteger bi2024 = new BigInteger("2024");

    private static final HashMap<Pair<BigInteger, Integer>, Long> results = new HashMap<>();

    static final String example = "0 1 10 99 999";

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);

        //List<String> inputLines = example.lines().toList();
        List<String> inputLines = AocUtils.getStringList(PATH);

        solve(inputLines, 25, 75);
    }

    static Pair<String, String> solve(List<String> inputLines, int iterationsPart1, int iterationsPart2) {
        if (inputLines.size() != 1) {
            throw new IllegalStateException("Input format doesn't fit!" + inputLines);
        }
        return new Pair<>(part1(inputLines.getFirst(), iterationsPart1), part1(inputLines.getFirst(), iterationsPart2));
    }

    private static String part1(String input, int numberOfIterations) {
        List<BigInteger> numbers = InputUtils.readLineAsBigIntegers(input, " ");

        long counter = 0;
        for (BigInteger bigInteger : numbers) {
            counter += applyRules(new Pair<>(bigInteger, numberOfIterations));
        }
        System.out.println("\nResult for " + numberOfIterations + " iterations: " + counter);
        return String.valueOf(counter);
    }

    private static long applyRules(Pair<BigInteger, Integer> current) {
        int iterationsToBeDone = current.getValue1();
        if (iterationsToBeDone == 0) {
            return 1;
        }
        Long cacheHit = results.get(current);
        if (cacheHit != null) {
            return cacheHit;
        }
        long result = 0;

        // go 1 step deeper
        List<BigInteger> bigIntegers = applyRules(current.getValue0());
        for (BigInteger bi : bigIntegers) {
            Pair<BigInteger, Integer> newPair = new Pair<>(bi, iterationsToBeDone - 1);
            long l = applyRules(newPair);
            result += l;
        }

        results.put(current, result);
        return result;
    }

    private static List<BigInteger> applyRules(BigInteger number) {
        if (number.equals(BigInteger.ZERO)) {
            return List.of(BigInteger.ONE);
        } else if (number.toString().length() % 2 == 0) {
            List<BigInteger> list = new ArrayList<>();
            String numberAsString = number.toString();
            String first = numberAsString.substring(0, numberAsString.length() / 2);
            String second = numberAsString.substring(numberAsString.length() / 2);
            list.add(new BigInteger(first));
            list.add(new BigInteger(second));
            return list;
        } else {
            return List.of(number.multiply(bi2024));
        }
    }

}