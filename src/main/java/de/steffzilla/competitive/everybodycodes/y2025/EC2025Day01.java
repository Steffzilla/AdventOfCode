package de.steffzilla.competitive.everybodycodes.y2025;

import de.steffzilla.competitive.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EC2025Day01 {

    private static final String DAY = "01";
    private static final String YEAR = "2025";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//EC//" + YEAR + "//";
    public static final String FILENAME_BASE = "everybody_codes_e" + YEAR + "_q" + DAY + "_p";
    public static final String PATH = BASEDIR + FILENAME_BASE;

    private static final String LEFT = "L";
    private static final String RIGHT = "R";
    static final String EXAMPLE = """
            Vyrdax,Drakzyph,Fyrryn,Elarzris
            
            R3,L2,R3,L5
            """;

    static final String EXAMPLE3 = """
            Vyrdax,Drakzyph,Fyrryn,Elarzris
            
            R3,L2,R3,L3
            """;

    static void main() {
        System.out.println(DAY + " " + YEAR);
        //List<String> inputLines = EXAMPLE.lines().toList();
        List<String> inputLines =
                Utils.getStringList(PATH + "1.txt");
        part1(inputLines);
        inputLines = Utils.getStringList(PATH + "2.txt");
        part2(inputLines);
        inputLines = Utils.getStringList(PATH + "3.txt");
        part3(inputLines);
    }

    static String part1(List<String> inputLines) {
        String first = inputLines.getFirst();
        String last = inputLines.getLast();
        List<String> names = new ArrayList<>(Arrays.asList(first.split(",")));
        List<String> commands = new ArrayList<>(Arrays.asList(last.split(",")));
        int currentIndex = 0;
        for (String command : commands) {
            int steps = Integer.parseInt(command.substring(1));
            String direction = command.substring(0, 1);
            if (direction.equals(LEFT)) {
                currentIndex -= steps;
                if (currentIndex < 0) currentIndex = 0;
            } else if (direction.equals(RIGHT)) {
                currentIndex += steps;
                if (currentIndex >= names.size()) currentIndex = names.size() - 1;
            } else {
                throw new IllegalStateException(direction);
            }
        }
        System.out.println("\nPart 1 > Result: " + names.get(currentIndex));
        return names.get(currentIndex);
    }

    static String part2(List<String> inputLines) {
        String first = inputLines.getFirst();
        String last = inputLines.getLast();
        List<String> names = new ArrayList<>(Arrays.asList(first.split(",")));
        List<String> commands = new ArrayList<>(Arrays.asList(last.split(",")));
        int currentIndex = 0;
        for (String command : commands) {
            int steps = Integer.parseInt(command.substring(1));
            String direction = command.substring(0, 1);
            if (direction.equals(LEFT)) {
                currentIndex -= steps;
            } else if (direction.equals(RIGHT)) {
                currentIndex += steps;
            } else {
                throw new IllegalStateException(direction);
            }
        }
        if (currentIndex >= names.size()) {
            currentIndex = currentIndex % names.size();
        } else if (currentIndex <= 0) {
            // In Java, the % operator is not a classic modulo operator in the mathematical sense, but a remainder operator.
            currentIndex = Math.floorMod(currentIndex, names.size());
        }
        System.out.println("\nPart 2 > Result: " + names.get(currentIndex));
        return names.get(currentIndex);
    }

    static String part3(List<String> inputLines) {
        String first = inputLines.getFirst();
        String last = inputLines.getLast();
        List<String> names = new ArrayList<>(Arrays.asList(first.split(",")));
        List<String> commands = new ArrayList<>(Arrays.asList(last.split(",")));
        for (String command : commands) {
            int steps = Integer.parseInt(command.substring(1));
            String direction = command.substring(0, 1);
            int pos;
            if (direction.equals(LEFT)) {
                // In Java, the % operator is not a classic modulo operator in the mathematical sense, but a remainder operator.
                pos = Math.floorMod(-steps, names.size());
            } else if (direction.equals(RIGHT)) {
                pos = steps % names.size();
            } else {
                throw new IllegalStateException(direction);
            }
            String temp = names.getFirst();
            names.set(0, names.get(pos));
            names.set(pos, temp);
        }
        System.out.println("\nPart 3 > Result: " + names.getFirst());
        return names.getFirst();
    }

}