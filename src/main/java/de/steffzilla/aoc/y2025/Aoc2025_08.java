package de.steffzilla.aoc.y2025;

import de.steffzilla.aoc.AocUtils;
import de.steffzilla.aoc.MathUtils;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

import java.util.*;

public class Aoc2025_08 {

    private static final String DAY = "08";
    private static final String YEAR = "2025";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    static final String example = """
            162,817,812
            57,618,57
            906,360,560
            592,479,940
            352,342,300
            466,668,158
            542,29,236
            431,825,988
            739,650,466
            52,470,668
            216,146,977
            819,987,18
            117,168,530
            805,96,715
            346,949,466
            970,615,88
            941,993,340
            862,61,35
            984,92,344
            425,690,689
            """;
    static final int SAMPLE_MAX_CONNECTIONS_PART1 = 10;
    static final int REAL_MAX_CONNECTIONS_PART1 = 1000;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        //List<String> inputLines = example.lines().toList();
        //solve(inputLines, SAMPLE_MAX_CONNECTIONS_PART1);

        List<String> inputLines =
                AocUtils.getStringList(PATH);
        solve(inputLines, REAL_MAX_CONNECTIONS_PART1);
    }

    static Pair<String, String> solve(List<String> inputLines, int maxConnections) {
        return new Pair<>(part1(inputLines, maxConnections), part2(inputLines));
    }

    private static String part1(List<String> inputLines, int maxConnections) {
        List<Triplet<Integer, Integer, Integer>> boxes = getBoxes(inputLines);

        Graph<Triplet<Integer, Integer, Integer>, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        TreeMap<Double, Set<Pair<Triplet<Integer, Integer, Integer>, Triplet<Integer, Integer, Integer>>>> distances =
                getDistancesAndAddVerticesToGraph(boxes, graph);

        int counter = 0;
        for (Map.Entry<Double, Set<Pair<Triplet<Integer, Integer, Integer>, Triplet<Integer, Integer, Integer>>>> entry : distances.entrySet()) {
            Set<Pair<Triplet<Integer, Integer, Integer>, Triplet<Integer, Integer, Integer>>> pairs = entry.getValue();
            for (Pair<Triplet<Integer, Integer, Integer>, Triplet<Integer, Integer, Integer>> pair : pairs) {
                Triplet<Integer, Integer, Integer> box1 = pair.getValue0();
                Triplet<Integer, Integer, Integer> box2 = pair.getValue1();
                ConnectivityInspector<Triplet<Integer, Integer, Integer>, DefaultEdge> inspector =
                        new ConnectivityInspector<>(graph);
                if (!inspector.pathExists(box1, box2)) {
                    graph.addEdge(box1, box2);
                }
                counter++; // all connections need to be counted, the newly created ones and the once that even existed before
                if (counter == maxConnections) break;
            }
            if (counter == maxConnections) break;
        }
        //System.out.println("Graph building is done");
        long result = getResultPart1(graph);
        System.out.println("\nPart 1 > Result: " + result);
        return String.valueOf(result);
    }

    private static @NonNull TreeMap<Double, Set<Pair<Triplet<Integer, Integer, Integer>, Triplet<Integer, Integer, Integer>>>> getDistancesAndAddVerticesToGraph(List<Triplet<Integer, Integer, Integer>> boxes, Graph<Triplet<Integer, Integer, Integer>, DefaultEdge> graph) {
        TreeMap<Double, Set<Pair<Triplet<Integer, Integer, Integer>, Triplet<Integer, Integer, Integer>>>> distances =
                new TreeMap<>();
        for (int i = 0; i < boxes.size(); i++) {
            Triplet<Integer, Integer, Integer> box = boxes.get(i);
            graph.addVertex(box);
            for (int j = i + 1; j < boxes.size(); j++) {
                Triplet<Integer, Integer, Integer> otherBox = boxes.get(j);
                if (!box.equals(otherBox)) {
                    double distance3D = MathUtils.euclideanDistance3D(box, otherBox);
                    Set<Pair<Triplet<Integer, Integer, Integer>, Triplet<Integer, Integer, Integer>>> pairs;
                    if (distances.containsKey(distance3D)) {
                        pairs = distances.get(distance3D);
                    } else {
                        pairs = new HashSet<>();
                        distances.put(distance3D, pairs);
                    }
                    pairs.add(new Pair<>(box, otherBox));
                }
            }
        }
        return distances;
    }

    private static long getResultPart1(Graph<Triplet<Integer, Integer, Integer>, DefaultEdge> graph) {
        ConnectivityInspector<Triplet<Integer, Integer, Integer>, DefaultEdge> inspector =
                new ConnectivityInspector<>(graph);
        List<Set<Triplet<Integer, Integer, Integer>>> connectedComponents = inspector.connectedSets();
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        for (Set<Triplet<Integer, Integer, Integer>> component : connectedComponents) {
            int size = component.size();
            if (size > max1) {
                max3 = max2;
                max2 = max1;
                max1 = size;
            } else if (size > max2) {
                max3 = max2;
                max2 = size;
            } else if (size > max3) {
                max3 = size;
            }
        }
        //System.out.println(max1 + "|" + max2 + "|" + max3);
        return (long) max1 * max2 * max3;
    }

    private static @NonNull List<Triplet<Integer, Integer, Integer>> getBoxes(List<String> inputLines) {
        List<Triplet<Integer, Integer, Integer>> boxes = new ArrayList<>();
        for (String line : inputLines) {
            String[] split = line.split(",");
            Triplet<Integer, Integer, Integer> box =
                    new Triplet<>(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]));
            boxes.add(box);
        }
        return boxes;
    }

    private static String part2(List<String> inputLines) {
        List<Triplet<Integer, Integer, Integer>> boxes = getBoxes(inputLines);

        Graph<Triplet<Integer, Integer, Integer>, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        TreeMap<Double, Set<Pair<Triplet<Integer, Integer, Integer>, Triplet<Integer, Integer, Integer>>>> distances =
                getDistancesAndAddVerticesToGraph(boxes, graph);

        long result = Long.MIN_VALUE;
        for (Map.Entry<Double, Set<Pair<Triplet<Integer, Integer, Integer>, Triplet<Integer, Integer, Integer>>>> entry : distances.entrySet()) {
            Set<Pair<Triplet<Integer, Integer, Integer>, Triplet<Integer, Integer, Integer>>> pairs = entry.getValue();
            for (Pair<Triplet<Integer, Integer, Integer>, Triplet<Integer, Integer, Integer>> pair : pairs) {
                Triplet<Integer, Integer, Integer> box1 = pair.getValue0();
                Triplet<Integer, Integer, Integer> box2 = pair.getValue1();
                ConnectivityInspector<Triplet<Integer, Integer, Integer>, DefaultEdge> inspector =
                        new ConnectivityInspector<>(graph);
                if (!inspector.pathExists(box1, box2)) {
                    graph.addEdge(box1, box2);
                }
                // find first connection which causes all the junction boxes to form a single circuit
                inspector = new ConnectivityInspector<>(graph);
                List<Set<Triplet<Integer, Integer, Integer>>> connectedComponents = inspector.connectedSets();
                if (connectedComponents.size() == 1) {
                    result = (long) box1.getValue0() * box2.getValue0();
                    break;
                }
            }
            if (result != Long.MIN_VALUE) {
                break;
            }
        }

        System.out.println("\nPart 2 > Result: " + result);
        return String.valueOf(result);
    }

}