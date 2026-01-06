package de.steffzilla.aoc.y2021;

import de.steffzilla.competitive.AocUtils;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class Aoc2021_12 {

    private static final String DAY = "12";
    private static final String YEAR = "2021";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static String FILENAME = "sample2021_12_3.txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;
    private static Set<String> pathsPart2;

    public static void main(String[] args) {
        List<String> inputLines = AocUtils.getStringList(PATH);
        Hashtable<String, Cave> caves = new Hashtable<>();
        for (String line : inputLines) {
            String[] nodes = line.split("-");
            String node1Name = nodes[0];
            String node2Name = nodes[1];
            Cave cave1 = caves.get(node1Name);
            if(cave1==null) {
                cave1 = new Cave(node1Name);
                caves.put(node1Name, cave1);
            }
            Cave cave2 = caves.get(node2Name);
            if(cave2==null) {
                cave2 = new Cave(node2Name);
                caves.put(node2Name, cave2);
            }
            cave1.setUnidirectionalPath(cave2);
            cave2.setUnidirectionalPath(cave1);
        }
        //part1(caves);
        part2(caves);
    }

    private static void part1(Hashtable<String, Cave> inputLines){
        Cave startCave = inputLines.get(Cave.START);
        HashSet<Cave> visitedSmallCaves = new HashSet<>();
        visitedSmallCaves.add(startCave);
        int numberOfPaths = findPathsToEnd(startCave.getPaths(), visitedSmallCaves);
        System.out.println("\nResult: "+numberOfPaths);
    }

    private static int findPathsToEnd(Set<Cave> paths, Set<Cave> visitedSmallCaves) {
        int noOfPaths = 0;
        for (Cave neighbour : paths) {
            if(neighbour.isEnd()) {
                noOfPaths++;
            } else {
                if(!visitedSmallCaves.contains(neighbour)) {
                    Set<Cave> newVisitedSmallCaves = new HashSet<>(visitedSmallCaves);
                    if(!neighbour.isBig()) {
                        newVisitedSmallCaves.add(neighbour);
                    }
                    noOfPaths += findPathsToEnd(neighbour.getPaths(), newVisitedSmallCaves);
                }
            }

        }
        return noOfPaths;
    }

    private static void part2(Hashtable<String, Cave> inputLines){
        pathsPart2 = new HashSet<>();
        Cave startCave = inputLines.get(Cave.START);
        HashSet<Cave> visitedSmallCaves = new HashSet<>();
        visitedSmallCaves.add(startCave);
        int numberOfPaths = findPathsToEndPart2(startCave.getPaths(), visitedSmallCaves, null, 0, Cave.START);
        System.out.println("\nResult: "+pathsPart2.size()+"|"+numberOfPaths);
    }

    private static int findPathsToEndPart2(Set<Cave> paths, Set<Cave> visitedSmallCaves,
                                           Cave specialSmallCave, int timesInSpecialCave,
                                           String debugPath) {
        int noOfPaths = 0;
        for (Cave neighbour : paths) {
            if(neighbour.isEnd()) {
                if (timesInSpecialCave!=1) {
                    // don't count cases double: e.g. start,A,b,end: once with b being the special cave and once not
                    // special caves visited only once is the same as no special cave
                    noOfPaths++;
                }
                //System.out.println(debugPath+",end");
                pathsPart2.add(debugPath+",end");
            } else {
                if(visitedSmallCaves.contains(neighbour)) {
                    // neighbour is a small cave and I've been there
                    if(neighbour.equals(specialSmallCave) && timesInSpecialCave == 1) {
                        // reentering special cave allowed
                        noOfPaths += findPathsToEndPart2(neighbour.getPaths(), visitedSmallCaves,
                                specialSmallCave, 2, debugPath+","+neighbour);
                    }
                } else {
                    // if neighbour is a small cave, I've not been here yet
                    Set<Cave> newVisitedSmallCaves = new HashSet<>(visitedSmallCaves);
                    if(neighbour.isBig()) {
                        noOfPaths += findPathsToEndPart2(neighbour.getPaths(), newVisitedSmallCaves, specialSmallCave,
                                timesInSpecialCave, debugPath+","+neighbour);
                    } else {
                        newVisitedSmallCaves.add(neighbour);
                        if (specialSmallCave==null && !neighbour.isStart()) {
                            noOfPaths += findPathsToEndPart2(neighbour.getPaths(), newVisitedSmallCaves,
                                    neighbour, 1,debugPath+","+neighbour);
                        }
                        noOfPaths += findPathsToEndPart2(neighbour.getPaths(), newVisitedSmallCaves,
                                specialSmallCave, timesInSpecialCave,debugPath+","+neighbour);
                    }
                }
            }
        }
        return noOfPaths;
    }

}