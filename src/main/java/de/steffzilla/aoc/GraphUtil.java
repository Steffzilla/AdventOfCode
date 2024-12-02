package de.steffzilla.aoc;

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

    // TODO implement DFS based on Guava graph, too

    // TODO move Karger’s algorithm to here

}
