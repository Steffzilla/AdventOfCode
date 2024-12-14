package de.steffzilla.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

}
