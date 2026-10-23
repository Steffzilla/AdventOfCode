package de.steffzilla.aoc.y2023;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.competitive.Pair;
import de.steffzilla.competitive.Utils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2023_10Test {

    static final String DAY = "10";

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutionsAoC.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2023_10.solve(Utils.getStringList(Aoc2023_10.PATH));
        assertEquals(root.at("/adventOfCode/2023/day" + DAY + "/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2023/day" + DAY + "/part2").asText(), solutions.getValue1());
    }

}
