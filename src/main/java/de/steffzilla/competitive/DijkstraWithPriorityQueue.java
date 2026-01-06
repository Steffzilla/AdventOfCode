package de.steffzilla.competitive;

import com.google.common.graph.ValueGraph;

import java.util.*;

// Inspired by https://www.happycoders.eu/de/algorithmen/dijkstra-algorithmus-java/#Dijkstras_Algorithmus_-_Java-Quellcode_mit_PriorityQueue
public class DijkstraWithPriorityQueue {

    public static <N> List<NodeWrapper<N>> findShortestPath(ValueGraph<N, Integer> graph, N source, N target) {
        PriorityQueue<NodeWrapper<N>> queue = new PriorityQueue<>();
        Map<N, NodeWrapper<N>> nodeWrappers = new HashMap<>();
        Set<N> shortestPathFound = new HashSet<>();

        NodeWrapper<N> sourceWrapper = new NodeWrapper<>(source, 0, null);
        nodeWrappers.put(source, sourceWrapper);
        queue.add(sourceWrapper);

        while (!queue.isEmpty()) {
            NodeWrapper<N> nodeWrapper = queue.poll();
            N node = nodeWrapper.getNode();
            shortestPathFound.add(node);

            // Have we reached the target? --> Build and return the path
            if (node.equals(target)) {
                System.out.println("Total distance to goal: "+nodeWrapper.getTotalDistance());
                return buildPath(nodeWrapper);
            }

            // Iterate over all neighbors
            Set<N> neighbors = graph.adjacentNodes(node);
            for (N neighbor : neighbors) {
                // Ignore neighbor if shortest path already found
                if (shortestPathFound.contains(neighbor)) {
                    continue;
                }

                // Calculate total distance to neighbor via current node
                int distance =
                        graph.edgeValue(node, neighbor).orElseThrow(IllegalStateException::new);
                int totalDistance = nodeWrapper.getTotalDistance() + distance;

                // Neighbor not yet discovered?
                NodeWrapper<N> neighborWrapper = nodeWrappers.get(neighbor);
                if (neighborWrapper == null) {
                    neighborWrapper = new NodeWrapper<>(neighbor, totalDistance, nodeWrapper);
                    nodeWrappers.put(neighbor, neighborWrapper);
                    queue.add(neighborWrapper);
                }

                // Neighbor discovered, but total distance via current node is shorter?
                // --> Update total distance and predecessor
                else if (totalDistance < neighborWrapper.getTotalDistance()) {
                    neighborWrapper.setTotalDistance(totalDistance);
                    neighborWrapper.setPredecessor(nodeWrapper);

                    // The position in the PriorityQueue won't change automatically;
                    // we have to remove and reinsert the node
                    queue.remove(neighborWrapper);
                    queue.add(neighborWrapper);
                }
            }
        }

        // All reachable nodes were visited but the target was not found
        return null;
    }

    private static <N> List<NodeWrapper<N>> buildPath(NodeWrapper<N> nodeWrapper) {
        List<NodeWrapper<N>> path = new ArrayList<>();
        while (nodeWrapper != null) {
            path.add(nodeWrapper);
            nodeWrapper = nodeWrapper.getPredecessor();
        }
        Collections.reverse(path);
        return path;
    }

}
