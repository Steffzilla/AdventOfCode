package de.steffzilla.aoc.y2024;

import de.steffzilla.aoc.AocUtils;
import de.steffzilla.aoc.CharacterField;
import de.steffzilla.aoc.InputUtils;
import org.javatuples.Pair;

import java.util.List;

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
    private static Pair<Integer, Integer> robotPos;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        //List<String> inputLines = example.lines().toList();
        List<String> inputLines = AocUtils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        Pair<List<String>, List<String>> inputs = InputUtils.splitLists(inputLines);
        CharacterField cf = new CharacterField(inputs.getValue0());

        List<String> movementLines = inputs.getValue1();
        StringBuilder sbMovements = new StringBuilder();
        for (String line : movementLines) {
            sbMovements.append(line);
        }
        return new Pair<>(part1(sbMovements.toString(), cf), part2(sbMovements.toString(), cf));
    }

    private static String part1(String movements, CharacterField cf) {
        setRobotPosition(cf);
        doMovements(movements, cf);
        long sumOfGPSCoordinates = sumOfGPSCoordinates(cf, BOX);
        System.out.println("\nPart 1 > Result: " + sumOfGPSCoordinates);
        return String.valueOf(sumOfGPSCoordinates);
    }

    private static void setRobotPosition(CharacterField cf) {
        List<Pair<Integer, Integer>> positions = cf.searchCharacters(ROBOT);
        if (positions.size() != 1) {
            throw new IllegalStateException("Robot position needs to be unique!" + positions.size());
        }
        robotPos = positions.getFirst();
    }

    private static void doMovements(String movements, CharacterField cf) {
        for (int i = 0; i < movements.length(); i++) {
            String move = movements.substring(i, i + 1);
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
            if (tryMove(robotPos, x, y, cf)) {
                Pair<Integer, Integer> oldPos = robotPos;
                Pair<Integer, Integer> newPos = new Pair<>(robotPos.getValue0() + x, robotPos.getValue1() + y);
                cf.setCharacterAt(cf.getCharacterAt(oldPos), newPos);
                cf.setCharacterAt(EMPTY, oldPos);
                robotPos = newPos;
            }
            //cf.prettyPrint();
        }
    }

    static boolean tryMove(Pair<Integer, Integer> oldPos, int x, int y, CharacterField cf) {
        Pair<Integer, Integer> potentialNewPos = new Pair<>(oldPos.getValue0() + x, oldPos.getValue1() + y);
        String nextCharacter = cf.getCharacterAt(potentialNewPos);
        switch (nextCharacter) {
            case WALL -> {
                System.out.println(potentialNewPos + " wall");
                return false;
            }
            case EMPTY -> {
                System.out.println(potentialNewPos + " empty");
                return true;
            }
            case BOX -> {
                if (tryMove(potentialNewPos, x, y, cf)) {
                    System.out.println("do move:");
                    cf.setCharacterAt(cf.getCharacterAt(potentialNewPos),
                            new Pair<>(potentialNewPos.getValue0() + x, potentialNewPos.getValue1() + y));
                    cf.setCharacterAt(EMPTY, potentialNewPos);
                    return true;
                }
                return false;
            }
            default -> throw new IllegalStateException("Unexpected field value: '" + nextCharacter + "'");
        }
    }

    static long sumOfGPSCoordinates(CharacterField characterField, String boxCharacter) {
        long sum = 0;
        List<Pair<Integer, Integer>> boxes = characterField.searchCharacters(boxCharacter);
        for (Pair<Integer, Integer> pos : boxes) {
            sum += pos.getValue0() + pos.getValue1() * 100;
        }
        return sum;
    }

    private static String part2(String movements, CharacterField smallField) {
        CharacterField largeField = enlargeField(smallField);
        setRobotPosition(largeField);
        //doMovementsPart2(movements, largeField);
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