package de.steffzilla.aoc.y2021;

import de.steffzilla.competitive.AocUtils;

import java.util.ArrayList;
import java.util.List;

public class Aoc2021_3 {

    private static final String DAY = "03";
    private static final String YEAR = "2021";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;


    public static void main(String[] args) {
        int epsilon = 0;
        int gamma = 0;

        int oxygenGenRating = 0;
        int co2ScrubberRating = 0;

        List<String> stringList = AocUtils.getStringList(PATH);
        int[] intArray = new int[stringList.get(0).length()];
        for (String code: stringList) {
            for (int i = 0; i < code.length(); i++) {
                int number = Integer.parseInt(code.substring(i, i+1));
                //System.out.print(number);
                intArray[i] = intArray[i] + number;
            }
            //System.out.println();
        }
        gamma = calculateGamma(intArray, stringList.size());
        epsilon = calculateEpsilon(intArray, stringList.size());
        int resultPart1 = epsilon * gamma;
        System.out.println("\nResult: "+resultPart1);

        oxygenGenRating = getOxygeneGenRate(stringList, 0);
        co2ScrubberRating = getCo2(stringList, 0);
        int lifeSupportRating = oxygenGenRating * co2ScrubberRating;
        System.out.println("\nLife support rating: "+lifeSupportRating);
    }

    private static int getOxygeneGenRate(List<String> stringList, int index) {
        int result = getValue(stringList, index, true);
        System.out.println("Ox:"+result);
        return result;
    }

    private static int getCo2(List<String> stringList, int index) {
        int result = getValue(stringList, index, false);
        System.out.println("CO2:"+result);
        return result;
    }

    private static int getValue(List<String> stringList, int index, boolean mostCommonBitOr1) {
        if (stringList.size() == 1) {
            return Integer.parseInt(stringList.get(0),2);
        } else {
            List<String> zeroStrings = new ArrayList<>();
            List<String> oneStrings = new ArrayList<>();
            for (String line : stringList) {
                if (line.charAt(index) == '0') {
                    zeroStrings.add(line);
                } else if (line.charAt(index) == '1') {
                    oneStrings.add(line);
                } else {
                    throw new IllegalStateException("Should not occur");
                }
            }
            if (mostCommonBitOr1) {
                if(zeroStrings.size() > oneStrings.size()) {
                    return getValue(zeroStrings,index+1, mostCommonBitOr1);
                } else {
                    return getValue(oneStrings,index+1, mostCommonBitOr1);
                }
            } else {
                // lease common or 0 as default
                System.out.print("oneString.size:"+oneStrings.size());
                System.out.print("|zeroString.size:"+zeroStrings.size());
                if(oneStrings.size() >= zeroStrings.size()) {
                    System.out.println("|Take zero");
                    return getValue(zeroStrings,index+1, mostCommonBitOr1);
                } else {
                    System.out.println("|Take one");
                    return getValue(oneStrings,index+1, mostCommonBitOr1);
                }
            }
        }
    }

    private static int calculateGamma(int[] intArray, int numberOfLines) {
        String binaryString = "";
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] < numberOfLines / 2) {
                binaryString = binaryString + "0";
            } else if (intArray[i] > numberOfLines / 2) {
                binaryString = binaryString + "1";
            } else {
                throw new IllegalStateException("gleiche Bits"+i+"|"+intArray[i]);
            }
        }
        int result = Integer.parseInt(binaryString,2);
        System.out.println(binaryString+"|"+result);

        return result;
    }

    private static int calculateEpsilon(int[] intArray, int numberOfLines) {
        String binaryString = "";
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] > numberOfLines / 2) {
                binaryString = binaryString + "0";
            } else if (intArray[i] < numberOfLines / 2) {
                binaryString = binaryString + "1";
            } else {
                throw new IllegalStateException("gleiche Bits"+i+"|"+intArray[i]);
            }
        }
        int result = Integer.parseInt(binaryString,2);
        System.out.println(binaryString+"|"+result);

        return result;
    }

}
