package de.steffzilla.aoc.y2024;

import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2024_01 {

    private static final String DAY = "01";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        return new Pair<>(part1(inputLines), part2(inputLines));
    }

    private static String part1(List<String> inputLines) {
        long sum = 0;
        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();
        Pattern pattern = Pattern.compile("^(\\d*)\\s*(\\d*)");
        for (String line : inputLines) {
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            //System.out.println(matcher.group(1) +"---"+ matcher.group(2));
            firstList.add(Integer.valueOf(matcher.group(1)));
            secondList.add(Integer.valueOf(matcher.group(2)));
        }
        Collections.sort(firstList);
        Collections.sort(secondList);
        for (int i = 0; i < firstList.size(); i++) {
            int first = firstList.get(i);
            int second = secondList.get(i);
            if (first > second) {
                sum += first - second;
            } else {
                sum += second - first;
            }
        }
        System.out.println("\nPart 1 > Result: " + sum);
        return String.valueOf(sum);
    }


    private static String part2(List<String> inputLines) {
        long sum = 0;
        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();
        Pattern pattern = Pattern.compile("^(\\d*)\\s*(\\d*)");
        for (String line : inputLines) {
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            //System.out.println(matcher.group(1) +"---"+ matcher.group(2));
            firstList.add(Integer.valueOf(matcher.group(1)));
            secondList.add(Integer.valueOf(matcher.group(2)));
        }
        for (int i = 0; i < firstList.size(); i++) {
            int first = firstList.get(i);
            sum += first * secondList.stream().filter(x -> x.equals(first)).count();
        }
        System.out.println("\nPart 2 > Result: " + sum);
        return String.valueOf(sum);
    }

}