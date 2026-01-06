package de.steffzilla.aoc.y2024;

import de.steffzilla.competitive.Utils;
import de.steffzilla.competitive.CharacterField;
import de.steffzilla.competitive.InputUtils;
import org.javatuples.Pair;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BFSShortestPath;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;


public class Aoc2024_18 {

    private static final String DAY = "18";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String INPUT_FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    public static final String SAMPLE_FILENAME = "sample" + YEAR + "_" + DAY + ".txt";
    public static final String BLOCKING = "#";

    static int maxXCoordinate;
    static int maxYCoordinate;
    static int numberOfBytesPart1;

    public static void main(String[] args) {
        List<String> inputLines = initialize(true);
        solve(inputLines);
    }

    static List<String> initialize(boolean solvePuzzle) {
        List<String> inputLines;
        if (solvePuzzle) {
            System.out.println(DAY + ".12." + YEAR + ": Solving the PUZZLE");
            maxXCoordinate = 70;
            maxYCoordinate = 70;
            numberOfBytesPart1 = 1024;
            inputLines = Utils.getStringList(BASEDIR + INPUT_FILENAME);
        } else {
            System.out.println(DAY + ".12." + YEAR + ": Solving the example");
            maxXCoordinate = 6;
            maxYCoordinate = 6;
            numberOfBytesPart1 = 12;
            inputLines = Utils.getStringList(BASEDIR + SAMPLE_FILENAME);
        }
        return inputLines;
    }

    static Pair<String, String> solve(List<String> inputLines) {
        return new Pair<>(part1(inputLines), part2(inputLines));
    }

    private static String part1(List<String> inputLines) {
        List<Pair<Integer, Integer>> coordinates = InputUtils.readLinesAsCoordinates(inputLines, numberOfBytesPart1);
        CharacterField cf = new CharacterField(maxXCoordinate + 1, maxYCoordinate + 1, ".");
        for (Pair<Integer, Integer> blockingByte : coordinates) {
            cf.setCharacterAt(BLOCKING, blockingByte);
        }
        //cf.prettyPrint();
        Graph<Pair<Integer, Integer>, DefaultEdge> graph = CharacterFieldUtils.calculateUndirectedGraph(cf, BLOCKING);
        BFSShortestPath<Pair<Integer, Integer>, DefaultEdge> bsfShortestPath = new BFSShortestPath<>(graph);
        GraphPath<Pair<Integer, Integer>, DefaultEdge> path =
                bsfShortestPath.getPath(new Pair<>(0, 0), new Pair<>(maxXCoordinate, maxYCoordinate));
        long count = path.getLength();
        System.out.println("\nPart 1 > Result: " + count);
        return String.valueOf(count);
    }

    private static String part2(List<String> inputLines) {
        List<Pair<Integer, Integer>> coordinates = InputUtils.readLinesAsCoordinates(inputLines);
        CharacterField cf = new CharacterField(maxXCoordinate + 1, maxYCoordinate + 1, ".");
        // from part 1 we know that there is a path for the first numberOfBytesPart1 blocking bytes
        int i = 0;
        while (i < numberOfBytesPart1) {
            Pair<Integer, Integer> blockingByte = coordinates.get(i);
            cf.setCharacterAt(BLOCKING, blockingByte);
            i++;
        }

        String result = "";
        while (i < coordinates.size()) {
            // add one more byte
            Pair<Integer, Integer> blockingByte = coordinates.get(i);
            cf.setCharacterAt(BLOCKING, blockingByte);

            Graph<Pair<Integer, Integer>, DefaultEdge> graph = CharacterFieldUtils.calculateUndirectedGraph(cf, BLOCKING);
            BFSShortestPath<Pair<Integer, Integer>, DefaultEdge> bsfShortestPath = new BFSShortestPath<>(graph);
            GraphPath<Pair<Integer, Integer>, DefaultEdge> path =
                    bsfShortestPath.getPath(new Pair<>(0, 0), new Pair<>(maxXCoordinate, maxYCoordinate));
            if (path == null) {
                result = blockingByte.getValue0() + "," + blockingByte.getValue1();
                break;
            }
            i++;
        }

        System.out.println("\nPart 2 > Result: " + result);
        return result;
    }

}