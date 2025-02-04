package de.steffzilla.aoc.y2024;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2024_11Test {

    static final String sample = "125 17";

    @Test
    public void testSolveSimpleCase() {
        Pair<String, String> solutions = Aoc2024_11.solve("0".lines().toList(), 7, 0);
        assertEquals("14", solutions.getValue0());
    }

    @Test
    public void testSolveExample() {
        Pair<String, String> solutions = Aoc2024_11.solve(sample.lines().toList(), 25, 75);
        assertEquals("55312", solutions.getValue0());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2024_11.solve(AocUtils.getStringList(Aoc2024_11.PATH), 25, 75);
        assertEquals(root.at("/adventOfCode/2024/day11/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2024/day11/part2").asText(), solutions.getValue1());
    }

}