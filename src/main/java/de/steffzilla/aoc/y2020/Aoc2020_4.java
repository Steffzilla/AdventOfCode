package de.steffzilla.aoc.y2020;

import de.steffzilla.aoc.AocUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2020_4 {

    private static final String DAY = "04";
    private static final String YEAR = "2020";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    //public static String FILENAME = "sample2020_4.txt";
    //public static String FILENAME = "sample2020_4_2.txt";
    //public static String FILENAME = "sample2020_4_3.txt";
    public static String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        List<String> stringList = AocUtils.getStringList(PATH);
        Pattern pattern = Pattern.compile("([a-zA]{3})(\\S+)");
        PassData passData = new PassData();
        int validPasses = 0;
        for (String line : stringList) {
            System.out.println("LINE:"+line);
            if (line.trim().isEmpty()) {
                // Check old one
                if(passData.checkData()) {
                    System.out.println("VALID");
                    validPasses++;
                } else {
                    System.out.println("invalid");
                }
                System.out.println("-------NEW PASSPORT---------");
                // Create new one
                passData = new PassData();
            } else {
                Matcher matcher = pattern.matcher(line);
                // check all occurrence
                while (matcher.find()) {
                    passData.addFieldValuePart2(matcher.group());
                }
            }
        }
        // Check last pass
        if(!passData.isChecked()) {
            if(passData.checkData()) {
                System.out.println("VALID");
                validPasses++;
            } else {
                System.out.println("invalid");
            }
        }
        System.out.println("\nNumber of valid passes:"+validPasses);
    }

}
