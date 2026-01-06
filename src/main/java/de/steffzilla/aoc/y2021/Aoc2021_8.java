package de.steffzilla.aoc.y2021;

import de.steffzilla.competitive.Utils;

import java.util.*;

public class Aoc2021_8 {

    private static final String DAY = "08";
    private static final String YEAR = "2021";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    //public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;

    private static final int DIGIT_0 = 0;
    private static final int DIGIT_1 = 1;
    private static final int DIGIT_2 = 2;
    private static final int DIGIT_3 = 3;
    private static final int DIGIT_4 = 4;
    private static final int DIGIT_5 = 5;
    private static final int DIGIT_6 = 6;
    private static final int DIGIT_7 = 7;
    private static final int DIGIT_8 = 8;
    private static final int DIGIT_9 = 9;

    private static final Character SEGMENT_AAAA = 'a';
    private static final Character SEGMENT_BBBB = 'b';
    private static final Character SEGMENT_CCCC = 'c';
    private static final Character SEGMENT_DDDD = 'd';
    private static final Character SEGMENT_EEEE = 'e';
    private static final Character SEGMENT_FFFF = 'f';
    private static final Character SEGMENT_GGGG = 'g';

    public static void main(String[] args) {
        List<String> inputLines = Utils.getStringList(PATH);

        part1(inputLines);
        //part2();
    }

    private static void part1(List<String> inputLines) {
        int[] numberOfNumbers = new int[10];

        String[] signalPatterns;
        String[] fourDigits;
        for (String line : inputLines) {
            String[] parts = line.split(" \\| ");
            signalPatterns = parts[0].split(" ");
            if(signalPatterns.length != 10) {
                System.out.println("ARG");
            }
            fourDigits = parts[1].split(" ");
            if(fourDigits.length != 4) {
                System.out.println("ARG");
            }
            numberOfNumbers = solveLine(signalPatterns, fourDigits, numberOfNumbers);
        }

        int sum = numberOfNumbers[1] + numberOfNumbers[4] +numberOfNumbers[7] +numberOfNumbers[8];
        System.out.println("\nResult: "+sum);
    }

    private static int[] solveLine(String[] signalPatterns, String[] fourDigits, int[] numberOfNumbers) {
        // acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf

        // ab --> 1; dab --> 7; fbcad --> [2,3,5]; ...
        HashMap<String, List<Integer>> patternToDigit = new HashMap<>();
        // für Rückschluss
        HashMap<Integer, String> digitToPattern = new HashMap<>();

        // a-->d => d as input means segment a as output
        // b-->ef => b as input means either segment e or segment f as output
        HashMap<Character, Set<Character>> segmentCharToInputChars = new HashMap<>();
        for (String inputPattern : signalPatterns) {
            if (inputPattern.length() == 2) {
                // 1 --> cf
                ArrayList<Integer> digit = new ArrayList<>();
                digit.add(DIGIT_1);
                patternToDigit.put(inputPattern, digit);
                digitToPattern.put(DIGIT_1, inputPattern);

                adjustMap(segmentCharToInputChars, inputPattern, List.of(SEGMENT_CCCC, SEGMENT_FFFF));
                if(digitToPattern.containsKey(DIGIT_7)) {
                    String sevenPattern = digitToPattern.get(DIGIT_7);
                    Character difference = Utils.findDiffChar(inputPattern, sevenPattern);
                    //a-->d => d as input means segment a
                    segmentCharToInputChars.put(SEGMENT_AAAA, Set.of(difference));
                }
            } else if (inputPattern.length() == 3) {
                // 7 --> acf
                ArrayList<Integer> digits = new ArrayList<>();
                digits.add(DIGIT_7);
                patternToDigit.put(inputPattern, digits);
                digitToPattern.put(DIGIT_7, inputPattern);

                adjustMap(segmentCharToInputChars, inputPattern, List.of(SEGMENT_AAAA, SEGMENT_CCCC, SEGMENT_FFFF));
                if(digitToPattern.containsKey(DIGIT_1)) {
                    String onePattern = digitToPattern.get(DIGIT_1);
                    Character difference = Utils.findDiffChar(inputPattern, onePattern);
                    //a-->d => d as input means segment a
                    segmentCharToInputChars.put(SEGMENT_AAAA, Set.of(difference));
                }
            } else if (inputPattern.length() == 4) {
                // 4 --> bcdf
                ArrayList<Integer> digits = new ArrayList<>();
                digits.add(DIGIT_4);
                patternToDigit.put(inputPattern, digits);
                digitToPattern.put(DIGIT_4, inputPattern);
                adjustMap(segmentCharToInputChars, inputPattern, List.of(SEGMENT_BBBB, SEGMENT_CCCC, SEGMENT_DDDD, SEGMENT_FFFF));
            } else if (inputPattern.length() == 5) {
                // 2,3,5
                ArrayList<Integer> digits = new ArrayList<>();
                digits.add(DIGIT_2);
                digits.add(DIGIT_3);
                digits.add(DIGIT_5);
                patternToDigit.put(inputPattern, digits);
            } else if (inputPattern.length() == 6) {
                // 0,6,9
                ArrayList<Integer> digits = new ArrayList<>();
                digits.add(DIGIT_0);
                digits.add(DIGIT_6);
                digits.add(DIGIT_9);
                patternToDigit.put(inputPattern, digits);
            } else if (inputPattern.length() == 7) {
                // 8 --> abcdefg
                ArrayList<Integer> digits = new ArrayList<>();
                digits.add(DIGIT_8);
                patternToDigit.put(inputPattern, digits);
                digitToPattern.put(DIGIT_8, inputPattern);
            } else {
                throw new IllegalStateException("Should not occur " + inputPattern);
            }
        }

        // TODO jetzt ist SEGMENT_AAAA geklärt --> diesen Buchstaben in den anderen Sets entfernen
        removeXXX(SEGMENT_AAAA, segmentCharToInputChars);
        return numberOfNumbers;
    }

    private static void removeXXX(Character clarifiedSegmentChar, HashMap<Character, Set<Character>> segmentCharToInputChars) {
        Set<Character> chars = segmentCharToInputChars.get(clarifiedSegmentChar);
        if(chars.size()!=1) {
            throw new IllegalStateException("Should not occur");
        }
        Character inputCharToBeRemoved = 'x';
        for (Character temp : chars) {
            inputCharToBeRemoved = temp;
        }

        for (Character currentSegmentCharacter : segmentCharToInputChars.keySet()) {
            if (!currentSegmentCharacter.equals(clarifiedSegmentChar)) {
                Set<Character> characters = segmentCharToInputChars.get(currentSegmentCharacter);
                boolean remove = characters.remove(inputCharToBeRemoved);
                "".toString();//segmentCharToInputChars.put(currentSegmentCharacter, characters);
            }
        }
    }

    private static void adjustMap(HashMap<Character, Set<Character>> outputCharToInputChars, String inputPattern, List<Character> possibleOutputChars) {
        HashSet<Character> inputChars = new HashSet<>(); // TODO keine wiederverwendung der Liste für mehrere buchstaben
        for (Character inputCharacter : inputPattern.toCharArray()) {
            inputChars.add(inputCharacter);
        }
        for (Character outputChar : possibleOutputChars) {
            // add existing ones before replacing them
            if(outputCharToInputChars.get(outputChar)!=null) {
                inputChars.addAll(outputCharToInputChars.get(outputChar));
            }
            outputCharToInputChars.put(outputChar, inputChars);
        }
    }


    private static void part2() {
        System.out.println("\nResult: ");
    }

}