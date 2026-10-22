package de.steffzilla.aoc.y2025;

import de.steffzilla.competitive.Utils;
import de.steffzilla.competitive.InputUtils;
import de.steffzilla.competitive.Pair;

import java.util.List;
import java.util.TreeMap;

public class Aoc2025_05 {

    private static final String DAY = "05";
    private static final String YEAR = "2025";
    public static final String BASEDIR = Utils.inputDir("AoC", YEAR);
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
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
        long count = 0;
        Pair<List<String>, List<String>> lists = InputUtils.splitLists(inputLines);
        List<String> freshIdRanges = lists.getValue0();
        List<String> availableIds = lists.getValue1();
        TreeMap<Long, Long> treemap = new TreeMap<>();
        for (String range : freshIdRanges) {
            String[] split = range.split("-");
            Long startId = Long.parseLong(split[0]);
            Long endId = Long.parseLong(split[1]);
            if (endId < startId) throw new IllegalStateException("End is smaller than start! " + startId + " " + endId);

            // because of the following line Long.MAX_VALUE doesn't work, yet the input doesn't contain it
            Long lowerKey = treemap.lowerKey(startId + 1L);
            if (lowerKey == null) {
                treemap.put(startId, endId);
            } else if (lowerKey.equals(startId)) {
                Long currentEnd = treemap.get(startId);
                if (currentEnd < endId) {
                    treemap.put(startId, endId);
                }
            } else {
                Long lowerKeysEnd = treemap.get(lowerKey);
                if (lowerKeysEnd >= startId) {
                    if (endId > lowerKeysEnd) {
                        // extend the range
                        treemap.put(lowerKey, endId);
                    }
                } else {
                    treemap.put(startId, endId);
                }
            }
        }
        long max = 0;
        for (String sId : availableIds) {
            long id = Long.parseLong(sId);
            if (id > max) {
                max = id;
            }
            Long lowerBoundary = treemap.lowerKey(id + 1L);
            if (lowerBoundary != null) {
                Long upperBoundary = treemap.get(lowerBoundary);
                if (lowerBoundary <= id && id <= upperBoundary) {
                    count++;
                }
            }
        }
        System.out.println("max: " + max);
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