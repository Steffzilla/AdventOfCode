package de.steffzilla.aoc.y2021;

import de.steffzilla.aoc.AocUtils;

import java.util.*;

public class Aoc2021_6 {

    private static final String DAY = "06";
    private static final String YEAR = "2021";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;

    static final int DAYS = 256;

    public static void main(String[] args) {
        List<String> stringList = AocUtils.getStringList(PATH);
        String[] sNumbers = stringList.getFirst().split(",");
        //part1(sNumbers);
        part2(sNumbers);
    }

    private static void part2(String[] sNumbers) {
        long[] fishes = new long[9];
        for (int i = 0; i < sNumbers.length; i++) {
            int internalTimer = Integer.parseInt(sNumbers[i]);
            fishes[internalTimer]++;
        }
        System.out.println(Arrays.toString(fishes));

        for (int i = 0; i < DAYS; i++) {
            long internalTimerWasZeroFishes = fishes[0];
            // internal timer --
            fishes[0] = fishes[1];
            fishes[1] = fishes[2];
            fishes[2] = fishes[3];
            fishes[3] = fishes[4];
            fishes[4] = fishes[5];
            fishes[5] = fishes[6];
            fishes[6] = fishes[7] + internalTimerWasZeroFishes; // +old generation
            fishes[7] = fishes[8];
            fishes[8] = internalTimerWasZeroFishes; // new generation
        }

        long numberOfFishs = 0;
        for (long fish : fishes) {
            numberOfFishs = numberOfFishs + fish;
        }
        System.out.println(numberOfFishs);
    }

    private static int getDirectChildren(int days) {
        return (int) Math.ceil((double) days / 7);
    }

    private static void part1(String[] sNumbers) {
        List<Lanternfish> fishes = new ArrayList<>();
        // create Fishes
        for (int i = 0; i < sNumbers.length; i++) {
            fishes.add(new Lanternfish(Integer.parseInt(sNumbers[i])));
        }
        System.out.println(fishes);

        for (int i = 1; i <= DAYS; i++) {
            List<Lanternfish> newFishes = new ArrayList<>();

            for (Lanternfish fish : fishes) {
                boolean newFish = fish.age();

                if (newFish) {
                    newFishes.add(new Lanternfish(8));
                }
            }
            fishes.addAll(newFishes);
            //System.out.println("after day "+i+": "+fishes);
        }

        System.out.println("\nResult: " + fishes.size());
    }

}