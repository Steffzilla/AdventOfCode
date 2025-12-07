package de.steffzilla.aoc.y2025;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2025_06Test {

    static final String DAY = "06";
    static final String sample = """
            123 328  51 64\s
             45 64  387 23\s
              6 98  215 314
            *   +   *   + \s
            """;


    @Test
    public void testSolveExample() {
        Pair<String, String> solutions = Aoc2025_06.solve(sample.lines().toList());
        //assertEquals("4277556", solutions.getValue0());//TODO
        assertEquals("3263827", solutions.getValue1());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2025_06.solve(AocUtils.getStringList(Aoc2025_06.PATH));
        //assertEquals(root.at("/adventOfCode/2025/day" + DAY + "/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2025/day" + DAY + "/part2").asText(), solutions.getValue1());
    }

}