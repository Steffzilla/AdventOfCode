package de.steffzilla.aoc.y2021;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.competitive.Pair;
import de.steffzilla.competitive.Utils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021_6Test {

    static final String DAY = "06";

    @Test
    public void testLanternfishToString() {
        assertEquals("3", new Lanternfish(3).toString());
        Lanternfish fish = new Lanternfish(0);
        fish.age();
        assertEquals("6", fish.toString());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutionsAoC.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2021_6.solve(Utils.getStringList(Aoc2021_6.PATH));
        assertEquals(root.at("/adventOfCode/2021/day" + DAY + "/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2021/day" + DAY + "/part2").asText(), solutions.getValue1());
    }

}
