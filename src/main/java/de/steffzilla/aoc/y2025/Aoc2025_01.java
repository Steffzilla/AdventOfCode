package de.steffzilla.aoc.y2025;

import de.steffzilla.competitive.AocUtils;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2025_01 {

    private static final String DAY = "01";
    private static final String YEAR = "2025";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    static final String example = """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
            """;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        //List<String> inputLines = example.lines().toList();
        List<String> inputLines = AocUtils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        Pattern pattern = Pattern.compile("^(\\w)(\\d*)");
        List<Instruction> instructions = new ArrayList<>();
        for (String line : inputLines) {
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            //System.out.println(matcher.group(1) + " " + matcher.group(2));
            instructions.add(new Instruction(matcher.group(1).equals("L") ? Dir.LEFT : Dir.RIGHT,
                    Integer.parseInt(matcher.group(2))));
        }
        return new Pair<>(part1(instructions), part2(instructions));
    }

    private static String part1(List<Instruction> instructions) {
        long count = 0;
        Dial dial = new Dial(50);
        for (Instruction instruction : instructions) {
            dial.moveAndCount(instruction);
            if (dial.getCurrentPos() == 0) count++;
        }
        System.out.println("\nPart 1 > Result: " + count);
        return String.valueOf(count);
    }

    private static String part2(List<Instruction> instructions) {
        long count = 0;
        Dial dial = new Dial(50);
        for (Instruction instruction : instructions) {
            count += dial.moveAndCount(instruction);
        }
        System.out.println("\nPart 2 > Result: " + count);
        return String.valueOf(count);
    }

    private static class Dial {
        int currentPos;

        public Dial(int currentPos) {
            this.currentPos = currentPos;
        }

        int moveAndCount(Instruction instruction) {
            int counter = 0;
            for (int i = 0; i < instruction.steps(); i++) {
                if (instruction.dir == Dir.RIGHT) {
                    currentPos++;
                } else {
                    currentPos--;
                }
                if (currentPos < 0) {
                    currentPos += 100;
                } else if (currentPos > 99) {
                    currentPos -= 100;
                }
                if (currentPos == 0) counter++;
            }
            return counter;
        }

        public int getCurrentPos() {
            return currentPos;
        }
    }

    enum Dir {
        LEFT,
        RIGHT
    }

    private record Instruction(Dir dir, int steps) {
    }

}