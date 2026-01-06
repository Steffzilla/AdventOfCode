package de.steffzilla.aoc.y2025;

import de.steffzilla.competitive.AocUtils;
import org.javatuples.Pair;

import java.util.HashSet;
import java.util.List;

public class Aoc2025_02 {

    private static final String DAY = "02";
    private static final String YEAR = "2025";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    static final String example = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124";

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
        long count = 0;
        if (inputLines.size() != 1) throw new IllegalStateException("There must be only be 1 line!");
        String input = inputLines.getFirst();
        String[] ranges = input.split(",");
        for (String range : ranges) {
            String[] rangeBorders = range.split("-");
            if (rangeBorders.length != 2) throw new IllegalStateException("There must be 2 borders! " + range);
            if (rangeBorders[0].startsWith("0"))
                throw new IllegalStateException("No leading 0 allowed! " + rangeBorders[0]);
            if (rangeBorders[1].startsWith("0"))
                throw new IllegalStateException("No leading 0 allowed! " + rangeBorders[1]);
            long startID = Long.parseLong(rangeBorders[0]);
            long endID = Long.parseLong(rangeBorders[1]);
            //System.out.println(startID + "->" + endID);
            long sumOfInvalidIds = getSumOfInvalidIdsPart1(startID, endID);
            count += sumOfInvalidIds;
        }
        System.out.println("\nPart 1 > Result: " + count);
        return String.valueOf(count);
    }

    static long getSumOfInvalidIdsPart1(long startID, long endID) {
        long invalidIdsSum = 0;
        long current = startID;
        while (current <= endID) {
            String stringID = String.valueOf(current);
            if (stringID.length() % 2 == 0) {
                String part1 = stringID.substring(0, stringID.length() / 2);
                String part2 = stringID.substring(stringID.length() / 2);
                System.out.println(part1 + " " + part2);
                if (part1.equals(part2)) {
                    System.out.println("==>" + part1);
                    invalidIdsSum += Long.parseLong(stringID);
                }
            }
            current++;
        }
        return invalidIdsSum;
    }


    private static String part2(List<String> inputLines) {
        long count = 0;
        if (inputLines.size() != 1) throw new IllegalStateException("There must be only be 1 line!");
        String input = inputLines.getFirst();
        String[] ranges = input.split(",");
        for (String range : ranges) {
            String[] rangeBorders = range.split("-");
            if (rangeBorders.length != 2) throw new IllegalStateException("There must be 2 borders! " + range);
            if (rangeBorders[0].startsWith("0"))
                throw new IllegalStateException("No leading 0 allowed! " + rangeBorders[0]);
            if (rangeBorders[1].startsWith("0"))
                throw new IllegalStateException("No leading 0 allowed! " + rangeBorders[1]);
            long startID = Long.parseLong(rangeBorders[0]);
            long endID = Long.parseLong(rangeBorders[1]);

            long sumOfInvalidIds = getSumOfInvalidIdsPart2(startID, endID);
            count += sumOfInvalidIds;
        }
        System.out.println("\nPart 2 > Result: " + count);
        return String.valueOf(count);
    }

    private static long getSumOfInvalidIdsPart2(long startID, long endID) {
        HashSet<Long> foundInvalidIds = new HashSet<>();
        long current = startID;
        while (current <= endID) {
            String stringID = String.valueOf(current);
            for (int i = 0; i < stringID.length() / 2; i++) {
                String substring = stringID.substring(0, i + 1);
                String regex = "^(" + substring + ")+$";
                if (stringID.matches(regex)) {
                    System.out.println(regex + "==>" + stringID);
                    foundInvalidIds.add(Long.parseLong(stringID));
                }
            }
            current++;
        }
        long invalidIdsSum = 0;
        for (Long id : foundInvalidIds) {
            invalidIdsSum += id;
        }
        return invalidIdsSum;
    }

}