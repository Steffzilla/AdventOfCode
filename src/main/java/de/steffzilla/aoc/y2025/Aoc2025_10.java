package de.steffzilla.aoc.y2025;

import de.steffzilla.competitive.AocUtils;
import org.apache.commons.lang3.StringUtils;
import org.javatuples.Pair;
import org.jspecify.annotations.NonNull;
import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;

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
            List<int[]> buttonWiringBinary = getButtonWiringBinary(lightDiagram.length(), parts[0]);

            List<Integer> buttonWiringAsNumbers = getWiringAsNumbers(buttonWiringBinary);

            String joltageRequirements = parts[1].substring(0, parts[1].length() - 1);
            String[] singleJoltages = joltageRequirements.split(",");
            int[] numericJoltages = new int[singleJoltages.length];
            for (int i = 0; i < singleJoltages.length; i++) {
                numericJoltages[i] = Integer.parseInt(singleJoltages[i]);
            }
            descriptions.add(
                    new MachineDescription(lightTargetState, buttonWiringAsNumbers, buttonWiringBinary, numericJoltages));
        }
        return new Pair<>(part1(descriptions), part2(descriptions));
    }

    static @NonNull List<Integer> getWiringAsNumbers(List<int[]> buttonWiringBinary) {
        List<Integer> buttonWiringAsNumbers = new ArrayList<>();
        for (int[] digits : buttonWiringBinary) {
            StringBuilder sb = new StringBuilder();
            for (int digit : digits) {
                sb.append(digit);
            }
            buttonWiringAsNumbers.add(Integer.parseInt(sb.toString(), 2));
        }
        return buttonWiringAsNumbers;
    }

    static int getLightTargetState(String lightDiagram) {
        lightDiagram = lightDiagram.replace('.', '0');
        lightDiagram = lightDiagram.replace('#', '1');
        return Integer.parseInt(lightDiagram, 2);
    }

    static List<int[]> getButtonWiringBinary(int numberOfDigits, String buttonWiring) {
        List<int[]> buttonWiringBinary = new ArrayList<>();
        Pattern pattern = Pattern.compile("([\\d,]+)");
        Matcher matcher = pattern.matcher(buttonWiring);

        while (matcher.find()) {
            int[] digits = new int[numberOfDigits];
            Arrays.fill(digits, 0);
            String buttonSpec = matcher.group(1);
            String[] split = buttonSpec.split(",");
            for (String digit : split) {
                digits[Integer.parseInt(digit)] = 1;
            }
            buttonWiringBinary.add(digits);
        }
        return buttonWiringBinary;
    }

    private static String part1(List<MachineDescription> descriptions) {
        long result = 0;
        for (MachineDescription description : descriptions) {
            result += getMinimalButtonPressesPart1(description);
        }
        System.out.println("\nPart 1 > Result: " + result);
        return String.valueOf(result);
    }

    private static long getMinimalButtonPressesPart1(MachineDescription description) {
        int count = 0;
        boolean found = false;
        // check if target state == start state (all lights off)
        if (description.lightTargetState == 0) return count;
        Set<Integer> states = Set.of(0);
        do {
            if (states.isEmpty()) {
                throw new IllegalStateException("states should never be empty");
            }
            count++;
            Set<Integer> newStates = new HashSet<>();
            for (Integer state : states) {
                for (Integer buttonWiring : description.buttonWiringAsNumbers) {
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
        long result = 0;
        for (MachineDescription description : descriptions) {
            result += getMinimalButtonPressesPart2(description);
        }
        System.out.println("\nPart 2 > Result: " + result);
        return String.valueOf(result);
    }

    private static long getMinimalButtonPressesPart2(MachineDescription description) {
        long count = 0;
        boolean found = false;
        int length = description.joltageRequirements.length;
        int[] initialState = new int[length];
        Arrays.fill(initialState, 0);
        // check if target state == start state (all lights off)
        if (Arrays.equals(description.joltageRequirements, initialState)) return count;

        count = solveEquations(description.joltageRequirements, description.buttonWiringBinary);
        return count;
    }

    static long solveEquations(int[] joltageRequirements, List<int[]> reformattedButtonWiringList) {
        ExpressionsBasedModel model = new ExpressionsBasedModel();
        List<Variable> variables = new ArrayList<>();
        //(1,3) (1,2) (0,1,2,3) {19,46,28,37}
        for (int i = 0; i < reformattedButtonWiringList.size(); i++) {
            variables.add(model.addVariable("v" + i).integer(true).lower(0));
            //System.out.println(variables.get(i));
        }
        for (int i = 0; i < joltageRequirements.length; i++) {
            Expression eq = model.addExpression("eq" + i).level(joltageRequirements[i]);
            for (int j = 0; j < reformattedButtonWiringList.size(); j++) {
                int[] button = reformattedButtonWiringList.get(j);
                if (button[i] > 0) {
                    eq.set(variables.get(j), button[i]);
                }
            }
        }
        Expression objective = model.addExpression("obj").weight(1.0);
        for (Variable v : variables) {
            objective.set(v, 1);
        }
        Optimisation.Result result = model.minimise();
        if (!result.getState().isOptimal()) {
            throw new IllegalStateException("Result state needs to be optimal! " + result.getState());
        }
        double value = result.getValue();
        return Math.round(value);
    }

    static List<int[]> reformatButtonWiringList(List<Integer> buttonWiringList, int length) {
        List<int[]> bW = new ArrayList<>();
        for (int buttonWiring : buttonWiringList) {
            String binaryString = StringUtils.leftPad(Integer.toBinaryString(buttonWiring), length, '0');
            int[] bits = new int[binaryString.length()];
            for (int i = 0; i < binaryString.length(); i++) {
                bits[i] = Character.getNumericValue(binaryString.charAt(i));
            }
            bW.add(bits);
        }
        return bW;
    }

    private record MachineDescription(int lightTargetState, List<Integer> buttonWiringAsNumbers,
                                      List<int[]> buttonWiringBinary, int[] joltageRequirements) {
    }

}