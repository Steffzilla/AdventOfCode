package de.steffzilla.aoc.y2024;

import de.steffzilla.competitive.Utils;
import de.steffzilla.competitive.CharacterField;
import de.steffzilla.competitive.InputUtils;
import de.steffzilla.competitive.Pair;
import de.steffzilla.competitive.Position;

import java.util.*;

public class Aoc2024_15 {

    private static final String DAY = "15";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    //public static final String FILENAME = "sample" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    static final String example = """
            ########
            #..O.O.#
            ##@.O..#
            #...O..#
            #.#.O..#
            #...O..#
            #......#
            ########

            <^^>>>vv<v>>v<<
            """;
    public static final String ROBOT = "@";
    public static final String WALL = "#";
    public static final String BOX = "O";
    public static final String BIG_BOX_LEFT = "[";
    public static final String BIG_BOX_RIGHT = "]";
    public static final String RIGHT = ">";
    public static final String LEFT = "<";
    public static final String DOWN = "v";
    public static final String UP = "^";
    public static final String EMPTY = ".";
    private static Position robotPos;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        //List<String> inputLines = example.lines().toList();
        List<String> inputLines = Utils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        Pair<List<String>, List<String>> inputs = InputUtils.splitLists(inputLines);

        List<String> movementLines = inputs.getValue1();
        StringBuilder sbMovements = new StringBuilder();
        for (String line : movementLines) {
            sbMovements.append(line);
        }
        return new Pair<>(part1(sbMovements.toString(), new CharacterField(inputs.getValue0())),
                part2(sbMovements.toString(), new CharacterField(inputs.getValue0())));
    }

    private static String part1(String movements, CharacterField cf) {
        setRobotPosition(cf);
        doMovements(movements, true, cf);
        long sumOfGPSCoordinates = sumOfGPSCoordinates(cf, BOX);
        System.out.println("\nPart 1 > Result: " + sumOfGPSCoordinates);
        return String.valueOf(sumOfGPSCoordinates);
    }

    static void setRobotPosition(CharacterField cf) {
        List<Position> positions = cf.searchCharacters(ROBOT);
        if (positions.size() != 1) {
            throw new IllegalStateException("Robot position needs to be unique!" + positions.size());
        }
        robotPos = positions.getFirst();
    }

    static void doMovements(String movements, boolean part1, CharacterField cf) {
        for (int i = 0; i < movements.length(); i++) {
            String move = movements.substring(i, i + 1);
            //System.out.println(move);
            Pair<Integer, Integer> movementDelta = getMovementDelta(move);
            int xDelta = movementDelta.getValue0();
            int yDelta = movementDelta.getValue1();
            if (part1) {
                if (tryMovePart1(robotPos, xDelta, yDelta, cf)) {
                    moveRobot(cf, xDelta, yDelta);
                }
            } else {
                Queue<Position> boxesToBeMoved = new LinkedList<>();
                if (tryMovePart2(robotPos, xDelta, yDelta, boxesToBeMoved, cf)) {
                    for (Position boxLeft : boxesToBeMoved) {
                        Position boxRight = new Position(boxLeft.x() + 1, boxLeft.y());
                        cf.setCharacterAt(EMPTY, boxLeft);
                        cf.setCharacterAt(EMPTY, boxRight);
                        // move left side
                        cf.setCharacterAt(BIG_BOX_LEFT, new Position(boxLeft.x() + xDelta, boxLeft.y() + yDelta));
                        // move right side
                        cf.setCharacterAt(BIG_BOX_RIGHT, new Position(boxRight.x() + xDelta, boxRight.y() + yDelta));
                    }
                    moveRobot(cf, xDelta, yDelta);
                }
            }
            //cf.prettyPrint();
        }
    }

    private static Pair<Integer, Integer> getMovementDelta(String move) {
        int x = 0;
        int y = 0;
        switch (move) {
            case LEFT:
                x = -1;
                break;
            case RIGHT:
                x = 1;
                break;
            case UP:
                y = -1;
                break;
            case DOWN:
                y = 1;
                break;
            default:
                throw new IllegalStateException("Invalid move character" + move);
        }
        return new Pair<>(x, y);
    }

    private static void moveRobot(CharacterField cf, int x, int y) {
        Position oldPos = robotPos;
        Position newPos = new Position(robotPos.x() + x, robotPos.y() + y);
        cf.setCharacterAt(cf.getCharacterAt(oldPos), newPos);
        cf.setCharacterAt(EMPTY, oldPos);
        robotPos = newPos;
    }

    static boolean tryMovePart1(Position oldPos, int x, int y, CharacterField cf) {
        Position potentialNewPos = new Position(oldPos.x() + x, oldPos.y() + y);
        String nextCharacter = cf.getCharacterAt(potentialNewPos);
        switch (nextCharacter) {
            case WALL -> {
                //System.out.println(potentialNewPos + " wall");
                return false;
            }
            case EMPTY -> {
                //System.out.println(potentialNewPos + " empty");
                return true;
            }
            case BOX -> {
                if (tryMovePart1(potentialNewPos, x, y, cf)) {
                    //System.out.println("do move:");
                    cf.setCharacterAt(cf.getCharacterAt(potentialNewPos),
                            new Position(potentialNewPos.x() + x, potentialNewPos.y() + y));
                    cf.setCharacterAt(EMPTY, potentialNewPos);
                    return true;
                }
                return false;
            }
            default -> throw new IllegalStateException("Unexpected field value: '" + nextCharacter + "'");
        }
    }

    /**
     * Checks if the object at oldPos can be moved by the delta x and y.
     * Boxes that should potentially be moved are collected in boxesToBeMoved
     */
    static boolean tryMovePart2(Position oldPos, int x, int y, Queue<Position> boxesToBeMoved, CharacterField cf) {
        Position potentialNewPos = new Position(oldPos.x() + x, oldPos.y() + y);
        String nextCharacter = cf.getCharacterAt(potentialNewPos);
        switch (nextCharacter) {
            case WALL -> {
                //System.out.println(potentialNewPos + " wall");
                return false;
            }
            case EMPTY -> {
                //System.out.println(potentialNewPos + " empty");
                return true;
            }
            case BIG_BOX_LEFT -> {
                boolean canMove = handleBigBox(x, y, cf, 1, potentialNewPos, boxesToBeMoved);
                if (canMove) {
                    // collect the coordinates of the left side box the boxes that shall possibly be moved
                    if (!boxesToBeMoved.contains(potentialNewPos)) {
                        boxesToBeMoved.add(potentialNewPos);
                    }
                }
                return canMove;
            }
            case BIG_BOX_RIGHT -> {
                boolean canMove = handleBigBox(x, y, cf, -1, potentialNewPos, boxesToBeMoved);
                if (canMove) {
                    // collect the coordinates of the left side box the boxes that shall possibly be moved
                    Position leftSideOfTheBox = new Position(potentialNewPos.x() - 1, potentialNewPos.y());
                    if (!boxesToBeMoved.contains(leftSideOfTheBox)) {
                        boxesToBeMoved.add(leftSideOfTheBox);
                    }
                }
                return canMove;
            }
            default -> throw new IllegalStateException("Unexpected field value: '" + nextCharacter + "'");
        }
    }

    private static boolean handleBigBox(int x, int y, CharacterField cf, int deltaOf2ndHalf, Position potentialNewPos, Queue<Position> boxesToBeMoved) {
        if (y == 0) {
            // horizontal case = easy case
            return tryMovePart2(potentialNewPos, x, y, boxesToBeMoved, cf);
        } else {
            // try to move both parts
            Position potentialNewPos2ndHalf = new Position(potentialNewPos.x() + deltaOf2ndHalf, potentialNewPos.y());
            return tryMovePart2(potentialNewPos, x, y, boxesToBeMoved, cf) &&
                    tryMovePart2(potentialNewPos2ndHalf, x, y, boxesToBeMoved, cf);
        }
    }

    static long sumOfGPSCoordinates(CharacterField characterField, String boxCharacter) {
        long sum = 0;
        List<Position> boxes = characterField.searchCharacters(boxCharacter);
        for (Position pos : boxes) {
            sum += pos.x() + pos.y() * 100;
        }
        return sum;
    }

    static String part2(String movements, CharacterField smallField) {
        CharacterField largeField = enlargeField(smallField);
        //largeField.prettyPrint();
        //System.out.println();
        setRobotPosition(largeField);
        doMovements(movements, false, largeField);
        //largeField.prettyPrint();
        long sumOfGPSCoordinates = sumOfGPSCoordinates(largeField, BIG_BOX_LEFT);
        System.out.println("\nPart 2 > Result: " + sumOfGPSCoordinates);
        return String.valueOf(sumOfGPSCoordinates);
    }

    public static CharacterField enlargeField(CharacterField cf) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < cf.getMaxY(); y++) {
            for (int x = 0; x < cf.getMaxX(); x++) {
                String orgCharacter = cf.getCharacterAt(x, y);
                String s = switch (orgCharacter) {
                    case WALL -> WALL + WALL;
                    case BOX -> BIG_BOX_LEFT + BIG_BOX_RIGHT;
                    case EMPTY -> EMPTY + EMPTY;
                    case ROBOT -> ROBOT + EMPTY;
                    default -> throw new IllegalStateException("Unexpected character in field: " + orgCharacter);
                };
                sb.append(s);
            }
            sb.append("\n");
        }
        return new CharacterField(sb.toString());
    }

}
