package de.steffzilla.aoc.y2022;

import de.steffzilla.competitive.AocUtils;

import java.util.List;

public class Aoc2022_25 {

    private static final String DAY = "25";
    private static final String YEAR = "2022";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);

        part1(inputLines);
        //part2(inputLines);
    }

    private static void part1(List<String> inputLines){
        long sum = 0;
        for (String line : inputLines) {
            long decimal = snafuToDecimal(line);
            sum += decimal;
        }
        System.out.println("Decimal sum: " + sum);
        System.out.println("\nPart 1 > Result: " + decimalToSnafu(sum));
    }

    static String decimalToSnafu(long decimalValue) {
        StringBuilder sb = new StringBuilder();
        long temp = decimalValue;

        while (temp > 0) {
            long carryForward = 0;
            long remainder = temp % 5;
            if (remainder == 3) {
                sb.append("=");
                carryForward = 1;
            } else if (remainder == 4) {
                sb.append("-");
                carryForward = 1;
            } else {
                sb.append(remainder);
                carryForward = 0;
            }

            temp = (temp-remainder) / 5 + carryForward;
        }
        return sb.reverse().toString();
    }

    static long snafuToDecimal(String line) {
        long decimalValue = 0;
        long base = 1;
        for (int i = line.length()-1; i >= 0 ; i--) {
            long value = switch (line.charAt(i)) {
                case '0' -> 0*base;
                case '1' -> 1*base;
                case '2' -> 2*base;
                case '-' -> -1*base;
                case '=' -> -2*base;
                default -> throw new IllegalStateException("Should not occur! "+line.charAt(i));
            };
            decimalValue+=value;
            base*=5;
        }
        return decimalValue;
    }


    private static void part2(List<String> inputLines) {
        for (String line : inputLines) {

        }
        System.out.println("\nPart 2 > Result: " + "x");
    }

}