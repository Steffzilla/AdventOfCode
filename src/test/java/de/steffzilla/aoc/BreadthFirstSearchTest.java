package de.steffzilla.aoc;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BreadthFirstSearchTest {

    @Test
    public void testSmallGraph() {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1,2,3));
        graph.put(1, Arrays.asList(0,3));
        graph.put(2, Arrays.asList(0,3));
        graph.put(3, Arrays.asList(0,1,2,4));
        graph.put(4, Arrays.asList(3));
        BreadthFirstSearch<Integer> bfs = new BreadthFirstSearch<>(graph);
        List<Integer> shortestPath = bfs.search(0, 4);
        assertEquals(3, shortestPath.size());
        assertEquals(Integer.valueOf(0), shortestPath.get(0));
        assertEquals(Integer.valueOf(4), shortestPath.get(2));
    }

    public void testBiggerGraph() {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(7, 9, 11));
        graph.put(1, Arrays.asList(8, 10));
        graph.put(2, Arrays.asList(3, 12));
        graph.put(3, Arrays.asList(2, 4, 7));
        graph.put(4, Arrays.asList(3));
        graph.put(5, Arrays.asList(6));
        graph.put(6, Arrays.asList(5, 7));
        graph.put(7, Arrays.asList(0, 3, 6, 11));
        graph.put(8, Arrays.asList(1, 9, 12));
        graph.put(9, Arrays.asList(0, 8, 10));
        graph.put(10, Arrays.asList(1, 9));
        graph.put(11, Arrays.asList(0, 7));
        graph.put(12, Arrays.asList(2, 8));
        BreadthFirstSearch<Integer> bfs = new BreadthFirstSearch(graph);
        List<Integer> shortestPath = bfs.search(0, 5);
        assertEquals(4, shortestPath.size());
        assertEquals(Integer.valueOf(0), shortestPath.get(0));
        assertEquals(Integer.valueOf(7), shortestPath.get(1));
        assertEquals(Integer.valueOf(6), shortestPath.get(2));
        assertEquals(Integer.valueOf(5), shortestPath.get(3));

        shortestPath = bfs.search(10, 4);
        assertEquals(6, shortestPath.size());
        assertEquals(Integer.valueOf(10), shortestPath.get(0));
        assertEquals(Integer.valueOf(9), shortestPath.get(1));
        assertEquals(Integer.valueOf(0), shortestPath.get(2));
        assertEquals(Integer.valueOf(7), shortestPath.get(3));
        assertEquals(Integer.valueOf(3), shortestPath.get(4));
        assertEquals(Integer.valueOf(4), shortestPath.get(5));
    }

}
