package de.steffzilla.aoc.y2020;

import de.steffzilla.competitive.Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class Aoc2020_2 {

    private static final String DAY = "02";
    private static final String YEAR = "2020";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        List<String> strings = Utils.getStringList(PATH);
        //System.out.println(strings);
        int correctPasswords = 0;
        for (String line : strings) {
            if(checkCode(line)) {
                correctPasswords++;
            }
        }
        System.out.println("No of correct passwords:"+correctPasswords);
    }

    private static boolean checkCode(String line) {
        String[] s = line.split(" ");
        System.out.println(line + "-->"+Arrays.stream(s).toList());
        String[] splittedMinMax = StringUtils.split(s[0], "-");
        int min = Integer.parseInt(splittedMinMax[0]);
        //Character.getNumericValue(s[0].charAt(0));
        int max = Integer.parseInt(splittedMinMax[1]);
        //return checkPart1(s, min, max, s[1].charAt(0));
        return checkPart2(min, max, s[1].charAt(0), s[2]);
    }

    private static boolean checkPart1(String[] s, int min, int max, char letter) {
        long count = StringUtils.countMatches(s[2],letter);

        if(count >= min && count <= max) {
            System.out.println("min:"+ min +" max:"+ max +"|Letter:"+letter+"|count:"+count+"-->ok");
            return true;
        } else {
            System.out.println("min:"+ min +" max:"+ max +"|Letter:"+letter+"|count:"+count+"-->NOT ok");
            return false;
        }
    }

    private static boolean checkPart2(int index1, int index2, char letter, String password) {
        char char1 = password.charAt(index1-1);
        char char2 = password.charAt(index2-1);

        if ((char1 == letter && char2 != letter) || (char1 != letter && char2 == letter)) {
            System.out.println("ind1:"+ index1 +" ind2:"+ index2 +"|Letter:"+letter+"|password:"+password+"|char1:"+char1+"|char2:"+char2+"|VALID");
            return true;
        } else {
            System.out.println("ind1:"+ index1 +" ind2:"+ index2 +"|Letter:"+letter+"|password:"+password+"|char1:"+char1+"|char2:"+char2+"|invalid");
            return false;
        }


    }
}
