package de.steffzilla.aoc.y2023;

import de.steffzilla.competitive.AocUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Aoc2023_15 {

    private static final String DAY = "15";
    private static final String YEAR = "2023";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    private static final HashMap<Integer, LinkedHashMap<String, Lens>> boxes = new HashMap<>();
    public static final String ADD_OPERATION = "=";
    public static final String REMOVE_OPERATION = "-";

    public record Lens(String label, int focalLength) { }

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);
        if (inputLines.size() != 1) {
            throw new IllegalStateException("Size of input not correct! " + inputLines.size());
        }

        //part1(inputLines.get(0));
        part2(inputLines.get(0));
    }

    private static void part1(String inputLine) {
        String[] steps = inputLine.split(",");
        long sum = 0;
        for (String step : steps) {
            sum += getHashResult(step);
        }
        System.out.println("\nPart 1 > Result: " + sum);
    }

    static int getHashResult(String step) {
        int currentValue = 0;
        for (int i = 0; i < step.length(); i++) {
            currentValue = getHashValue(currentValue, step.charAt(i));
        }
        return currentValue;
    }

    private static int getHashValue(int currentValue, char c) {
        currentValue += c;
        currentValue *= 17;
        return (currentValue % 256);
    }


    private static void part2(String inputLine) {
        String[] steps = inputLine.split(",");
        for (String step : steps) {
            if (step.contains(ADD_OPERATION)) {
                processAddOperation(step);
            } else if (step.endsWith(REMOVE_OPERATION)) {
                processRemoveOperation(step);
            } else {
                throw new IllegalStateException(step);
            }
        }
        System.out.println("\nPart 2 > Result: " + getFocusingPower());
    }

    private static void processRemoveOperation(String step) {
        String label = step.substring(0, step.length() - 1);
        int boxNumber = getHashResult(label);
        LinkedHashMap<String, Lens> lensesOfBox = boxes.get(boxNumber);
        if (lensesOfBox != null) {
            lensesOfBox.remove(label);
        }
        //System.out.println(step + " -> remove:" + label + " from box " + boxNumber);
    }

    private static void processAddOperation(String step) {
        String[] parts = step.split(ADD_OPERATION);
        int boxNumber = getHashResult(parts[0]);
        Lens lens = new Lens(parts[0], Integer.parseInt(parts[1]));
        /*System.out.println(step + " -> Label:" + lens.label + "|Hash/Box:" + boxNumber +
                "|focal length:" + lens.focalLength);*/
        LinkedHashMap<String, Lens> lensesOfBox = boxes.get(boxNumber);
        if (lensesOfBox == null) {
            lensesOfBox = new LinkedHashMap<>();
            boxes.put(boxNumber, lensesOfBox);
        }
        if (lensesOfBox.containsKey(lens.label)) {
            lensesOfBox.replace(lens.label, lens);
        } else {
            lensesOfBox.put(lens.label, lens);
        }
    }

    private static long getFocusingPower() {
        long sumOfFocusingPowers = 0;
        for (int boxNumber = 0; boxNumber < 256; boxNumber++) {
            LinkedHashMap<String, Lens> lensesOfBox = boxes.get(boxNumber);
            if (lensesOfBox != null) {
                //System.out.println(boxNumber + lensesOfBox.toString());
                int slot = 1;
                for (Lens lens : lensesOfBox.values()) {
                    // Multiply
                    // One plus the box number of the lens in question.
                    // The slot number of the lens within the box: 1 for the first lens, 2 for the second lens, and so on.
                    // The focal length of the lens.
                    long focusingPowerOfLens = (long) (1 + boxNumber) * slot * lens.focalLength;
                    //System.out.println(focusingPowerOfLens);
                    sumOfFocusingPowers += focusingPowerOfLens;
                    slot++;
                }

            }
        }
        return sumOfFocusingPowers;
    }

}