package de.steffzilla.aoc.y2021;

import de.steffzilla.competitive.AocUtils;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class Aoc2021_25 {

    private static final String DAY = "25";
    private static final String YEAR = "2021";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;
    private static int maxX;
    private static int maxY;

    public static void main(String[] args) {
        List<String> inputLines = AocUtils.getStringList(PATH);

        part1(inputLines);
        //part2(inputLines);
    }

    private static void part1(List<String> inputLines){
        String[][] field = readInputField(inputLines);
        //printField(field);
        int rounds = 0;
        while (move(field)) {
            rounds++;
            System.out.println();
            //printField(field);
        }


        System.out.println("\nResult: "+(rounds+1));
    }

    private static boolean move(String[][] field) {
        boolean movedEast = moveEast(field);
        boolean movedSouth = moveSouth(field);
        return movedEast || movedSouth;
    }

    private static boolean moveSouth(String[][] field) {
        boolean moved = false;
        List<Pair> willMove = new ArrayList<>();
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                String s = field[x][y];
                if(s != null && s.equals("v")) {
                    if ((y < maxY-1 && field[x][y+1] == null) || (y == maxY-1 && field[x][0] == null)) {
                        willMove.add(new Pair<>(x, y));
                    }
                }
            }
        }
        if (!willMove.isEmpty()) {
            moved = true;
        }
        for (Pair<Integer, Integer> pair: willMove) {
            String s = field[pair.getValue0()][pair.getValue1()];
            field[pair.getValue0()][pair.getValue1()] = null;
            if (pair.getValue1() < maxY-1) {
                field[pair.getValue0()][pair.getValue1()+1] = s;
            } else if (pair.getValue1() == maxY-1) {
                field[pair.getValue0()][0] = s;
            } else {
                throw new IllegalStateException("Should not occur");
            }
        }
        return moved;
    }

    private static boolean moveEast(String[][] field) {
        boolean moved = false;
        List<Pair> willMove = new ArrayList<>();
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                String s = field[x][y];
                if(s != null && s.equals(">")) {
                    if ((x < maxX-1 && field[x+1][y] == null) || (x == maxX-1 && field[0][y] == null)) {
                        willMove.add(new Pair<>(x, y));
                    }
                }
            }
        }
        if (!willMove.isEmpty()) {
            moved = true;
        }
        for (Pair<Integer, Integer> pair: willMove) {
            String s = field[pair.getValue0()][pair.getValue1()];
            field[pair.getValue0()][pair.getValue1()] = null;
            if (pair.getValue0() < maxX-1) {
                field[pair.getValue0()+1][pair.getValue1()] = s;
            } else if (pair.getValue0() == maxX-1) {
                field[0][pair.getValue1()] = s;
            } else {
                throw new IllegalStateException("Should not occur");
            }
        }
        return moved;
    }

    private static String[][] readInputField(List<String> inputLines) {
        maxX = inputLines.get(0).length();
        maxY = inputLines.size();
        String[][] field = new String[maxX][maxY];
        for (int y = 0; y < inputLines.size(); y++) {
            String line = inputLines.get(y);
            for (int x = 0; x < line.length() ; x++) {
                String s = line.substring(x, x+1);
                if (s.equals(">") || s.equals("v")) {
                    field[x][y] = s;
                }
            }
        }
        return field;
    }

    private static void part2(List<String> inputLines) {
        for (String line : inputLines) {

        }
        System.out.println("\nResult: ");
    }

    private static void printField(String[][] field) {
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                String s = field[x][y];
                if(null==s) {
                    s =".";
                }
                System.out.print(s);
            }
            System.out.println();
        }
    }

}