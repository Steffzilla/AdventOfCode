package de.steffzilla.aoc.y2023;

import de.steffzilla.aoc.AocUtils;
import de.steffzilla.aoc.CharacterField;

import java.util.ArrayList;
import java.util.List;

public class Aoc2023_13 {

    private static final String DAY = "13";
    private static final String YEAR = "2023";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    //public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    public static final String FILENAME = "sample" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;
    public static final String ROCKS = "#";
    public static final String ASH = ".";

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);

        //part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines) {
        long result = 0;
        List<String> currentField = new ArrayList<>();
        for (String line : inputLines) {
            if (!line.isEmpty()) {
                currentField.add(line);
            } else {
                CharacterField field = new CharacterField(currentField);
                result += processField(field);
                currentField = new ArrayList<>();
                System.out.println();
            }
        }
        System.out.println("\nPart 1 > Result: " + result);
    }

    private static long processField(CharacterField field) {
        //field.prettyPrint();
        int colResult = processColumns(field);
        int rowResult = processRows(field);
        if (colResult > 0 && rowResult > 0) {
            throw new IllegalStateException("Only vertical OR horizontal expected! Cols: " + colResult + " | Rows: " + rowResult);
        }
        return colResult + rowResult;
    }

    private static long processFieldPart2(CharacterField orgField) {
        if(orgField.getMaxY() % 2 == 0) {
            throw new IllegalStateException("Expecting only odd Y sizes " + orgField.getMaxY());
        }
        if(orgField.getMaxX() % 2 == 0) {
            throw new IllegalStateException("Expecting only odd X sizes " + orgField.getMaxX());
        }
        //FIXME int colResult = processColumnsPart2(orgField);
        int rowResult = processRowsPart2(orgField);
        /*if (colResult > 0 && rowResult > 0) {
            throw new IllegalStateException("Only vertical OR horizontal expected! Cols: " + colResult + " | Rows: " + rowResult);
        }
        return colResult + rowResult;*/
        return rowResult;
    }

    static int processColumnsPart2(CharacterField orgField) {
        for (int x = 0; x < orgField.getMaxX() - 1; x++) {
            if (hasVerticalMirror(orgField, x)) {
                return x + 1;
            }
        }
        return 0;
    }

    static int processRowsPart2(CharacterField orgField) {
        // to the middle
        for (int xPointer = 1; xPointer <= (orgField.getMaxY() / 2); xPointer++) {
            CharacterField orgPartialField =
                    orgField.copyFieldPartially(0, orgField.getMaxX() - 1, xPointer - 1, 2 * xPointer - 1);
            // change flip one field after another and check it
            for (int y = 0; y < orgPartialField.getMaxY(); y++) {
                for (int x = 0; x < orgPartialField.getMaxX(); x++) {
                    CharacterField modifiedPartialField = new CharacterField(orgPartialField);
                    if (ASH.equals(modifiedPartialField.getCharacterAt(x, y))) {
                        modifiedPartialField.setCharacterAt(ROCKS, x, y);
                    } else {
                        modifiedPartialField.setCharacterAt(ASH, x, y);
                    }
                    if (hasHorizontalMirror(modifiedPartialField, xPointer)) {
                        return (xPointer + 1) * 100;
                    }
                }
            }
        }
        // FIXME 2. Hälfte fehlt

        return 0;
    }

    private static int processRows(CharacterField field) {
        for (int y = 0; y < field.getMaxY() - 1; y++) {
            if (hasHorizontalMirror(field, y)) {
                return (y + 1) * 100;
            }
        }
        return 0;
    }

    private static boolean hasHorizontalMirror(CharacterField field, int yPos) {
        boolean horizontalMirror = false;
        if (field.areRowsEqual(yPos, yPos + 1)) {
            horizontalMirror = true;
            for (int i = 1; i < field.getMaxY(); i++) {
                int row1 = yPos - i;
                int row2 = yPos + 1 + i;
                // check for borders
                if (row1 < 0 || row2 >= field.getMaxY()) {
                    break;
                }
                if (!field.areRowsEqual(row1, row2)) {
                    horizontalMirror = false;
                    break;
                }
            }

        }
        return horizontalMirror;
    }

    private static int processColumns(CharacterField field) {
        for (int x = 0; x < field.getMaxX() - 1; x++) {
            if (hasVerticalMirror(field, x)) {
                return x + 1;
            }
        }
        return 0;
    }

    private static boolean hasVerticalMirror(CharacterField field, int xPos) {
        boolean verticalMirror = false;
        if (field.areColumnsEqual(xPos, xPos + 1)) {
            verticalMirror = true;
            for (int i = 1; i < field.getMaxX(); i++) {
                int col1 = xPos - i;
                int col2 = xPos + 1 + i;
                // check for borders
                if (col1 < 0 || col2 >= field.getMaxX()) {
                    break;
                }
                if (!field.areColumnsEqual(col1, col2)) {
                    verticalMirror = false;
                    break;
                }
            }
        }
        return verticalMirror;
    }

    private static void part2(List<String> inputLines) {
        long result = 0;
        List<String> currentField = new ArrayList<>();
        int i = 1;
        for (String line : inputLines) {
            if (!line.isEmpty()) {
                currentField.add(line);
            } else {
                long intermediateResult = Long.MIN_VALUE;
                CharacterField orgField = new CharacterField(currentField);
                intermediateResult = processFieldPart2(orgField);
                System.out.println(i + ":" + intermediateResult);
                result += intermediateResult;
                currentField = new ArrayList<>();
                i++;
            }
        }
        System.out.println("\nPart 2 > Result: " + result);
    }


}