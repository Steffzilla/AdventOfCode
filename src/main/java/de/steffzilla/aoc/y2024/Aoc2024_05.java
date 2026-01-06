package de.steffzilla.aoc.y2024;

import de.steffzilla.competitive.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Aoc2024_05 {

    private static final String DAY = "05";
    private static final String YEAR = "2024";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    //public static final String FILENAME = "sample" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    private static HashMap<Integer, List<Integer>> map;
    private static List<List<Integer>> incorrectOrder;

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        List<String> inputLines = Utils.getStringList(PATH);

        map = new HashMap<>();
        incorrectOrder = new ArrayList<>();

        part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines) {
        boolean orderingRules = true;
        long count = 0;
        for (String line : inputLines) {
            if (line.isEmpty()) {
                orderingRules = false;
                continue;
            }
            if (orderingRules) {
                readPageOrderingRule(line);
            } else {
                List<Integer> pageNumbers = Arrays.stream(line.split(","))
                        .mapToInt(Integer::parseInt)
                        .boxed() // Convert to Stream<Integer>
                        .toList(); // Collect into a List;
                if (isOrderOk(pageNumbers)) {
                    count += pageNumbers.get(pageNumbers.size() / 2);
                } else {
                    incorrectOrder.add(pageNumbers);
                }

            }
        }
        System.out.println("\nPart 1 > Result: " + count);
    }

    private static boolean isOrderOk(List<Integer> pageNumbers) {
        for (int indexOfCurrentPage = 0; indexOfCurrentPage < pageNumbers.size(); indexOfCurrentPage++) {
            Integer currentPageNumber = pageNumbers.get(indexOfCurrentPage);
            List<Integer> rulesForPage = map.getOrDefault(currentPageNumber, new ArrayList<>());
            // check pages before
            for (int indexOtherPage = 0; indexOtherPage < indexOfCurrentPage; indexOtherPage++) {
                if (rulesForPage.contains(pageNumbers.get(indexOtherPage))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void readPageOrderingRule(String line) {
        String[] parts = line.split("\\|");
        int currentPage = Integer.parseInt(parts[0]);
        List<Integer> pages = map.get(currentPage);
        if (pages == null) {
            pages = new ArrayList<>();
            map.put(currentPage, pages);
        }
        pages.add(Integer.parseInt(parts[1]));
    }

    private static List<Integer> getCorrectOrderRecursive(List<Integer> candidatesForNextPage, int indexToVariate, List<Integer> soFarCorrectCandidate) {
        for (int indexOfCurrentPageCandidate = 0; indexOfCurrentPageCandidate < candidatesForNextPage.size(); indexOfCurrentPageCandidate++) {
            List<Integer> newCandidateForCorrect = new ArrayList<>(soFarCorrectCandidate);
            newCandidateForCorrect.add(candidatesForNextPage.get(indexOfCurrentPageCandidate));
            if (isOrderOk(newCandidateForCorrect)) {
                List<Integer> remainingCandidatesForNextPage = new ArrayList<>();
                for (int j = 0; j < candidatesForNextPage.size(); j++) {
                    if (j != indexOfCurrentPageCandidate) {
                        remainingCandidatesForNextPage.add(candidatesForNextPage.get(j));
                    }
                }
                if (remainingCandidatesForNextPage.isEmpty()) {
                    return soFarCorrectCandidate; // finally correct order
                } else {
                    List<Integer> result = getCorrectOrderRecursive(remainingCandidatesForNextPage, indexToVariate + 1, newCandidateForCorrect);
                    if (result == null) {
                        continue;
                    }
                    return result;
                }
            } else {
                continue;
            }
        }
        return null;
    }

    private static void part2(List<String> inputLines) {
        long count = 0;

        for (List<Integer> incorrectLine : incorrectOrder) {
            List<Integer> correctOrder = getCorrectOrderRecursive(incorrectLine, 0, new ArrayList<>());
            count += correctOrder.get(correctOrder.size() / 2);
        }
        System.out.println("\nPart 2 > Result: " + count);
    }

}