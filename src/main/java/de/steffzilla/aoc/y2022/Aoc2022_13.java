package de.steffzilla.aoc.y2022;

import de.steffzilla.competitive.Utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("rawtypes")
public class Aoc2022_13 {

    private static final String DAY = "13";
    private static final String YEAR = "2022";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;
    private static Pattern pattern = Pattern.compile("(\\d+)");

    enum State {
        CORRECT, INCORRECT, UNKNOWN;
    }


    static class ListComperatorPart2 implements Comparator<List> {

        @Override
        public int compare(List left, List right) {
            State state = checkRightOrder(left, right);
            return switch (state) {
                case UNKNOWN -> 0;
                case CORRECT -> -1;
                case INCORRECT -> 1;
            };
        }
    }


    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = Utils.getStringList(PATH);

        //part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines){
        int rightOrderCounterSum = 0;
        Queue<List> leftQueue = new LinkedList<>();
        Queue<List> rightQueue = new LinkedList<>();
        List list;
        boolean useLeft = true;

        for (String line : inputLines) {
            if(line.isBlank()) {
                continue;
            }
            list = new ArrayList();
            // only pass the inner part. Starting [ and closing ] are left out
            readList(list, line.substring(1,line.length()-1));
            if (useLeft) {
                leftQueue.add(list);
                useLeft = false;
            } else {
                rightQueue.add(list);
                useLeft = true;
            }
        }
        List<Integer> correctPairNumbers = new ArrayList<>();
        int pairCounter = 1;
        while (!leftQueue.isEmpty()) {
            List left = leftQueue.poll();
            List right = rightQueue.poll();
            //System.out.println(left.toString().replaceAll("\\s+",""));
            //System.out.println(right.toString().replaceAll("\\s+",""));
            if(checkRightOrder(left, right) == State.CORRECT) {
                System.out.println("Correct: " + pairCounter);
                correctPairNumbers.add(pairCounter);
                rightOrderCounterSum+=pairCounter;
            }
            pairCounter++;
            System.out.println();
        }
        System.out.println("\nPart 1 > Result: " + rightOrderCounterSum);
    }

    static void readList(List parentList, String line) {
        char[] cArray = line.toCharArray();
        for (int i = 0; i < cArray.length; i++) {
            if (cArray[i] == '[') {
                // find the corresponding closing bracket
                int intermediateBracketsCounter  = 0;
                for (int j = i+1; j < cArray.length; j++) {
                    if (cArray[j] == '[') {
                        intermediateBracketsCounter++;
                    } else if (cArray[j] == ']') {
                        if(intermediateBracketsCounter > 0) {
                            intermediateBracketsCounter--;
                        } else {
                            // end of inner list found
                            List innerList = new ArrayList();
                            parentList.add(innerList);
                            readList(innerList, line.substring(i + 1, j));
                            i = j; // outer loop continues after the inner list
                            break;
                        }
                    }
                }
            } else if(cArray[i] == ',') {
                continue;
            } else {
                // numbers can have more than 1 digit --> get all digits form the start of the string
                String substring = line.substring(i);
                Matcher matcher = pattern.matcher(substring);
                matcher.find();
                String number = matcher.group(0);
                parentList.add(Integer.valueOf(number));
                i = i+number.length()-1; // adjust i so that the loop continues after number
            }
        }
    }

    static State checkRightOrder(List leftList, List rightList) {
        System.out.println("Compare "+ leftList + "\n     vs " + rightList);

        int maxNoOfItems;
        if (leftList.size() >= rightList.size()) {
            maxNoOfItems = leftList.size();
        } else {
            maxNoOfItems = rightList.size();
        }

        for (int i = 0; i < maxNoOfItems; i++) {
            Object left = null;
            Object right = null;
            if (i < leftList.size()) {
                left = leftList.get(i);
            }
            if (i < rightList.size()) {
                right = rightList.get(i);
            }
            System.out.println("  Compare "+ left + " vs " + right);

            // if left or right list is empty fist, return correct / incorrect
            if (left == null && right != null) {
                System.out.println("Left is empty, right not (" + right + ") --> CORRECT");
                return State.CORRECT;
            }
            if (left != null && right == null) {
                System.out.println("Left is not empty (" + left + "), right is empty --> INcorrect");
                return State.INCORRECT;
            }

            State localState = State.UNKNOWN;
            // compare 2 Integers
            if (left instanceof Integer && right instanceof Integer) {
                if ((Integer) left < (Integer) right) {
                    System.out.println(left + " < " + right + " --> CORRECT");
                    return State.CORRECT;
                }
                if ((Integer) left > (Integer) right) {
                    System.out.println(left + " > " + right + " --> INcorrect");
                    return State.INCORRECT;
                }
            } else if (left instanceof Integer && right instanceof List) {
                localState = checkRightOrder(Arrays.asList(left), (List) right);
            } else if(left instanceof List && right instanceof Integer) {
                localState = checkRightOrder((List) left, Arrays.asList(right));
            } else if (left instanceof List && right instanceof List) {
                localState = checkRightOrder((List) left, (List) right);
            } else {
                throw new IllegalStateException("Should never occur: " + left + " | " + right);
            }

            if (localState != State.UNKNOWN) {
                return localState;
            }
        }

        return State.UNKNOWN;
    }

    private static void addDecoderKeys(Queue<List> queue) {
        List list = new ArrayList();
        // only pass the inner part. Starting [ and closing ] are left out
        readList(list, "[2]");
        queue.add(list);
        list = new ArrayList();
        // only pass the inner part. Starting [ and closing ] are left out
        readList(list, "[6]");
        queue.add(list);
    }

    private static void part2(List<String> inputLines) {
        LinkedList<List> queue = new LinkedList<>();
        List list;

        for (String line : inputLines) {
            if(line.isBlank()) {
                continue;
            }
            list = new ArrayList();
            // only pass the inner part. Starting [ and closing ] are left out
            readList(list, line.substring(1, line.length()-1));
            queue.add(list);
        }

        //addDecoderKeys(queue);
        List fristDecoderKey = new ArrayList();
        readList(fristDecoderKey, "[2]");
        queue.add(fristDecoderKey);
        List secondDecoderKey = new ArrayList();
        readList(secondDecoderKey, "[6]");
        queue.add(secondDecoderKey);

        Collections.sort(queue, new ListComperatorPart2());
        int index1 = queue.indexOf(fristDecoderKey);
        int index2 = queue.indexOf(secondDecoderKey);

        System.out.println("\nPart 2 > Result: " + (index1 + 1) * (index2 + 1));
    }

}