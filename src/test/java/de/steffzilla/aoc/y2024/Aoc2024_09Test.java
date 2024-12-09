package de.steffzilla.aoc.y2024;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class Aoc2024_09Test {

    static final String sample = "2333133121414131402";

    @Test
    public void testSolveExample() {
        Pair<String, String> solutions = Aoc2024_09.solve(sample.lines().toList());
        assertEquals("1928", solutions.getValue0());
        //assertEquals("2858", solutions.getValue1());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2024_09.solve(AocUtils.getStringList(Aoc2024_09.PATH));
        assertEquals(root.at("/adventOfCode/2024/day9/part1").asText(), solutions.getValue0());
        //assertEquals(root.at("/adventOfCode/2024/day9/part2").asText(), solutions.getValue1());
    }

}