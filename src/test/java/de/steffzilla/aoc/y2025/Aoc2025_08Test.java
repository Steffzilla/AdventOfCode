package de.steffzilla.aoc.y2025;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2025_08Test {

    static final String DAY = "08";

    @Test
    public void testSolveExample() {
        Pair<String, String> solutions =
                Aoc2025_08.solve(Aoc2025_08.example.lines().toList(), Aoc2025_08.SAMPLE_MAX_CONNECTIONS_PART1);
        assertEquals("40", solutions.getValue0());
        assertEquals("25272", solutions.getValue1());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions =
                Aoc2025_08.solve(AocUtils.getStringList(Aoc2025_08.PATH), Aoc2025_08.REAL_MAX_CONNECTIONS_PART1);
        assertEquals(root.at("/adventOfCode/2025/day" + DAY + "/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2025/day" + DAY + "/part2").asText(), solutions.getValue1());
    }

}