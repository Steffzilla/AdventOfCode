package de.steffzilla.aoc.y2021;

import de.steffzilla.aoc.AocUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Aoc2021_10 {

    private static final String DAY = "10";
    private static final String YEAR = "2021";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;

    private static final String RUND_START = "(";
    private static final String ECKIG_START = "[";
    private static final String GESCHWEIFT_START = "{";
    private static final String SPITZ_START = "<";
    private static final List<String> START_CHARS = List.of(RUND_START, ECKIG_START, GESCHWEIFT_START, SPITZ_START);
    private static final String RUND_ENDE = ")";
    private static final String ECKIG_ENDE = "]";
    private static final String GESCHWEIFT_ENDE = "}";
    private static final String SPITZ_ENDE = ">";
    private static final List<String> END_CHARS = List.of(RUND_ENDE, ECKIG_ENDE, GESCHWEIFT_ENDE, SPITZ_ENDE);

    public static void main(String[] args) {
        List<String> inputLines = AocUtils.getStringList(PATH);

        /*System.out.println(checkLine("(((((((((())))))))))"));
        System.out.println(checkLine("[]"));
        System.out.println(checkLine("[]]"));
        System.out.println(checkLine("<[]]"));
        System.out.println(checkLine("[<>({}){}[([])<>]]"));*/

        /*System.out.println(checkLine("{()()()>"));
        System.out.println(checkLine("(((()))}"));
        System.out.println(checkLine("<([]){()}[{}])"));*/

        //System.out.println(checkLine("{([(<{}[<>[]}>{[]{[(<()>"));

        //part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines) {
        // ), ], }, >
        //long[] count1stErrorChars = new long[]{2,1,1,1};
        long[] count1stErrorChars = new long[4];
        for (String line : inputLines) {
            String firstErrorChar = checkLine(line);
            System.out.println(line+"|errorChar:"+firstErrorChar);
            if (firstErrorChar.length() == 1) {
                int index = switch (firstErrorChar) {
                    case RUND_ENDE -> 0;
                    case ECKIG_ENDE -> 1;
                    case GESCHWEIFT_ENDE -> 2;
                    case SPITZ_ENDE -> 3;
                    default -> -1;
                };
                count1stErrorChars[index]++;
            }
        }

        long errorScore = calculateErrorScore(count1stErrorChars[0], count1stErrorChars[1], count1stErrorChars[2], count1stErrorChars[3]);
        System.out.println("\nResult: "+errorScore);
    }

    private static String checkLine(String line) {
        /*if(line.length() % 2 == 1) {
            System.out.println("skipped:"+line+"|"+line.length());
            return "";
        } else {*/
            Stack<String> stack = new Stack<>();
            for (int i = 0; i < line.length(); i++) {
                String character = line.substring(i, i + 1);
                if(START_CHARS.contains(character)) {
                    stack.add(character);
                } else if(END_CHARS.contains(character)) {
                    String openingChar = stack.pop();
                    switch (openingChar) {
                        case RUND_START:
                            if (!character.equals(RUND_ENDE)) {
                                return character;
                            }
                            break;
                        case ECKIG_START:
                            if (!character.equals(ECKIG_ENDE)) {
                                return character;
                            }
                            break;
                        case GESCHWEIFT_START:
                            if (!character.equals(GESCHWEIFT_ENDE)) {
                                return character;
                            }
                            break;
                        case SPITZ_START:
                            if (!character.equals(SPITZ_ENDE)) {
                                return character;
                            }
                            break;
                        default:
                            throw new IllegalStateException("Should not occur "+openingChar);
                    }
                } else {
                    throw new IllegalStateException("Should not occur "+character);
                }
            }
        //}
        return "";
    }

    private static long calculateErrorScore(long rund, long eckig, long geschweift, long spitz) {
        long result = rund * 3 + eckig * 57 + geschweift * 1197 + spitz * 25137;
        return result;
    }

    private static void part2(List<String> inputLines) {
        List<String> completionStrings = new ArrayList<>();
        for (String line : inputLines) {
            String completion = completeLine(line);
            if(completion.length()>1) {
                completionStrings.add(completion);
            }
        }

        //completionStrings = List.of("}}]])})]", ")}>]})", "}}>}>))))", "]]}}]}]}>", "])}>");
        List<Long> scores = new ArrayList<>();
        for (String completionString : completionStrings) {
            long score = getScore(completionString);
            System.out.println(score);
            scores.add(score);
        }
        Collections.sort(scores);
        int middleIndex = (scores.size() / 2);
        System.out.println("\nResult: "+scores.get(middleIndex));
    }

    private static String completeLine(String line) {
        String completion = "";
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            String character = line.substring(i, i + 1);
            if(START_CHARS.contains(character)) {
                stack.add(character);
            } else if(END_CHARS.contains(character)) {
                String openingChar = stack.pop();
                switch (openingChar) {
                    case RUND_START:
                        if (!character.equals(RUND_ENDE)) {
                            return "";
                        }
                        break;
                    case ECKIG_START:
                        if (!character.equals(ECKIG_ENDE)) {
                            return "";
                        }
                        break;
                    case GESCHWEIFT_START:
                        if (!character.equals(GESCHWEIFT_ENDE)) {
                            return "";
                        }
                        break;
                    case SPITZ_START:
                        if (!character.equals(SPITZ_ENDE)) {
                            return "";
                        }
                        break;
                    default:
                        throw new IllegalStateException("Should not occur "+openingChar);
                }
            } else {
                throw new IllegalStateException("Should not occur "+character);
            }
        }
        if(!stack.empty()) {
            while (!stack.empty()) {
                String startChar = stack.pop();
                switch (startChar) {
                    case RUND_START:
                        completion+=RUND_ENDE;
                        break;
                    case ECKIG_START:
                        completion+=ECKIG_ENDE;
                        break;
                    case GESCHWEIFT_START:
                        completion+=GESCHWEIFT_ENDE;
                        break;
                    case SPITZ_START:
                        completion+=SPITZ_ENDE;
                        break;
                    default:
                        throw new IllegalStateException("Should not occur"+startChar);
                }
            }
        }
        System.out.println(completion);
        return completion;
    }

    private static long getScore(String completionString) {
        long score = 0;
        for (int i = 0; i < completionString.length(); i++) {
            String character = completionString.substring(i,i+1);
            score*=5;
            switch (character) {
                case RUND_ENDE:
                    score+=1;
                    break;
                case ECKIG_ENDE:
                    score+=2;
                    break;
                case GESCHWEIFT_ENDE:
                    score+=3;
                    break;
                case SPITZ_ENDE:
                    score+=4;
                    break;
                default:
                    throw new IllegalStateException("Should not occur"+character);
            }
        }
        return score;
    }
}