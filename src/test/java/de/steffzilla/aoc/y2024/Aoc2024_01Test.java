package de.steffzilla.aoc.y2024;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class Aoc2024_01Test {

    static final String DAY = "01";

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2024_01.solve(AocUtils.getStringList(Aoc2024_01.PATH));
        assertEquals(root.at("/adventOfCode/2024/day" + DAY + "/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2024/day" + DAY + "/part2").asText(), solutions.getValue1());
    }

}