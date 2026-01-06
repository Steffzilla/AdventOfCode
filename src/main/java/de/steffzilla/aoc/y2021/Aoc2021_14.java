package de.steffzilla.aoc.y2021;

import de.steffzilla.competitive.Utils;

import java.util.Hashtable;
import java.util.List;

public class Aoc2021_14 {

    private static final String DAY = "14";
    private static final String YEAR = "2021";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    //public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;
    private static final int ITERATIONS_PART1 = 10;
    private static final int ITERATIONS_PART2 = 40;

    public static void main(String[] args) {
        List<String> inputLines = Utils.getStringList(PATH);
        String template = inputLines.get(0);

        Hashtable<String, String> rules = readRules(inputLines);

        //part1(template, rules);

        Hashtable<String, Integer> countingTable = new Hashtable<>();
        part2(template, rules, countingTable, ITERATIONS_PART2);

        System.out.println("String processing done");

        // count last letter that remains always last and is not counted
        //System.out.print(lastLetter);
        String lastLetter = template.substring(template.length() - 1);
        Integer i = countingTable.get(lastLetter);
        countingTable.put(lastLetter, i+1);
        Integer min = countingTable.values().stream().sorted().findFirst().get();
        long sizeOfValues = countingTable.values().stream().count();
        Integer max = countingTable.values().stream().sorted().skip(sizeOfValues - 1).findFirst().get();
        System.out.println("\nResult: "+max+" - " + min + " = "+(max-min));
    }

    private static Hashtable<String, String> readRules(List<String> inputLines) {
        Hashtable<String, String> rules = new Hashtable<>();
        for (String line : inputLines) {
            String[] strings = line.split(" -> ");
            if(strings.length==2) {
                String firstLetter = strings[0].substring(0,1);

                String secondLetter = strings[0].substring(1,2);
                rules.put(strings[0], firstLetter + strings[1] + secondLetter);
            }
        }
        return rules;
    }

    private static void part1(String template, Hashtable<String, String> rules){
        for (int i = 0; i < ITERATIONS_PART1; i++) {
             template = processStringOnce(template, rules);
        }

        // count
        long noOfMostCommonElements = 0;
        long noOfLeastCommonElements = 0;
        for (char c = 'A'; c <= 'Z' ; c++) {
            long l = Utils.countCharInString(template, c);
            if(l>0) {
                if(l>noOfMostCommonElements) {
                    noOfMostCommonElements = l;
                }
                if(noOfLeastCommonElements==0 || noOfLeastCommonElements > l) {
                    noOfLeastCommonElements = l;
                }
            }
        }
        System.out.println("\nResult: "+noOfMostCommonElements+" - " + noOfLeastCommonElements + " = "+(noOfMostCommonElements-noOfLeastCommonElements));
    }

    private static String processStringOnce(String template, Hashtable<String, String> rules){
        String result = "";
        for (int i = 0; i < template.length()-1; i++) {
            String resultPart = rules.get(template.substring(i, i + 2));
            if(result.length()==0) {
                result = resultPart;
            } else {
                result = result.substring(0,result.length()-1) + resultPart;
            }
        }
        //System.out.println(result);
        return result;
    }


    private static void part2(String template, Hashtable<String, String> rules, Hashtable<String, Integer> countingTable, int iterationsLeft){
        if (iterationsLeft==0) {
            //System.out.print(template.substring(0,2));
            //add the first two letters
            for (int j = 0; j < 2; j++) {
                String letter = template.substring(j, j+1);
                //System.out.print(letter);
                Integer i = countingTable.get(letter);
                if(i==null) {
                    countingTable.put(letter, 1);
                } else {
                    countingTable.put(letter, i+1);
                }
            }
        } else {
            String partToProcess = rules.get(template.substring(0, 2));
            part2(partToProcess, rules, countingTable, iterationsLeft-1);
            if(template.length()>2) {
                part2(template.substring(1), rules, countingTable, iterationsLeft);
            }
        }
    }












    private static void processStringOnceStringBuilder(StringBuilder sb, Hashtable<String, String> rules){
        for (int i = 0; i < sb.length()-1; i=i+2) {
            String resultPart = rules.get(sb.substring(i, i + 2));
            sb.replace(i,i+2, resultPart);
        }
        //System.out.println(sb);
    }

    private static void part2StringBuilderDoesntHelp(String template, Hashtable<String, String> rules) {
        StringBuilder sb = new StringBuilder(template);
        for (int i = 0; i < ITERATIONS_PART2; i++) {
            processStringOnceStringBuilder(sb, rules);
        }

        // count
        long noOfMostCommonElements = 0;
        long noOfLeastCommonElements = 0;
        for (char c = 'A'; c <= 'Z' ; c++) {
            long l = Utils.countCharInString(sb.toString(), c);
            if(l>0) {
                if(l>noOfMostCommonElements) {
                    noOfMostCommonElements = l;
                }
                if(noOfLeastCommonElements==0 || noOfLeastCommonElements > l) {
                    noOfLeastCommonElements = l;
                }
            }
        }
        System.out.println("\nResult: "+noOfMostCommonElements+" - " + noOfLeastCommonElements + " = "+(noOfMostCommonElements-noOfLeastCommonElements));
    }

}