package de.steffzilla.aoc.y2021;

import de.steffzilla.competitive.AocUtils;
import org.javatuples.Pair;

import java.util.*;

public class Aoc2021_9 {

    private static final String DAY = "09";
    private static final String YEAR = "2021";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;

    private static final int xDimension = 100;
    private static final int yDimension = 100;

    public static void main(String[] args) {
        List<String> inputLines = AocUtils.getStringList(PATH);

        //part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines) {
        int[][] matrix = getIntMatrix(inputLines);
        int sumOfRiskLevels=0;
        for (int y = 0; y < yDimension; y++) {
            for (int x = 0; x < xDimension; x++) {
                boolean found = true;
                int valueAtPos = matrix[x][y];
                if(y > 0) {
                     if (matrix[x][y-1] <= valueAtPos) {
                         found = false;
                     }
                }
                if (y < yDimension -1 ) {
                    if (matrix[x][y+1] <= valueAtPos) {
                        found = false;
                    }
                }
                if(x > 0) {
                    if (matrix[x-1][y] <= valueAtPos) {
                        found = false;
                    }
                }
                if (x < xDimension -1 ) {
                    if (matrix[x+1][y] <= valueAtPos) {
                        found = false;
                    }
                }
                if(found) {
                    sumOfRiskLevels += valueAtPos+1;
                }
            }
        }
        System.out.println("\nResult: "+sumOfRiskLevels);
    }

    private static int[][] getIntMatrix(List<String> inputLines) {
        int[][] matrix = new int[xDimension][yDimension];
        //int[][] matrix = new int[inputLines.size()][inputLines.size()];
        int lineIndex = 0;
        for (String line : inputLines) {
            for (int x = 0; x < line.length(); x++) {
                matrix[x][lineIndex] = Integer.parseInt(line.substring(x, x+1));
            }
            lineIndex++;
        }
        return matrix;
    }

    private static void part2(List<String> inputLines) {
        int[][] matrix = getIntMatrix(inputLines);

        List<Integer> basinSizes = new ArrayList<>();
        List<Pair<Integer, Integer>> lowPoints = findLowPoints(matrix);
        for (Pair<Integer,Integer> lowPoint: lowPoints) {
            int basinSize = getBasinSize(lowPoint.getValue0(), lowPoint.getValue1(), new HashSet<Pair<Integer, Integer>>(), matrix);
            System.out.println(basinSize);
            basinSizes.add(basinSize);
        }
        Collections.sort(basinSizes, Collections.reverseOrder());
        System.out.println("\nResult: "+basinSizes.get(0)*basinSizes.get(1)*basinSizes.get(2));
    }

    private static int getBasinSize(int currentX, int currentY, HashSet<Pair<Integer,Integer>> checkedPositions, int[][] matrix) {
        int valueAtPos = matrix[currentX][currentY];
        checkedPositions.add(new Pair<>(currentX,currentY));

        if(valueAtPos==9) {
            return 0;
        } else {
            int size = 1; // current position adds 1

            if(currentY > 0) {
                Pair<Integer, Integer> newPos = new Pair<>(currentX, currentY - 1);
                if (!checkedPositions.contains(newPos)) {
                    size += getBasinSize(currentX, currentY-1, checkedPositions, matrix);
                }
            }
            if (currentY < yDimension - 1) {
                Pair<Integer, Integer> newPos = new Pair<>(currentX, currentY + 1);
                if (!checkedPositions.contains(newPos)) {
                    size += getBasinSize(currentX, currentY+1, checkedPositions, matrix);
                }
            }
            if(currentX > 0) {
                Pair<Integer, Integer> newPos = new Pair<>(currentX-1, currentY);
                if (!checkedPositions.contains(newPos)) {
                    size += getBasinSize(currentX-1, currentY, checkedPositions, matrix);
                }
            }
            if (currentX < xDimension -1) {
                Pair<Integer, Integer> newPos = new Pair<>(currentX+1, currentY);
                if (!checkedPositions.contains(newPos)) {
                    size += getBasinSize(currentX+1, currentY, checkedPositions, matrix);
                }
            }
            return size;
        }
    }

    private static List<Pair<Integer, Integer>> findLowPoints(int[][] matrix) {
        List<Pair<Integer, Integer>> lowPoints = new ArrayList<>();
        for (int y = 0; y < yDimension; y++) {
            for (int x = 0; x < xDimension; x++) {
                boolean found = true;
                int valueAtPos = matrix[x][y];
                if(y > 0) {
                    if (matrix[x][y-1] <= valueAtPos) {
                        found = false;
                    }
                }
                if (y < yDimension -1 ) {
                    if (matrix[x][y+1] <= valueAtPos) {
                        found = false;
                    }
                }
                if(x > 0) {
                    if (matrix[x-1][y] <= valueAtPos) {
                        found = false;
                    }
                }
                if (x < xDimension -1 ) {
                    if (matrix[x+1][y] <= valueAtPos) {
                        found = false;
                    }
                }
                if(found) {
                    lowPoints.add(new Pair<>(x,y));
                }
            }
        }
        return lowPoints;
    }

}