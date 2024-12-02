package de.steffzilla.aoc.y2023;

import de.steffzilla.aoc.AocUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2023_02 {

    private static final String DAY = "02";
    private static final String YEAR = "2023";
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
        Pattern pattern = Pattern.compile("Game (\\d+):");
        Pattern patternRed = Pattern.compile("(\\d+) red");
        Pattern patternGreen = Pattern.compile("(\\d+) green");
        Pattern patternBlue = Pattern.compile("(\\d+) blue");

        long sumOfGameIds = 0;
        for (String rolesOfGame : inputLines) {
            Matcher matcher = pattern.matcher(rolesOfGame);
            if(matcher.find()) {
                String gameNumber = matcher.group(1);
                String diceRoles = rolesOfGame.substring(rolesOfGame.indexOf(": ")+2);
                System.out.println(gameNumber+"-->"+diceRoles);
                String[] roles = diceRoles.split(";");

                int redMax = 0;
                int greenMax = 0;
                int blueMax = 0;
                for (String role : roles) {
                    Matcher matcher2 = patternRed.matcher(role);
                    if(matcher2.find()) {
                        String red = matcher2.group(1);
                        int localRed = Integer.parseInt(red);
                        if (localRed > redMax) {
                            redMax = localRed;
                        }
                    }
                    matcher2 = patternGreen.matcher(role);
                    if(matcher2.find()) {
                        String green = matcher2.group(1);
                        int localGreen = Integer.parseInt(green);
                        if (localGreen > greenMax) {
                            greenMax = localGreen;
                        }
                    }
                    matcher2 = patternBlue.matcher(role);
                    if(matcher2.find()) {
                        String blue = matcher2.group(1);
                        int localBlue = Integer.parseInt(blue);
                        if (localBlue > blueMax) {
                            blueMax = localBlue;
                        }
                    }
                }
                System.out.println("redMax: "+redMax+" | greenMax: "+greenMax+" | blueMax: "+blueMax);
                if (redMax <= 12 && greenMax <= 13 && blueMax <= 14) {
                    sumOfGameIds+=Integer.parseInt(gameNumber);
                }
            } else {
                throw new IllegalStateException();
            }
        }
        System.out.println("\nPart 1 > Result: " + sumOfGameIds);
    }



    private static void part2(List<String> inputLines) {
        Pattern pattern = Pattern.compile("Game (\\d+):");
        Pattern patternRed = Pattern.compile("(\\d+) red");
        Pattern patternGreen = Pattern.compile("(\\d+) green");
        Pattern patternBlue = Pattern.compile("(\\d+) blue");

        long sumOfPowers = 0;
        for (String rolesOfGame : inputLines) {
            Matcher matcher = pattern.matcher(rolesOfGame);
            if(matcher.find()) {
                String gameNumber = matcher.group(1);
                String diceRoles = rolesOfGame.substring(rolesOfGame.indexOf(": ")+2);
                System.out.println(gameNumber+"-->"+diceRoles);
                String[] roles = diceRoles.split(";");

                int redMax = 0;
                int greenMax = 0;
                int blueMax = 0;
                for (String role : roles) {
                    Matcher matcher2 = patternRed.matcher(role);
                    if(matcher2.find()) {
                        String red = matcher2.group(1);
                        int localRed = Integer.parseInt(red);
                        if (localRed > redMax) {
                            redMax = localRed;
                        }
                    }
                    matcher2 = patternGreen.matcher(role);
                    if(matcher2.find()) {
                        String green = matcher2.group(1);
                        int localGreen = Integer.parseInt(green);
                        if (localGreen > greenMax) {
                            greenMax = localGreen;
                        }
                    }
                    matcher2 = patternBlue.matcher(role);
                    if(matcher2.find()) {
                        String blue = matcher2.group(1);
                        int localBlue = Integer.parseInt(blue);
                        if (localBlue > blueMax) {
                            blueMax = localBlue;
                        }
                    }
                }
                System.out.println("redMax: "+redMax+" | greenMax: "+greenMax+" | blueMax: "+blueMax);
                long power = redMax*greenMax*blueMax;
                System.out.println(power);
                sumOfPowers+=power;
            } else {
                throw new IllegalStateException();
            }
        }
        System.out.println("\nPart 2 > Result: " + sumOfPowers);
    }

}