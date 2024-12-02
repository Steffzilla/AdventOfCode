package de.steffzilla.aoc.y2021;

import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2021_13 {

    private static final String DAY = "13";
    private static final String YEAR = "2021";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;
    public static final String HASH = "#";

    public static void main(String[] args) {
        List<String> inputLines = AocUtils.getStringList(PATH);
        List<List<String>> paper;
        Pattern pattern = Pattern.compile("fold along (x|y){1}=(\\d{1,3})");
        List<Pair<String, Integer>> foldings = new ArrayList<>();
        List<Pair<Integer, Integer>> points = new ArrayList<>();
        int maxX = 0;
        int maxY = 0;
        for (String line : inputLines) {
            String[] splits = line.split(",");
            if(splits.length==2) {
                // paper
                int x = Integer.parseInt(splits[0]);
                int y = Integer.parseInt(splits[1]);
                if (x > maxX) {
                    maxX = x;
                }
                if (y > maxY) {
                    maxY = y;
                }
                points.add(new Pair<>(x,y));
            } else if(line.startsWith("fold along")) {
                Matcher matcher = pattern.matcher(line);
                matcher.find();
                //System.out.println(matcher.group(1)+Integer.parseInt(matcher.group(2)));
                foldings.add(new Pair<>(matcher.group(1), Integer.parseInt(matcher.group(2))));
            }
        }
        System.out.println(maxX+"|"+maxY);
        paper = AocUtils.createFilledDotMatrix(points, maxX+1, maxY+1, " ");

        //part1(paper, foldings);
        part2(paper, foldings);
    }

    private static void part2(List<List<String>> paper, List<Pair<String, Integer>> foldings) {
        for (Pair<String, Integer> folding: foldings) {
            String xy = folding.getValue0();
            if (xy.equals("x")) {
                paper = fold(paper, true, folding.getValue1());
            } else {
                paper = fold(paper, false, folding.getValue1());
            }
        }

        printPaper(paper);
    }

    private static void printPaper(List<List<String>> paper) {
        for (List<String> line : paper) {
            for (String value : line) {
                System.out.print(value);
            }
            System.out.println();
        }
    }

    private static void part1(List<List<String>> paper, List<Pair<String, Integer>> foldings) {
        Pair<String, Integer> folding = foldings.get(0);
        String xy = folding.getValue0();
        if (xy.equals("x")) {
            paper = fold(paper, true, folding.getValue1());
        } else {
            paper = fold(paper, false, folding.getValue1());
        }

        int numberOfHashs = countHashs(paper);
        System.out.println("\nPart1: "+numberOfHashs);
    }

    private static int countHashs(List<List<String>> paper) {
        int count = 0;
        for (List<String> line : paper) {
            for (String value : line) {
                if(value.equals(HASH)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     *
     * @param paper
     * @param isXFolding true is a right to left folding
     * @param foldingPos
     * @return
     */
    private static List<List<String>> fold(List<List<String>> paper, boolean isXFolding, int foldingPos) {
        List<List<String>> newPaper;
        int newXSize;
        int newYSize;
        if(isXFolding) {
            newXSize = (paper.get(0).size()-1)/2;
            newYSize = paper.size();
        } else {
            newXSize = paper.get(0).size();
            newYSize = (paper.size()-1)/2;
        }
        newPaper= AocUtils.createEmptyMatrix(newXSize, newYSize," ");
        AocUtils.copyMatrix(paper, newPaper, 0, newXSize, 0, newYSize);
        if (isXFolding) {
            copyRightPartleft(paper, newPaper, newXSize);
        } else {
            copyLowerPartUp(paper, newPaper, newYSize);
        }

        return newPaper;
    }

    private static void copyRightPartleft(List<List<String>> from, List<List<String>> to, int newXSize) {
        for (int y = 0; y < from.size(); y++) {
            List<String> fromLine = from.get(y);
            List<String> toLine = to.get(y);
            for (int x = 0; x < newXSize; x++) {
                String fromValue = fromLine.get(fromLine.size()-1-x);
                if(fromValue.equals(HASH)) {
                    toLine.set(x, HASH);
                }
            }
        }
    }

    private static void copyLowerPartUp(List<List<String>> from, List<List<String>> to, int newYSize) {
        for (int y = 0; y < newYSize; y++) {
            List<String> fromLine = from.get(from.size()-1-y);
            List<String> toLine = to.get(y);
            for (int x = 0; x < fromLine.size(); x++) {
                String fromValue = fromLine.get(x);
                if(fromValue.equals(HASH)) {
                    toLine.set(x, HASH);
                }
            }
        }
    }

    private static void part1(List<String> inputLines){
        System.out.println("\nResult: ");
    }

}