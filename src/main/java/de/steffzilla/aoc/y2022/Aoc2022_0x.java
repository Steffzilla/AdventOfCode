package de.steffzilla.aoc.y2022;

import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;

import java.util.List;

public class Aoc2022_0x {

    private static final String DAY = "0x";
    private static final String YEAR = "2022";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    static final String example = """
            x
            y
            """;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        //List<String> inputLines = example.lines().toList();
        List<String> inputLines = AocUtils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        return new Pair<>(part1(inputLines), part2(inputLines));
    }

    private static String part1(List<String> inputLines) {
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

    /*
    Template for reading a maze graph
    private static HashMap<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> readMaze(List<String> inputLines) {
        CharacterField charField = new CharacterField(inputLines);
        Pair<Integer, Integer> startNode = null;
        Pair<Integer, Integer> goalNode = null;

        HashMap<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> graph = new HashMap<>();
        for (int y = 0; y < charField.getMaxY(); y++) {
            for (int x = 0; x < charField.getMaxX(); x++) {
                String letter = charField.getCharacterAt(x, y);
                List<Pair<Integer, Integer>> neighbors = new ArrayList<>();
                // check if start
                if (START.equals(letter)) {
                    startNode = new Pair<>(x, y);

                    System.out.println("Start node: " + startNode);
                }

                if(charField.isContained(x, y - 1)) {
                    //neighbors.add(new Pair<>(x, y-1));

                }
                if(charField.isContained(x, y + 1)) {
                    //neighbors.add(new Pair<>(x, y + 1));
                }
                if(charField.isContained(x - 1, y)) {
                    // neighbors.add(new Pair<>(x - 1, y));
                }
                if(charField.isContained(x + 1, y)) {
                    ///neighbors.add(new Pair<>(x + 1, y));
                }
                graph.put(new Pair<>(x, y), neighbors);
            }
        }
        return graph;
    }
    */

}