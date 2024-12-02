package de.steffzilla.aoc.y2021;

public class Bingoboard {

    final static int SIZE = 5;
    private int[][] board = new int[SIZE][SIZE];

    private boolean[][] markedFields= new boolean[SIZE][SIZE];

    public Bingoboard(String line1, String line2, String line3, String line4, String line5) {
        fillLine(line1, 0);
        fillLine(line2, 1);
        fillLine(line3, 2);
        fillLine(line4, 3);
        fillLine(line5, 4);
    }

    public void markNumber(int number) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j]==number) {
                    markedFields[i][j] = true;
                    return;
                }
            }
        }
    }

    private void fillLine(String line, int lineIndex) {
        String[] sNumbers = line.trim().split(" ");
        int colIndex = 0;
        for (int i = 0; i < sNumbers.length; i++) {
            if (!sNumbers[i].trim().isEmpty()) {
                board[lineIndex][colIndex] = Integer.parseInt(sNumbers[i].trim());
                colIndex++;
            }
        }
    }

    boolean isWinning() {
        // check lines
        for (int i = 0; i < SIZE; i++) {
            boolean lineIsWinning = true;
            for (int j = 0; j < SIZE; j++) {
                boolean b = markedFields[i][j];
                if(!b) {
                    lineIsWinning = false;
                    break;
                }
            }
            if(lineIsWinning) {
                System.out.println("Line "+(i+1)+" is winning!");
                return true;
            }
        }
        // Check rows
        for (int y = 0; y < SIZE; y++) {
            boolean rowIsWinning = true;
            for (int x = 0; x < SIZE; x++) {
                boolean b = markedFields[x][y];
                if(!b) {
                    rowIsWinning = false;
                    break;
                }
            }
            if (rowIsWinning) {
                System.out.println("Row "+(y+1)+" is winning!");
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sbBoard = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sbBoard.append(board[i][j]);
                sbBoard.append("|");
            }
            sbBoard.append("\n");
        }
        StringBuilder sbMarked = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (markedFields[i][j]){
                    sbMarked.append("1");
                } else {
                    sbMarked.append("0");
                }

                sbMarked.append("|");
            }
            sbMarked.append("\n");
        }
        return "Bingoboard{" +
                "board=\n" + sbBoard.toString() +
                "markedFields=\n" + sbMarked +
                '}';
    }

    public int getFinalScore(int winningNumber) {
        int finalScore = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!markedFields[i][j]) {
                    finalScore+= board[i][j];
                }
            }
        }
        System.out.println("Sum of unmarked fields: "+finalScore);
        return finalScore * winningNumber;
    }
}
