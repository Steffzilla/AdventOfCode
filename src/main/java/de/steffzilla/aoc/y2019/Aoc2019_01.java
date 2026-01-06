package de.steffzilla.aoc.y2019;

import de.steffzilla.competitive.Utils;

public class Aoc2019_01 {

    private static final String DAY = "01";
    private static final String YEAR = "2019";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1(){
        long[] numbers = Utils.getLongArrayFromFile(PATH);
        double fuelSum = 0;
        for (int i = 0; i < numbers.length; i++) {
            double requiredFuel = getRequiredFuel(numbers[i]);
            fuelSum += requiredFuel;
        }
        System.out.println("\nResult: "+ fuelSum);
    }

    private static double getRequiredFuelRecursive(double mass) {
        double currentNumber = mass;
        double requiredFuel = Math.floor(currentNumber / 3d) - 2d;
        if (requiredFuel > 0) {
            requiredFuel += getRequiredFuelRecursive(requiredFuel);
            return requiredFuel;
        } else {
            return 0;
        }
    }

    private static double getRequiredFuel(long numbers) {
        double currentNumber = (double) numbers;
        return Math.floor(currentNumber / 3d) - 2d;
    }

    private static void part2() {
        int[] numbers = Utils.getIntArrayFromFile(PATH);
        double fuelSum = 0;
        for (int i = 0; i < numbers.length; i++) {
            double requiredFuel = getRequiredFuelRecursive((double)numbers[i]);
            fuelSum += requiredFuel;
        }
        System.out.println("\nResult: "+ fuelSum);
    }

}