package de.steffzilla.aoc.y2021;

import de.steffzilla.competitive.Utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Aoc2021_16 {

    private static final String DAY = "16";
    private static final String YEAR = "2021";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;
    private static int versionNumberSum = 0;

    private static class ReturnValue {

        public ReturnValue() {}

        public ReturnValue(int charsRead, BigInteger value) {
            this.charsRead = charsRead;
            this.value = value;
        }

        int charsRead=0;
        BigInteger value;
    }

    public static void main(String[] args) {
        List<String> inputLines = Utils.getStringList(PATH);
        if(inputLines.size() != 1) {
            throw new IllegalStateException("Expecting 1 line!");
        }

        //String sample = "D2FE28";
        //String sample = "38006F45291200";
        //String sample = "EE00D40C823060";
        //String sample = "8A004A801A8002F478";
        //String sample = "620080001611562C8802118E34";
        //String sample = "C0015000016115A2E0802F182340";
        //String sample = "A0016C880162017C3686B18A3D4780";

        //String sample = "C200B40A82";
        //String sample = "04005AC33890";
        //String sample = "880086C3E88112";
        //String sample = "CE00C43D881120";
        //String sample = "D8005AC2A8F0";
        //String sample = "F600BC2D8F";
        String sample = "9C0141080250320F1802104A08";
        //String binaryInput = AocUtils.hexToBinWithLeadingZeros(sample);
        String binaryInput = Utils.hexToBinWithLeadingZeros(inputLines.get(0));

        //part1(binaryInput);
        part2(binaryInput);
    }

    private static void part1(String binaryInput){
        System.out.println(binaryInput);
        readPackage(binaryInput);

        System.out.println("\nResult: "+versionNumberSum);
    }

    private static void part2(String binaryInput) {
        System.out.println(binaryInput);
        ReturnValue returnValue = readPackage(binaryInput);

        System.out.println("\nResult: "+returnValue.value);
    }

    private static ReturnValue readPackage(String binaryPackage) {
        String versionBin = binaryPackage.substring(0, 3);
        String typeBin = binaryPackage.substring(3, 6);
        int version = Integer.parseInt(versionBin, 2);
        versionNumberSum += version;
        int type = Integer.parseInt(typeBin, 2);
        System.out.println(versionBin+"|"+version+"|"+typeBin+"|"+type);
        int charactersRead = 6;
        ReturnValue returnValue = new ReturnValue();
        if (4 == type) {
            ReturnValue literalReturnValue = readLiteralPackage(binaryPackage.substring(charactersRead));
            returnValue.value = literalReturnValue.value;
            charactersRead += literalReturnValue.charsRead;
            System.out.println("characters read:"+charactersRead);
        } else {
            ReturnValue operatorReturnValue = readOperatorPackage(type, binaryPackage.substring(6));
            returnValue.value = operatorReturnValue.value;
            charactersRead += operatorReturnValue.charsRead;
        }
        returnValue.charsRead = charactersRead;
        return returnValue;
    }

    private static ReturnValue readOperatorPackage(int type, String binaryPackage) {
        String lengthTypeId = binaryPackage.substring(0, 1);
        int charactersRead = 1;
        List<BigInteger> values = new ArrayList<>();
        if (lengthTypeId.equals("0")) {
            // total length in bits
            String lengthBin = binaryPackage.substring(1, 16);
            charactersRead += 15;
            int length = Integer.parseInt(lengthBin, 2);
            String subPackage = binaryPackage.substring(charactersRead, charactersRead + length);
            System.out.println("Operator -> total length in bits: "+lengthBin+"|"+length+"| package to be processed:"+subPackage);
            while(subPackage.length() > 0) {
                ReturnValue returnValue = readPackage(subPackage);
                int charsJustRead = returnValue.charsRead;
                subPackage = subPackage.substring(charsJustRead);
                charactersRead += charsJustRead;
                values.add(returnValue.value);
            }
        } else if (lengthTypeId.equals("1")) {
            // number of sub-packets immediately contained
            String numberOfPackagesBin = binaryPackage.substring(1, 12);
            charactersRead += 11;
            int numberOfPackages = Integer.parseInt(numberOfPackagesBin, 2);
            System.out.println("Operator -> number of sub-packets:"+numberOfPackages);
            for (int i = 0; i < numberOfPackages; i++) {
                String packageToBeProcessed = binaryPackage.substring(charactersRead);
                ReturnValue returnValue = readPackage(packageToBeProcessed);
                charactersRead += returnValue.charsRead;
                values.add(returnValue.value);
            }
        } else {
            throw new IllegalStateException("Should not occur");
        }

        // Process values
        ReturnValue returnValue = new ReturnValue();
        if (0 == type) {
            System.out.println("SUM");
            returnValue.value = BigInteger.valueOf(0);
            for (BigInteger value : values) {
                returnValue.value = returnValue.value.add(value);
            }
        } else if (1 == type) {
            System.out.println("PRODUCT");
            returnValue.value = BigInteger.ONE;
            for (BigInteger value : values) {
                returnValue.value = returnValue.value.multiply(value);
            }
        } else if (2 == type) {
            System.out.println("MINIMUM");
            Collections.sort(values);
            returnValue.value = values.get(0);
        } else if (3 == type) {
            System.out.println("MAXIMUM");
            Collections.sort(values);
            returnValue.value = values.get(values.size() - 1);
        } else if (5 == type) {
            System.out.println("GREATER THAN");
            if (values.size()!=2) {
                throw new IllegalStateException("Should not occur");
            }
            if (values.get(0).compareTo(values.get(1)) == 1) {
                returnValue.value = BigInteger.ONE;
            } else {
                returnValue.value = BigInteger.ZERO;
            }
        } else if (6 == type) {
            System.out.println("LESS THAN");
            if (values.size()!=2) {
                throw new IllegalStateException("Should not occur");
            }
            if (values.get(0).compareTo(values.get(1)) == -1) {
                returnValue.value = BigInteger.ONE;
            } else {
                returnValue.value = BigInteger.ZERO;
            }
        } else if (7 == type) {
            System.out.println("EQUAL TO");
            if (values.size()!=2) {
                throw new IllegalStateException("Should not occur");
            }
            if (values.get(0).compareTo(values.get(1)) == 0) {
                returnValue.value = BigInteger.ONE;
            } else {
                returnValue.value = BigInteger.ZERO;
            }
        }
        returnValue.charsRead = charactersRead;
        return returnValue;

    }

    /**
     * @return number of read binary chars
     */
    private static ReturnValue readLiteralPackage(String binaryPackage) {
        //System.out.println("Parsing literal: "+binaryPackage);
        StringBuilder sb = new StringBuilder();
        boolean continueReading = true;
        int i = 0;
        while (continueReading) {
            sb.append(binaryPackage.substring(i+1,i+5)); // value
            if(binaryPackage.charAt(i) == '0') { // continue?
                continueReading = false;
            }
            i+=5;
        }
        BigInteger value = new BigInteger(sb.toString(), 2);
        System.out.println("parsed literal package "+binaryPackage+" with value: "+ value);

        return new ReturnValue(i, value);
    }

}