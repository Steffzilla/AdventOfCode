package com.learning;

import com.google.common.graph.MutableNetwork;
import com.google.common.graph.NetworkBuilder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KargerAlgorithmTest {

    // Probabilistic tests: the repetition counts keep the failure probability far below 1e-15

    private static final int NUMBER_OF_DRAWS = 1000;
    // one run finds the minimum cut with probability >= 2/(n(n-1)) = 1/45 for n = 10 nodes
    private static final int NUMBER_OF_RUNS = 2000;

    private static MutableNetwork<String, EdgeType> createGraph(List<List<String>> adjacencyLists) {
        MutableNetwork<String, EdgeType> graph = NetworkBuilder
                .undirected()
                .allowsParallelEdges(true)
                .allowsSelfLoops(false)
                .build();
        for (List<String> adjacencyList : adjacencyLists) {
            KargerAlgorithm.buildNeighborhood(graph, new ArrayList<>(adjacencyList));
        }
        return graph;
    }

    /**
     * Two complete graphs with 5 nodes each (a1..a5 and b1..b5), connected by the 3 edges a1-b1, a2-b2, a3-b3.
     * The minimum cut consists of exactly these 3 edges.
     */
    private static MutableNetwork<String, EdgeType> createTwoClustersWithCutOf3() {
        return createGraph(List.of(
                List.of("a1", "a2", "a3", "a4", "a5", "b1"),
                List.of("a2", "a3", "a4", "a5", "b2"),
                List.of("a3", "a4", "a5", "b3"),
                List.of("a4", "a5"),
                List.of("b1", "b2", "b3", "b4", "b5"),
                List.of("b2", "b3", "b4", "b5"),
                List.of("b3", "b4", "b5"),
                List.of("b4", "b5")));
    }

    @Test
    public void testChooseNodeAtRandom() {
        MutableNetwork<String, EdgeType> graph = createGraph(List.of(
                List.of("a", "b", "c"),
                List.of("c", "d")));
        Set<String> chosenNodes = new HashSet<>();
        for (int i = 0; i < NUMBER_OF_DRAWS; i++) {
            chosenNodes.add(KargerAlgorithm.chooseNodeAtRandom(graph));
        }
        assertEquals(Set.of("a", "b", "c", "d"), chosenNodes);
    }

    @Test
    public void testChooseNeighborAtRandom() {
        MutableNetwork<String, EdgeType> graph = createGraph(List.of(
                List.of("a", "b", "c"),
                List.of("c", "d")));
        Set<String> chosenNeighbors = new HashSet<>();
        for (int i = 0; i < NUMBER_OF_DRAWS; i++) {
            chosenNeighbors.add(KargerAlgorithm.chooseNeighborAtRandom(graph, "a"));
        }
        assertEquals(Set.of("b", "c"), chosenNeighbors);
    }

    @Test
    public void testFindMinimumCut() {
        int smallestCutFound = Integer.MAX_VALUE;
        for (int i = 0; i < NUMBER_OF_RUNS; i++) {
            smallestCutFound = Math.min(smallestCutFound, KargerAlgorithm.findMinimumCut(createTwoClustersWithCutOf3()));
        }
        assertEquals(3, smallestCutFound);
    }

}
