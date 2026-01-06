package de.steffzilla.aoc.y2024;

import de.steffzilla.competitive.CharacterField;
import org.javatuples.Pair;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

/**
 * Utility methods related to {@link CharacterField}
 */
public class CharacterFieldUtils {

    /**
     * Returns an undirected Graph based on the {@link CharacterField} and the blocking character blocking.
     */
    public static Graph<Pair<Integer, Integer>, DefaultEdge> calculateUndirectedGraph(CharacterField cf, String blocking) {
        Graph<Pair<Integer, Integer>, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        // add all nodes
        for (int y = 0; y < cf.getMaxY(); y++) {
            for (int x = 0; x < cf.getMaxX(); x++) {
                graph.addVertex(new Pair<>(x, y));
            }
        }
        // add edges
        for (int y = 0; y < cf.getMaxY(); y++) {
            for (int x = 0; x < cf.getMaxX(); x++) {
                String character = cf.getCharacterAt(x, y);
                if (!blocking.equals(character)) {
                    Pair<Integer, Integer> currentNode = new Pair<>(x, y);
                    if (cf.isContained(x, y - 1) && !blocking.equals(cf.getCharacterAt(x, y - 1))) {
                        graph.addEdge(currentNode, new Pair<>(x, y - 1));
                    }
                    if (cf.isContained(x, y + 1) && !blocking.equals(cf.getCharacterAt(x, y + 1))) {
                        graph.addEdge(currentNode, new Pair<>(x, y + 1));
                    }
                    if (cf.isContained(x - 1, y) && !blocking.equals(cf.getCharacterAt(x - 1, y))) {
                        graph.addEdge(currentNode, new Pair<>(x - 1, y));
                    }
                    if (cf.isContained(x + 1, y) && !blocking.equals(cf.getCharacterAt(x + 1, y))) {
                        graph.addEdge(currentNode, new Pair<>(x + 1, y));
                    }
                }
            }
        }
        return graph;
    }
}
