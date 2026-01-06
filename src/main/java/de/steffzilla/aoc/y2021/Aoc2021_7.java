package de.steffzilla.aoc.y2021;

import de.steffzilla.competitive.AocUtils;

import java.util.*;

public class Aoc2021_7 {

    private static final String DAY = "07";
    private static final String YEAR = "2021";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        //List<String> stringList = AocUtils.getStringList(PATH);
        int[] numbers = AocUtils.getIntArrayFromFile(PATH, ",");
        System.out.println(Arrays.toString(numbers));
        //part1(numbers);
        part2(numbers);
    }

    private static void part1(int[] numbers) {
        TreeMap<Integer,Integer> position2fuel = new TreeMap<>();

        // testGoals
        for (int goal = 0; goal < 2000; goal++) {
            int fuel = calculateFuelPart1(numbers, goal);
            System.out.println(goal+"->"+fuel);
            position2fuel.put(goal, fuel);
        }

        ArrayList<Integer> sortedList = new ArrayList<>(position2fuel.values());
        Collections.sort(sortedList);
        System.out.println(sortedList);
        Integer leastFuel = sortedList.get(0);
        System.out.println(leastFuel);
        System.out.println("\nResult: " + position2fuel.get(leastFuel));
    }

    private static int calculateFuelPart1(int[] positions, int goalPosition) {
        int sumOfFuel = 0;
        for (int i = 0; i < positions.length; i++) {
            int fuel = Math.abs(goalPosition - positions[i]);
            //System.out.println("start "+ positions[i] + " Ziel "+goalPosition+" -> "+fuel);
            sumOfFuel += fuel;
            //System.out.println(sumOfFuel);
        }
        return sumOfFuel;
    }

    private static int calculateFuelPart2(int[] positions, int goalPosition) {
        int sumOfFuel = 0;
        for (int i = 0; i < positions.length; i++) {
            int distance = Math.abs(goalPosition - positions[i]);

            int fuel = getFuelForDistance(distance);
            //System.out.println("start "+ positions[i] + " Ziel "+goalPosition+" -> "+fuel);
            sumOfFuel += fuel;
            //System.out.println(sumOfFuel);
        }
        return sumOfFuel;
    }

    private static int getFuelForDistance(int distance) {
        int fuel = 0;
        for (int i = 1; i <= distance; i++) {
            fuel+=i;
        }
        return fuel;
    }

    private static void part2(int[] numbers) {
        TreeMap<Integer,Integer> position2fuel = new TreeMap<>();

        // testGoals
        for (int goal = 0; goal < 2000; goal++) {
            int fuel = calculateFuelPart2(numbers, goal);
            System.out.println(goal+"->"+fuel);
            position2fuel.put(goal, fuel);
        }

        ArrayList<Integer> sortedList = new ArrayList<>(position2fuel.values());
        Collections.sort(sortedList);
        System.out.println(sortedList);
        Integer leastFuel = sortedList.get(0);
        System.out.println("\nResult: " + leastFuel); // 100220525
    }

}