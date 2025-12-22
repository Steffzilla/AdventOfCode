package de.steffzilla.aoc.y2025;

import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2025_10 {

    private static final String DAY = "10";
    private static final String YEAR = "2025";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    static final String example = """
            [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
            [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
            [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
            """;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        //List<String> inputLines = example.lines().toList();
        List<String> inputLines =
        AocUtils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        List<MachineDescription> descriptions = new ArrayList<>();
        for (String line : inputLines) {
            String[] parts = line.split("] ");
            String lightDiagram = parts[0].substring(1);
            int lightTargetState = getLightTargetState(lightDiagram);
            String remainder = parts[1];
            parts = remainder.split(" \\{");
            List<Integer> buttonWiringList = getButtonWiringList(lightDiagram.length(), parts[0]);
            String joltageRequirements = parts[1].substring(0, parts[1].length() - 1);
            descriptions.add(
                    new MachineDescription(lightTargetState, buttonWiringList, joltageRequirements));
        }
        return new Pair<>(part1(descriptions), part2(descriptions));
    }

    static int getLightTargetState(String lightDiagram) {
        lightDiagram = lightDiagram.replace('.', '0');
        lightDiagram = lightDiagram.replace('#', '1');
        return Integer.parseInt(lightDiagram, 2);
    }

    static List<Integer> getButtonWiringList(int numberOfDigits, String buttonWiring) {
        List<Integer> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("([\\d,]+)");
        Matcher matcher = pattern.matcher(buttonWiring);
        int[] digits = new int[numberOfDigits];

        while (matcher.find()) {
            Arrays.fill(digits, 0);
            String buttonSpec = matcher.group(1);
            String[] split = buttonSpec.split(",");
            for (String digit : split) {
                digits[Integer.parseInt(digit)] = 1;
            }
            StringBuilder sb = new StringBuilder();
            for (int digit : digits) {
                sb.append(digit);
            }
            result.add(Integer.parseInt(sb.toString(), 2));
        }
        return result;
    }

    private static String part1(List<MachineDescription> descriptions) {
        long result = 0;
        for (MachineDescription description : descriptions) {
            result += getMinimalButtonPresses(description);
//            System.out.println(result);
        }
        System.out.println("\nPart 1 > Result: " + result);
        return String.valueOf(result);
    }

    private static long getMinimalButtonPresses(MachineDescription description) {
        int count = 0;
        boolean found = false;
        // check if target state == start state (all lights off)
        if (description.lightTargetState == 0) return count;
        Set<Integer> states = Set.of(0);
        do {
            count++;
            Set<Integer> newStates = new HashSet<>();
            for (Integer state : states) {
                for (Integer buttonWiring : description.buttonWiringList) {
                    newStates.add(state ^ buttonWiring);
                }
            }
            if (newStates.contains(description.lightTargetState)) {
                found = true;
            } else {
                states = newStates;
            }
        } while (!found);
        return count;
    }


    private static String part2(List<MachineDescription> descriptions) {
        long count = 0;
        for (MachineDescription description : descriptions) {

        }
        System.out.println("\nPart 2 > Result: " + count);
        return String.valueOf(count);
    }

    private record MachineDescription(int lightTargetState, List<Integer> buttonWiringList,
                                      String joltageRequirements) {
    }

}