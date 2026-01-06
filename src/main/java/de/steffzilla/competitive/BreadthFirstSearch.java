package de.steffzilla.competitive;

import com.google.common.collect.Lists;

import java.util.*;

public class BreadthFirstSearch<T> {

    private final HashMap<T, List<T>> graph;

    /**
     * @param graph Graph as Adjacency List
     */
    public BreadthFirstSearch(HashMap<T, List<T>>  graph) {
        this.graph = graph;
    }

    /**
     * Searches one shortest path between startNode and endNode
     * @param startNode
     * @param endNode
     * @return List that contains one shortest path or an empty list, if there is no path from start to end
     */
    public List<T> search(T startNode, T endNode) {
        HashMap<T, T> previousNodes = solve(startNode);
        return reconstructPath(startNode, endNode, previousNodes);
    }

    private HashMap<T, T> solve(T startNode) {
        HashMap<T, T> previous = new HashMap<>();
        Set<T> visited = new HashSet<>();
        visited.add(startNode);
        Deque<T> queue = new LinkedList<>();
        queue.add(startNode);

        while(!queue.isEmpty()) {
            T node = queue.poll();
            List<T> neighbors = graph.get(node);
            for (T neighbor : neighbors) {
                if(!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    previous.put(neighbor, node);
                }
            }
        }
        return previous;
    }

    private List<T> reconstructPath(T startNode, T endNode, HashMap<T, T> previous) {
        List<T> path = new ArrayList<>();
        path.add(endNode);
        T currentNode = endNode;
        while(previous.get(currentNode) != null) {
            T predecessor = previous.get(currentNode);
            path.add(predecessor);
            currentNode = predecessor;
        }
        List<T> reversedList = Lists.reverse(path);
        if(startNode.equals(reversedList.get(0))) {
            return reversedList;
        } else {
            return new ArrayList<>();
        }
    }

}
