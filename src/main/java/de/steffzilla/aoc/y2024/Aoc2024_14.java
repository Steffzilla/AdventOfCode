package de.steffzilla.aoc.y2024;

import de.steffzilla.competitive.AocUtils;
import de.steffzilla.competitive.CharacterField;
import de.steffzilla.competitive.InputUtils;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class Aoc2024_14 {

    private static final String DAY = "14";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    //public static final String FILENAME = "sample" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    static int part1Moves = 100;
    static int part2Moves = 100000;
    static int fieldDimensionX = 101;
    static int fieldDimensionY = 103;
    static List<Robot> robots;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        List<List<Integer>> inputData = InputUtils.readLinesAsIntegers("^p=(-?\\d*),(-?\\d*) v=(-?\\d*),(-?\\d*)", inputLines);
        return new Pair<>(part1(inputData), part2(inputData));
    }

    private static String part1(List<List<Integer>> inputLines) {
        setupInitialRobotState(inputLines);
        for (int i = 0; i < part1Moves; i++) {
            moveRobots(robots);
        }
        // In which quadrant are the robots
        long safetyFactor = calculateSafetyFactor(robots);
        System.out.println("\nPart 1 > Result: " + safetyFactor);
        return String.valueOf(safetyFactor);
    }

    private static void setupInitialRobotState(List<List<Integer>> inputLines) {
        robots = new ArrayList<>();
        for (List<Integer> line : inputLines) {
            Robot robot = new Robot(line.get(0), line.get(1), line.get(2), line.get(3));
            robots.add(robot);
        }
    }

    private static long calculateSafetyFactor(List<Robot> robots) {
        long q1 = 0;
        long q2 = 0;
        long q3 = 0;
        long q4 = 0;
        int maxPosQ1X = (fieldDimensionX - 1) / 2 - 1;
        int maxPosQ1Y = (fieldDimensionY - 1) / 2 - 1;
        for (Robot robot : robots) {
            if (robot.posX <= maxPosQ1X) {
                if (robot.posY <= maxPosQ1Y) {
                    q1++;
                } else if (robot.posY > maxPosQ1Y + 1) {
                    q3++;
                }
            } else if (robot.posX > maxPosQ1X + 1) {
                if (robot.posY <= maxPosQ1Y) {
                    q2++;
                } else if (robot.posY > maxPosQ1Y + 1) {
                    q4++;
                }
            }
        }
        return q1 * q2 * q3 * q4;
    }

    private static void moveRobots(List<Robot> robots) {
        for (Robot robot : robots) {
            int x = robot.posX + robot.velX;
            if (x >= fieldDimensionX) { // the max value is out of bounds
                x -= fieldDimensionX;
            } else if (x < 0) {
                x += fieldDimensionX;
            }
            robot.posX = x;
            int y = robot.posY + robot.velY;
            if (y >= fieldDimensionY) {
                y -= fieldDimensionY;
            } else if (y < 0) {
                y += fieldDimensionY;
            }
            robot.posY = y;
        }
    }

    private static String part2(List<List<Integer>> inputLines) {
        long count = 0;
        setupInitialRobotState(inputLines);
        for (int i = 1; i <= part2Moves; i++) {
            moveRobots(robots);
            if (searchLine(robots)) {
                count = i;
                break;
            }
        }
        System.out.println("\nPart 2 > Result: " + count);
        return String.valueOf(count);
    }

    private static boolean searchLine(List<Robot> robots) {
        CharacterField cf = new CharacterField(fieldDimensionX, fieldDimensionY, ".");
        for (Robot robot : robots) {
            cf.setCharacterAt("#", robot.posX, robot.posY);
        }
        for (int line = 0; line < fieldDimensionY; line++) {
            if (cf.lineContains(line, "###############################")) {
                System.out.println();
                System.out.println();
                cf.prettyPrint();
                return true;
            }
        }
        return false;
    }

    static class Robot {
        int posX;
        int posY;
        int velX;
        int velY;

        public Robot(int posX, int posY, int velX, int velY) {
            this.posX = posX;
            this.posY = posY;
            this.velX = velX;
            this.velY = velY;
        }

        @Override
        public String toString() {
            return "Robot{" +
                    "posX=" + posX +
                    ", posY=" + posY +
                    ", velX=" + velX +
                    ", velY=" + velY +
                    '}';
        }
    }

}