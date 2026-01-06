package de.steffzilla.aoc.y2024;

import de.steffzilla.competitive.AocUtils;
import de.steffzilla.competitive.CharacterField;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Aoc2024_08 {

    private static final String DAY = "08";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    static final String example = """
            T.........
            ...T......
            .T........
            ..........
            ..........
            ..........
            ..........
            ..........
            ..........
            ..........
            """;

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
        HashMap<String, List<Pair<Integer, Integer>>> antennaPositions = readPositions(cf);
        HashSet<Pair<Integer, Integer>> antinodePositions = new HashSet<>();

        for (String frequency : antennaPositions.keySet()) {
            System.out.print(frequency + ": ");
            List<Pair<Integer, Integer>> pairs = antennaPositions.get(frequency);
            System.out.println(pairs);
            for (int outerIter = 0; outerIter < pairs.size(); outerIter++) {
                for (int innerIter = outerIter + 1; innerIter < pairs.size(); innerIter++) {
                    Pair<Integer, Integer> first = pairs.get(outerIter);
                    Pair<Integer, Integer> second = pairs.get(innerIter);
                    int x = first.getValue0() + first.getValue0() - second.getValue0();
                    int y = first.getValue1() + first.getValue1() - second.getValue1();
                    if (cf.isContained(x, y)) {
                        antinodePositions.add(new Pair<>(x, y));
                    }
                    x = second.getValue0() + second.getValue0() - first.getValue0();
                    y = second.getValue1() + second.getValue1() - first.getValue1();
                    if (cf.isContained(x, y)) {
                        antinodePositions.add(new Pair<>(x, y));
                    }
                }
            }
        }

        System.out.println("\nPart 1 > Result: " + antinodePositions.size());
        return String.valueOf(antinodePositions.size());
    }

    private static HashMap<String, List<Pair<Integer, Integer>>> readPositions(CharacterField cf) {
        HashMap<String, List<Pair<Integer, Integer>>> antennaPositions = new HashMap<>();
        for (int y = 0; y < cf.getMaxY(); y++) {
            for (int x = 0; x < cf.getMaxX(); x++) {
                String frequency = cf.getCharacterAt(x, y);
                if (!frequency.equals(".")) {
                    List<Pair<Integer, Integer>> positions =
                            antennaPositions.computeIfAbsent(frequency, k -> new ArrayList<>());
                    positions.add(new Pair<>(x, y));
                }
            }
        }
        return antennaPositions;
    }

    private static String part2(CharacterField cf) {
        HashMap<String, List<Pair<Integer, Integer>>> antennaPositions = readPositions(cf);
        HashSet<Pair<Integer, Integer>> antinodePositions = new HashSet<>();

        for (String frequency : antennaPositions.keySet()) {
            System.out.print(frequency + ": ");
            List<Pair<Integer, Integer>> pairs = antennaPositions.get(frequency);
            System.out.println(pairs);
            for (int outerIter = 0; outerIter < pairs.size(); outerIter++) {
                for (int innerIter = outerIter + 1; innerIter < pairs.size(); innerIter++) {
                    Pair<Integer, Integer> first = pairs.get(outerIter);
                    Pair<Integer, Integer> second = pairs.get(innerIter);
                    boolean endOfField = false;
                    int factor = 0;
                    while (!endOfField) {
                        int x = first.getValue0() + factor * (first.getValue0() - second.getValue0());
                        int y = first.getValue1() + factor * (first.getValue1() - second.getValue1());
                        if (cf.isContained(x, y)) {
                            antinodePositions.add(new Pair<>(x, y));
                        } else {
                            endOfField = true;
                        }
                        factor++;
                    }

                    endOfField = false;
                    factor = 0;
                    while (!endOfField) {
                        int x = second.getValue0() + factor * (second.getValue0() - first.getValue0());
                        int y = second.getValue1() + factor * (second.getValue1() - first.getValue1());
                        if (cf.isContained(x, y)) {
                            antinodePositions.add(new Pair<>(x, y));
                        } else {
                            endOfField = true;
                        }
                        factor++;
                    }
                }
            }
        }

        System.out.println("\nPart 2 > Result: " + antinodePositions.size());
        return String.valueOf(antinodePositions.size());
    }

}