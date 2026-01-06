package de.steffzilla.aoc.y2024;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.competitive.AocUtils;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2024_10Test {

    static final String DAY = "10";
    static final String sample = """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
            """;
    static final String simpleExample = """
            8888808
            8843218
            8858828
            8865438
            1171148
            1187658
            1191111
            """;

    @Test
    public void testSolveSimpleExample() {
        Pair<String, String> solutions = Aoc2024_10.solve(simpleExample.lines().toList());
        assertEquals("3", solutions.getValue1());
    }

    @Test
    public void testSolveExample() {
        Pair<String, String> solutions = Aoc2024_10.solve(sample.lines().toList());
        assertEquals("36", solutions.getValue0());
        assertEquals("81", solutions.getValue1());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2024_10.solve(AocUtils.getStringList(Aoc2024_10.PATH));
        assertEquals(root.at("/adventOfCode/2024/day" + DAY + "/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2024/day" + DAY + "/part2").asText(), solutions.getValue1());
    }

}