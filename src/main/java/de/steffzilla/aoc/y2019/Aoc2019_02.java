package de.steffzilla.aoc.y2019;

import de.steffzilla.aoc.AocUtils;

import java.util.Arrays;
import java.util.List;

public class Aoc2019_02 {

    private static final String DAY = "02";
    private static final String YEAR = "2019";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;
    public static int REQUIRED_OUTPUT_PART2 = 19690720;


    private static long[] numbers;

    public static void main(String[] args) {
        List<String> stringList = AocUtils.getStringList(PATH);
        numbers = AocUtils.splitStringToLongArray(stringList.get(0), ",");

        //part1(12, 2);
        part2();
    }

    private static long part1(long noun, long verb){
        int pos = 0;
        boolean continueLoop = true;

        // fix input: the first step is to restore the gravity assist program (your puzzle input) to the "1202 program alarm" state it had just before the last computer caught fire.
        numbers[1] = noun;
        numbers[2] = verb;

        while(continueLoop) {
            continueLoop = processNextStep(pos);
            pos+=4;
        }
        //System.out.println("\nResult: " + numbers[0]);
        return numbers[0];
    }

    private static boolean processNextStep(int nextPos) {
        long opcode = numbers[nextPos];
        if(opcode == 99) {
            return false;
        } else if (opcode == 1) {
            // SUM
            long pos1 = numbers[nextPos+1];
            long pos2 = numbers[nextPos+2];
            long pos3 = numbers[nextPos+3];

            long value1 = numbers[(int) pos1];
            long value2 = numbers[(int) pos2];
            numbers[(int) pos3] = value1+value2;
        } else if (opcode == 2) {
            // MULTIPLY
            long pos1 = numbers[nextPos+1];
            long pos2 = numbers[nextPos+2];
            long pos3 = numbers[nextPos+3];

            long value1 = numbers[(int) pos1];
            long value2 = numbers[(int) pos2];
            numbers[(int) pos3] = value1*value2;
        } else {
            throw new IllegalStateException("Unexpected opcode " + opcode + " at pos " + nextPos);
        }
        return true; // true = continue
    }

    private static void part2() {
        // make a backup of numbers
        int length = numbers.length;
        long[] backupOfNumbers = Arrays.copyOf(numbers, length);

        for (long noun = 0; noun < 100; noun++) {
            for (long verb = 0; verb < 100; verb++) {
                if (REQUIRED_OUTPUT_PART2 == part1(noun, verb)) {
                    System.out.println("noun="+noun+"|verb="+verb);
                    System.out.println("\nResult: " + (100 * noun + verb));
                    numbers = Arrays.copyOf(backupOfNumbers, length);
                } else {
                    numbers = Arrays.copyOf(backupOfNumbers, length);
                }
            }
        }
    }

}