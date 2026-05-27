package de.steffzilla.competitive.everybodycodes.y2025;

import de.steffzilla.competitive.Utils;
import de.steffzilla.competitive.Pair;

import java.util.List;

public class EC2025Day0x {

    private static final String DAY = "0x";
    private static final String YEAR = "2025";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//EC//" + YEAR + "//";
    public static final String FILENAME_BASE = "everybody_codes_e" + YEAR + "_q" + DAY + "_p";
    public static final String PATH = BASEDIR + FILENAME_BASE;

    static final String EXAMPLE = """
            x
            y
            """;

    static void main() {
        System.out.println("EC " + DAY + " " + YEAR);
        //List<String> inputLines = EXAMPLE.lines().toList();
        List<String> inputLines =
                Utils.getStringList(PATH + "1.txt");
        part1(inputLines);
        inputLines = Utils.getStringList(PATH + "2.txt");
        part2(inputLines);
        inputLines = Utils.getStringList(PATH + "3.txt");
        part3(inputLines);
    }

    static String part1(List<String> inputLines) {
        long count = 0;
        for (String line : inputLines) {

        }
        System.out.println("\nPart 1 > Result: " + count);
        return String.valueOf(count);
    }

    static String part2(List<String> inputLines) {
        long count = 0;
        for (String line : inputLines) {

        }
        System.out.println("\nPart 2 > Result: " + count);
        return String.valueOf(count);
    }

    static String part3(List<String> inputLines) {
        long count = 0;
        for (String line : inputLines) {

        }
        System.out.println("\nPart 3 > Result: " + count);
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