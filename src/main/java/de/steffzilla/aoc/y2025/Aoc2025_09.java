package de.steffzilla.aoc.y2025;

import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class Aoc2025_09 {

    private static final String DAY = "09";
    private static final String YEAR = "2025";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    static final String example = """
            7,1
            11,1
            11,7
            9,7
            9,5
            2,5
            2,3
            7,3
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
        List<Pair<Long, Long>> points = new ArrayList<>();
        for (String line : inputLines) {
            String[] split = line.split(",");
            points.add(new Pair<>(Long.valueOf(split[0]), Long.valueOf(split[1])));
        }
        long largesArea = 0;

        for (int i = 0; i < points.size(); i++) {
            Pair<Long, Long> first = points.get(i);
            for (int j = 0; j < points.size(); j++) {
                if (i == j) continue;
                Pair<Long, Long> second = points.get(j);
                // count including both tiles --> +1
                long area = (Math.abs(first.getValue0() - second.getValue0()) + 1) *
                        (Math.abs(first.getValue1() - second.getValue1()) + 1);
                if (area > largesArea) largesArea = area;
            }
        }
        System.out.println("\nPart 1 > Result: " + largesArea);
        return String.valueOf(largesArea);
    }


    private static String part2(List<String> inputLines) {
        long count = 0;
        for (String line : inputLines) {

        }
        System.out.println("\nPart 2 > Result: " + count);
        return String.valueOf(count);
    }

}