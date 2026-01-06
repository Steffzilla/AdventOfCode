package de.steffzilla.aoc.y2022;

import de.steffzilla.competitive.AocUtils;

import java.util.HashSet;
import java.util.List;

public class Aoc2022_06 {

    private static final String DAY = "06";
    private static final String YEAR = "2022";
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
        long posOfMarkerEnd = 0;
        String line = inputLines.get(0);
        char[] chars = line.toCharArray();
        for (int i = 0; i < chars.length-3; i++) {
            if(chars[i] != chars[i+1] && chars[i] != chars[i+2] && chars[i] != chars[i+3] &&
               chars[i+1] != chars[i+2] && chars[i+1] != chars[i+3] &&
               chars[i+2]!=chars[i+3]) {
                posOfMarkerEnd=i+4;
                break;
            }
        }
        System.out.println("\nPart 1 > Result: " + posOfMarkerEnd);
    }

    private static void part2(List<String> inputLines) {
        int neededLength = 14;
        long posOfMarkerEnd = 0;
        String line = inputLines.get(0);
        for (int pos = 0; pos < line.length()-neededLength-1; pos++) {
            HashSet<String> setOfChars = new HashSet<>();
            for (int i = 0; i < neededLength; i++) {
                setOfChars.add(line.substring(pos+i,pos+i+1));
            }
            if(setOfChars.size()==neededLength) {
                posOfMarkerEnd=pos+neededLength;
                break;
            }
        }

        System.out.println("\nPart 1 > Result: " + posOfMarkerEnd);
    }

}