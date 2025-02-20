package de.steffzilla.aoc.y2023;

import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;

import java.util.*;

public class Aoc2023_18_old {

    private static final String DAY = "18";
    private static final String YEAR = "2023";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;
    public static final String RIGHT = "R";
    public static final String LEFT = "L";
    public static final String DOWN = "D";
    public static final String UP = "U";
    public static final String START = "START";
    private static final LinkedHashMap<Pair<Integer, Integer>, DirectionAndColor> fieldBorder = new LinkedHashMap<>();
    /** Max x value of field. 0 based */
    private static int maxX = 0;
    /** Max y value of field. 0 based */
    private static int maxY = 0;
    /** Min x value of field. 0 based */
    private static int minX = 0;
    /** Min y value of field. 0 based */
    private static int minY = 0;

    static final String example = """
            U 6 (#70c710)
            L 6 (#70c710)
            D 12 (#70c710)
            R 12 (#70c710)
            U 6 (#70c710)
            L 6 (#70c710)
            """;

    public record DirectionAndColor (String direction, String color) {}

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        //List<String> inputLines = AocUtils.getStringList(PATH);
        List<String> inputLines = example.lines().toList();

        part1(inputLines);
        //part2(inputLines);
    }

    private static void part1(List<String> inputLines) {
        Pair<Integer, Integer> currentPos = new Pair<>(0, 0);
        fieldBorder.put(currentPos, new DirectionAndColor(START, START));
        for (String line : inputLines) {
            String[] inputParts = line.split(" ");
            String direction = inputParts[0];
            int steps = Integer.parseInt(inputParts[1]);
            String hexCode = inputParts[2].substring(2, 8);
            System.out.println(direction + "|" + steps + "|" + hexCode);
            for (int stepNo = 1; stepNo <= steps; stepNo++) {
                currentPos = move(currentPos.getValue0(), currentPos.getValue1(), direction);
                fieldBorder.put(currentPos, new DirectionAndColor(direction, hexCode));
            }
            if (currentPos.getValue0() > maxX) {
                maxX = currentPos.getValue0();
            }
            if (currentPos.getValue1() > maxY) {
                maxY = currentPos.getValue1();
            }
            if (currentPos.getValue0() < minX) {
                minX = currentPos.getValue0();
            }
            if (currentPos.getValue1() < minY) {
                minY = currentPos.getValue1();
            }
        }
        printFieldPath(fieldBorder.keySet());
        Set<Pair<Integer, Integer>> joinedSet = new HashSet<>(fieldBorder.keySet());
        joinedSet.addAll(fillField());
        printFieldPath(joinedSet);

        System.out.println("\nPart 1 > Result: " + joinedSet.size());
    }

    private static Set<Pair<Integer, Integer>> fillField() {
        Set<Pair<Integer, Integer>> collectedFields = new HashSet<>();
        Pair<Integer, Integer> oldPosition = fieldBorder.keySet().stream().findFirst().get();
        String oldDirection = "";
        for (Map.Entry<Pair<Integer, Integer>, DirectionAndColor> entry : fieldBorder.entrySet()) {
            Pair<Integer, Integer> position = entry.getKey();
            DirectionAndColor directionAndColor = entry.getValue();
            switch (directionAndColor.direction) {
                case START:
                    break; // nothing to do
                case RIGHT:
                    collectedFields.addAll(getStraightConnectedFields(position, 0, 1)); // go down
                    if (!directionAndColor.direction.equals(oldDirection)) {
                        collectedFields.addAll(getStraightConnectedFields(oldPosition, 0, 1)); // go down
                    }
                    break;
                case LEFT:
                    collectedFields.addAll(getStraightConnectedFields(position, 0, -1)); // go up
                    if (!directionAndColor.direction.equals(oldDirection)) {
                        collectedFields.addAll(getStraightConnectedFields(oldPosition, 0, -1)); // go up
                    }
                    break;
                case DOWN:
                    collectedFields.addAll(getStraightConnectedFields(position, -1, 0)); // go left
                    if (!directionAndColor.direction.equals(oldDirection)) {
                        collectedFields.addAll(getStraightConnectedFields(oldPosition, -1, 0)); // go left
                    }
                    break;
                case UP:
                    collectedFields.addAll(getStraightConnectedFields(position, +1, 0)); // go right
                    if (!directionAndColor.direction.equals(oldDirection)) {
                        collectedFields.addAll(getStraightConnectedFields(oldPosition, +1, 0)); // go right
                    }
                    break;
                default:
                    throw new IllegalStateException("Should not occur:" + directionAndColor.direction);
            }
            oldPosition = position;
            oldDirection = directionAndColor.direction;
        }
        return collectedFields;
    }

    private static Collection<Pair<Integer, Integer>> getStraightConnectedFields(Pair<Integer, Integer> start, int xStep, int yStep) {
        Set<Pair<Integer, Integer>> fields = new HashSet<>();
        int currentX = start.getValue0()+xStep;
        int currentY = start.getValue1()+yStep;
        Pair<Integer, Integer> currentPos = new Pair<>(currentX, currentY);
        while (currentX >= minX && currentX <= maxX && currentY >= minY && currentY <= maxY &&
                !fieldBorder.containsKey(currentPos)) {
            fields.add(currentPos);
            currentX += xStep;
            currentY += yStep;
            currentPos = new Pair<>(currentX, currentY);
        }
        return fields;
    }

    private static void printFieldPath(Set<Pair<Integer, Integer>> path) {
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                if(path.contains(new Pair<>(x, y))){
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    private static Pair<Integer, Integer> move(Integer x, Integer y, String direction) {
        return switch (direction) {
            case RIGHT -> new Pair<>(x + 1, y);
            case LEFT -> new Pair<>(x - 1, y);
            case DOWN -> new Pair<>(x, y + 1);
            case UP -> new Pair<>(x, y - 1);
            default -> throw new IllegalStateException("Illegal direction " + direction);
        };
    }

    private static void part2(List<String> inputLines) {
        for (String line : inputLines) {

        }
        System.out.println("\nPart 2 > Result: " + "x");
    }

}