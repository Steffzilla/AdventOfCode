package de.steffzilla.aoc.y2024;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.competitive.Utils;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2024_13Test {

    @Test
    public void testSolveExample() {
        Pair<String, String> solutions = Aoc2024_13.solve(Aoc2024_13.example.lines().toList());
        assertEquals("480", solutions.getValue0());
        //assertEquals("x", solutions.getValue1());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2024_13.solve(Utils.getStringList(Aoc2024_13.PATH));
        assertEquals(root.at("/adventOfCode/2024/day13/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2024/day13/part2").asText(), solutions.getValue1());
    }

}