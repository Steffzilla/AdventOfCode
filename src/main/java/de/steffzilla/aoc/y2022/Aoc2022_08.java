package de.steffzilla.aoc.y2022;

import de.steffzilla.aoc.AocUtils;

import java.util.List;

public class Aoc2022_08 {

    private static final String DAY = "08";
    private static final String YEAR = "2022";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    private static final int xDimension = 99;
    private static final int yDimension = xDimension;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);

        //part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines){
        int[][] matrix = AocUtils.readIntMatrix(inputLines, xDimension, yDimension);
        int sumOfVisibleTrees=0;
        for (int y = 0; y < yDimension; y++) {
            for (int x = 0; x < xDimension; x++) {
                if(x == 0 || y == 0 || x==xDimension-1 || y==yDimension-1) {
                    sumOfVisibleTrees++;
                } else {
                    // check inner trees
                    int currentTree = matrix[x][y];
                    //x from left
                    boolean allShorter = true;
                    for (int i = 0; i < x; i++) {
                        int otherTree = matrix[i][y];
                        if(otherTree >= currentTree) {
                            allShorter=false;
                            break;
                        }
                    }
                    if(allShorter) {
                        sumOfVisibleTrees++;
                        continue; // continue to check the next current tree
                    }

                    allShorter = true;
                    for (int i = x+1; i < xDimension; i++) {
                        int otherTree = matrix[i][y];
                        if(otherTree >= currentTree) {
                            allShorter=false;
                            break;
                        }
                    }
                    if(allShorter) {
                        sumOfVisibleTrees++;
                        continue; // continue to check the next current tree
                    }

                    allShorter = true;
                    for (int i = 0; i < y; i++) {
                        int otherTree = matrix[x][i];
                        if(otherTree >= currentTree) {
                            allShorter=false;
                            break;
                        }
                    }
                    if(allShorter) {
                        sumOfVisibleTrees++;
                        continue; // continue to check the next current tree
                    }

                    allShorter = true;
                    for (int i = y+1; i < yDimension; i++) {
                        int otherTree = matrix[x][i];
                        if(otherTree >= currentTree) {
                            allShorter=false;
                            break;
                        }
                    }
                    if(allShorter) {
                        sumOfVisibleTrees++;
                        continue; // continue to check the next current tree
                    }
                }
            }
        }
        System.out.println("\nPart 1 > Result: " + sumOfVisibleTrees);
    }



    private static void part2(List<String> inputLines) {
        int[][] matrix = AocUtils.readIntMatrix(inputLines, xDimension, yDimension);
        long maxScore=0;
        for (int y = 0; y < yDimension; y++) {
            for (int x = 0; x < xDimension; x++) {
                long currentScore=1;
                int currentTree = matrix[x][y];

                int counter = 0;
                for (int i = x-1; i >= 0; i--) {
                    counter++;
                    int otherTree = matrix[i][y];
                    if (otherTree >= currentTree){
                        break;
                    }
                }
                currentScore*=counter;

                counter = 0;
                for (int i = x+1; i < xDimension; i++) {
                    counter++;
                    int otherTree = matrix[i][y];
                    if (otherTree >= currentTree){
                        break;
                    }
                }
                currentScore*=counter;

                counter = 0;
                for (int i = y-1; i >= 0; i--) {
                    counter++;
                    int otherTree = matrix[x][i];
                    if (otherTree >= currentTree){
                        break;
                    }
                }
                currentScore*=counter;

                counter = 0;
                for (int i = y+1; i < yDimension; i++) {
                    counter++;
                    int otherTree = matrix[x][i];
                    if (otherTree >= currentTree){
                        break;
                    }
                }
                currentScore*=counter;

                if(currentScore>maxScore) {
                    maxScore=currentScore;
                }
            }
        }
        System.out.println("\nPart 2 > Result: " + maxScore);
    }

}