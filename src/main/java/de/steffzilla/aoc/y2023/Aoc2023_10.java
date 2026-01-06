package de.steffzilla.aoc.y2023;

import de.steffzilla.competitive.AocUtils;
import de.steffzilla.competitive.BreadthFirstSearch;
import de.steffzilla.competitive.CharacterField;
import de.steffzilla.competitive.GraphUtil;
import org.javatuples.Pair;

import java.util.*;

public class Aoc2023_10 {

    private static final String DAY = "10";
    private static final String YEAR = "2023";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;
    public static final String START = "S";
    public static final String VERTICAL_PIPE = "|";
    public static final String HORIZONTAL_PIPE = "-";
    public static final String TOP_RIGHT_BEND = "L";
    public static final String TOP_LEFT_BEND = "J";
    public static final String BOTTOM_LEFT_BEND = "7";
    public static final String BOTTOM_RIGHT_BEND = "F";
    public static final String CYCLE_MARKER = "#";
    private static Pair<Integer, Integer> startNode = null;

    static final String example = """
            .....
            .S-7.
            .|.|.
            .L-J.
            .....
            """;

    static final String example2 = """
            -L|F7
            7S-7|
            L|7||
            -L-J|
            L|-JF
            """;

    static final String example3 = """
            7-F7-
            .FJ|7
            SJLL7
            |F--J
            LJ.LJ
            """;

    static final String example4 = """
            ...........
            .S-------7.
            .|F-----7|.
            .||.....||.
            .||.....||.
            .|L-7.F-J|.
            .|..|.|..|.
            .L--J.L--J.
            ...........
            """;

    static final String example5 = """
            .F----7F7F7F7F-7....
            .|F--7||||||||FJ....
            .||.FJ||||||||L7....
            FJL7L7LJLJ||LJ.L-7..
            L--J.L7...LJS7F-7L7.
            ....F-J..F7FJ|L7L7L7
            ....L7.F7||L7|.L7L7|
            .....|FJLJ|FJ|F7|.LJ
            ....FJL-7.||.||||...
            ....L---J.LJ.LJLJ...
            """;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        //List<String> inputLines = example4.lines().toList();
        List<String> inputLines = AocUtils.getStringList(PATH);

        CharacterField field = new CharacterField(inputLines);
        field.prettyPrint();
        System.out.println();

        HashMap<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> graph = readMaze(inputLines);

        //part1(graph);
        part2(field, graph);
    }

    private static HashMap<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> readMaze(List<String> inputLines) {
        CharacterField charField = new CharacterField(inputLines);

        HashMap<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> graph = new HashMap<>();
        for (int y = 0; y < charField.getMaxY(); y++) {
            for (int x = 0; x < charField.getMaxX(); x++) {
                String letter = charField.getCharacterAt(x, y);
                List<Pair<Integer, Integer>> neighbors = getNeighbors(letter, x, y, charField);
                if (!neighbors.isEmpty()) {
                    // optimization to keep the graph small: unconnected nodes don't matter
                    graph.put(new Pair<>(x, y), neighbors);
                }
            }
        }
        return graph;
    }

    private static List<Pair<Integer, Integer>> getNeighbors(String letter, int x, int y, CharacterField charField) {
        List<Pair<Integer, Integer>> neighbors = new ArrayList<>();
        // check if start
        if (START.equals(letter)) {
            startNode = new Pair<>(x, y);
            neighbors.addAll(addAbove(charField, x, y - 1));
            neighbors.addAll(addBelow(charField, x, y + 1));
            neighbors.addAll(addLeft(charField, x-1, y));
            neighbors.addAll(addRight(charField, x+1, y));
            if (neighbors.size()!=2) {
                throw new IllegalStateException("Wrong number of neighbors of S:"+neighbors);
            }
        } else if(HORIZONTAL_PIPE.equals(letter)) {
            neighbors.addAll(addLeft(charField, x-1, y));
            neighbors.addAll(addRight(charField, x+1, y));
        } else if(VERTICAL_PIPE.equals(letter)) {
            neighbors.addAll(addAbove(charField, x, y - 1));
            neighbors.addAll(addBelow(charField, x, y + 1));
        } else if(TOP_LEFT_BEND.equals(letter)) {
            neighbors.addAll(addLeft(charField, x - 1, y));
            neighbors.addAll(addAbove(charField, x, y - 1));
        } else if(TOP_RIGHT_BEND.equals(letter)) {
            neighbors.addAll(addRight(charField, x + 1, y));
            neighbors.addAll(addAbove(charField, x, y - 1));
        } else if(BOTTOM_LEFT_BEND.equals(letter)) {
            neighbors.addAll(addLeft(charField, x - 1, y));
            neighbors.addAll(addBelow(charField, x, y + 1));
        } else if(BOTTOM_RIGHT_BEND.equals(letter)) {
            neighbors.addAll(addRight(charField, x + 1, y));
            neighbors.addAll(addBelow(charField, x, y + 1));
        }
        return neighbors;
    }

    private static Collection<Pair<Integer, Integer>> addAbove(CharacterField charField, int x, int y) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        if(charField.isContained(x, y)) {
            String otherChar = charField.getCharacterAt(x, y);
            if(VERTICAL_PIPE.equals(otherChar) || BOTTOM_LEFT_BEND.equals(otherChar) ||
                    BOTTOM_RIGHT_BEND.equals(otherChar) || START.equals(otherChar)) {
                list.add(new Pair<>(x,y));
            }
        }
        return list;
    }

    private static Collection<Pair<Integer, Integer>> addBelow(CharacterField charField, int x, int y) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        if(charField.isContained(x, y)) {
            String otherChar = charField.getCharacterAt(x, y);
            if(VERTICAL_PIPE.equals(otherChar) || TOP_LEFT_BEND.equals(otherChar) ||
                    TOP_RIGHT_BEND.equals(otherChar) || START.equals(otherChar)) {
                list.add(new Pair<>(x,y));
            }
        }
        return list;
    }

    private static Collection<Pair<Integer, Integer>> addLeft(CharacterField charField, int x, int y) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        if(charField.isContained(x, y)) {
            String otherChar = charField.getCharacterAt(x, y);
            if(HORIZONTAL_PIPE.equals(otherChar) || BOTTOM_RIGHT_BEND.equals(otherChar) ||
                    TOP_RIGHT_BEND.equals(otherChar) || START.equals(otherChar)) {
                list.add(new Pair<>(x,y));
            }
        }
        return list;
    }

    private static Collection<Pair<Integer, Integer>> addRight(CharacterField charField, int x, int y) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        if(charField.isContained(x, y)) {
            String otherChar = charField.getCharacterAt(x, y);
            if(HORIZONTAL_PIPE.equals(otherChar) || BOTTOM_LEFT_BEND.equals(otherChar) ||
                    TOP_LEFT_BEND.equals(otherChar) || START.equals(otherChar)) {
                list.add(new Pair<>(x,y));
            }
        }
        return list;
    }

    private static String getCharForS(CharacterField charField, int x, int y) {
        Collection<Pair<Integer, Integer>> above = addAbove(charField, x, y - 1);
        Collection<Pair<Integer, Integer>> below = addBelow(charField, x, y + 1);
        Collection<Pair<Integer, Integer>> left = addLeft(charField, x - 1, y);
        Collection<Pair<Integer, Integer>> right = addRight(charField, x + 1, y);
        if(!above.isEmpty() && !below.isEmpty()){
            return VERTICAL_PIPE;
        }
        if(!left.isEmpty() && !right.isEmpty()){
            return HORIZONTAL_PIPE;
        }
        if(!left.isEmpty() && !above.isEmpty()){
            return TOP_LEFT_BEND;
        }
        if(!right.isEmpty() && !above.isEmpty()){
            return TOP_RIGHT_BEND;
        }
        if(!left.isEmpty() && !below.isEmpty()){
            return BOTTOM_LEFT_BEND;
        }
        if(!right.isEmpty() && !below.isEmpty()){
            return BOTTOM_RIGHT_BEND;
        }
        throw new IllegalStateException("Should not occur during calculation of S:"+x+","+y);
    }

    private static void part1(HashMap<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> graph){
        BreadthFirstSearch<Pair<Integer, Integer>> bfs = new BreadthFirstSearch<>(graph);
        // get all nodes connected to start node
        List<Pair<Integer, Integer>> dfsOrder = GraphUtil.getDfsOrderIterative(graph, startNode);
        long longestPath = 0;
        for (Pair<Integer, Integer> node: dfsOrder) {
            List<Pair<Integer, Integer>> path = bfs.search(startNode, node);
            if (path.size() > longestPath) {
                longestPath = path.size();
            }
        }
        System.out.println("\nPart 1 > Result: " + (longestPath-1));
    }

    private static void part2(CharacterField field, HashMap<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> graph) {
        List<Pair<Integer, Integer>> dfsOrder = GraphUtil.getDfsOrderIterative(graph, startNode);
        /*for (Pair<Integer, Integer> node : dfsOrder) {
            field.setCharacterAt(CYCLE_MARKER, node.getValue0(), node.getValue1());
        }*/
        //field.prettyPrint();
        //System.out.println();
        long count = markAndCountEnclosedNodes(field, dfsOrder);
        System.out.println("\nPart 2 > Result: " + count);
    }

    private static long markAndCountEnclosedNodes(CharacterField field, List<Pair<Integer, Integer>> allGraphNodes) {
        long countInnerNodes = 0;
        for (int y = 0; y < field.getMaxY(); y++) {
            for (int x = 0; x < field.getMaxX(); x++) {
                Pair<Integer, Integer> currentPos = new Pair<>(x, y);
                if (!allGraphNodes.contains(currentPos)) {
                    // needs to be checked
                    int count = countCycleCrossings(field, allGraphNodes, x, y);
                    if (count % 2 == 1) {
                        field.setCharacterAt("I", x, y);
                        countInnerNodes++;
                    } else {
                        field.setCharacterAt("o", x, y);
                    }
                }
            }
        }
        field.prettyPrint();
        return countInnerNodes;
    }

    private static int countCycleCrossings(CharacterField field, List<Pair<Integer, Integer>> allGraphNodes, int x, int y) {
        int count = 0;
        // go left and count
        String firstPartOfCycleCrossing = null;
        for (int innerX = x - 1; innerX >= 0; innerX--) {
            Pair<Integer, Integer> posToCheck = new Pair<>(innerX, y);
            if (allGraphNodes.contains(posToCheck)) {
                String cycleCharacter = field.getCharacterAt(innerX, y);
                if(START.equals(cycleCharacter)) {
                    cycleCharacter = getCharForS(field, innerX, y);
                }
                if (VERTICAL_PIPE.equals(cycleCharacter)) {
                    count++;
                } else if (TOP_LEFT_BEND.equals(cycleCharacter)) {
                    if (firstPartOfCycleCrossing == null) {
                        firstPartOfCycleCrossing = cycleCharacter;
                    } else {
                        throw new IllegalStateException("Should not occur:"+cycleCharacter);
                    }
                } else if (BOTTOM_LEFT_BEND.equals(cycleCharacter)) {
                    if (firstPartOfCycleCrossing == null) {
                        firstPartOfCycleCrossing = cycleCharacter;
                    } else {
                        throw new IllegalStateException("Should not occur:"+cycleCharacter);
                    }
                } else if (BOTTOM_RIGHT_BEND.equals(cycleCharacter)) {
                    if (firstPartOfCycleCrossing == null) {
                        throw new IllegalStateException("Should not occur:"+cycleCharacter);
                    } else if (firstPartOfCycleCrossing.equals(TOP_LEFT_BEND)) {
                        count++;
                        firstPartOfCycleCrossing = null;
                    } else if (firstPartOfCycleCrossing.equals(BOTTOM_LEFT_BEND)) {
                        // partial crossing is no crossing
                        firstPartOfCycleCrossing = null;
                    } else {
                        throw new IllegalStateException("Should not occur");
                    }
                } else if (TOP_RIGHT_BEND.equals(cycleCharacter)) {
                    if (firstPartOfCycleCrossing == null) {
                        throw new IllegalStateException("Should not occur:"+cycleCharacter);
                    } else if (firstPartOfCycleCrossing.equals(BOTTOM_LEFT_BEND)) {
                        count++;
                        firstPartOfCycleCrossing = null;
                    } else if (firstPartOfCycleCrossing.equals(TOP_LEFT_BEND)) {
                        // partial crossing is no crossing
                        firstPartOfCycleCrossing = null;
                    } else {
                        throw new IllegalStateException("Should not occur");
                    }
                }
            }
        }
        return count;
    }

}