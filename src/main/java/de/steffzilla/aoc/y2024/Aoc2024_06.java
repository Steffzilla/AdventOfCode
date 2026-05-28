package de.steffzilla.aoc.y2024;

import de.steffzilla.competitive.Utils;
import de.steffzilla.competitive.CharacterField;
import de.steffzilla.competitive.Pair;
import de.steffzilla.competitive.Position;

import java.util.HashSet;
import java.util.List;

public class Aoc2024_06 {

    private static final String DAY = "06";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;
    public static final String BLOCKER = "#";
    private static Directions orientation;
    private static HashSet<Position> distinctPositions;

    private enum Directions {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private static CharacterField cf;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        List<String> inputLines = Utils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        distinctPositions = new HashSet<>();
        cf = new CharacterField(inputLines);
        List<Position> positions = cf.searchCharacters("^");
        if (positions.size() != 1) {
            throw new IllegalStateException("^ pos not found");
        }
        orientation = Directions.UP; // TODO Using a field is rather hacky...

        String result1 = part1(inputLines, positions.getFirst());
        String result2 = part2(positions.getFirst());
        return new Pair<>(result1, result2); // for testability
    }

    static String part1(List<String> inputLines, Position currentPos) {
        distinctPositions.add(currentPos);
        while (cf.isContained(currentPos)) {
            distinctPositions.add(currentPos);
            currentPos = move(currentPos, cf);
        }

        System.out.println("\nPart 1 > Result: " + distinctPositions.size());
        return String.valueOf(distinctPositions.size());
    }

    private static Position move(Position currentPos, CharacterField field) {
        Position newPos = switch (orientation) {
            case UP -> new Position(currentPos.x(), currentPos.y() - 1);
            case DOWN -> new Position(currentPos.x(), currentPos.y() + 1);
            case LEFT -> new Position(currentPos.x() - 1, currentPos.y());
            case RIGHT -> new Position(currentPos.x() + 1, currentPos.y());
        };
        if (!field.isContained(newPos)) {
            return newPos; // way out found
        }
        if (field.getCharacterAt(newPos).equals(BLOCKER)) {
            switch (orientation) {
                case UP -> orientation = Directions.RIGHT;
                case DOWN -> orientation = Directions.LEFT;
                case LEFT -> orientation = Directions.UP;
                case RIGHT -> orientation = Directions.DOWN;
            }
            return move(currentPos, field);
        }
        return newPos;
    }

    static String part2(Position startPos) {
        long loopCount = 0;

        for (Position newBlockerPos : distinctPositions) {
            CharacterField newField = new CharacterField(cf);
            newField.setCharacterAt(BLOCKER, newBlockerPos);
            //System.out.println("Check if Block on " + newBlockerPos + " causes a loop");
            orientation = Directions.UP; // start orientation
            if (checkForLoop(newField, startPos)) loopCount++;
        }
        System.out.println("\nPart 2 > Result: " + loopCount);
        return String.valueOf(loopCount);
    }

    private static boolean checkForLoop(CharacterField newField, Position currentPos) {
        HashSet<Pair<Position, Directions>> positionsForLoopDetection = new HashSet<>();
        int oldSize = 0;
        while (newField.isContained(currentPos)) {
            positionsForLoopDetection.add(new Pair<>(currentPos, orientation));
            if(oldSize == positionsForLoopDetection.size()) {
                return true;
            } else {
                oldSize = positionsForLoopDetection.size();
                currentPos = move(currentPos, newField);
            }
        }
        return false;
    }

}
