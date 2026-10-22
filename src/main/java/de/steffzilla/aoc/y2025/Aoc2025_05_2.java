package de.steffzilla.aoc.y2025;

import de.steffzilla.competitive.InputUtils;
import de.steffzilla.competitive.Utils;
import de.steffzilla.competitive.Pair;

import java.util.List;

public class Aoc2025_05_2 {

    private static final String DAY = "05";
    private static final String YEAR = "2025";
    public static final String BASEDIR = Utils.inputDir("AoC", YEAR);
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    static final String example = """
            3-5
            10-14
            16-20
            12-18
            
            1
            5
            8
            11
            17
            32
            """;

    static void main() {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = example.lines().toList();
        //List<String> inputLines =
                Utils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        return new Pair<>(part1(inputLines), part2(inputLines));
    }

    private static String part1(List<String> inputLines) {
        Pair<List<String>, List<String>> lists = InputUtils.splitLists(inputLines);
        List<String> freshIdRanges = lists.getValue0();
        List<String> availableIds = lists.getValue1();

        long count = 0;
        for (String line : inputLines) {

        }
        System.out.println("\nPart 1 > Result: " + count);
        return String.valueOf(count);
    }

    private static String part2(List<String> inputLines) {
        long count = 0;
        for (String line : inputLines) {

        }
        System.out.println("\nPart 2 > Result: " + count);
        return String.valueOf(count);
    }

}