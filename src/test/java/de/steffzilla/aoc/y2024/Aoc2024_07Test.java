package de.steffzilla.aoc.y2024;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.competitive.AocUtils;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2024_07Test {

    static final String sample = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
            """;

    @Test
    public void testSolveExample() {
        Pair<String, String> solutions = Aoc2024_07.solve(sample.lines().toList());
        assertEquals("3749", solutions.getValue0());
        assertEquals("11387", solutions.getValue1());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2024_07.solve(AocUtils.getStringList(Aoc2024_07.PATH));
        assertEquals(root.at("/adventOfCode/2024/day07/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2024/day07/part2").asText(), solutions.getValue1());
    }

}