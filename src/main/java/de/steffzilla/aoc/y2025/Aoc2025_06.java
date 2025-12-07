package de.steffzilla.aoc.y2025;

import de.steffzilla.aoc.AocUtils;
import de.steffzilla.aoc.InputUtils;
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
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
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
        AocUtils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        return new Pair<>(part1(inputLines), part2(inputLines));
    }

    private static String part1(List<String> inputLines) {
        // TODO Refactor so that different line numbers work
        BigInteger result = BigInteger.ZERO;
        /*
        List<Long> row1 = getNumberRow(inputLines, 0);
        List<Long> row2 = getNumberRow(inputLines, 1);
        List<Long> row3 = getNumberRow(inputLines, 2);
        List<Long> row4 = getNumberRow(inputLines, 3);


        int index = 0;
        String operatorLine = inputLines.getLast();
        String[] operators = operatorLine.split("\\s+");
        for (String operator : operators) {
            if ("+".equals(operator)) {
                long localResult = row1.get(index) + row2.get(index) + row3.get(index) + row4.get(index);
                //System.out.println(operator + " --> " + localResult);
                result = result.add(BigInteger.valueOf(localResult));
            } else if ("*".equals(operator)) {
                long localResult = row1.get(index) * row2.get(index) * row3.get(index) * row4.get(index);
                //System.out.println(operator + " --> " + localResult);
                result = result.add(BigInteger.valueOf(localResult));
            } else {
                throw new IllegalStateException("Should never occur: " + operator);
            }
            index++;
        }*/

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
                if(!trimmedNumber.isEmpty()) {
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