package de.steffzilla.aoc.y2024;

import de.steffzilla.aoc.AocUtils;
import de.steffzilla.aoc.BreadthFirstSearch;
import de.steffzilla.aoc.CharacterField;
import org.javatuples.Pair;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Aoc2024_10 {

    private static final String DAY = "10";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    //public static final String FILENAME = "sample" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;
    private static final String START = "0";
    private static final String END = "9";

    static final String example = """
            0123
            1234
            8765
            9876
            """;
    static final String example2 = """
            .....0.
            ..4321.
            ..5..2.
            ..6543.
            ..7..4.
            ..8765.
            ..9....
            """;

    static final String example3 = """
            012
            193
            234
            """;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        //List<String> inputLines = example.lines().toList();
        List<String> inputLines = AocUtils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        CharacterField charField = new CharacterField(inputLines);
        return new Pair<>(part1(charField), part2(charField));
    }

    private static String part1(CharacterField charField) {
        int allowedStepsUpward = 1;
        HashMap<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> graph = new HashMap<>();
        List<Pair<Integer, Integer>> trailheads = new ArrayList<>();
        List<Pair<Integer, Integer>> summits = new ArrayList<>();

        for (int y = 0; y < charField.getMaxY(); y++) {
            for (int x = 0; x < charField.getMaxX(); x++) {
                String letter = charField.getCharacterAt(x, y);
                List<Pair<Integer, Integer>> neighbors = new ArrayList<>();
                // check if start or end
                if (START.equals(letter)) {
                    trailheads.add(new Pair<>(x, y));
                    //System.out.println("Start node: " + startNode);
                } else if (END.equals(letter)) {
                    summits.add(new Pair<>(x, y));
                    //System.out.println("Goal node: " + goalNode);
                }
                if (charField.isContained(x, y - 1)) {
                    int neighborHeight = getNeighborHeight(charField.getCharacterAt(x, y - 1));
                    if (neighborHeight <= letter.charAt(0) + allowedStepsUpward) {
                        neighbors.add(new Pair<>(x, y - 1));
                    }
                }
                if (charField.isContained(x, y + 1)) {
                    int neighborHeight = getNeighborHeight(charField.getCharacterAt(x, y + 1));
                    if (neighborHeight <= letter.charAt(0) + allowedStepsUpward) {
                        neighbors.add(new Pair<>(x, y + 1));
                    }
                }
                if (charField.isContained(x - 1, y)) {
                    int neighborHeight = getNeighborHeight(charField.getCharacterAt(x - 1, y));
                    if (neighborHeight <= letter.charAt(0) + allowedStepsUpward) {
                        neighbors.add(new Pair<>(x - 1, y));
                    }
                }
                if (charField.isContained(x + 1, y)) {
                    int neighborHeight = getNeighborHeight(charField.getCharacterAt(x + 1, y));
                    if (neighborHeight <= letter.charAt(0) + allowedStepsUpward) {
                        neighbors.add(new Pair<>(x + 1, y));
                    }
                }
                graph.put(new Pair<>(x, y), neighbors);
            }
        }

        long count = 0;
        for (Pair<Integer, Integer> trailhead : trailheads) {
            long trailOfTrailHead = 0;
            for (Pair<Integer, Integer> summit : summits) {
                BreadthFirstSearch<Pair<Integer, Integer>> bfs = new BreadthFirstSearch<>(graph);
                //System.out.println(graph.get(trailhead));
                //System.out.println(graph.get(summit));
                List<Pair<Integer, Integer>> path = bfs.search(trailhead, summit);
                if (!path.isEmpty()) {
                    if (path.size() != 10) {
                        // BFS goes 1 step down too, since the graph is undirected --> ignore such cases
                        continue;
                    }
                    trailOfTrailHead++;
                }
            }
            count += trailOfTrailHead;
        }
        System.out.println("\nPart 1 > Result: " + count);
        return String.valueOf(count);
    }

    private static String part2(CharacterField charField) {
        int allowedStepsUpward = 1;
        Graph<Pair<Integer, Integer>, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);

        List<Pair<Integer, Integer>> trailheads = new ArrayList<>();
        List<Pair<Integer, Integer>> summits = new ArrayList<>();

        // add all nodes
        for (int y = 0; y < charField.getMaxY(); y++) {
            for (int x = 0; x < charField.getMaxX(); x++) {
                graph.addVertex(new Pair<>(x, y));
            }
        }

        // add edges
        for (int y = 0; y < charField.getMaxY(); y++) {
            for (int x = 0; x < charField.getMaxX(); x++) {
                String letter = charField.getCharacterAt(x, y);
                Pair<Integer, Integer> currentNode = new Pair<>(x, y);
                // check if start or end
                if (START.equals(letter)) {
                    trailheads.add(currentNode);
                } else if (END.equals(letter)) {
                    summits.add(currentNode);
                }
                if (charField.isContained(x, y - 1)) {
                    int neighborHeight = getNeighborHeight(charField.getCharacterAt(x, y - 1));
                    if (neighborHeight == letter.charAt(0) + allowedStepsUpward) {
                        graph.addEdge(currentNode, new Pair<>(x, y - 1));
                    }
                }
                if (charField.isContained(x, y + 1)) {
                    int neighborHeight = getNeighborHeight(charField.getCharacterAt(x, y + 1));
                    if (neighborHeight == letter.charAt(0) + allowedStepsUpward) {
                        graph.addEdge(currentNode, new Pair<>(x, y + 1));
                    }
                }
                if (charField.isContained(x - 1, y)) {
                    int neighborHeight = getNeighborHeight(charField.getCharacterAt(x - 1, y));
                    if (neighborHeight == letter.charAt(0) + allowedStepsUpward) {
                        graph.addEdge(currentNode, new Pair<>(x - 1, y));
                    }
                }
                if (charField.isContained(x + 1, y)) {
                    int neighborHeight = getNeighborHeight(charField.getCharacterAt(x + 1, y));
                    if (neighborHeight == letter.charAt(0) + allowedStepsUpward) {
                        graph.addEdge(currentNode, new Pair<>(x + 1, y));
                    }
                }
            }
        }

        long count = 0;
        AllDirectedPaths<Pair<Integer, Integer>, DefaultEdge> allDirectedPaths = new AllDirectedPaths<>(graph);
        for (Pair<Integer, Integer> trailhead : trailheads) {
            for (Pair<Integer, Integer> summit : summits) {
                List<GraphPath<Pair<Integer, Integer>, DefaultEdge>> allPaths
                        = allDirectedPaths.getAllPaths(trailhead, summit, true, null);
                count += allPaths.size();
            }
        }

        System.out.println("\nPart 2 > Result: " + count);
        return String.valueOf(count);
    }

    private static int getNeighborHeight(String otherLetter) {
        return otherLetter.charAt(0);
    }

}