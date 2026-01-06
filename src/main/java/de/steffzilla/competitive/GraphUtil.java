package de.steffzilla.competitive;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.connectivity.KosarajuStrongConnectivityInspector;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.alg.interfaces.StrongConnectivityAlgorithm;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class GraphUtil {

    // based on https://www.youtube.com/watch?v=PMMc4VsIacU
    // Basic iterative DFS
    // Delivers all connected nodes
    // Besides that not very useful without application to a certain problem
    public static <T> List<T> getDfsOrderIterative(HashMap<T, List<T>> graph, T startNode) {
        List<T> dfsOrder = new ArrayList<>();
        HashMap<T, Boolean> marked = new HashMap<>();
        for (T node : graph.keySet()) {
            marked.put(node, false);
        }
        Deque<T> stack = new LinkedList<>();
        stack.push(startNode);
        while (!stack.isEmpty()) {
            T currentNode = stack.pop();
            if (!marked.get(currentNode)) {
                dfsOrder.add(currentNode);
                marked.put(currentNode, true);
                for (T neighbor : graph.get(currentNode)) {
                    if (!marked.get(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        return dfsOrder;
    }

    public static <T> void printDirectedGraphBaseData(Graph<T, DefaultEdge> graph) {
        if (!graph.getType().isDirected()) {
            throw new IllegalArgumentException("Directed graph required");
        }
        Set<T> vertices = graph.vertexSet();
        int vertexCount = vertices.size();
        int edgeCount = graph.edgeSet().size();

        System.out.println("vertexCount=" + vertexCount + " edgeCount=" + edgeCount);

        long totalOut = 0;
        long totalIn = 0;

        int maxOut = 0;
        int maxIn = 0;
        int zeroOut = 0;
        int zeroIn = 0;

        Map<Integer, Integer> outDegreeHist = new HashMap<>();

        for (T v : vertices) {
            int out = graph.outDegreeOf(v);
            int in = graph.inDegreeOf(v);

            totalOut += out;
            totalIn += in;

            maxOut = Math.max(maxOut, out);
            maxIn = Math.max(maxIn, in);

            if (out == 0) zeroOut++;
            if (in == 0) zeroIn++;

            outDegreeHist.merge(out, 1, Integer::sum);
        }

        if (totalOut != edgeCount || totalIn != edgeCount) {
            throw new IllegalStateException("Graph degree inconsistency detected");
        }

        double avgOut = (vertexCount == 0) ? 0.0 : (double) totalOut / vertexCount;
        double avgIn = (vertexCount == 0) ? 0.0 : (double) totalIn / vertexCount;

        System.out.println("avgOut=" + avgOut + " avgIn=" + avgIn);
        System.out.println("maxOut=" + maxOut + ", maxIn=" + maxIn);
        System.out.println("zeroOut=" + zeroOut + ", zeroIn=" + zeroIn);

        System.out.println("outDegreeHistogram=" + outDegreeHist);

        StrongConnectivityAlgorithm<T, DefaultEdge> sccAlg =
                new KosarajuStrongConnectivityInspector<>(graph);

        System.out.println("Strongly Connected Components:");
        List<Set<T>> sccs = sccAlg.stronglyConnectedSets();
        long largeSccCount = sccs.stream()
                .filter(scc -> scc.size() > 1)
                .count();

        int maxSccSize = sccs.stream()
                .mapToInt(Set::size)
                .max()
                .orElse(0);

        System.out.println("SCCs total=" + sccs.size());
        System.out.println("SCCs with size>1=" + largeSccCount);
        System.out.println("Largest SCC size=" + maxSccSize);

        System.out.println();
        CycleDetector<T, DefaultEdge> detector = new CycleDetector<>(graph);

        boolean hasCycles = detector.detectCycles();
        System.out.println("Has cycles:" + hasCycles);

        ConnectivityInspector<T, DefaultEdge> inspector = new ConnectivityInspector<>(graph);
        System.out.println("Number of connected components: " + inspector.connectedSets().size());
    }

    public static <T> void printUndirectedGraphBaseData(Graph<T, DefaultEdge> graph) {
        if (graph.getType().isDirected()) {
            throw new IllegalArgumentException("Undirected graph required");
        }
        // TODO
    }

    // TODO implement DFS based on Guava graph, too

    // TODO move Karger’s algorithm to here

}
