package de.steffzilla.aoc.y2024;

import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Aoc2024_09 {

    private static final String DAY = "09";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    //public static final String FILENAME = "sample" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    static final String example = "12345";
    public static final String SPACE = " ";
    public static final int INVALID_FILE_ID = -1;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        //List<String> inputLines = example.lines().toList();
        List<String> inputLines = AocUtils.getStringList(PATH);
        if (inputLines.size() != 1) {
            throw new IllegalStateException("Unexpected number of lines!");
        }
        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        String line = inputLines.getFirst();
        int[] digits = line.chars()
                .map(Character::getNumericValue)
                .toArray();
        List<String> lineWithIdsAndFreeSpace = calculateLineWithIdsAndFreeSpace(digits);
        //System.out.println(lineWithIdsAndFreeSpace);
        return new Pair<>(part1(lineWithIdsAndFreeSpace), part2(lineWithIdsAndFreeSpace));
    }

    private static String part1(List<String> lineWithIdsAndFreeSpace) {
        ArrayList<Integer> compressed = compressLinePart1(lineWithIdsAndFreeSpace);

        long checksum = calculateChecksum(compressed);
        System.out.println("\nPart 1 > Result: " + checksum);
        return String.valueOf(checksum);
    }

    private static long calculateChecksum(List<Integer> compressed) {
        long checksum = 0;
        for (int i = 0; i < compressed.size(); i++) {
            checksum += (long) i * compressed.get(i);
        }
        return checksum;
    }

    private static ArrayList<Integer> compressLinePart1(List<String> lineWithIdsAndFreeSpace) {
        ArrayList<Integer> compressed = new ArrayList<>();
        int lastPos = lineWithIdsAndFreeSpace.size() - 1;
        for (int currentPos = 0; currentPos < lineWithIdsAndFreeSpace.size(); currentPos++) {
            if (currentPos > lastPos) {
                break;
            }
            String charAtCurrentPos = lineWithIdsAndFreeSpace.get(currentPos);
            if (!charAtCurrentPos.equals(SPACE)) {
                compressed.add(Integer.parseInt(charAtCurrentPos));
            } else {
                String character = null;
                while (character == null) {
                    String s = lineWithIdsAndFreeSpace.get(lastPos);
                    if (!s.equals(SPACE)) {
                        character = s;
                        lastPos--;
                    } else {
                        lastPos--;
                        if (lastPos <= currentPos) {
                            break;
                        }
                    }
                }
                if (character != null) {
                    compressed.add(Integer.parseInt(character));
                }
            }
        }
        return compressed;
    }

    private static List<String> calculateLineWithIdsAndFreeSpace(int[] digits) {
        ArrayList<String> lineWithIdsAndFreeSpace;
        lineWithIdsAndFreeSpace = new ArrayList<>();
        boolean isFile = true;
        long fileIdNumber = 0;
        for (int digit : digits) {
            String value;
            if (isFile) {
                value = String.valueOf(fileIdNumber);
                fileIdNumber++;
            } else {
                value = SPACE;
            }
            for (int j = 0; j < digit; j++) {
                lineWithIdsAndFreeSpace.add(value);
            }
            isFile = !isFile;
        }
        return lineWithIdsAndFreeSpace;
    }


    private static String part2(List<String> lineWithIdsAndFreeSpace) {
        List<String> compressed = compressLinePart2(lineWithIdsAndFreeSpace);
        //System.out.println(compressed);
        long checksum = calculateChecksumPart2(compressed);
        System.out.println("\nPart 2 > Result: " + checksum);
        return String.valueOf(checksum);
    }

    private static List<String> compressLinePart2(List<String> lineWithIdsAndFreeSpace) {
        TreeMap<Integer, Integer> mapOfFreeSpace =
                calculateMapOfFreeSpace(lineWithIdsAndFreeSpace);

        // first copy it
        List<String> compressed = new ArrayList<>(lineWithIdsAndFreeSpace);
        // processLastPart
        int currentFileId = Integer.parseInt(lineWithIdsAndFreeSpace.getLast());
        int numberOfParts = 1;
        // continue with the 2nd last part and all others
        for (int currentPos = lineWithIdsAndFreeSpace.size() - 2; currentPos > 0; currentPos--) {
            String s = lineWithIdsAndFreeSpace.get(currentPos);
            int fileId;
            if (s.equals(SPACE)) {
                fileId = INVALID_FILE_ID;
            } else {
                fileId = Integer.parseInt(s);
            }
            if (fileId == currentFileId) {
                numberOfParts++;
            } else {
                // complete file found
                if (currentFileId != INVALID_FILE_ID) {
                    tryToInsert(mapOfFreeSpace, currentFileId, numberOfParts, compressed, currentPos + 1);
                }

                // prepare for next currentFileId
                currentFileId = fileId;
                numberOfParts = 1;
            }
        }
        return compressed;
    }

    private static TreeMap<Integer, Integer> calculateMapOfFreeSpace(List<String> lineWithIdsAndFreeSpace) {
        // key: start pos | value: size of free space
        TreeMap<Integer, Integer> mapOfFreeSpace = new TreeMap<>();

        int startPosOfFreeSpace = -1;
        int counter = 0;
        for (int i = 0; i < lineWithIdsAndFreeSpace.size(); i++) {
            String s = lineWithIdsAndFreeSpace.get(i);
            if (s.equals(SPACE)) {
                if (startPosOfFreeSpace < 0) {
                    startPosOfFreeSpace = i;
                }
                counter++;
            } else {
                if (counter > 0) {
                    // Space section ended
                    mapOfFreeSpace.put(startPosOfFreeSpace, counter);

                    startPosOfFreeSpace = -1;
                    counter = 0;
                }
            }
        }
        return mapOfFreeSpace;
    }

    private static void tryToInsert(TreeMap<Integer, Integer> mapOfFreeSpace, int fileId, int length, List<String> compressed, int startPosOld) {
        //System.out.println("fileId:" + fileId + " length:" + length + " currentPos:" + currentPos);

        for (Map.Entry<Integer, Integer> entry : mapOfFreeSpace.entrySet()) {
            Integer startPos = entry.getKey();
            Integer sizeOfFreeSpace = entry.getValue();
            if(startPos >= startPosOld) {
                break; // don't move backward
            }

            boolean spaceFound = false;
            if (sizeOfFreeSpace == length) {
                spaceFound = true;
                mapOfFreeSpace.remove(startPos);
            } else if (sizeOfFreeSpace > length) {
                spaceFound = true;
                mapOfFreeSpace.remove(startPos);
                mapOfFreeSpace.put(startPos + length, sizeOfFreeSpace - length);
            }
            if (spaceFound) {
                for (int i = startPos; i < startPos + length; i++) {
                    compressed.set(i, String.valueOf(fileId));
                }
                // remove it at the end
                for (int i = startPosOld; i < startPosOld + length; i++) {
                    compressed.set(i, SPACE);
                }
                break;
            }
        }
    }

    private static long calculateChecksumPart2(List<String> compressed) {
        long checksum = 0;
        for (int i = 0; i < compressed.size(); i++) {
            if (!compressed.get(i).equals(SPACE)) {
                checksum += (long) i * Integer.parseInt(compressed.get(i));
            }
        }
        return checksum;
    }

}