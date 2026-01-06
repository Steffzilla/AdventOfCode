package de.steffzilla.aoc.y2024;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.competitive.AocUtils;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2024_08Test {

    static final String sample = """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............
            """;

    @Test
    public void testSolveExample() {
        Pair<String, String> solutions = Aoc2024_08.solve(sample.lines().toList());
        assertEquals("14", solutions.getValue0());
        assertEquals("34", solutions.getValue1());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2024_08.solve(AocUtils.getStringList(Aoc2024_08.PATH));
        assertEquals(root.at("/adventOfCode/2024/day08/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2024/day08/part2").asText(), solutions.getValue1());
    }

}