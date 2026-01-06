package de.steffzilla.aoc.y2023;

import de.steffzilla.competitive.Utils;
import de.steffzilla.competitive.CharacterField;
import org.javatuples.Pair;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Aoc2023_11 {

    private static final String DAY = "11";
    private static final String YEAR = "2023";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    private static final String GALAXY = "#";
    private static final String EMPTY_FIELD = ".";
    //private static final String EMPTY_LINE = "..........";
    private static final String EMPTY_LINE = "............................................................................................................................................";
    private static final long EXPANSION_FACTOR = 1000000;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = Utils.getStringList(PATH);

        //part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines) {
        List<String> newInputLines = adjustInput(inputLines);
        CharacterField field = new CharacterField(newInputLines);
        //field.prettyPrint();
        List<Pair<Integer, Integer>> galaxies = field.searchCharacters(GALAXY);
        System.out.println("No of Galaxies:"+galaxies.size());
        long sum = 0;
        for (int i = 0; i < galaxies.size(); i++) {
            Pair<Integer, Integer> galaxyStart = galaxies.get(i);
            for (int j = i + 1; j < galaxies.size(); j++) {
                Pair<Integer, Integer> galaxyEnd = galaxies.get(j);
                long distance = Utils.getManhattanDistance(galaxyEnd, galaxyStart);
                sum+=distance;
                System.out.println("Start: "+galaxyStart+" End: "+galaxyEnd+ " Distance:"+distance);
            }
        }
        System.out.println("\nPart 1 > Result: " + sum);
    }

    private static List<String> adjustInput(List<String> inputLines) {
        List<String> newInputLines = new ArrayList<>();
        for (String line : inputLines) {
            newInputLines.add(line);
            if (EMPTY_LINE.equals(line)) {
                newInputLines.add(EMPTY_LINE);
            }
        }

        List<StringBuilder> builders = new ArrayList<>();
        for (int i = 0; i < newInputLines.size(); i++) {
            builders.add(new StringBuilder());
        }
        for (int x = 0; x < newInputLines.getFirst().length(); x++) {
            boolean emptyLine = true;
            for (int y = 0; y < newInputLines.size(); y++) {
                char currentChar = newInputLines.get(y).charAt(x);
                if(GALAXY.equals(String.valueOf(currentChar))) {
                    emptyLine = false;
                }
                builders.get(y).append(currentChar);
            }
            if (emptyLine) {
                for (int y = 0; y < newInputLines.size(); y++) {
                    builders.get(y).append(EMPTY_FIELD);
                }
            }
        }

        for (int i = 0; i < builders.size(); i++) {
            newInputLines.set(i, builders.get(i).toString());
        }
        return newInputLines;
    }

    private static void part2(List<String> inputLines) {
        TreeSet<Integer> emptyLineNumbers = findEmptyLineNumbers(inputLines);
        TreeSet<Integer> emptyColumnNumbers = findEmptyColumnNumbers(inputLines);
        System.out.println("Columns:"+emptyColumnNumbers);
        System.out.println("Lines:"+emptyLineNumbers);

        CharacterField field = new CharacterField(inputLines);
        List<Pair<Integer, Integer>> galaxies = field.searchCharacters(GALAXY);
        System.out.println("No of Galaxies:"+galaxies.size());
        BigInteger sum = new BigInteger("0");
        //long sum=0;
        for (int i = 0; i < galaxies.size(); i++) {
            Pair<Integer, Integer> galaxyStart = galaxies.get(i);
            for (int j = i + 1; j < galaxies.size(); j++) {
                Pair<Integer, Integer> galaxyEnd = galaxies.get(j);
                // Expansion
                int xExpansion;
                if(galaxyStart.getValue0() < galaxyEnd.getValue0()) {
                    xExpansion = emptyColumnNumbers.subSet(galaxyStart.getValue0(), galaxyEnd.getValue0()).size();
                } else {
                    xExpansion = emptyColumnNumbers.subSet(galaxyEnd.getValue0(), galaxyStart.getValue0()).size();
                }
                int yExpansion;
                if(galaxyStart.getValue1() < galaxyEnd.getValue1()) {
                    yExpansion = emptyLineNumbers.subSet(galaxyStart.getValue1(), galaxyEnd.getValue1()).size();
                } else {
                    yExpansion = emptyLineNumbers.subSet(galaxyEnd.getValue1(), galaxyStart.getValue1()).size();
                }

                long distanceWithout = Utils.getManhattanDistance(galaxyEnd, galaxyStart);

                long realDistance = distanceWithout + xExpansion * (EXPANSION_FACTOR-1) + yExpansion * (EXPANSION_FACTOR-1);

                //System.out.println("Start: "+galaxyStart+" End: "+galaxyEnd+ " | xExpansion: "+ xExpansion + " | yExpansion: "+ yExpansion+" distance:"+distanceWithout+" RealDis"+realDistance);
                sum = sum.add(BigInteger.valueOf(realDistance));
                //System.out.println(count++);
            }
        }


        System.out.println("\nPart 2 > Result: " + sum);
    }

    private static TreeSet<Integer> findEmptyColumnNumbers(List<String> inputLines) {
        TreeSet<Integer> emptyColumns = new TreeSet<>();
        for (int x = 0; x < inputLines.getFirst().length(); x++) {
            boolean emptyLine = true;
            for (int y = 0; y < inputLines.size(); y++) {
                char currentChar = inputLines.get(y).charAt(x);
                if(GALAXY.equals(String.valueOf(currentChar))) {
                    emptyLine = false;
                    break;
                }
            }
            if (emptyLine) {
                emptyColumns.add(x);
            }
        }
        return emptyColumns;
    }

    private static TreeSet<Integer> findEmptyLineNumbers(List<String> inputLines) {
        TreeSet<Integer> emptyLines = new TreeSet<>();
        for (int i = 0; i < inputLines.size(); i++) {
            if (EMPTY_LINE.equals(inputLines.get(i))) {
                emptyLines.add(i);
            }
        }
        return emptyLines;
    }

}