package de.steffzilla.aoc.y2021;

import de.steffzilla.aoc.AocUtils;

import java.util.ArrayList;
import java.util.List;

public class Aoc2021_4 {

    private static final String DAY = "04";
    private static final String YEAR = "2021";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;


    public static void main(String[] args) {
        List<String> stringList = AocUtils.getStringList(PATH);

        /*Bingoboard bingoboard = new Bingoboard();
        bingoboard.markedFields[0][3] = true;
        bingoboard.markedFields[1][3] = true;
        bingoboard.markedFields[2][3] = true;
        bingoboard.markedFields[3][3] = true;
        bingoboard.markedFields[4][3] = true;
        System.out.println(bingoboard.isWinning());*/

        List<Bingoboard> bingoboards = new ArrayList<>();
        String bingoInput = stringList.get(0);

        int[] drawnNumbers = AocUtils.splitStringToIntArray(bingoInput, ",");

        for (int i = 2; i < stringList.size(); i+=Bingoboard.SIZE+1) {
            Bingoboard bingoboard = new Bingoboard(
                    stringList.get(i),
                    stringList.get(i + 1),
                    stringList.get(i + 2),
                    stringList.get(i + 3),
                    stringList.get(i + 4)
            );
            bingoboards.add(bingoboard);
            System.out.println(bingoboard);
        }

        // PART 1
        // turn by turn draw numbers
/*        int finalScoreOfWinningBoard = 0;
        for (int i = 0; i < drawnNumbers.length; i++) {
            Bingoboard winningBoard = checkBoards(drawnNumbers[i], bingoboards);
            if(winningBoard!=null) {
                finalScoreOfWinningBoard = winningBoard.getFinalScore(drawnNumbers[i]);
                break;
            }
        }

        System.out.println("\nResult: "+finalScoreOfWinningBoard);*/

        // PART 2
        // turn by turn draw numbers
        int finalScoreOfWinningBoard = getFinalScoreLastOfWinningBoard(bingoboards, drawnNumbers);

        System.out.println("\nResult: "+finalScoreOfWinningBoard);

    }

    private static int getFinalScoreLastOfWinningBoard(List<Bingoboard> bingoboards, int[] drawnNumbers) {
        int finalScoreOfWinningBoard = 0;
        for (int i = 0; i < drawnNumbers.length; i++) {
            List<Bingoboard> winningBoards = checkBoards(drawnNumbers[i], bingoboards);
            for (Bingoboard winningBoard : winningBoards) {
                    if(bingoboards.size()>1) {
                        System.out.println("Winning board with number "+ drawnNumbers[i]);
                        System.out.println(winningBoard.toString());
                        bingoboards.remove(winningBoard);
                    } else {
                        System.out.println("***************************");
                        System.out.println(winningBoard.toString());
                        System.out.println("winning number:"+ drawnNumbers[i]);
                        return winningBoard.getFinalScore(drawnNumbers[i]);
                    }
            }
        }
        return 0;
    }

    private static List<Bingoboard> checkBoards(int drawnNumber, List<Bingoboard> bingoboards) {
        List<Bingoboard> winningBoards = new ArrayList<>();
        for (Bingoboard board : bingoboards) {
            board.markNumber(drawnNumber);
            if(board.isWinning()) {
                winningBoards.add(board);
            }
        }
        return winningBoards;
    }




}