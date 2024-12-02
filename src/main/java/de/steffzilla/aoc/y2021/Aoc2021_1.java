package de.steffzilla.aoc.y2021;

import de.steffzilla.aoc.AocUtils;

public class Aoc2021_1 {

    private static final String DAY = "01";
    private static final String YEAR = "2021";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        //List<String> stringList = AocUtils.getStringList(PATH);
        int[] numbers = AocUtils.getIntArrayFromFile(PATH);
        int counter = 0;//doPart1(numbers);

        for (int i = 4; i <= numbers.length; i++) {
            int sum1 = numbers[i-4] +numbers[i-3] +numbers[i-2];
            int sum2 = numbers[i-3] +numbers[i-2] +numbers[i-1];
            System.out.print(i - 3);
            System.out.print("|");
            System.out.print(i - 2);
            System.out.print("|");
            System.out.print(i - 1);
            System.out.print("|");
            System.out.println(i);

            System.out.print(sum1);
            System.out.print(">");
            System.out.print(sum2);
            if(sum2 > sum1) {
                System.out.println("----> JA");
                counter++;
            } else {
                System.out.println("----> NEIN");
            }
        }
        System.out.println("\ncounter:"+counter);
    }

    private static int doPart1(int[] numbers) {
        int counter = 0;
        for (int i = 1; i < numbers.length; i++) {
            System.out.print(i);
            System.out.print(">");
            System.out.println(i-1);
            if(numbers[i] > numbers[i-1]) {
                System.out.println("--> JA");
                counter++;
            } else {
                System.out.println("--> NEIN");
            }
        }
        return counter;
    }

}
