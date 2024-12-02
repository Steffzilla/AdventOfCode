package de.steffzilla.aoc.y2022;

import de.steffzilla.aoc.AocUtils;

import java.util.List;

public class Aoc2022_0x {

    private static final String DAY = "0x";
    private static final String YEAR = "2022";
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
        for (String line : inputLines) {

        }
        System.out.println("\nPart 1 > Result: " + "x");
    }



    private static void part2(List<String> inputLines) {
        for (String line : inputLines) {

        }
        System.out.println("\nPart 2 > Result: " + "x");
    }

}