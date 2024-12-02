package de.steffzilla.aoc.y2023;

import de.steffzilla.aoc.AocUtils;
import de.steffzilla.aoc.CharacterField;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

public class Aoc2023_03 {

    private static final String DAY = "03";
    private static final String YEAR = "2023";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;
    public static final String GEAR = "*";

    static CharacterField field;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);
        field = new CharacterField(inputLines);
        field.prettyPrint();
        System.out.println();

        //part1();
        part2();
    }

    private static void part1(){
        long sum = 0;
        for (int y = 0; y < field.getMaxY(); y++) {
            for (int x = 0; x < field.getMaxX(); x++) {
                String character = field.getCharacterAt(x,y);
                if (!character.equals(".")) {
                     if(NumberUtils.isCreatable(character)) {
                         // first digit found
                         String number = getCompleteNumber(x, y);
                         System.out.println(number);

                         if (isPartNumber(x,y, number)) {
                            sum+=Integer.parseInt(number);
                         }

                         // move x behind number and one more (which is empty)
                         if(field.isContained(x+number.length(),y)) {
                             x = x + number.length();
                         } else {
                             // continue in next line
                             break;
                         }
                     }

                }
            }
        }
        System.out.println("\nPart 1 > Result: " + sum);
    }

    private static boolean isPartNumber(int x, int y, String number) {
        for (int y2 = y-1; y2 <= y+1; y2++) {
            for (int x2 = x-1; x2 <= x+number.length(); x2++) {
                if (field.isContained(x2,y2)) {
                    String character = field.getCharacterAt(x2, y2);
                    if (!character.equals(".") && !NumberUtils.isCreatable(character)) {
                        System.out.println("found " + character + " for "+number);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static int getGearRatio(int xGear, int yGear) {
        List<Integer> numbers = new ArrayList<>(2);
        for (int y = yGear - 1; y <= yGear + 1; y++) {
            boolean numberFound = false;
            for (int x = xGear - 1; x <= xGear + 1; x++) {
                if(field.isContained(x,y)) {
                    if (!NumberUtils.isCreatable(field.getCharacterAt(x, y))) {
                        // reset number found to be ready for a potential next number
                        numberFound = false;
                    } else if (!numberFound) {
                        numberFound = true;
                        String number = getCompleteNumber(x, y);
                        numbers.add(Integer.valueOf(number));
                    }
                }
            }
        }
        if (numbers.size() == 2) {
            System.out.println(numbers.get(0) + "*" + numbers.get(1) + "=" + (numbers.get(0) * numbers.get(1)));
            return numbers.get(0) * numbers.get(1);
        } else {
            return 0;
        }
    }

    private static String getCompleteNumber(int x, int y) {
        StringBuilder stringBuilder = new StringBuilder();
        // first go left to the start
        for (int x1 = x; x1 >= 0; x1--) {
            if (field.isContained(x1-1, y) && NumberUtils.isCreatable(field.getCharacterAt(x1-1, y))) {
                continue;
            } else {
                // start found
                x = x1;
                break;
            }
        }
        // go right
        for (int x2 = x; x2 < field.getMaxX() ; x2++) {
            String nextCharacter = field.getCharacterAt(x2, y);
            stringBuilder.append(nextCharacter);
            // look ahead
            if (field.isContained(x2+1, y) && NumberUtils.isCreatable(field.getCharacterAt(x2+1, y))) {
                continue;
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }


    private static void part2() {
        long sumOfRatios = 0;
        for (int y = 0; y < field.getMaxY(); y++) {
            for (int x = 0; x < field.getMaxX(); x++) {
                String character = field.getCharacterAt(x,y);
                if (GEAR.equals(character)) {
                    sumOfRatios+=getGearRatio(x, y);
                }
            }
        }
        System.out.println("\nPart 2 > Result: " + sumOfRatios);
    }

}