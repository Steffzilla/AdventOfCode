package de.steffzilla.aoc.y2024;

import de.steffzilla.competitive.AocUtils;
import de.steffzilla.competitive.CharacterField;

import java.util.List;

public class Aoc2024_04 {

    private static final String DAY = "04";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    //public static final String FILENAME = "sample" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    public static final String X = "X";
    public static final String M = "M";
    public static final String A = "A";
    public static final String S = "S";
    private static CharacterField cf;

    static final String example = """
            S..S..S
            .A.A.A.
            ..MMM..
            SAMXMAS
            ..MMM..
            .A.A.A.
            S..S..S
            """;

    static final String examplePart2Wrong = """
            M.S.
            .A..
            S.M.
            """;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);
        cf = new CharacterField(inputLines);
        //cf = new CharacterField(examplePart2Wrong);

        part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines) {
        long count = 0;

        for (int y = 0; y < cf.getMaxY(); y++) {
            for (int x = 0; x < cf.getMaxX(); x++) {
                String character = cf.getCharacterAt(x, y);
                if (character.equals(X)) {
                    count += numberOfWordsStartingHere(x, y);
                }
            }
        }
        System.out.println("\nPart 1 > Result: " + count);
    }

    private static long numberOfWordsStartingHere(int x, int y) {
        long counter = 0;
        // 12 o'clock
        if (cf.isContained(x, y - 3)) {
            if (cf.getCharacterAt(x, y - 1).equals(M) &&
                    cf.getCharacterAt(x, y - 2).equals(A) &&
                    cf.getCharacterAt(x, y - 3).equals(S)) {
                counter++;
            }
        }
        // 1,5 o'clock
        if (cf.isContained(x + 3, y - 3)) {
            if (cf.getCharacterAt(x + 1, y - 1).equals(M) &&
                    cf.getCharacterAt(x + 2, y - 2).equals(A) &&
                    cf.getCharacterAt(x + 3, y - 3).equals(S)) {
                counter++;
            }
        }
        // 3 o'clock
        if (cf.isContained(x + 3, y)) {
            if (cf.getCharacterAt(x + 1, y).equals(M) &&
                    cf.getCharacterAt(x + 2, y).equals(A) &&
                    cf.getCharacterAt(x + 3, y).equals(S)) {
                counter++;
            }
        }
        // 4,5 o'clock
        if (cf.isContained(x + 3, y + 3)) {
            if (cf.getCharacterAt(x + 1, y + 1).equals(M) &&
                    cf.getCharacterAt(x + 2, y + 2).equals(A) &&
                    cf.getCharacterAt(x + 3, y + 3).equals(S)) {
                counter++;
            }
        }
        // 6 o'clock
        if (cf.isContained(x, y + 3)) {
            if (cf.getCharacterAt(x, y + 1).equals(M) &&
                    cf.getCharacterAt(x, y + 2).equals(A) &&
                    cf.getCharacterAt(x, y + 3).equals(S)) {
                counter++;
            }
        }
        // 7,5 o'clock
        if (cf.isContained(x - 3, y + 3)) {
            if (cf.getCharacterAt(x - 1, y + 1).equals(M) &&
                    cf.getCharacterAt(x - 2, y + 2).equals(A) &&
                    cf.getCharacterAt(x - 3, y + 3).equals(S)) {
                counter++;
            }
        }
        // 9 o'clock
        if (cf.isContained(x - 3, y)) {
            if (cf.getCharacterAt(x - 1, y).equals(M) &&
                    cf.getCharacterAt(x - 2, y).equals(A) &&
                    cf.getCharacterAt(x - 3, y).equals(S)) {
                counter++;
            }
        }
        // 10,5 o'clock
        if (cf.isContained(x - 3, y - 3)) {
            if (cf.getCharacterAt(x - 1, y - 1).equals(M) &&
                    cf.getCharacterAt(x - 2, y - 2).equals(A) &&
                    cf.getCharacterAt(x - 3, y - 3).equals(S)) {
                counter++;
            }
        }
        return counter;
    }


    private static void part2(List<String> inputLines) {
        long count = 0;

        for (int y = 0; y < cf.getMaxY(); y++) {
            for (int x = 0; x < cf.getMaxX(); x++) {
                String character = cf.getCharacterAt(x, y);
                if (character.equals(A) && isPart2Xmas(x, y)) count++;
            }
        }
        System.out.println("\nPart 2 > Result: " + count);
    }

    private static boolean isPart2Xmas(int x, int y) {
        // check boundaries
        if (cf.isContained(x - 1, y - 1) && cf.isContained(x + 1, y - 1) &&
                cf.isContained(x + 1, y + 1) && cf.isContained(x - 1, y + 1)) {
            int count = 0;
            if (cf.getCharacterAt(x - 1, y - 1).equals(M) && cf.getCharacterAt(x + 1, y + 1).equals(S)) count++;
            if (cf.getCharacterAt(x - 1, y - 1).equals(S) && cf.getCharacterAt(x + 1, y + 1).equals(M)) count++;

            if (cf.getCharacterAt(x - 1, y + 1).equals(M) && cf.getCharacterAt(x + 1, y - 1).equals(S)) count++;
            if (cf.getCharacterAt(x - 1, y + 1).equals(S) && cf.getCharacterAt(x + 1, y - 1).equals(M)) count++;
            if (count == 2) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}