package de.steffzilla.aoc.y2023;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.competitive.Utils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2023_25Test {

    static final String DAY = "25";

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutionsAoC.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        assertEquals(root.at("/adventOfCode/2023/day" + DAY + "/part1").asText(),
                Aoc2023_25.solve(Utils.getStringList(Aoc2023_25.PATH)));
    }

}
