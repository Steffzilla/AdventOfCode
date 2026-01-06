package de.steffzilla.aoc.y2022;

import de.steffzilla.competitive.AocUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Aoc2022_01 {

    private static final String DAY = "01";
    private static final String YEAR = "2022";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        List<String> inputLines = AocUtils.getStringList(PATH);

        //part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines){
        long sumOfElf = 0;
        long biggestSum = 0;
        for (String line : inputLines) {
            if(line.length()>0){
                long calories = Long.parseLong(line);
                sumOfElf += calories;
            } else {
                if(sumOfElf > biggestSum) {
                    biggestSum = sumOfElf;

                }
                sumOfElf = 0;
            }

        }
        if(sumOfElf > biggestSum) {
            biggestSum = sumOfElf;
        }
        System.out.println("\nResult: "+biggestSum);
    }

    private static void part2(List<String> inputLines) {
        ArrayList<Long> listOfCalories = new ArrayList<>();
        long sumOfElf = 0;
        for (String line : inputLines) {
            if(line.length()>0){
                long calories = Long.parseLong(line);
                sumOfElf += calories;
            } else {
                listOfCalories.add(sumOfElf);
                sumOfElf = 0;
            }

        }
        Collections.sort(listOfCalories);
        long biggestSum = listOfCalories.get(listOfCalories.size()-1);
        biggestSum += listOfCalories.get(listOfCalories.size()-2);
        biggestSum += listOfCalories.get(listOfCalories.size()-3);
        System.out.println("\nResult: "+biggestSum);

    }

}