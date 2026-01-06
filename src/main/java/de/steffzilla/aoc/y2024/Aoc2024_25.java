package de.steffzilla.aoc.y2024;

import de.steffzilla.competitive.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Aoc2024_25 {

    private static final String DAY = "25";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    public static final String FIRST_LINE_OF_LOCK = "#####";
    public static final String FIRST_LINE_OF_KEY = ".....";
    public static final int PIN_HEIGHT = 5;
    public static final int INVALID = -42;
    public static final int INITIAL_HEIGHT = -1;

    private static List<List<Integer>> locks;
    private static List<List<Integer>> keys;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        List<String> inputLines = Utils.getStringList(PATH);

        solve(inputLines);
    }

    static String solve(List<String> inputLines) {
        locks = new ArrayList<>();
        keys = new ArrayList<>();
        return part1(inputLines);
    }

    private static String part1(List<String> inputLines) {
        readKeysAndLocks(inputLines);
        long count = 0;
        for (List<Integer> lock : locks) {
            for (List<Integer> key : keys) {
                boolean isFitting = true;
                for (int i = 0; i < lock.size(); i++) {
                    if (lock.get(i) + key.get(i) > PIN_HEIGHT) {
                        isFitting = false;
                        break;
                    }
                }
                if (isFitting) {
                    //System.out.println("Lock: " + lock + " and Key: " + key + " fit!");
                    count++;
                }
            }
        }
        System.out.println("\nPart 1 > Result: " + count);
        return String.valueOf(count);
    }

    private static void readKeysAndLocks(List<String> inputLines) {
        int heightCounter = INITIAL_HEIGHT;
        boolean isKey = false;
        List<Integer> currentList = new ArrayList<>(Arrays.asList(INVALID, INVALID, INVALID, INVALID, INVALID));
        for (String line : inputLines) {
            if (line.isEmpty()) {
                // reset the state
                heightCounter = INITIAL_HEIGHT;
            } else {
                if (heightCounter == INITIAL_HEIGHT) {
                    // initialize with -1 as marker
                    currentList = new ArrayList<>(Arrays.asList(INVALID, INVALID, INVALID, INVALID, INVALID));
                    if (FIRST_LINE_OF_LOCK.equals(line)) {
                        isKey = false;
                    } else if (FIRST_LINE_OF_KEY.equals(line)) {
                        isKey = true;
                    } else {
                        throw new IllegalStateException("First line has an unexpected state: '" + line + "'");
                    }
                } else if (heightCounter == PIN_HEIGHT) {
                    Collections.replaceAll(currentList, INVALID, isKey ? 0 : PIN_HEIGHT);
                    // save
                    if (isKey) {
                        //System.out.println("new key:" + currentList);
                        keys.add(currentList);
                    } else {
                        //System.out.println("new lock:" + currentList);
                        locks.add(currentList);
                    }
                } else {
                    for (int i = 0; i < line.length(); i++) {
                        String character = line.substring(i, i + 1);
                        if (character.equals("#") && isKey) {
                            if (currentList.get(i) == INVALID) {
                                currentList.set(i, PIN_HEIGHT - heightCounter);
                            }
                        } else if (character.equals(".") && !isKey) {
                            if (currentList.get(i) == INVALID) {
                                currentList.set(i, heightCounter);
                            }
                        }
                    }
                }
                heightCounter++;
            }
        }
    }

}