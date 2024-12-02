package de.steffzilla.aoc.y2023;

import de.steffzilla.aoc.AocUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2023_12 {

    private static final String DAY = "12";
    private static final String YEAR = "2023";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;
    private static final String DEFECT = "#";
    private static final String OK = ".";

    private static final Pattern pattern = Pattern.compile("(#+)+");

    static final String example = """
            #.#.### 1,1,3
            .#...#....###. 1,1,3
            .#.###.#.###### 1,3,1,6
            ####.#...#... 4,1,1
            #....######..#####. 1,6,5
            .###.##....# 3,2,1
            """;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);
        //inputLines = example.lines().toList();

        part1(inputLines);
        //part2(inputLines);
    }


    private static void part1(List<String> inputLines) {
        long counter = 0;
        for (String line : inputLines) {
            String[] inputs = line.split(" ");
            Integer[] groups = Arrays.stream(inputs[1].split(",")).map(Integer::valueOf).toArray(Integer[]::new);
            String row = inputs[0];

            long localCounter = 0;
            List<String> variants = new ArrayList<>();
            getVariants(variants, "", row);
            for (String variant : variants) {
                if(meetsCondition(variant, groups)) {
                    localCounter++;
                }
            }

            System.out.println(localCounter);
            counter+=localCounter;
        }
        System.out.println("\nPart 1 > Result: " + counter);
    }

    private static void getVariants(List<String> result, String prefix, String restOfRow) {
        // ???.###
        if(!restOfRow.contains("?")) {
            result.add(prefix+restOfRow);
            //System.out.println(prefix+restOfRow);
        } else {
            int pos = restOfRow.indexOf("?");
            String newPrefix = restOfRow.substring(0, pos);
            String newRest = restOfRow.substring(pos+1);
            getVariants(result, prefix+newPrefix+DEFECT, newRest);
            getVariants(result, prefix+newPrefix+OK, newRest);
        }
    }

    private static boolean meetsCondition(String row, Integer[] groups) {
        // #.#.### 1,1,3
        Matcher m = pattern.matcher(row);
        int counter = 0;
        while (m.find()) {
            if (counter >= groups.length) {
                return false;
            }
            if(groups[counter] != m.group().length()) {
                return false;
            }
            counter++;
        }
        if(counter!= groups.length) {
            return false;
        } else return true;
    }


    private static void part2(List<String> inputLines) {
        for (String line : inputLines) {

        }
        System.out.println("\nPart 2 > Result: " + "x");
    }

    /*
    Template for reading a maze graph
    private static HashMap<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> readMaze(List<String> inputLines) {
        CharacterField charField = new CharacterField(inputLines);
        Pair<Integer, Integer> startNode = null;
        Pair<Integer, Integer> goalNode = null;

        HashMap<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> graph = new HashMap<>();
        for (int y = 0; y < charField.getMaxY(); y++) {
            for (int x = 0; x < charField.getMaxX(); x++) {
                String letter = charField.getCharacterAt(x, y);
                List<Pair<Integer, Integer>> neighbors = new ArrayList<>();
                // check if start
                if (START.equals(letter)) {
                    startNode = new Pair<>(x, y);

                    System.out.println("Start node: " + startNode);
                }

                if(charField.isContained(x, y - 1)) {
                    //neighbors.add(new Pair<>(x, y-1));

                }
                if(charField.isContained(x, y + 1)) {
                    //neighbors.add(new Pair<>(x, y + 1));
                }
                if(charField.isContained(x - 1, y)) {
                    // neighbors.add(new Pair<>(x - 1, y));
                }
                if(charField.isContained(x + 1, y)) {
                    ///neighbors.add(new Pair<>(x + 1, y));
                }
                graph.put(new Pair<>(x, y), neighbors);
            }
        }
        return graph;
    }
     */

}