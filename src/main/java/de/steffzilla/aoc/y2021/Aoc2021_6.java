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
        long[] fishs = new long[9];
        for (int i = 0; i < sNumbers.length; i++) {
            int internalTimer = Integer.parseInt(sNumbers[i]);
            fishs[internalTimer]++;
        }
        System.out.println(Arrays.toString(fishs));

        for (int i = 0; i < DAYS; i++) {
            long internalTimerWasZeroFishs = fishs[0];
            // internal timer --
            fishs[0] = fishs[1];
            fishs[1] = fishs[2];
            fishs[2] = fishs[3];
            fishs[3] = fishs[4];
            fishs[4] = fishs[5];
            fishs[5] = fishs[6];
            fishs[6] = fishs[7] + internalTimerWasZeroFishs; // +old generation
            fishs[7] = fishs[8];
            fishs[8] = internalTimerWasZeroFishs; // new generation
        }

        long numberOfFishs = 0;
        for (long fish : fishs) {
            numberOfFishs = numberOfFishs + fish;
        }
        System.out.println(numberOfFishs);
    }

    private static int getDirectChilds(int days) {
        return (int) Math.ceil((double) days / 7);
    }

    private static void part1(String[] sNumbers) {
        List<Lanternfish> fishs = new ArrayList<>();
        // create Fishs
        for (int i = 0; i < sNumbers.length; i++) {
            fishs.add(new Lanternfish(Integer.parseInt(sNumbers[i])));
        }
        System.out.println(fishs);

        for (int i = 1; i <= DAYS; i++) {
            List<Lanternfish> newFishs = new ArrayList<>();

            for (Lanternfish fish : fishs) {
                boolean newFish = fish.age();

                if (newFish) {
                    newFishs.add(new Lanternfish(8));
                }
            }
            fishs.addAll(newFishs);
            //System.out.println("after day "+i+": "+fishs);
        }

        System.out.println("\nResult: " + fishs.size());
    }

}