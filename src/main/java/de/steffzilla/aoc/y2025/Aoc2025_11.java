package de.steffzilla.aoc.y2025;

import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

public class Aoc2025_11 {

    private static final String DAY = "11";
    private static final String YEAR = "2025";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;
    private static final String YOU = "you";
    private static final String OUT = "out";

    static final String example = """
            aaa: you hhh
            you: bbb ccc
            bbb: ddd eee
            ccc: ddd eee fff
            ddd: ggg
            eee: out
            fff: out
            ggg: out
            hhh: ccc fff iii
            iii: out
            """;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        //List<String> inputLines = example.lines().toList();
        List<String> inputLines =
                AocUtils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        return new Pair<>(part1(inputLines), part2(inputLines));
    }

    private static String part1(List<String> inputLines) {
        long result = 0;
        Graph<String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        // first the vertices with outgoing connections --> vertices without outgoing connections aren't added!
        graph.addVertex(OUT);
        for (String line : inputLines) {
            String[] inputParts = line.split(": ");
            graph.addVertex(inputParts[0]);

        }
        // then the edges
        for (String line : inputLines) {
            String[] inputParts = line.split(": ");
            String currentDevice = inputParts[0];
            String[] otherDevices = inputParts[1].split(" ");
            for (String otherDevice : otherDevices) {
                graph.addEdge(currentDevice, otherDevice);
            }
        }
        AllDirectedPaths<String, DefaultEdge> allDirectedPaths = new AllDirectedPaths<>(graph);
        List<GraphPath<String, DefaultEdge>> allPaths
                = allDirectedPaths.getAllPaths(YOU, OUT, true, null);
        result = allPaths.size();
        System.out.println("\nPart 1 > Result: " + result);
        return String.valueOf(result);
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