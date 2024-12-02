package de.steffzilla.aoc.y2020;

import de.steffzilla.aoc.AocUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Aoc2020_6 {

    private static final String DAY = "06";
    private static final String YEAR = "2020";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        List<String> answers = AocUtils.getStringList(PATH);
        int subOfGroupCounts = getSumOfGroupCountsPart2(answers);
        System.out.println("\nResult:" + subOfGroupCounts);
    }

    private static int getSumOfGroupCountsPart2(List<String> answers) {
        int subOfGroupCounts = 0;
        HashMap<String, Integer> groupAnswers = new HashMap<>();
        int groupSize = 0;
        for (String answer : answers) {
            if (answer.isEmpty()) {
                System.out.println(groupAnswers);
                for (String letter : groupAnswers.keySet()) {
                    Integer number = groupAnswers.get(letter);
                    if (number.intValue() == groupSize) {
                        System.out.println(letter+" was answered by everyone");
                        subOfGroupCounts++;
                    }
                }
                groupAnswers.clear();
                groupSize = 0;
            } else {
                groupSize++;
                for (int i = 0; i < answer.length(); i++) {
                    String letter = answer.substring(i, i + 1);
                    Integer number = groupAnswers.get(letter);
                    if (number==null) {
                        groupAnswers.put(letter, Integer.valueOf(1));
                    } else {
                        groupAnswers.put(letter, Integer.valueOf(number.intValue() + 1));
                    }
                }
            }
        }

        // last group
        System.out.println(groupAnswers.toString());
        for (String letter : groupAnswers.keySet()) {
            Integer number = groupAnswers.get(letter);
            if (number.intValue() == groupSize) {
                System.out.println(letter+" was answered by everyone");
                subOfGroupCounts++;
            }
        }

        return subOfGroupCounts;
    }

    private static int getSumOfGroupCountsPart1(List<String> answers) {
        int subOfGroupCounts = 0;
        Set<String> groupAnswers = new HashSet<>();
        for (String answer : answers) {
            if(answer.isEmpty()) {
                System.out.println(groupAnswers.toString()+"|"+groupAnswers.size());
                subOfGroupCounts += groupAnswers.size();
                groupAnswers.clear();
            }
            for (int i = 0; i < answer.length(); i++) {
                groupAnswers.add(answer.substring(i, i+1));
            }
        }
        System.out.println(groupAnswers.toString()+"|"+groupAnswers.size());
        subOfGroupCounts += groupAnswers.size();
        return subOfGroupCounts;
    }
}
