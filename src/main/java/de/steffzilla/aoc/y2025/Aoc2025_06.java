package de.steffzilla.aoc.y2025;

import de.steffzilla.competitive.Utils;
import de.steffzilla.competitive.InputUtils;
import org.javatuples.Pair;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Aoc2025_06 {

    private static final String DAY = "06";
    private static final String YEAR = "2025";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    static final String example = """
            123 328  51 64\s
             45 64  387 23\s
              6 98  215 314
            *   +   *   + \s
            """;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        //List<String> inputLines = example.lines().toList();
        List<String> inputLines =
                Utils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        return new Pair<>(part1(inputLines), part2(inputLines));
    }

    private static String part1(List<String> inputLines) {
        BigInteger result = BigInteger.ZERO;
        List<List<Long>> rows = new ArrayList<>();
        for (int i = 0; i < inputLines.size() - 1; i++) {
            rows.add(getNumberRow(inputLines, i));
        }

        int index = 0;
        String operatorLine = inputLines.getLast();
        String[] operators = operatorLine.split("\\s+");
        for (String operator : operators) {
            if ("+".equals(operator)) {
                long localResult = 0L;
                for (List<Long> row : rows) {
                    localResult += row.get(index);
                }
                //System.out.println(operator + " --> " + localResult);
                result = result.add(BigInteger.valueOf(localResult));
            } else if ("*".equals(operator)) {
                long localResult = 1L;
                for (List<Long> row : rows) {
                    localResult *= row.get(index);
                }
                //System.out.println(operator + " --> " + localResult);
                result = result.add(BigInteger.valueOf(localResult));
            } else {
                throw new IllegalStateException("Should never occur: " + operator);
            }
            index++;
        }

        System.out.println("\nPart 1 > Result: " + result);
        return String.valueOf(result);
    }

    private static List<Long> getNumberRow(List<String> inputLines, int index) {
        List<Long> results = new ArrayList<>();
        String line = inputLines.get(index);
        line = line.replaceFirst("^\\s*", "");
        String[] split = line.split("\\s+");
        for (String number : split) {
            results.add(Long.valueOf(number));
        }
        return results;
    }


    private static String part2(List<String> inputLines) {
        List<String> numberInputLines = inputLines.subList(0, Math.max(0, inputLines.size() - 1));
        List<String> numbersAsStrings = InputUtils.readColumnOfFileFromRight(numberInputLines);
        String operatorLine = inputLines.getLast();
        String[] operators = operatorLine.split("\\s+");

        BigInteger result = BigInteger.ZERO;
        int numbersIndex = 0;
        for (int operatorsIndex = operators.length - 1; operatorsIndex >= 0; operatorsIndex--) {
            String operator = operators[operatorsIndex];
            List<Long> numbers = new ArrayList<>();
            boolean keepGoing = true;
            do {
                if (numbersIndex >= numbersAsStrings.size()) {
                    break;
                }
                String trimmedNumber = numbersAsStrings.get(numbersIndex).trim();
                if (!trimmedNumber.isEmpty()) {
                    numbers.add(Long.parseLong(trimmedNumber));
                } else {
                    keepGoing = false;
                }
                numbersIndex++;
            } while (keepGoing);

            if ("+".equals(operator)) {
                long localResult = 0;
                for (Long number : numbers) {
                    localResult += number;
                }
                result = result.add(BigInteger.valueOf(localResult));
            } else if ("*".equals(operator)) {
                long localResult = 1;
                for (Long number : numbers) {
                    localResult *= number;
                }
                result = result.add(BigInteger.valueOf(localResult));
            } else {
                throw new IllegalStateException("Should never occur: " + operator);
            }
        }

        System.out.println("\nPart 2 > Result: " + result);
        return String.valueOf(result);
    }

}