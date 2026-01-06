package de.steffzilla.aoc.y2020;

import de.steffzilla.competitive.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Aoc2020_3 {

    private static final String DAY = "03";
    private static final String YEAR = "2020";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        List<String> stringList = Utils.getStringList(PATH);
        long noOfLines = stringList.size();

        int countedTrees1 = getCountedTrees(stringList, noOfLines, 1, 1);
        int countedTrees2 = getCountedTrees(stringList, noOfLines, 3, 1);
        int countedTrees3 = getCountedTrees(stringList, noOfLines, 5, 1);
        int countedTrees4 = getCountedTrees(stringList, noOfLines, 7, 1);
        int countedTrees5 = getCountedTrees(stringList, noOfLines, 1, 2);
        System.out.println("result:"+countedTrees1*countedTrees2*countedTrees3*countedTrees4*countedTrees5);

    }

    private static int getCountedTrees(List<String> stringList, long noOfLines, int right, int down) {
        long numberTilesNeeded = getNumberTilesNeeded(right, (double) down, noOfLines);

        int countedTrees = countTrees(right, down, stringList, noOfLines, numberTilesNeeded);
        System.out.println("countedTrees(r:"+right+"|d:"+down+"):"+countedTrees);
        return countedTrees;
    }

    private static int countTrees(int right, int down, List<String> stringList, long noOfLines, long numberTilesNeeded) {
        int countedTrees = 0;
        int movedRight = 1;
        int movedDown = 1;
        while (movedDown < noOfLines) {
            movedRight += right;
            movedDown += down;
            System.out.println("movedRight:"+ movedRight +" movedDown:"+ movedDown);
            String line = "";
            for (int i = 0; i < numberTilesNeeded; i++) {
                line += stringList.get(movedDown - 1);
            }
            System.out.println(line);
            char c = line.charAt(movedRight -1);
            if(c == '#') {
                countedTrees++;
                System.out.println("Tree");
            }

        }
        return countedTrees;
    }

    private static long getNumberTilesNeeded(int right, double down, long noOfLines) {
        long allRightSteps = (long) Math.ceil(noOfLines / down) * right;
        System.out.println("allRightSteps:"+allRightSteps);

        long widthOfInput = 0;
        try {
            BufferedReader brTest = new BufferedReader(new FileReader(PATH));
            String firstLine = brTest.readLine();
            widthOfInput = firstLine.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (long) Math.ceil(allRightSteps / (double) widthOfInput);
    }

}
