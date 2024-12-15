package de.steffzilla.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    public static List<List<Integer>> readLinesAsInts(String regex, List<String> inputLines) {
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

    public static List<String> getStringList(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            return stream.toList();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Pair<List<String>, List<String>> getStringLists(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            List<String> wholeList = stream.toList();
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
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
