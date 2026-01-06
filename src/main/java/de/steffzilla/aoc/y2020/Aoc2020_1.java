package de.steffzilla.aoc.y2020;

import de.steffzilla.competitive.AocUtils;

public class Aoc2020_1 {

    private static final String DAY = "01";
    private static final String YEAR = "2020";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        int[] numbers = AocUtils.getIntArrayFromFile(PATH);

        //doFirstPart(numbers);
        doSecondPart(numbers);
    }


    private static void doSecondPart(int[] numbers) {
        int firstNumber = 0;
        int secondNumber = 0;
        int thirdNumber = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                for (int k = j + 1; k < numbers.length; k++) {
                    if (2020 == numbers[i] + numbers[j] + numbers[k]) {
                        firstNumber = numbers[i];
                        secondNumber = numbers[j];
                        thirdNumber = numbers[k];
                    }
                }
            }
        }
        System.out.println(firstNumber);
        System.out.println(secondNumber);
        System.out.println(thirdNumber);
        System.out.println(firstNumber * secondNumber * thirdNumber);
    }
}
