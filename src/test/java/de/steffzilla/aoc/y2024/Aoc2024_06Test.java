package de.steffzilla.aoc.y2024;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class Aoc2024_06Test {

    static final String sample = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
            """;

    @Test
    public void testSolveExample() {
        Pair<String, String> solutions = Aoc2024_06.solve(sample.lines().toList());
        assertEquals("41", solutions.getValue0());
        assertEquals("6", solutions.getValue1());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2024_06.solve(AocUtils.getStringList(Aoc2024_06.PATH));
        assertEquals(root.at("/adventOfCode/2024/day06/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2024/day06/part2").asText(), solutions.getValue1());
    }

}