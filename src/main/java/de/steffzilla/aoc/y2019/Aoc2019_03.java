package de.steffzilla.aoc.y2019;

import java.util.*;

import de.steffzilla.competitive.Utils;
import org.javatuples.Pair;

public class Aoc2019_03 {

    private static final String DAY = "03";
    private static final String YEAR = "2019";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    private static final HashMap<Pair<Long, Long>, String> cableRoutes = new HashMap<>();
    // Start point
    private static final Pair<Long, Long> centralPort = new Pair<>(0L, 0L);
    private static final Set<Pair<Long, Long>> intersections = new HashSet<>();
    private static final List<Pair<Long, Long>> wirePath1 = new ArrayList<>();
    private static final List<Pair<Long, Long>> wirePath2 = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = Utils.getStringList(PATH);
        String sInputWire1 = inputLines.get(0);
        String sInputWire2 = inputLines.get(1);
        String[] inputWire1 = sInputWire1.split(",");
        String[] inputWire2 = sInputWire2.split(",");

        part1(inputWire1, inputWire2);
        part2();
    }

    private static Pair<Long, Long> executeRoute(Pair<Long, Long> start, String command, boolean secondCable,
                                                 List<Pair<Long, Long>> wirePath) {
        Pair<Long, Long> currentPos = null;
        String direction = command.substring(0,1);
        long steps = Long.parseLong(command.substring(1));
        switch (direction) {
            case "L" :
                for (long i = 1; i <= steps; i++) {
                    currentPos = new Pair<>(start.getValue0()-i, start.getValue1());
                    wirePath.add(currentPos);
                    addToCableRoutes(currentPos, secondCable);
                }
                break;
            case "R" :
                for (long i = 1; i <= steps; i++) {
                    currentPos = new Pair<>(start.getValue0()+i, start.getValue1());
                    wirePath.add(currentPos);
                    addToCableRoutes(currentPos, secondCable);
                }
                break;
            case "U" :
                for (long i = 1; i <= steps; i++) {
                    currentPos = new Pair<>(start.getValue0(), start.getValue1()+i);
                    wirePath.add(currentPos);
                    addToCableRoutes(currentPos, secondCable);
                }
                break;
            case "D" :
                for (long i = 1; i <= steps; i++) {
                    currentPos = new Pair<>(start.getValue0(), start.getValue1()-i);
                    wirePath.add(currentPos);
                    addToCableRoutes(currentPos, secondCable);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }
        //System.out.println(currentPos);
        return currentPos;
    }

    private static void addToCableRoutes(Pair<Long, Long> newLocation, boolean secondCable) {
        if(centralPort.equals(newLocation)) {
            return;
        }
        if (!secondCable) {
            cableRoutes.put(newLocation, "1");
        } else {
            String s = cableRoutes.get(newLocation);
            if("1".equals(s)) {
                intersections.add(newLocation);
                cableRoutes.put(newLocation, "X");
            } else {
                cableRoutes.put(newLocation, "2");
            }
        }
    }

    private static void part1(String[] inputWire1, String[] inputWire2) {
        Pair<Long, Long> currentPos = new Pair<>(0L,0L);
        for (String command : inputWire1) {
            currentPos = executeRoute(currentPos, command, false, wirePath1);
        }
        currentPos = new Pair<>(0L, 0L);
        for (String command : inputWire2) {
            currentPos = executeRoute(currentPos, command, true, wirePath2);
        }
        long distance = Long.MAX_VALUE;
        for (Pair<Long, Long> point : intersections) {
            long manhattanDistance = Math.abs(point.getValue0()) + Math.abs(point.getValue1());
            if (manhattanDistance < distance) {
                distance = manhattanDistance;
            }
        }

        System.out.println("\nPart 1 > Result: " + distance);
    }

    private static void part2() {
        if(intersections.isEmpty()) {
            throw new IllegalStateException("part 1 needs to run first!");
        }
        long minSteps = Long.MAX_VALUE;
        for (Pair<Long, Long> goal : intersections) {
            long steps = 0;
            for (Pair<Long, Long> point : wirePath1) {
                steps++;
                if(goal.equals(point)) {
                    break;
                }
            }
            for (Pair<Long, Long> point : wirePath2) {
                steps++;
                if(goal.equals(point)) {
                    break;
                }
            }
            if(steps < minSteps) {
                minSteps = steps;
            }
        }

        System.out.println("\nPart 2 > Result: " + minSteps);
    }



}