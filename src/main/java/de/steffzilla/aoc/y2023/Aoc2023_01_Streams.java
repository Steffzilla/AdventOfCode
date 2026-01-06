package de.steffzilla.aoc.y2023;

import de.steffzilla.competitive.AocUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Aoc2023_01_Streams {

    private static final String DAY = "01";
    private static final String YEAR = "2023";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+"_2.txt";
    public static final String PATH = BASEDIR + FILENAME;
    public static final String ONE = "one";
    public static final String ONE_REVERSED = "eno";
    public static final String TWO = "two";
    public static final String TWO_REVERSED = "owt";
    public static final String THREE = "three";
    public static final String THREE_REVERSED = "eerht";
    public static final String FOUR = "four";
    public static final String FOUR_REVERSED = "ruof";
    public static final String FIVE = "five";
    public static final String FIVE_REVERSED = "evif";
    public static final String SIX = "six";
    public static final String SIX_REVERSED = "xis";
    public static final String SEVEN = "seven";
    public static final String SEVEN_REVERSED = "neves";
    public static final String EIGHT = "eight";
    public static final String EIGHT_REVERSED = "thgie";
    public static final String NINE = "nine";
    public static final String NINE_REVERSED = "enin";

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);

        //part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines){
        long sum = getSumPart1(inputLines);
        System.out.println("\nPart 1 > Result: " + sum);
    }

    private static long getSumPart1(List<String> inputLines) {
        long sum = 0;
        for (String line : inputLines) {
            System.out.println(line);
            Stream<Character> characterStream
                    = line.codePoints().mapToObj(c -> (char) c);
            Character digit = characterStream.filter(Character::isDigit)
                    .findFirst()
                    .get();
            StringBuilder sb = new StringBuilder();
            sb.append(digit);
            StringBuilder sbToRevers = new StringBuilder(line);
            characterStream = sbToRevers.reverse().toString().codePoints().mapToObj(c -> (char) c);
            Character digit2 = characterStream.filter(Character::isDigit)
                    .findFirst()
                    .get();
            sb.append(digit2);
            System.out.println(sb);
            sum+=Integer.parseInt(sb.toString());
        }
        return sum;
    }


    private static void part2(List<String> inputLines) {


        List<String> processedLines = new ArrayList<>();
        for (String line : inputLines) {
            System.out.println(line);

            line = replaceStringNumbers(line);
            processedLines.add(line);

        }
        System.out.println();
        System.out.println("\nPart 2 > Result: " + getSumPart1(processedLines));
    }

    static String replaceStringNumbers(String line) {
        Pattern pattern = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine)");
        Pattern reversePattern = Pattern.compile("("+ONE_REVERSED+"|"+TWO_REVERSED+"|"+THREE_REVERSED+
                "|"+FOUR_REVERSED+"|"+FIVE_REVERSED+"|"+SIX_REVERSED+"|"+SEVEN_REVERSED+
                "|"+EIGHT_REVERSED+"|"+NINE_REVERSED+")");
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            String replacement = matcher.group(1);
            line = replaceFirst(line, replacement);

            StringBuilder sbToRevers = new StringBuilder(line);
            String reversedLine = sbToRevers.reverse().toString();
            Matcher matcher2 = reversePattern.matcher(reversedLine);
            if (matcher2.find()) {
                replacement = matcher2.group(1);
                //System.out.println("Reverse replacement:"+replacement);
                reversedLine = replaceFirstReversed(reversedLine, replacement);
                //System.out.println("after replacement:"+line);
                sbToRevers = new StringBuilder(reversedLine);
                line = sbToRevers.reverse().toString();
                //System.out.println("back in right order:"+line);
            }

        }
        System.out.println("-->"+ line);
        return line;
    }

    static String replaceFirst(String line, String replacement) {
        switch (replacement) {
            case ONE:
                line = line.replaceFirst(ONE, "1"+ONE);
                break;
            case TWO:
                line = line.replaceFirst(TWO, "2"+TWO);
                break;
            case THREE:
                line = line.replaceFirst(THREE, "3"+THREE);
                break;
            case FOUR:
                line = line.replaceFirst(FOUR, "4"+FOUR);
                break;
            case FIVE:
                line = line.replaceFirst(FIVE, "5"+FIVE);
                break;
            case SIX:
                line = line.replaceFirst(SIX, "6"+SIX);
                break;
            case SEVEN:
                line = line.replaceFirst(SEVEN, "7"+SEVEN);
                break;
            case EIGHT:
                line = line.replaceFirst(EIGHT, "8"+EIGHT);
                break;
            case NINE:
                line = line.replaceFirst(NINE, "9"+NINE);
                break;
        }
        return line;
    }

    static String replaceFirstReversed(String line, String replacement) {
        switch (replacement) {
            case ONE_REVERSED:
                line = line.replaceFirst(ONE_REVERSED, "1");
                break;
            case TWO_REVERSED:
                line = line.replaceFirst(TWO_REVERSED, "2");
                break;
            case THREE_REVERSED:
                line = line.replaceFirst(THREE_REVERSED, "3");
                break;
            case FOUR_REVERSED:
                line = line.replaceFirst(FOUR_REVERSED, "4");
                break;
            case FIVE_REVERSED:
                line = line.replaceFirst(FIVE_REVERSED, "5");
                break;
            case SIX_REVERSED:
                line = line.replaceFirst(SIX_REVERSED, "6");
                break;
            case SEVEN_REVERSED:
                line = line.replaceFirst(SEVEN_REVERSED, "7");
                break;
            case EIGHT_REVERSED:
                line = line.replaceFirst(EIGHT_REVERSED, "8");
                break;
            case NINE_REVERSED:
                line = line.replaceFirst(NINE_REVERSED, "9");
                break;
        }
        return line;
    }

}