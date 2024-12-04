package de.steffzilla.aoc.y2024;

import de.steffzilla.aoc.AocUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2024_03 {

    private static final String DAY = "03";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    //public static final String FILENAME = "sample" + YEAR + "_" + DAY + ".txt";
    //public static final String FILENAME = "sample" + YEAR + "_" + DAY + "_part2.txt";
    public static final String PATH = BASEDIR + FILENAME;
    public static final String DO = "do()";
    public static final String DON_T = "don't()";

    private static long sum = 0;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);

        //part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines) {
        for (String line : inputLines) {
            processString(line);
        }
        System.out.println("\nPart 1 > Result: " + sum);
    }

    private static void processString(String line) {
        Pattern pattern = Pattern.compile("(mul\\(\\d*,\\d*\\))");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String relevantPart = matcher.group(1);
            String statement = relevantPart.substring(4, relevantPart.length() - 1);
            String[] split = statement.split(",");
            sum += (long) Integer.parseInt(split[0]) * Integer.parseInt(split[1]);
            //System.out.println(statement);
        }
    }


    private static void part2(List<String> inputLines) {
        sum = 0;
        boolean doProcess = true;
        List<String> toBeProcessed = new ArrayList<>();
        for (String line : inputLines) {
            while (!line.isEmpty()) {
                int indexOfDo = line.indexOf(DO);
                int indexOfDont = line.indexOf(DON_T);
                if (indexOfDo == -1 && indexOfDont == -1) {
                    // no more dos and don'ts
                    if (doProcess) {
                        toBeProcessed.add(line);
                    }
                    break; // nothing more to process -> terminate the loop
                } else if (indexOfDo == -1) {
                    indexOfDo = Integer.MAX_VALUE;
                } else if (indexOfDont == -1) {
                    indexOfDont = Integer.MAX_VALUE;
                }

                if (indexOfDo < indexOfDont) {
                    if (doProcess) {
                        toBeProcessed.add(line.substring(0, indexOfDo));
                    }
                    line = line.substring(indexOfDo + DO.length());
                    doProcess = true;
                } else {
                    if (doProcess) {
                        toBeProcessed.add(line.substring(0, indexOfDont));
                    }
                    line = line.substring(indexOfDont + DON_T.length());
                    doProcess = false;
                }
            }
        }
        for (String statement : toBeProcessed) {
            processString(statement);
        }

        System.out.println("\nPart 2 > Result: " + sum);
    }

}