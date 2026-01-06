package de.steffzilla.aoc.y2025;

import de.steffzilla.competitive.Utils;
import org.javatuples.Pair;
import org.jetbrains.annotations.NotNull;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.TopologicalOrderIterator;

import java.util.*;

public class Aoc2025_11 {

    private static final String DAY = "11";
    private static final String YEAR = "2025";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;
    private static final String YOU = "you";
    private static final String OUT = "out";
    private static final String DAC = "dac";
    private static final String FFT = "fft";
    private static final String SVR = "svr";


    static final String examplePart1 = """
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

    static final String examplePart2 = """
            svr: aaa bbb
            aaa: fft
            fft: ccc
            bbb: tty
            tty: ccc
            ccc: ddd eee
            ddd: hub
            hub: fff
            eee: dac
            dac: fff
            fff: ggg hhh
            ggg: out
            hhh: out
            """;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        //List<String> inputLines = examplePart1.lines().toList();
        //List<String> inputLines = examplePart2.lines().toList();
        List<String> inputLines =
                Utils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        Graph<String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        // first the vertices with outgoing connections --> vertices without outgoing connections aren't added!
        graph.addVertex(OUT);
        for (String line : inputLines) {
            String[] inputParts = line.split(": ");
            graph.addVertex(inputParts[0]);
        }
        Set<String> vertices = graph.vertexSet();
        // then the edges
        for (String line : inputLines) {
            String[] inputParts = line.split(": ");
            String currentDevice = inputParts[0];
            String[] otherDevices = inputParts[1].split(" ");
            for (String otherDevice : otherDevices) {
                graph.addEdge(currentDevice, otherDevice);
            }
        }

        //GraphUtil.printDirectedGraphBaseData(graph);

        // only the real input is valid for part1 and part2
        if (vertices.contains(FFT) && vertices.contains(YOU)) {
            return new Pair<>(part1(graph), part2(graph));
        } else if (vertices.contains(YOU)) {
            return new Pair<>(part1(graph), "");
        } else {
            return new Pair<>("", part2(graph));
        }
    }

    private static String part1(Graph<String, DefaultEdge> graph) {
        AllDirectedPaths<String, DefaultEdge> allDirectedPaths = new AllDirectedPaths<>(graph);
        List<GraphPath<String, DefaultEdge>> allPaths
                = allDirectedPaths.getAllPaths(YOU, OUT, true, null);
        long result = allPaths.size();
        System.out.println("\nPart 1 > Result: " + result);
        return String.valueOf(result);
    }

    private static String part2(Graph<String, DefaultEdge> graph) {
        // Analyzing the graph has shown that it is a Directed Acyclic Graph
        // With this knowledge the topological order can be used

        TopologicalOrderIterator<String, DefaultEdge> it = new TopologicalOrderIterator<>(graph);
        List<String> topoOrder = new ArrayList<>();
        while (it.hasNext()) {
            String nextVertex = it.next();
            topoOrder.add(nextVertex);
        }

        int fftIndex = topoOrder.indexOf(FFT);
        int dacIndex = topoOrder.indexOf(DAC);

        int iP1;
        int iP2;
        String p1;
        String p2;
        if (fftIndex < dacIndex) {
            p1 = FFT;
            p2 = DAC;
            iP1 = fftIndex;
            iP2 = dacIndex;
        } else if (fftIndex > dacIndex) {
            p1 = DAC;
            p2 = FFT;
            iP1 = dacIndex;
            iP2 = fftIndex;
        } else {
            throw new IllegalStateException("FFT and DAC need to be in the order. One of them needs to have the smaller index!");
        }

        Map<String, Long> pathsFromSource = getPathsFromSource(graph, topoOrder);
        Map<String, Long> pathsToSink = getPathsToSink(graph, topoOrder);
        Map<String, Long> pathsBetween = getPathsBetween(graph, p1, pathsFromSource, iP1, iP2, topoOrder);

        long pathsThroughP1P2 = pathsBetween.getOrDefault(p2, 0L) * pathsToSink.get(p2);
        System.out.println("\nPart 2 > Result: " + pathsThroughP1P2);
        return String.valueOf(pathsThroughP1P2);
    }

    private static @NotNull Map<String, Long> getPathsBetween(Graph<String, DefaultEdge> graph, String p1, Map<String, Long> pathsFromSource, int iP1, int iP2, List<String> topoOrder) {
        Map<String, Long> pathsBetween = new HashMap<>();

        // Start value: all paths that reach P1
        pathsBetween.put(p1, pathsFromSource.get(p1));

        for (int i = iP1; i <= iP2; i++) {
            String v = topoOrder.get(i);
            long count = pathsBetween.getOrDefault(v, 0L);

            if (count == 0) continue;

            for (DefaultEdge e : graph.outgoingEdgesOf(v)) {
                String w = graph.getEdgeTarget(e);

                // Only continue counting if w lies between P1 and P2.
                int wi = topoOrder.indexOf(w);
                if (wi <= iP2) {
                    pathsBetween.merge(w, count, Long::sum);
                }
            }
        }
        return pathsBetween;
    }

    private static @NotNull Map<String, Long> getPathsFromSource(Graph<String, DefaultEdge> graph, List<String> topoOrder) {
        Map<String, Long> pathsFromSource = new HashMap<>();
        pathsFromSource.put(SVR, 1L);

        for (String v : topoOrder) {
            long count = pathsFromSource.getOrDefault(v, 0L);
            for (DefaultEdge e : graph.outgoingEdgesOf(v)) {
                String w = graph.getEdgeTarget(e);
                pathsFromSource.merge(w, count, Long::sum);
            }
        }
        return pathsFromSource;
    }

    private static @NotNull Map<String, Long> getPathsToSink(Graph<String, DefaultEdge> graph, List<String> topoOrder) {
        Map<String, Long> pathsToSink = new HashMap<>();
        pathsToSink.put(OUT, 1L);

        List<String> reverseTopo = new ArrayList<>(topoOrder);
        Collections.reverse(reverseTopo);

        for (String v : reverseTopo) {
            long count = pathsToSink.getOrDefault(v, 0L);
            for (DefaultEdge e : graph.incomingEdgesOf(v)) {
                String u = graph.getEdgeSource(e);
                pathsToSink.merge(u, count, Long::sum);
            }
        }
        return pathsToSink;
    }

}