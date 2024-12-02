package de.steffzilla.aoc.y2022;

import de.steffzilla.aoc.AocUtils;

import java.util.List;

public class Aoc2022_04 {

    private static final String DAY = "04";
    private static final String YEAR = "2022";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        List<String> inputLines = AocUtils.getStringList(PATH);

        //part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines){
        System.out.println(DAY+".12."+YEAR);
        long counter=0;
        for (String line : inputLines) {
            String[] split = line.split(",");
            if(split.length>2) throw new IllegalStateException("Too much data! "+line);
            int[] range1 = getRange(line, split[0]);
            int[] range2 = getRange(line, split[1]);
            if(range1[0]>=range2[0]&&range1[1]<=range2[1] || range2[0]>=range1[0]&&range2[1]<=range1[1] ) {
                counter++;
            }
        }
        System.out.println("\nPart 1 > Result: " + counter);
    }

    private static int[] getRange(String line, String elf) {
        String[] split = elf.split("-");
        if(split.length>2) throw new IllegalStateException("Too much data! "+ elf);
        int start = Integer.parseInt(split[0]);
        int end = Integer.parseInt(split[1]);
        //System.out.println(start + "--" + end);
        int[] range = new int[2];
        range[0] = start;
        range[1] = end;
        return range;
    }


    private static void part2(List<String> inputLines) {
        long counter=0;
        for (String line : inputLines) {
            String[] split = line.split(",");
            if(split.length>2) throw new IllegalStateException("Too much data! "+line);
            int[] range1 = getRange(line, split[0]);
            int[] range2 = getRange(line, split[1]);
            boolean overlap = isOverlapping(range1[0], range1[1], range2);
            if(overlap) {
                counter++;
            } else {
                overlap = isOverlapping(range2[0], range2[1], range1);
                if(overlap) {
                    counter++;
                }
            }
        }
        System.out.println("\nPart 2 > Result: " + counter);
    }

    private static boolean isOverlapping(int start1, int end1, int[] range2) {
        for (int section = start1; section <= end1 ; section++) {
            if(section >= range2[0] && section <= range2[1]) {
                return true;
            }
        }
        return false;
    }

}