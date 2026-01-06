package de.steffzilla.aoc.y2025;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.competitive.AocUtils;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2025_11Test {

    static final String DAY = "11";

    @Test
    public void testSolveExample() {
        Pair<String, String> solutions = Aoc2025_11.solve(Aoc2025_11.examplePart1.lines().toList());
        assertEquals("5", solutions.getValue0());
        solutions = Aoc2025_11.solve(Aoc2025_11.examplePart2.lines().toList());
        assertEquals("2", solutions.getValue1());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2025_11.solve(AocUtils.getStringList(Aoc2025_11.PATH));
        assertEquals(root.at("/adventOfCode/2025/day" + DAY + "/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2025/day" + DAY + "/part2").asText(), solutions.getValue1());
    }

}