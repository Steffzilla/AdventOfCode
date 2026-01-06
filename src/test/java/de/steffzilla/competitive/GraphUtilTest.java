package de.steffzilla.competitive;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphUtilTest {

    @Test
    public void getDfsOrderIterative() {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 3));
        graph.put(2, Arrays.asList(0, 3));
        graph.put(3, Arrays.asList(0, 4));
        graph.put(4, List.of(3));
        List<Integer> dfsOrderIterative = GraphUtil.getDfsOrderIterative(graph, 1);
        assertTrue(dfsOrderIterative != null && dfsOrderIterative.size()==5);
        assertTrue(dfsOrderIterative.containsAll(graph.keySet()));
    }
}