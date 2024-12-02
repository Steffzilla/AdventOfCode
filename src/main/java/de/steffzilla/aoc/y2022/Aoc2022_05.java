package de.steffzilla.aoc.y2022;

import de.steffzilla.aoc.AocUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2022_05 {

    private static final String DAY = "05";
    private static final String YEAR = "2022";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);
        long numberOfLines = AocUtils.countLines(PATH);

        //part1(inputLines, numberOfLines);
        part2(inputLines, numberOfLines);
    }

    private static void part1(List<String> inputLines, long numberOfLines){
        int lineNumberStackNumbers = 0;
        String stacksLine="";
        for (String line : inputLines) {
            lineNumberStackNumbers++;
            String potentialNumber = line.substring(1, 2);
            try {
                Integer.parseInt(potentialNumber);
            } catch (NumberFormatException e) {continue;}
            stacksLine = line;
            break;
        }
        int numberOfStacks = 0;
        List<Stack<String>> stacks = new ArrayList<>();
        for (int i = 1; i < stacksLine.length(); i=i+4) {
            String potentialNumber = stacksLine.substring(i, i+1);
            int stackNumber = Integer.parseInt(potentialNumber); // cause potential exception on purpose
            stacks.add(new Stack<String>());
            numberOfStacks++;
        }

        // read initial stacks
        for (int i = lineNumberStackNumbers-2; i >= 0; i--) {
            String line = inputLines.get(i);
            //System.out.println(line);
            int stackCount=0;
            for (int pos = 1; pos < line.length(); pos=pos+4) {
                String character = line.substring(pos, pos + 1);
                //System.out.print(character);
                if(!character.equals(" ")) {
                    stacks.get(stackCount).add(character);
                }
                stackCount++;
            }
            //System.out.println();
        }

        Pattern pattern = Pattern.compile("move (\\d{1,2}) from (\\d) to (\\d)");
        // move orders
        for (int i = lineNumberStackNumbers+1; i < numberOfLines; i++) {
            String line = inputLines.get(i);
            Matcher matcher = pattern.matcher(line);
            matcher.find();

            //System.out.println(inputLines.get(i));
            //System.out.println(matcher.group(1)+"|"+matcher.group(2)+"|"+matcher.group(3));
            int numberOfMoves =  Integer.parseInt(matcher.group(1));
            int fromStack =  Integer.parseInt(matcher.group(2));
            int toStack =  Integer.parseInt(matcher.group(3));
            move(stacks, numberOfMoves, fromStack, toStack);
            System.out.println();
        }
        System.out.println("\nPart 1 > Result: ");
        for (Stack stack : stacks) {
            System.out.print(stack.peek());
        }


    }

    private static void move(List<Stack<String>> stacks, int times, int fromStack, int toStack) {
        for (int i = 0; i < times; i++) {
            String element = stacks.get(fromStack-1).pop();
            stacks.get(toStack-1).push(element);
        }

    }


    private static void part2(List<String> inputLines, long numberOfLines) {
        int lineNumberStackNumbers = 0;
        String stacksLine="";
        for (String line : inputLines) {
            lineNumberStackNumbers++;
            String potentialNumber = line.substring(1, 2);
            try {
                Integer.parseInt(potentialNumber);
            } catch (NumberFormatException e) {continue;}
            stacksLine = line;
            break;
        }
        int numberOfStacks = 0;
        List<Stack<String>> stacks = new ArrayList<>();
        for (int i = 1; i < stacksLine.length(); i=i+4) {
            String potentialNumber = stacksLine.substring(i, i+1);
            int stackNumber = Integer.parseInt(potentialNumber); // cause potential exception on purpose
            stacks.add(new Stack<String>());
            numberOfStacks++;
        }

        // read initial stacks
        for (int i = lineNumberStackNumbers-2; i >= 0; i--) {
            String line = inputLines.get(i);
            //System.out.println(line);
            int stackCount=0;
            for (int pos = 1; pos < line.length(); pos=pos+4) {
                String character = line.substring(pos, pos + 1);
                //System.out.print(character);
                if(!character.equals(" ")) {
                    stacks.get(stackCount).add(character);
                }
                stackCount++;
            }
            //System.out.println();
        }

        Pattern pattern = Pattern.compile("move (\\d{1,2}) from (\\d) to (\\d)");
        // move orders
        for (int i = lineNumberStackNumbers+1; i < numberOfLines; i++) {
            String line = inputLines.get(i);
            Matcher matcher = pattern.matcher(line);
            matcher.find();

            //System.out.println(inputLines.get(i));
            //System.out.println(matcher.group(1)+"|"+matcher.group(2)+"|"+matcher.group(3));
            int numberOfMoves =  Integer.parseInt(matcher.group(1));
            int fromStack =  Integer.parseInt(matcher.group(2));
            int toStack =  Integer.parseInt(matcher.group(3));
            movePart2(stacks, numberOfMoves, fromStack, toStack);
            System.out.println();
        }
        System.out.println("\nPart 2 > Result: ");
        for (Stack stack : stacks) {
            System.out.print(stack.peek());
        }
    }

    private static void movePart2(List<Stack<String>> stacks, int numberElements, int fromStack, int toStack) {
        Stack<String> tempStack = new Stack<>();
        for (int i = 0; i < numberElements; i++) {
            String element = stacks.get(fromStack-1).pop();
            tempStack.push(element);
        }
        for (int i = 0; i < numberElements; i++) {
            String element = tempStack.pop();
            stacks.get(toStack-1).push(element);
        }

    }

}