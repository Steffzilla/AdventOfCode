package de.steffzilla.aoc.y2024;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Aoc2024_18Test {

    static final String DAY = "18";

    @Test
    public void testSolveExample() {
        List<String> lines = Aoc2024_18.initialize(false);
        Pair<String, String> solutions = Aoc2024_18.solve(lines);
        assertEquals("22", solutions.getValue0());
        assertEquals("6,1", solutions.getValue1());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        List<String> lines = Aoc2024_18.initialize(true);
        Pair<String, String> solutions = Aoc2024_18.solve(lines);
        assertEquals(root.at("/adventOfCode/2024/day" + DAY + "/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2024/day" + DAY + "/part2").asText(), solutions.getValue1());
    }

}