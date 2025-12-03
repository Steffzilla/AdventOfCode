package de.steffzilla.aoc.y2025;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2025_03Test {

    static final String DAY = "03";
    static final String sample = """
            987654321111111
            811111111111119
            234234234234278
            818181911112111
            """;

    @Test
    public void testGetBiggestNumber() {
        assertEquals(98, Aoc2025_03.getBiggestNumber("987654321111111", 2));
        assertEquals(987654321111L, Aoc2025_03.getBiggestNumber("987654321111111", 12));
    }

    @Test
    public void testSolveExample() {
        Pair<String, String> solutions = Aoc2025_03.solve(sample.lines().toList());
        assertEquals("357", solutions.getValue0());
        assertEquals("3121910778619", solutions.getValue1());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2025_03.solve(AocUtils.getStringList(Aoc2025_03.PATH));
        assertEquals(root.at("/adventOfCode/2025/day" + DAY + "/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2025/day" + DAY + "/part2").asText(), solutions.getValue1());
    }

}