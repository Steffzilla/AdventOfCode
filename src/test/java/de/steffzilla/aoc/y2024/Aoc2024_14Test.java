package de.steffzilla.aoc.y2024;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.competitive.AocUtils;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2024_14Test {

    static final String sample = """
            p=0,4 v=3,-3
            p=6,3 v=-1,-3
            p=10,3 v=-1,2
            p=2,0 v=2,-1
            p=0,0 v=1,3
            p=3,0 v=-2,-2
            p=7,6 v=-1,-3
            p=3,0 v=-1,-2
            p=9,3 v=2,3
            p=7,3 v=-1,2
            p=2,4 v=2,-3
            p=9,5 v=-3,-3
            """;

    static final String test = """
            p=0,0 v=1,0
            p=0,1 v=-1,0
            p=0,3 v=0,1
            p=0,4 v=0,-1
            p=1,1 v=2,2
            p=3,3 v=-2,-2
            p=5,5 v=0,0
            """;

    static final String testQ = """
            p=0,0 v=0,0
            p=1,1 v=0,0
            p=10,0 v=0,0
            p=9,1 v=0,0
            p=0,6 v=0,0
            p=1,5 v=0,0
            p=10,6 v=0,0
            p=9,5 v=0,0
            p=5,3 v=0,0
            """;

    static final String testQExample = """
            p=0,2 v=0,0
            p=6,0 v=0,0
            p=6,0 v=0,0
            p=9,0 v=0,0
            p=3,5 v=0,0
            p=4,5 v=0,0
            p=4,5 v=0,0
            p=1,6 v=0,0
            p=6,6 v=0,0
            """;

    @Test
    public void testQuadrants() {
        Aoc2024_14.fieldDimensionX = 11;
        Aoc2024_14.fieldDimensionY = 7;
        Pair<String, String> solutions = Aoc2024_14.solve(testQ.lines().toList());
        assertEquals("16", solutions.getValue0());
        solutions = Aoc2024_14.solve(testQExample.lines().toList());
        assertEquals("12", solutions.getValue0());
    }

    @Test
    public void testSolveExample() {
        Aoc2024_14.fieldDimensionX = 11;
        Aoc2024_14.fieldDimensionY = 7;
        Pair<String, String> solutions = Aoc2024_14.solve(sample.lines().toList());
        assertEquals("12", solutions.getValue0());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2024_14.solve(AocUtils.getStringList(Aoc2024_14.PATH));
        assertEquals(root.at("/adventOfCode/2024/day14/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2024/day14/part2").asText(), solutions.getValue1());
    }

}