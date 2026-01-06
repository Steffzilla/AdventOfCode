package de.steffzilla.aoc.y2023;

import de.steffzilla.competitive.AocUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Aoc2023_09 {

    private static final String DAY = "09";
    private static final String YEAR = "2023";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);

        part1(inputLines);
        //part2(inputLines);
    }

    private static void part1(List<String> inputLines){
        long sumOfNextSteps = 0;
        for (String line : inputLines) {
            sumOfNextSteps += getNextStep(line,true);
        }
        System.out.println("\nPart 1 > Result: " + sumOfNextSteps);
    }

    private static long getNextStep(String line, boolean part1) {
        LinkedList<Long> sequence = Arrays.stream(line.split(" ")).map(Long::valueOf).
                collect(Collectors.toCollection(LinkedList::new)); // List needs to be modifiable
        //System.out.println(sequence);
        Deque<LinkedList<Long>> stack = new ArrayDeque<>();
        stack.push(sequence);

        fillStack(sequence, stack);
        if(part1) {
            return processStack(stack);
        } else {
            return processStackPart2(stack);
        }
    }

    private static long processStackPart2(Deque<LinkedList<Long>> stack) {
        LinkedList<Long> currentSequence = stack.pop();
        while (!stack.isEmpty()) {
            LinkedList<Long> nextSequence = stack.pop();
            Long currentFirst = currentSequence.getFirst();
            Long nextFirst = nextSequence.getFirst();
            Long newValue = nextFirst - currentFirst;
            //System.out.println(newValue);
            nextSequence.addFirst(newValue);
            currentSequence = nextSequence;
        }
        return currentSequence.getFirst();
    }

    private static long processStack(Deque<LinkedList<Long>> stack) {
        LinkedList<Long> currentSequence = stack.pop();
        while (!stack.isEmpty()) {
            LinkedList<Long> nextSequence = stack.pop();
            Long currentLast = currentSequence.getLast();
            Long nextLast = nextSequence.getLast();
            Long newValue = currentLast + nextLast;
            //System.out.println(newValue);
            nextSequence.add(newValue);
            currentSequence = nextSequence;
        }
        return currentSequence.get(currentSequence.size() - 1);
    }

    private static void fillStack(LinkedList<Long> sequence, Deque<LinkedList<Long>> stack) {
        while (true) {
            LinkedList<Long> nextSequence = new LinkedList<>();
            boolean allZero = true;
            for (int i = 0; i < sequence.size()-1; i++) {
                long difference = sequence.get(i+1) - sequence.get(i);
                if(difference != 0L) {
                    allZero = false;
                }
                nextSequence.add(difference);
            }
            stack.push(nextSequence);
            sequence = nextSequence;
            if(allZero) {
                break;
            }
        }
    }

    private static void part2(List<String> inputLines) {
        long sumOfNextSteps = 0;
        for (String line : inputLines) {
            sumOfNextSteps += getNextStep(line, false);
            System.out.println(sumOfNextSteps);
        }
        System.out.println("\nPart 2 > Result: " + sumOfNextSteps);
    }

}