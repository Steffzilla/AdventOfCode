package de.steffzilla.aoc.y2023;

import java.util.ArrayList;
import java.util.List;

public class Aoc2023_06 {

    private static final String DAY = "06";
    private static final String YEAR = "2023";

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);

        part1();
        //part2();
    }

    private static void part1(){
        List<Long> numberOfWaysToWin = new ArrayList<>();
        //List<Race> races = getSampleDataPart2();
        List<Race> races = getInputDataPart2();
        for (Race race : races) {
            numberOfWaysToWin.add(getNumberOfWaysToWin(race));
        }
        long result = 1;
        for (Long numOfWays : numberOfWaysToWin) {
            result*=numOfWays;
        }
        System.out.println("\nPart 1 > Result: " + result);
    }

    private static Long getNumberOfWaysToWin(Race race) {
        long time = race.time;
        long distance = race.distance;
        int lowerBoundary = 1;
        int upperBoundary = 0;
        for (int chargeTime = 1; chargeTime <= time; chargeTime++) {
            if((time-chargeTime)*chargeTime >= distance) {
               lowerBoundary = chargeTime;
               break;
            }
        }
        for (int chargeTime = (int) time; chargeTime > lowerBoundary; chargeTime--) {
            if((time-chargeTime)*chargeTime >= distance) {
                upperBoundary=chargeTime;
                break;
            }
        }
        System.out.println(lowerBoundary+"|"+upperBoundary+"|"+(upperBoundary + 1 - lowerBoundary ));
        return (long) upperBoundary + 1 - lowerBoundary;
    }


    private static void part2() {

        System.out.println("\nPart 2 > Result: " + "x");
    }

    //Time:      7  15   30
    //Distance:  9  40  200

    private static List<Race> getSampleData() {
        List<Race> races = new ArrayList<>();
        races.add(new Race(7,9));
        races.add(new Race(15,40));
        races.add(new Race(30,200));
        return races;
    }

    private static List<Race> getSampleDataPart2() {
        List<Race> races = new ArrayList<>();
        races.add(new Race(71530,940200));
        return races;
    }

    // Time:        52     94     75     94
    // Distance:   426   1374   1279   1216

    private static List<Race> getInputData() {
        List<Race> races = new ArrayList<>();
        races.add(new Race(52,426));
        races.add(new Race(94,1374));
        races.add(new Race(75,1279));
        races.add(new Race(94,1216));
        return races;
    }

    private static List<Race> getInputDataPart2() {
        List<Race> races = new ArrayList<>();
        races.add(new Race(52947594L,426137412791216L));

        return races;
    }

    private static class Race {
        Race(long time, long distance) {
            this.time = time;
            this.distance = distance;
        }
        long time;
        long distance;
    }

}