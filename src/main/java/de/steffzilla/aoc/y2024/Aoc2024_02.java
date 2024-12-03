package de.steffzilla.aoc.y2024;

import de.steffzilla.aoc.AocUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Aoc2024_02 {

    private static final String DAY = "02";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;


    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        //List<String> inputLines = example.lines().toList();
        List<String> inputLines = AocUtils.getStringList(PATH);

        //part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines) {
        int counter = 0;
        for (String line : inputLines) {
            List<Integer> numbers = new ArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(line, " ");
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                numbers.add(Integer.valueOf(token));
            }
            if (isIncreasing(numbers) || isDecreasing(numbers)) counter++;
        }
        System.out.println("\nPart 1 > Result: " + counter);
    }

    private static boolean isDecreasing(List<Integer> numbers) {
        int former = numbers.getFirst();
        for (int i = 1; i < numbers.size(); i++) {
            int current = numbers.get(i);
            if ((current >= former) || (current < former - 3)) return false;
            former = current;
        }
        return true;
    }

    private static boolean isIncreasing(List<Integer> numbers) {
        int former = numbers.getFirst();
        for (int i = 1; i < numbers.size(); i++) {
            int current = numbers.get(i);
            if ((current <= former) || (current > former + 3)) return false;
            former = current;
        }
        return true;
    }


    private static void part2(List<String> inputLines) {
        int counter = 0;
        for (String line : inputLines) {
            List<Integer> numbers = new ArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(line, " ");
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                numbers.add(Integer.valueOf(token));
            }
            for (int i = 0; i < numbers.size(); i++) {
                List<Integer> numbers2 = new ArrayList<>(numbers);
                numbers2.remove(i);
                if (isIncreasing(numbers2) || isDecreasing(numbers2)) {
                    counter++;
                    break;
                }
            }
        }
        System.out.println("\nPart 2 > Result: " + counter);
    }

}