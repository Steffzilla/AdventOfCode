package de.steffzilla.aoc.y2022;

import de.steffzilla.aoc.AocUtils;

import java.util.HashMap;
import java.util.List;

public class Aoc2022_03 {

    private static final String DAY = "03";
    private static final String YEAR = "2022";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);

        //part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines){
        long sumOfPrios = 0;
        // rating preparation
        HashMap<String, Integer> map = getMap();

        for (String line : inputLines) {
            int lineLength = line.length();
            if (lineLength % 2 != 0) {
                throw new IllegalStateException(line+" Length: "+lineLength);
            }
            String compartment1 = line.substring(0, lineLength / 2);
            String compartment2 = line.substring(lineLength / 2, lineLength);
            char[] chars1 = compartment1.toCharArray();

            // Traverse the character array

            for (int i = 0; i < chars1.length; i++) {
                if(compartment2.contains(String.valueOf(chars1[i]))) {
                    System.out.println(chars1[i]);
                    sumOfPrios+=map.get(String.valueOf(chars1[i]));
                    break;
                }
            }

        }
        System.out.println("\nPart 1 > Result: " + sumOfPrios);
    }

    private static HashMap<String, Integer> getMap() {
        HashMap<String, Integer> map = new HashMap<>();
        String alphabets = "abcdefghijklmnopqrstuvwxyz" + "abcdefghijklmnopqrstuvwxyz".toUpperCase();
        for (int i = 0; i < alphabets.length(); i++) {
            map.put(alphabets.substring(i, i + 1), i + 1);
        }
        return map;
    }


    private static void part2(List<String> inputLines) {
        long sumOfPrios = 0;
        // rating preparation
        HashMap<String, Integer> map = getMap();

        for (int i = 0; i < inputLines.size(); i=i+3) {
            String rucksack1 = inputLines.get(i);
            String rucksack2 = inputLines.get(i+1);
            String rucksack3 = inputLines.get(i+2);
            char[] chars1 = rucksack1.toCharArray();
            for (int j = 0; j < chars1.length; j++) {
                if (rucksack2.contains(String.valueOf(chars1[j]))) {
                    if (rucksack3.contains(String.valueOf(chars1[j]))) {
                        //System.out.println(chars1[i]);
                        sumOfPrios += map.get(String.valueOf(chars1[j]));
                        break;
                    }
                }
            }
        }
        System.out.println("\nPart 2 > Result: " + sumOfPrios);
    }

}