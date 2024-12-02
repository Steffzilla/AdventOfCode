package de.steffzilla.aoc.y2019;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Aoc2019_0x {

    private static final String DAY = "0x";
    private static final String YEAR = "2019";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    static final String example = """
            x
            y
            """;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = example.lines().toList();
        //List<String> inputLines = AocUtils.getStringList(PATH);

        List<Integer> list = Arrays.asList(3, 6, 8, 14, 15);
        double v = list.stream()
                .mapToInt(num -> num)
                .average().orElse(0d);
        System.out.println(v);

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