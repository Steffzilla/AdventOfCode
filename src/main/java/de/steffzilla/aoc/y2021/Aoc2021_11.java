package de.steffzilla.aoc.y2021;

import de.steffzilla.competitive.Utils;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Aoc2021_11 {

    private static final String DAY = "11";
    private static final String YEAR = "2021";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;
    public static final int X_DIMENSION = 10;
    private static final int NUMBER_OF_ROUNDS=500;

    public static void main(String[] args) {
        List<String> inputLines = Utils.getStringList(PATH);

        part1(inputLines);
    }

    private static void part1(List<String> inputLines){
        int noOfFlashes = 0;
        List<Octopus> octopuses = readOctopuses(inputLines);
        printOctopusField(octopuses);
        System.out.println();

        for (int roundCounter = 1; roundCounter <= NUMBER_OF_ROUNDS; roundCounter++) {
            Stack<Octopus> flashingOctopuses = new Stack<>();
            for (Octopus octopus : octopuses) {
                boolean willFlash = octopus.doNormalEnergyLevelInc();
                if (willFlash) {
                    flashingOctopuses.push(octopus);
                }
            }
            while (!flashingOctopuses.empty()) {
                Octopus flashingOctopus = flashingOctopuses.pop();
                List<Pair<Integer, Integer>> neighbours = flashingOctopus.getNeighbours();
                for (Pair<Integer,Integer> pair: neighbours) {
                    Octopus octopusToBeIncremented = octopuses.get(pair.getValue1()* X_DIMENSION +pair.getValue0());
                    boolean willFlash =  octopusToBeIncremented.getsIncremented();
                    if (willFlash) {
                        flashingOctopuses.push(octopusToBeIncremented);
                    }
                }
                noOfFlashes++;
            }
            System.out.println();
            int fieldFlashCounter = printOctopusField(octopuses);
            // Part 2
            if(fieldFlashCounter == X_DIMENSION * X_DIMENSION) {
                System.out.println("All octopuses flash in round: "+roundCounter);
                break;
            }
        }
        System.out.println("\nNumber of Flashes: "+noOfFlashes);
    }

    private static List<Octopus> readOctopuses(List<String> inputLines) {
        List<Octopus> list = new ArrayList<>();
        int lineCounter = 0;
        for (String line : inputLines) {
            for (int x = 0; x < line.length(); x++) {
                int value = Integer.parseInt(line.substring(x, x + 1));
                list.add(new Octopus(x, lineCounter, value));
                //System.out.print(value);
            }
            lineCounter++;
            //System.out.println();
        }
        return list;
    }

    private static int printOctopusField(List<Octopus> list) {
        int fieldFlashCounter = 0;
        int x = 1;
        for (Octopus octopus : list) {
            int energyLevel = octopus.getEnergyLevel();
            if(energyLevel > 9) {
                System.out.print("#");
                fieldFlashCounter++;
            } else {
                System.out.print(energyLevel);
            }
            if (x % X_DIMENSION == 0) {
                System.out.println();
            }
            x++;
        }
        return fieldFlashCounter;
    }

}