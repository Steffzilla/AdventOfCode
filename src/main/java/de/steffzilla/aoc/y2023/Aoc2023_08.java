package de.steffzilla.aoc.y2023;

import de.steffzilla.aoc.AocUtils;
import de.steffzilla.competitive.MathUtils;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Aoc2023_08 {

    private static final String DAY = "08";
    private static final String YEAR = "2023";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+"_part2.txt";
    public static final String PATH = BASEDIR + FILENAME;
    public static final String LEFT = "L";
    public static final String RIGHT = "R";
    public static final String END_NODE_MARKER = "Z";
    public static final String START_NODE_MARKER = "A";

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);
        String instructions = inputLines.get(0);
        HashMap<String, Pair<String, String>> map = readNetwork(inputLines);

        //part1(instructions, map);
        part2(instructions, map);
    }

    private static HashMap<String, Pair<String, String>> readNetwork(List<String> inputLines) {
        HashMap<String, Pair<String, String>> map = new HashMap<>();
        for (int i = 2; i < inputLines.size(); i++) {
            String line = inputLines.get(i);
            String node = line.substring(0, 3);
            String left = line.substring(7, 10);
            String right = line.substring(12, 15);
            map.put(node, new Pair<>(left,right));
        }
        return map;
    }

    private static void part1(String instructions, HashMap<String, Pair<String, String>> map){
        String currentNode = "AAA";
        boolean zzzFound = false;
        int instructionPointer = 0;
        long steps = 0;
        while (!zzzFound) {
            steps++;
            String instruction = instructions.substring(instructionPointer, instructionPointer+1);
            currentNode = move(currentNode, instruction, map);
            if("ZZZ".equals(currentNode)) {
                zzzFound=true;
            }

            instructionPointer++;
            if(instructionPointer==instructions.length()) {
                //repeat instructions
                instructionPointer=0;
            }
        }

        System.out.println("\nPart 1 > Result: " + steps);
    }

    private static void part2(String instructions, HashMap<String, Pair<String, String>> map) {
        List<String> startNodes = map.keySet().stream().filter(s -> s.endsWith(START_NODE_MARKER)).
                collect(Collectors.toCollection(ArrayList::new));

        // brute force (let all camels travel until state is reached) doesn't work
        // found out that each camel has a repeating route with only one goal node
        // --> find for each camel the needed steps until this goal
        List<Long> stepsPerCamel = new ArrayList<>();
        for (String startNode : startNodes) {
            stepsPerCamel.add(getStepsPerCamelPart2(instructions, startNode, map));
        }
        System.out.println("\nPart 2 > Result: " + MathUtils.leastCommonMultiple(stepsPerCamel));
    }

    private static Long getStepsPerCamelPart2(String instructions, String startNode, HashMap<String, Pair<String, String>> map) {
        String currentNode = startNode;
        boolean endFound = false;
        int instructionPointer = 0;
        long steps = 0;
        while (!endFound) {
            steps++;
            String instruction = instructions.substring(instructionPointer, instructionPointer+1);
            currentNode = move(currentNode, instruction, map);
            if(currentNode.endsWith(END_NODE_MARKER)) {
                endFound=true;
            }

            instructionPointer++;
            if(instructionPointer==instructions.length()) {
                //repeat instructions
                instructionPointer=0;
            }
        }
        return steps;
    }

    private static String move(String currentNode, String instruction, HashMap<String, Pair<String, String>> map) {
        Pair<String, String> connections = map.get(currentNode);
        String nextNode;
        if (LEFT.equals(instruction)) {
            nextNode = connections.getValue0();
        } else if (RIGHT.equals(instruction)) {
            nextNode = connections.getValue1();
        } else {
            throw new IllegalStateException("Should never occur");
        }
        //System.out.println(currentNode+"-->"+nextNode);
        return nextNode;
    }

}