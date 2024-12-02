package de.steffzilla.aoc.y2022;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import de.steffzilla.aoc.AocUtils;
import de.steffzilla.aoc.CharacterField;
import de.steffzilla.aoc.DijkstraWithPriorityQueue;
import de.steffzilla.aoc.NodeWrapper;
import org.javatuples.Pair;

import java.util.*;

/**
 * Does not work! Probably the creation of the graph is buggy!
 * Plus Dijkstra is to "much" to solve this easily, breadth first search is better.
 */
public class Aoc2022_12_Dijkstra {

    private static final String DAY = "12";
    private static final String YEAR = "2022";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    //public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;
    private static final String START = "S";
    private static final String END = "E";
    private static final String START_HEIGHT = "a";
    private static final String END_HEIGHT = "z";


    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);

        part1(inputLines);
        //part2(inputLines);
    }

    private static void part1(List<String> inputLines) {
        CharacterField characterField = new CharacterField(inputLines);
        Pair<Integer, Integer> startNode = null;
        Pair<Integer, Integer> goalNode = null;
        MutableValueGraph<Pair<Integer, Integer>, Integer> graph = ValueGraphBuilder.directed().build();
        int allowedStepsUpward = 1;

        for (int y = 0; y < characterField.getMaxY(); y++) {
            for (int x = 0; x < characterField.getMaxX(); x++) {
                String letter = characterField.getCharacterAt(x, y);
                // check if start or end
                if(START.equals(letter)) {
                    startNode = new Pair<>(x, y);
                    letter = START_HEIGHT;
                } else if(END.equals(letter)) {
                    goalNode = new Pair<>(x, y);
                    letter = END_HEIGHT;
                }
                // is there a node left of the current that is reachable?
                if(x+1 < characterField.getMaxX()) {
                    String neighborLetter = characterField.getCharacterAt(x+1, y);
                    int neighborHeight = getNeighborHeight(neighborLetter);
                    if(neighborHeight <= letter.charAt(0)+allowedStepsUpward) {
                        System.out.println(letter+"->"+neighborLetter);
                        graph.putEdgeValue(new Pair<>(x, y), new Pair<>(x+1, y), 1); // steps have the same cost = 1
                    }
                    // way back?
                    if(letter.charAt(0) <= neighborHeight+allowedStepsUpward) {
                        System.out.println(letter+"<-"+neighborLetter);
                        graph.putEdgeValue(new Pair<>(x+1, y), new Pair<>(x, y), 1); // steps have the same cost = 1
                    }
                }
                // is there a node below of the current that is reachable?
                if(y+1 < characterField.getMaxY()) {
                    String neighborLetter = characterField.getCharacterAt(x, y+1);
                    int neighborHeight = getNeighborHeight(neighborLetter);
                    if(neighborHeight <= letter.charAt(0)+allowedStepsUpward) {
                        System.out.println(letter+"v"+neighborLetter);
                        graph.putEdgeValue(new Pair<>(x, y), new Pair<>(x, y+1), 1); // steps have the same cost = 1
                    }
                    // way back?
                    if(letter.charAt(0) <= neighborHeight+allowedStepsUpward) {
                        System.out.println(letter+"^"+neighborLetter);
                        graph.putEdgeValue(new Pair<>(x, y+1), new Pair<>(x, y), 1); // steps have the same cost = 1
                    }
                }
            }
        }
        if (startNode == null || goalNode == null) {
            throw new IllegalStateException("Start or goal node not found!");
        }
        System.out.println(graph);
        List<NodeWrapper<Pair<Integer, Integer>>> shortestPath =
                DijkstraWithPriorityQueue.findShortestPath(graph, startNode, goalNode);
        System.out.println("\nPart 1 > Result: " + shortestPath.size());
    }

    private static int getNeighborHeight(String otherLetter) {
        if(START.equals(otherLetter)) {
            return START_HEIGHT.charAt(0);
        } else if(END.equals(otherLetter)) {
            return END_HEIGHT.charAt(0);
        }
        return otherLetter.charAt(0);
    }

    private static void part2(List<String> inputLines) {
        for (String line : inputLines) {

        }
        System.out.println("\nPart 2 > Result: " + "x");
    }

}