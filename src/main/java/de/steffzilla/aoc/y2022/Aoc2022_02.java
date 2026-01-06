package de.steffzilla.aoc.y2022;

import de.steffzilla.competitive.Utils;

import java.util.List;

public class Aoc2022_02 {

    private static final String DAY = "02";
    private static final String YEAR = "2022";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    private static final String ROCK_OTHER = "A";
    private static final String PAPER_OTHER = "B";
    private static final String SCISSORS_OTHER = "C";

    private static final String ROCK_ME = "X";
    private static final String PAPER_ME = "Y";
    private static final String SCISSORS_ME = "Z";

    private static final int DRAW = 3;
    private static final int I_WIN = 6;
    private static final int I_LOSE = 0;

    private static final String SHOULD_DRAW = "Y";
    private static final String SHOULD_WIN = "Z";
    private static final String SHOULD_LOSE = "X";

    public static void main(String[] args) {
        List<String> inputLines = Utils.getStringList(PATH);

        //part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines){
        long sumOfScores = 0;
        for (String line : inputLines) {
            long localScore = getLocalScore(line);
            sumOfScores += localScore;
            System.out.println();
        }
        System.out.println("\nResult: "+sumOfScores);
    }

    private static long getLocalScore(String line) {
        long localScore = 0;
        // 1 for Rock, 2 for Paper, and 3 for Scissors)
        // plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
        String[] parts = line.split(" ", 2);
        if(ROCK_OTHER.equals(parts[0])) {
            if(ROCK_ME.equals(parts[1])) {
                localScore = DRAW + 1;
            } else if(PAPER_ME.equals(parts[1])) {
                localScore = I_WIN + 2;
            } else if(SCISSORS_ME.equals(parts[1])){
                localScore = I_LOSE + 3;
            } else {
                throw new IllegalStateException("Should not occur: " + parts[0]);
            }
            System.out.println();
        } else if(PAPER_OTHER.equals(parts[0])) {
            if(ROCK_ME.equals(parts[1])) {
                localScore = I_LOSE + 1;
            } else if(PAPER_ME.equals(parts[1])) {
                localScore = DRAW + 2;
            } else if(SCISSORS_ME.equals(parts[1])){
                localScore = I_WIN + 3;
            } else {
                throw new IllegalStateException("Should not occur");
            }
        } else if(SCISSORS_OTHER.equals(parts[0])){
            if(ROCK_ME.equals(parts[1])) {
                localScore = I_WIN + 1;
            } else if(PAPER_ME.equals(parts[1])) {
                localScore = I_LOSE + 2;
            } else if(SCISSORS_ME.equals(parts[1])){
                localScore = DRAW + 3;
            } else {
                throw new IllegalStateException("Should not occur");
            }
        } else {
            throw new IllegalStateException("Should not occur: other played: "+parts[0]);
        }
        return localScore;
    }

    private static void part2(List<String> inputLines) {
        long sumOfScores = 0;
        for (String line : inputLines) {
            String[] parts = line.split(" ", 2);
            long localScore;
            if(SHOULD_LOSE.equals(parts[1])) {
                if(ROCK_OTHER.equals(parts[0])){
                    localScore = getLocalScore(ROCK_OTHER+" "+SCISSORS_ME);
                } else if(PAPER_OTHER.equals(parts[0])){
                    localScore = getLocalScore(PAPER_OTHER+" "+ROCK_ME);
                } else if(SCISSORS_OTHER.equals(parts[0])){
                    localScore = getLocalScore(SCISSORS_OTHER+" "+PAPER_ME);
                } else {
                    throw new IllegalStateException("Should not occur: parts[0]="+parts[0]);
                }
            } else if(SHOULD_DRAW.equals(parts[1])) {
                if(ROCK_OTHER.equals(parts[0])){
                    localScore = getLocalScore(ROCK_OTHER+" "+ROCK_ME);
                } else if(PAPER_OTHER.equals(parts[0])){
                    localScore = getLocalScore(PAPER_OTHER+" "+PAPER_ME);
                } else if(SCISSORS_OTHER.equals(parts[0])){
                    localScore = getLocalScore(SCISSORS_OTHER+" "+SCISSORS_ME);
                } else {
                    throw new IllegalStateException("Should not occur: parts[0]="+parts[0]);
                }
            } else if(SHOULD_WIN.equals(parts[1])) {
                if(ROCK_OTHER.equals(parts[0])){
                    localScore = getLocalScore(ROCK_OTHER+" "+PAPER_ME);
                } else if(PAPER_OTHER.equals(parts[0])){
                    localScore = getLocalScore(PAPER_OTHER+" "+SCISSORS_ME);
                } else if(SCISSORS_OTHER.equals(parts[0])){
                    localScore = getLocalScore(SCISSORS_OTHER+" "+ROCK_ME);
                } else {
                    throw new IllegalStateException("Should not occur: parts[0]="+parts[0]);
                }
            } else {
                throw new IllegalStateException("Should not occur: parts[1]="+parts[1]);
            }
            sumOfScores+=localScore;
        }
        System.out.println("\nResult: " + sumOfScores);
    }

}