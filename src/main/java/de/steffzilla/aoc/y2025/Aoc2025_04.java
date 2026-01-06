package de.steffzilla.aoc.y2025;

import de.steffzilla.aoc.AocUtils;
import de.steffzilla.competitive.CharacterField;
import org.javatuples.Pair;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Aoc2025_04 {

    private static final String DAY = "04";
    private static final String YEAR = "2025";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    static final String example = """
            ..@@.@@@@.
            @@@.@.@.@@
            @@@@@.@.@@
            @.@@@@..@.
            @@.@@@@.@@
            .@@@@@@@.@
            .@.@.@.@@@
            @.@@@.@@@@
            .@@@@@@@@.
            @.@.@@@.@.
            """;
    public static final String ROLL = "@";

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        //List<String> inputLines = example.lines().toList();
        List<String> inputLines = AocUtils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        CharacterField cf = new CharacterField(inputLines);
        return new Pair<>(part1(cf), part2(cf));
    }

    private static String part1(CharacterField cf) {
        long count = countAccessibleRolls(cf);
        System.out.println("\nPart 1 > Result: " + count);
        return String.valueOf(count);
    }

    private static long countAccessibleRolls(CharacterField cf) {
        long counter = 0;
        for (int y = 0; y < cf.getMaxY(); y++) {
            for (int x = 0; x < cf.getMaxX(); x++) {
                if (!cf.getCharacterAt(x, y).equals(ROLL)) continue;
                List<String> surroundingChars = cf.getNeighborChars(x, y);
                int frequency = Collections.frequency(surroundingChars, ROLL);
                if (frequency < 4) counter++;
            }
        }
        return counter;
    }

    private static long removeRolls(CharacterField cf) {
        HashSet<Pair<Integer, Integer>> toBeRemoved = new HashSet<>();
        for (int y = 0; y < cf.getMaxY(); y++) {
            for (int x = 0; x < cf.getMaxX(); x++) {
                if (!cf.getCharacterAt(x, y).equals(ROLL)) continue;
                List<String> surroundingChars = cf.getNeighborChars(x, y);
                int frequency = Collections.frequency(surroundingChars, ROLL);
                if (frequency < 4) {
                    toBeRemoved.add(new Pair<>(x, y));
                }
            }
        }
        for (Pair<Integer, Integer> posToBeRemoved : toBeRemoved) {
            cf.setCharacterAt(".", posToBeRemoved);
        }
        return toBeRemoved.size();
    }

    private static String part2(CharacterField cf) {
        long count = 0;
        long newlyRemoved;
        do {
            newlyRemoved = removeRolls(cf);
            //System.out.println(newlyRemoved);
            count += newlyRemoved;
        } while (newlyRemoved > 0);
        System.out.println("\nPart 2 > Result: " + count);
        return String.valueOf(count);
    }

}