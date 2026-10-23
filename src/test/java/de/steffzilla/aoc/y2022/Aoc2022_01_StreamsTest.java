package de.steffzilla.aoc.y2022;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.competitive.Pair;
import de.steffzilla.competitive.Utils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2022_01_StreamsTest {

    static final String DAY = "01";

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutionsAoC.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        // part 2 is not implemented in this variant
        Pair<String, String> solutions = Aoc2022_01_Streams.solve(Utils.getStringList(Aoc2022_01_Streams.PATH));
        assertEquals(root.at("/adventOfCode/2022/day" + DAY + "/part1").asText(), solutions.getValue0());
    }

}
