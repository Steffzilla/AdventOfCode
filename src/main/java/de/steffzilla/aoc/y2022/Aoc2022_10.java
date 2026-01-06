package de.steffzilla.aoc.y2022;

import de.steffzilla.competitive.AocUtils;

import java.util.List;

public class Aoc2022_10 {

    private static final String DAY = "10";
    private static final String YEAR = "2022";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    private static long registerX = 1;
    private static long cycleNumber = 0;
    private static long resultPart1 = 0;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);
        //inputLines = Arrays.asList("noop", "addx 3", "addx -5");
        //part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines){
        for (String line : inputLines) {
            incrementCycleNumber();
            if (!"noop".equals(line)) {
                String[] parts = line.split(" ");
                long value = Long.parseLong(parts[1]);
                incrementCycleNumber();
                registerX+=value;
            }
        }
        System.out.println("\nPart 1 > Result: " + resultPart1);
    }

    private static void incrementCycleNumber() {
        cycleNumber++;
        if(cycleNumber == 20 || ((cycleNumber-20) % 40 == 0)) {
            System.out.println("*** Cycle number: "+cycleNumber+" X="+registerX);
            resultPart1+=(cycleNumber * registerX);
        }
        System.out.println("Cycle number: "+cycleNumber+" X="+registerX);
    }


    private static void part2(List<String> inputLines) {
        for (String line : inputLines) {
            incrementCycleNumberPart2();
            if (!"noop".equals(line)) {
                String[] parts = line.split(" ");
                long value = Long.parseLong(parts[1]);
                incrementCycleNumberPart2();
                registerX+=value;
            }
        }

        System.out.println("\nPart 2 > Result: " + "x");
    }

    private static void incrementCycleNumberPart2() {
        cycleNumber++;
        // -1 because index of screen starts at 0; cycle at 1
        long mod40 = (cycleNumber-1) % 40;
        if(mod40 >= registerX-1 && mod40 <= registerX+1) {
            System.out.print("#");
        } else {
            System.out.print(".");
        }
        if(cycleNumber % 40 == 0) {
            System.out.println(); // new line after every 40th row
        }
    }

}