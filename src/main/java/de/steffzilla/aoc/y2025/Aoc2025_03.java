package de.steffzilla.aoc.y2025;

import de.steffzilla.competitive.AocUtils;
import org.javatuples.Pair;

import java.util.Arrays;
import java.util.List;

public class Aoc2025_03 {

    private static final String DAY = "03";
    private static final String YEAR = "2025";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    static final String example = """
            987654321111111
            811111111111119
            234234234234278
            818181911112111
            """;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        //List<String> inputLines = example.lines().toList();
        List<String> inputLines = AocUtils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        return new Pair<>(part1(inputLines), part2(inputLines));
    }

    private static String part1(List<String> inputLines) {
        long sum = 0;
        int totalNoOfDigits = 2;
        for (String bank : inputLines) {
            long biggestNumber = getBiggestNumber(bank, totalNoOfDigits);
            //System.out.println(biggestNumber);
            sum += biggestNumber;
        }
        System.out.println("\nPart 1 > Result: " + sum);
        return String.valueOf(sum);
    }

    static long getBiggestNumber(String remainderOfBank, int totalNoOfDigits) {
        long[] digits = new long[totalNoOfDigits];
        Arrays.fill(digits, 9);

        for (int currentDigit = 1; currentDigit <= totalNoOfDigits; currentDigit++) {
            int noOfDigitsToIgnoreAtTheEnd = totalNoOfDigits - currentDigit;
            while (digits[currentDigit - 1] >= 0) {
                int indexOfFirst = remainderOfBank.indexOf(String.valueOf(digits[currentDigit - 1]));
                if (indexOfFirst > -1 && indexOfFirst < remainderOfBank.length() - noOfDigitsToIgnoreAtTheEnd) {
                    remainderOfBank = remainderOfBank.substring(indexOfFirst + 1);
                    break;
                } else {
                    digits[currentDigit - 1] = digits[currentDigit - 1] - 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (long l : digits) {
            sb.append(l);
        }
        return Long.parseLong(sb.toString());
    }


    private static String part2(List<String> inputLines) {
        long sum = 0;
        int totalNoOfDigits = 12;
        for (String bank : inputLines) {
            long biggestNumber = getBiggestNumber(bank, totalNoOfDigits);
            //System.out.println(biggestNumber);
            sum += biggestNumber;
        }
        System.out.println("\nPart 2 > Result: " + sum);
        return String.valueOf(sum);
    }

}