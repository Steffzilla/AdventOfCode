package de.steffzilla.aoc.y2023;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.competitive.Utils;
import de.steffzilla.competitive.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Aoc2023_07Test {

    static final String DAY = "07";

    @Test
    public void testCardHandConstruction() {
        Aoc2023_07.CardHand a2345 = new Aoc2023_07.CardHand("A2345", 23);
        assertEquals(Aoc2023_07.RESULT_TYPE.HIGH_CARD, a2345.resultType);
        Aoc2023_07.CardHand a3445 = new Aoc2023_07.CardHand("A3445", 23);
        assertEquals(Aoc2023_07.RESULT_TYPE.ONE_PAIR, a3445.resultType);
        Aoc2023_07.CardHand aatt2 = new Aoc2023_07.CardHand("AATT2", 23);
        assertEquals(Aoc2023_07.RESULT_TYPE.TWO_PAIRS, aatt2.resultType);
        Aoc2023_07.CardHand aaaaa = new Aoc2023_07.CardHand("AAAAA", 23);
        assertEquals(Aoc2023_07.RESULT_TYPE.FIVE_OF_A_KIND, aaaaa.resultType);
        Aoc2023_07.CardHand aaaab = new Aoc2023_07.CardHand("AAAAB", 23);
        assertEquals(Aoc2023_07.RESULT_TYPE.FOUR_OF_A_KIND, aaaab.resultType);
        Aoc2023_07.CardHand aaabb = new Aoc2023_07.CardHand("AAABB", 23);
        assertEquals(Aoc2023_07.RESULT_TYPE.FULL_HOUSE, aaabb.resultType);
        Aoc2023_07.CardHand aaab2 = new Aoc2023_07.CardHand("AAAB2", 23);
        assertEquals(Aoc2023_07.RESULT_TYPE.THREE_OF_A_KIND, aaab2.resultType);
    }

    @Test
    public void testCompare() {
        Aoc2023_07.CardHand aaaaa = new Aoc2023_07.CardHand("AAAAA", 23);
        Aoc2023_07.CardHand aaaak = new Aoc2023_07.CardHand("AAAAK", 23);
        Aoc2023_07.CardHand aaaa9 = new Aoc2023_07.CardHand("AAAA9", 23);
        Aoc2023_07.CardHand a9aaa = new Aoc2023_07.CardHand("A9AAA", 325423);
        Aoc2023_07.CardHand a2345 = new Aoc2023_07.CardHand("A2345", 23);
        Aoc2023_07.CardHand a2345_2 = new Aoc2023_07.CardHand("A2345", 43);
        assertEquals(1, aaaaa.compareTo(aaaak));
        assertTrue(a2345.compareTo(aaaak) < 0);
        assertEquals(0, a2345.compareTo(a2345_2));
        assertTrue(aaaa9.compareTo(aaaak) < 0);
        assertTrue(aaaa9.compareTo(a9aaa) > 0);
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutionsAoC.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2023_07.solve(Utils.getStringList(Aoc2023_07.PATH));
        assertEquals(root.at("/adventOfCode/2023/day" + DAY + "/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2023/day" + DAY + "/part2").asText(), solutions.getValue1());
    }

}