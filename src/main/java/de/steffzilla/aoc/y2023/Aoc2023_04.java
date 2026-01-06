package de.steffzilla.aoc.y2023;

import de.steffzilla.competitive.AocUtils;

import java.util.Arrays;
import java.util.List;

public class Aoc2023_04 {

    private static final String DAY = "04";
    private static final String YEAR = "2023";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;


    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);

        List<String> inputLines = AocUtils.getStringList(PATH);

        //part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines){
        long totalPoints = 0;
        for (String line : inputLines) {
            System.out.println(line);
            totalPoints+=getResultOfCard(line);
        }
        System.out.println("\nPart 1 > Result: " + totalPoints);
    }

    private static long getResultOfCard(String line) {
        int winCount = getWinCount(line);

        long result = (long) Math.pow(2, winCount - 1);
        System.out.println(result);
        return result;
    }

    private static int getWinCount(String line) {
        int winCount =0;
        line = line.substring(line.indexOf(":")+1).trim();
        String[] split = line.split(" \\| ");

        String[] winningNumbers = split[0].split(" +");
        String[] cardsNumbers = split[1].split(" +");
        System.out.println(Arrays.toString(winningNumbers));
        System.out.println(Arrays.toString(cardsNumbers));
        for (String winningNumber : winningNumbers) {
            for (String cardNumber : cardsNumbers) {
                if (cardNumber.trim().equals(winningNumber.trim())) {
                    winCount++;
                }
            }
        }
        return winCount;
    }


    private static void part2(List<String> inputLines) {
        int cardNumber = 0;
        long[] noOfCards = new long[(int) AocUtils.countLines(PATH)];
        Arrays.fill(noOfCards, 1);

        for (String line : inputLines) {
            System.out.println(line);
            int winCount = getWinCount(line);
            System.out.println(winCount);

            long countOfCurrentCard = noOfCards[cardNumber];
            for (int i = 1; i <= winCount ; i++) {
                noOfCards[cardNumber+i]=noOfCards[cardNumber+i]+countOfCurrentCard;
            }
            cardNumber++;
        }
        System.out.println(Arrays.toString(noOfCards));
        long totalNoOfCards = 0;
        for (long noOfCard : noOfCards) {
            totalNoOfCards+=noOfCard;
        }
        System.out.println("\nPart 2 > Result: " + totalNoOfCards);
    }

}