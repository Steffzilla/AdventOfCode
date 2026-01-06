package de.steffzilla.aoc.y2022;

import de.steffzilla.competitive.AocUtils;
import de.steffzilla.competitive.BreadthFirstSearch;
import de.steffzilla.competitive.CharacterField;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Aoc2022_12_BreadthFirst {

    private static final String DAY = "12";
    private static final String YEAR = "2022";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;
    private static final String START = "S";
    private static final String END = "E";
    private static final String START_HEIGHT = "a";
    private static final String END_HEIGHT = "z";


    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);

        //part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines) {
        CharacterField charField = new CharacterField(inputLines);
        Pair<Integer, Integer> startNode = null;
        Pair<Integer, Integer> goalNode = null;
        int allowedStepsUpward = 1;
        HashMap<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> graph = new HashMap<>();

        for (int y = 0; y < charField.getMaxY(); y++) {
            for (int x = 0; x < charField.getMaxX(); x++) {
                String letter = charField.getCharacterAt(x, y);
                List<Pair<Integer, Integer>> neighbors = new ArrayList<>();
                // check if start or end
                if (START.equals(letter)) {
                    startNode = new Pair<>(x, y);
                    letter = START_HEIGHT;
                    System.out.println("Start node: " + startNode);
                } else if (END.equals(letter)) {
                    goalNode = new Pair<>(x, y);
                    letter = END_HEIGHT;
                    System.out.println("Goal node: " + goalNode);
                }
                if(charField.isContained(x, y - 1)) {
                    int neighborHeight = getNeighborHeight(charField.getCharacterAt(x, y - 1));
                    if (neighborHeight <= letter.charAt(0) + allowedStepsUpward) {
                        neighbors.add(new Pair<>(x, y-1));
                    }
                }
                if(charField.isContained(x, y + 1)) {
                    int neighborHeight = getNeighborHeight(charField.getCharacterAt(x, y + 1));
                    if (neighborHeight <= letter.charAt(0) + allowedStepsUpward) {
                        neighbors.add(new Pair<>(x, y + 1));
                    }
                }
                if(charField.isContained(x - 1, y)) {
                    int neighborHeight = getNeighborHeight(charField.getCharacterAt(x - 1, y));
                    if (neighborHeight <= letter.charAt(0) + allowedStepsUpward) {
                        neighbors.add(new Pair<>(x - 1, y));
                    }
                }
                if(charField.isContained(x + 1, y)) {
                    int neighborHeight = getNeighborHeight(charField.getCharacterAt(x + 1, y));
                    if (neighborHeight <= letter.charAt(0) + allowedStepsUpward) {
                        neighbors.add(new Pair<>(x + 1, y));
                    }
                }
                graph.put(new Pair<>(x, y), neighbors);
            }
        }
        BreadthFirstSearch<Pair<Integer, Integer>> bfs = new BreadthFirstSearch<>(graph);
        System.out.println(graph.get(startNode));
        System.out.println(graph.get(goalNode));
        List<Pair<Integer, Integer>> path = bfs.search(startNode, goalNode);
        System.out.println("\nPart 1 > Result: " + (path.size() - 1)); // steps is 1 less than nodes
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




        CharacterField charField = new CharacterField(inputLines);
        List<Pair<Integer, Integer>> startNodes = new ArrayList<>();
        Pair<Integer, Integer> goalNode = null;
        int allowedStepsUpward = 1;
        HashMap<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> graph = new HashMap<>();

        for (int y = 0; y < charField.getMaxY(); y++) {
            for (int x = 0; x < charField.getMaxX(); x++) {
                String letter = charField.getCharacterAt(x, y);
                char currentHeight = letter.charAt(0);
                List<Pair<Integer, Integer>> neighbors = new ArrayList<>();
                // check if start or end
                if (START.equals(letter)) {
                    letter = START_HEIGHT;
                } else if (END.equals(letter)) {
                    goalNode = new Pair<>(x, y);
                    letter = END_HEIGHT;
                    System.out.println("Goal node: " + goalNode);
                }
                if(START_HEIGHT.equals(letter)) {
                    startNodes.add(new Pair<>(x, y));
                }
                if(charField.isContained(x, y - 1)) {
                    int neighborHeight = getNeighborHeight(charField.getCharacterAt(x, y - 1));
                    if (neighborHeight <= currentHeight + allowedStepsUpward) {
                        neighbors.add(new Pair<>(x, y - 1));
                    }
                }
                if(charField.isContained(x, y + 1)) {
                    int neighborHeight = getNeighborHeight(charField.getCharacterAt(x, y + 1));
                    if (neighborHeight <= currentHeight + allowedStepsUpward) {
                        neighbors.add(new Pair<>(x, y + 1));
                    }
                }
                if(charField.isContained(x - 1, y)) {
                    int neighborHeight = getNeighborHeight(charField.getCharacterAt(x - 1, y));
                    if (neighborHeight <= currentHeight + allowedStepsUpward) {
                        neighbors.add(new Pair<>(x - 1, y));
                    }
                }
                if(charField.isContained(x + 1, y)) {
                    int neighborHeight = getNeighborHeight(charField.getCharacterAt(x + 1, y));
                    if (neighborHeight <= currentHeight + allowedStepsUpward) {
                        neighbors.add(new Pair<>(x + 1, y));
                    }
                }
                graph.put(new Pair<>(x, y), neighbors);
            }
        }
        BreadthFirstSearch<Pair<Integer, Integer>> bfs = new BreadthFirstSearch<>(graph);
        int shortestPathLength = Integer.MAX_VALUE;
        for (Pair<Integer, Integer> startNode : startNodes) {
            System.out.println(graph.get(startNode));
            System.out.println(graph.get(goalNode));
            List<Pair<Integer, Integer>> path = bfs.search(startNode, goalNode);

            if(path.size() > 0 // needs to be a valid path
                    && (path.size() - 1) < shortestPathLength) { // steps is 1 less than nodes
                shortestPathLength = path.size() - 1;
            }
        }
        System.out.println("\nPart 1 > Result: " + shortestPathLength);
    }

}