package de.steffzilla.aoc.y2022;

import de.steffzilla.competitive.Pair;
import de.steffzilla.competitive.Utils;

import java.util.*;

public class Aoc2022_01_Streams {

    private static final String DAY = "01";
    private static final String YEAR = "2022";
    public static final String BASEDIR = Utils.inputDir("AoC", YEAR);
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        List<String> inputLines = Utils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        return new Pair<>(part1(inputLines), part2(inputLines));
    }

    private static String part1(List<String> inputLines){
        String[] blocks = String.join("\n", inputLines).split("\\n\\n");
        int sum = Arrays.stream(blocks).mapToInt(block -> block.lines().mapToInt(Integer::parseInt).sum()).max().orElse(0);;
        System.out.println("\nResult: "+sum);
        return String.valueOf(sum);
    }

    private static String part2(List<String> inputLines) {

        System.out.println("\nResult: ");
        return "";

    }

}