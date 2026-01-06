package de.steffzilla.aoc.y2024;

import de.steffzilla.competitive.AocUtils;
import de.steffzilla.competitive.MathUtils;
import org.javatuples.Pair;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2024_13 {

    private static final String DAY = "13";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    // Max 100 times per button
    public static final int MAX_BUTTON_PRESSING = 100;
    public static final int COST_BTN_A = 3;
    public static final int COST_BTN_B = 1;
    public static final long CONVERSION_FACTOR_PART2 = 10000000000000L;

    static final String example = """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400
            
            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176
            
            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=7870, Y=6450
            
            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279
            """;
    public static final int NO_RESULT = -1;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        //List<String> inputLines = example.lines().toList();
        List<String> inputLines =
                AocUtils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        List<Game> games = readGames(inputLines);
        return new Pair<>(part1(games), part2(games));
    }

    private static String part1(List<Game> games) {
        List<Integer> fewestTokensPerGame = new ArrayList<>();
        for (Game game : games) {
            int neededTokens = play(game);
            if (neededTokens != NO_RESULT) {
                fewestTokensPerGame.add(neededTokens);
            }
        }
        Integer fewestTokens = fewestTokensPerGame.stream().reduce(0, Integer::sum);
        System.out.println("\nPart 1 > Result: " + fewestTokens);
        return String.valueOf(fewestTokens);
    }

    private static int play(Game game) {
        int minimalTokens = Integer.MAX_VALUE;
        for (int btnACounter = 0; btnACounter <= MAX_BUTTON_PRESSING; btnACounter++) {
            // fail fast
            if (btnACounter * game.buttonA_X() > game.prizeX() || btnACounter * game.buttonA_Y() > game.prizeY()) {
                break;
            }
            for (int btnBCounter = 0; btnBCounter < MAX_BUTTON_PRESSING; btnBCounter++) {
                int currentX = btnACounter * game.buttonA_X() + btnBCounter * game.buttonB_X();
                int currentY = btnACounter * game.buttonA_Y() + btnBCounter * game.buttonB_Y();
                if (currentX == game.prizeX() && currentY == game.prizeY()) {
                    // a solution found
                    int tokensNeeded = btnACounter * COST_BTN_A + btnBCounter * COST_BTN_B;
                    if (tokensNeeded < minimalTokens) {
                        minimalTokens = tokensNeeded;
                    }
                    break; // the inner loop finds no solution anymore
                } else if (currentX > game.prizeX() || currentY > game.prizeY()) {
                    break;
                }
            }
        }
        if (minimalTokens == Integer.MAX_VALUE) {
            return NO_RESULT;
        } else {
            return minimalTokens;
        }
    }


    private static String part2(List<Game> games) {
        long totalCost = 0;
        for (Game game : games) {
            Pair<BigInteger, BigInteger> solution =
                    MathUtils.solveLinearDiophantineSystem2x2(BigInteger.valueOf(game.buttonA_X),
                            BigInteger.valueOf(game.buttonB_X),
                            BigInteger.valueOf(game.prizeX + CONVERSION_FACTOR_PART2),
                            BigInteger.valueOf(game.buttonA_Y),
                            BigInteger.valueOf(game.buttonB_Y),
                            BigInteger.valueOf(game.prizeY + CONVERSION_FACTOR_PART2));
            if (solution != null) {
                long bttnA = solution.getValue0().longValue();
                long bttnB = solution.getValue1().longValue();
                long cost = 3 * bttnA + bttnB;
                //System.out.println("Button A: " + bttnA + " | Button B: " + bttnB + " | Cost: " + cost);
                totalCost += cost;
            }
        }
        System.out.println("\nPart 2 > Result: " + totalCost);
        return String.valueOf(totalCost);
    }

    private static List<Game> readGames(List<String> inputLines) {
        Pattern patternBtnA = Pattern.compile("^Button A: X\\+(\\d*), Y\\+(\\d*)");
        Pattern patternBtnB = Pattern.compile("^Button B: X\\+(\\d*), Y\\+(\\d*)");
        Pattern patternPrize = Pattern.compile("^Prize: X=(\\d*), Y=(\\d*)");
        List<Game> games = new ArrayList<>();
        int buttonA_X = 0;
        int buttonA_Y = 0;
        int buttonB_X = 0;
        int buttonB_Y = 0;
        int prizeX = 0;
        int prizeY = 0;
        for (String line : inputLines) {
            if (line.startsWith("Button A")) {
                Matcher matcher = patternBtnA.matcher(line);
                matcher.find();
                buttonA_X = Integer.parseInt(matcher.group(1));
                buttonA_Y = Integer.parseInt(matcher.group(2));
            } else if (line.startsWith("Button B")) {
                Matcher matcher = patternBtnB.matcher(line);
                matcher.find();
                buttonB_X = Integer.parseInt(matcher.group(1));
                buttonB_Y = Integer.parseInt(matcher.group(2));
            } else if (line.startsWith("Prize")) {
                Matcher matcher = patternPrize.matcher(line);
                matcher.find();
                prizeX = Integer.parseInt(matcher.group(1));
                prizeY = Integer.parseInt(matcher.group(2));
                games.add(new Game(buttonA_X, buttonA_Y, buttonB_X, buttonB_Y, prizeX, prizeY));
            }
        }
        return games;
    }

    private record Game(int buttonA_X, int buttonA_Y, int buttonB_X, int buttonB_Y, int prizeX, int prizeY) {
    }

}