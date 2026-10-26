package de.steffzilla.aoc.y2023;

import de.steffzilla.competitive.Utils;
import de.steffzilla.competitive.GraphUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Aoc2023_25 {

    private static final String DAY = "25";
    private static final String YEAR = "2023";
    public static final String BASEDIR = Utils.inputDir("AoC", YEAR);
    public static final String FILENAME = "input"+YEAR+"_"+DAY+"_angepasst.txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+"_angepasst.txt";
    public static final String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = Utils.getStringList(PATH);

        solve(inputLines);
    }

    static String solve(List<String> inputLines) {
        return part1(inputLines);
    }

    private static String part1(List<String> inputLines) {
        HashMap<String, List<String>> graph = new HashMap<>();
        for (String line : inputLines) {
            String[] parts = line.split(": ");
            String leftSideNode = parts[0];
            List<String> neighbours = graph.computeIfAbsent(leftSideNode, k -> new ArrayList<>());
            String[] connectedNodes = parts[1].split(" ");
            for (String connectedNode : connectedNodes) {
                if(neighbours.contains(connectedNode)) {
                    throw new IllegalStateException("Double connection: " + leftSideNode + "-" + connectedNode);
                }
                neighbours.add(connectedNode);
                List<String> otherNeighbours = graph.computeIfAbsent(connectedNode, k -> new ArrayList<>());
                otherNeighbours.add(leftSideNode);
            }
        }
        List<String> component1 = GraphUtil.getDfsOrderIterative(graph, "jll");
        List<String> component2 = GraphUtil.getDfsOrderIterative(graph, "lnf");
        System.out.println("\nPart 1 > Result: " + component1.size()*component2.size());
        return String.valueOf(component1.size()*component2.size());
    }

}