package de.steffzilla.aoc;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DijkstraWithPriorityQueueTest {
/*
    @Test
    public void testFindShortestUndirectedPath() {
        ValueGraph<String, Integer> graph = createSampleGraphUndirected();
        List<NodeWrapper<String>> shortestPath = DijkstraWithPriorityQueue.findShortestPath(graph, "D", "H");
        Assert.assertEquals(4, shortestPath.size());

        Assert.assertEquals("D", shortestPath.get(0).getNode());
        Assert.assertEquals("C", shortestPath.get(1).getNode());
        Assert.assertEquals("G", shortestPath.get(2).getNode());
        Assert.assertEquals("H", shortestPath.get(3).getNode());
        Assert.assertEquals(9, shortestPath.get(3).getTotalDistance());
    }

    @Test
    public void testFindShortestDirectedPath() {
        ValueGraph<String, Integer> graph = createSampleGraphDirected();
        List<NodeWrapper<String>> shortestPath = DijkstraWithPriorityQueue.findShortestPath(graph, "A", "C");
        Assert.assertEquals(2, shortestPath.size());

        Assert.assertEquals("A", shortestPath.get(0).getNode());
        Assert.assertEquals("C", shortestPath.get(1).getNode());
        Assert.assertEquals(2, shortestPath.get(1).getTotalDistance());
    }


    private static ValueGraph<String, Integer> createSampleGraphUndirected() {
        MutableValueGraph<String, Integer> graph = ValueGraphBuilder.undirected().build();
        graph.putEdgeValue("A", "C", 2);
        graph.putEdgeValue("A", "E", 3);
        graph.putEdgeValue("B", "E", 5);
        graph.putEdgeValue("B", "I", 15);
        graph.putEdgeValue("C", "D", 3);
        graph.putEdgeValue("C", "G", 2);
        graph.putEdgeValue("D", "E", 1);
        graph.putEdgeValue("D", "F", 4);
        graph.putEdgeValue("E", "F", 6);
        graph.putEdgeValue("F", "H", 7);
        graph.putEdgeValue("G", "H", 4);
        graph.putEdgeValue("H", "I", 3);
        return graph;
    }

    private static ValueGraph<String, Integer> createSampleGraphDirected() {
        MutableValueGraph<String, Integer> graph = ValueGraphBuilder.directed().build();
        graph.putEdgeValue("A", "B", 1);
        graph.putEdgeValue("B", "A", 10);
        graph.putEdgeValue("B", "C", 3);
        graph.putEdgeValue("C", "B", 10);
        graph.putEdgeValue("A", "C", 2);
        return graph;
    }*/

}
