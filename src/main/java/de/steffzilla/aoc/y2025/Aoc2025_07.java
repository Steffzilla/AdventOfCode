package de.steffzilla.aoc.y2025;

import de.steffzilla.competitive.Utils;
import de.steffzilla.competitive.CharacterField;
import de.steffzilla.competitive.Pair;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class Aoc2025_07 {

    private static final String DAY = "07";
    private static final String YEAR = "2025";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;
    public static final String START = "S";
    public static final String BEAM = "|";
    public static final String EMPTY = ".";
    public static final String SPLITTER = "^";

    static final String example = """
            .......S.......
            ...............
            .......^.......
            ...............
            ......^.^......
            ...............
            .....^.^.^.....
            ...............
            ....^.^...^....
            ...............
            ...^.^...^.^...
            ...............
            ..^...^.....^..
            ...............
            .^.^.^.^.^...^.
            ...............
            """;

    private static Pair<Integer, Integer> startField;


    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        List<String> inputLines = example.lines().toList();
        //List<String> inputLines =
        Utils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        return new Pair<>(part1(inputLines), part2(inputLines));
    }

    private static String part1(List<String> inputLines) {
        long count = 0;
        CharacterField cf = new CharacterField(inputLines);
        List<Pair<Integer, Integer>> startFields = cf.searchCharacters(START);
        if (startFields.size() != 1) throw new IllegalStateException("More than 1 Start!" + startFields.size());
        Pair<Integer, Integer> startField = startFields.getFirst();
        cf.setCharacterAt(BEAM, startField.getValue0(), startField.getValue1() + 1);
        for (int yPosAbove = startField.getValue1() + 1; yPosAbove < cf.getMaxY() - 1; yPosAbove++) {
            for (int xPos = 0; xPos < cf.getMaxX(); xPos++) {
                String characterAbove = cf.getCharacterAt(xPos, yPosAbove);
                if (BEAM.equals(characterAbove)) {
                    String characterToBeProcessed = cf.getCharacterAt(xPos, yPosAbove + 1);
                    if (EMPTY.equals(characterToBeProcessed)) {
                        cf.setCharacterAt(BEAM, xPos, yPosAbove + 1);
                    } else if (SPLITTER.equals(characterToBeProcessed)) {
                        if (EMPTY.equals(cf.getCharacterAt(xPos - 1, yPosAbove + 1))) {
                            cf.setCharacterAt(BEAM, xPos - 1, yPosAbove + 1);
                        }
                        if (EMPTY.equals(cf.getCharacterAt(xPos + 1, yPosAbove + 1))) {
                            cf.setCharacterAt(BEAM, xPos + 1, yPosAbove + 1);
                        }
                        count++;
                    }
                }
            }
        }
        System.out.println();
        cf.prettyPrint();
        System.out.println("\nPart 1 > Result: " + count);
        return String.valueOf(count);
    }

    private static Graph<Pair<Integer, Integer>, DefaultEdge> buildGraph(CharacterField cf) {
        Graph<Pair<Integer, Integer>, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        List<Pair<Integer, Integer>> startFields = cf.searchCharacters(START);
        if (startFields.size() != 1) throw new IllegalStateException("More than 1 Start!" + startFields.size());
        startField = startFields.getFirst();
        graph.addVertex(startField);
        Pair<Integer, Integer> firstBeam = new Pair<>(startField.getValue0(), startField.getValue1() + 1);
        cf.setCharacterAt(BEAM, firstBeam);
        graph.addVertex(firstBeam);
        graph.addEdge(startField, firstBeam);
        int counter = 0;
        for (int yPosAbove = startField.getValue1() + 1; yPosAbove < cf.getMaxY() - 1; yPosAbove++) {
            for (int xPos = 0; xPos < cf.getMaxX(); xPos++) {
                Pair<Integer, Integer> posAbove = new Pair<>(xPos, yPosAbove);
                String characterAbove = cf.getCharacterAt(posAbove);
                if (BEAM.equals(characterAbove)) {
                    Pair<Integer, Integer> potentialNewPos = new Pair<>(xPos, yPosAbove + 1);
                    String characterToBeProcessed = cf.getCharacterAt(potentialNewPos);
                    if (EMPTY.equals(characterToBeProcessed)) {
                        Pair<Integer, Integer> newPos = new Pair<>(xPos, yPosAbove + 1);
                        cf.setCharacterAt(BEAM, newPos);
                        graph.addVertex(newPos);
                        graph.addEdge(posAbove, newPos);
                    } else if (SPLITTER.equals(characterToBeProcessed)) {
                        //graph.addVertex(potentialNewPos);
                        counter++;//TODO remove me
                        if (!SPLITTER.equals(cf.getCharacterAt(xPos - 1, yPosAbove + 1))) {
                            Pair<Integer, Integer> newPos = new Pair<>(xPos - 1, yPosAbove + 1);
                            cf.setCharacterAt(BEAM, newPos);
                            graph.addVertex(newPos);
                            graph.addEdge(posAbove, newPos);
                        }
                        if (!SPLITTER.equals(cf.getCharacterAt(xPos + 1, yPosAbove + 1))) {
                            Pair<Integer, Integer> newPos = new Pair<>(xPos + 1, yPosAbove + 1);
                            cf.setCharacterAt(BEAM, newPos);
                            graph.addVertex(newPos);
                            graph.addEdge(posAbove, newPos);
                        }
                    }
                }
            }
        }
        return graph;
    }

    private static String part2(List<String> inputLines) {
        return "TODO";
    }

}