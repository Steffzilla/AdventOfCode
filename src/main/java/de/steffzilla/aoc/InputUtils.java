package de.steffzilla.aoc;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.javatuples.Pair;

public class InputUtils {

    public static List<List<String>> readLinesAsStrings(String regex, List<String> inputLines) {
        Pattern pattern = Pattern.compile(regex);
        List<List<String>> result = new ArrayList<>();
        for (String line : inputLines) {
            List<String> groupResults = new ArrayList<>();
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            for (int i = 1; i <= matcher.groupCount(); i++) {
                groupResults.add(matcher.group(i));
            }
            result.add(groupResults);
        }
        return result;
    }

    /**
     * Extracts integers form lines by a regular expression.
     * Example:
     * p=79,42 v=17,-55
     * p=74,2 v=60,8
     * readLinesAsIntegers("^p=(-?\\d*),(-?\\d*) v=(-?\\d*),(-?\\d*)", inputLines);
     */
    public static List<List<Integer>> readLinesAsIntegers(String regex, List<String> inputLines) {
        Pattern pattern = Pattern.compile(regex);
        List<List<Integer>> result = new ArrayList<>();
        for (String line : inputLines) {
            List<Integer> groupResults = new ArrayList<>();
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            for (int i = 1; i <= matcher.groupCount(); i++) {
                String s = matcher.group(i);
                groupResults.add(Integer.parseInt(s));
            }
            result.add(groupResults);
        }
        return result;
    }

    /**
     * Converts the numbers in the given line to {@link BigInteger}s. regex is used to split line.
     */
    public static List<BigInteger> readLineAsBigIntegers(String line, String regex) {
        return Arrays.stream(line.split(regex)).map(BigInteger::new).collect(Collectors.toCollection(LinkedList::new));
    }

    public static List<String> readColumnOfFileFromRight(List<String> inputLines) {
        int length = inputLines.getFirst().length();
        for (String line : inputLines) {
            if (line.length() != length) throw new IllegalStateException("Line length needs to be the same! " + line);
        }

        List<String> result = new ArrayList<>();
        for (int i = length - 1; i >= 0; i--) {
            StringBuilder column = new StringBuilder();
            for (String line : inputLines) {
                column.append(line.charAt(i));
            }
            result.add(column.toString());
        }

        return result;
    }

    public static List<String> getStringList(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            return stream.toList();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Pair<List<String>, List<String>> splitLists(List<String> wholeList) {
        int emptyLineIndex = wholeList.indexOf("");

        List<String> beforeEmptyLine;
        List<String> afterEmptyLine;

        if (emptyLineIndex == -1) {
            // No empty line found, all goes into the first list
            beforeEmptyLine = new ArrayList<>(wholeList);
            afterEmptyLine = new ArrayList<>();
        } else {
            // Split the list at the empty line
            beforeEmptyLine = new ArrayList<>(wholeList.subList(0, emptyLineIndex));
            afterEmptyLine = new ArrayList<>(wholeList.subList(emptyLineIndex + 1, wholeList.size()));
        }
        return new Pair<>(beforeEmptyLine, afterEmptyLine);
    }

    public static List<Pair<Integer, Integer>> readLinesAsCoordinates(List<String> inputLines) {
        return readLinesAsCoordinates(inputLines, Integer.MAX_VALUE);
    }

    /**
     * Returns the lines as coordinates including line number untilLineNumber.
     */
    public static List<Pair<Integer, Integer>> readLinesAsCoordinates(List<String> inputLines, int untilLineNumber) {
        List<Pair<Integer, Integer>> coordinates = new ArrayList<>();
        int lineNumber = 1;
        for (String line : inputLines) {
            if (lineNumber > untilLineNumber) {
                break;
            }
            String[] parts = line.split(",");
            coordinates.add(new Pair<>(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            lineNumber++;
        }
        return coordinates;
    }

    /**
     * For a file with one number per line a list of Longs is returned.
     */
    public static List<Long> getListOfLongFromFile(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            return stream.mapToLong(Long::valueOf).boxed().toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
