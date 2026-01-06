package de.steffzilla.aoc.y2025;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.competitive.AocUtils;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Aoc2025_10Test {

    static final String DAY = "10";

    @Test
    void testSolveExample() {
        Pair<String, String> solutions = Aoc2025_10.solve(Aoc2025_10.example.lines().toList());
        assertEquals("7", solutions.getValue0());
        assertEquals("33", solutions.getValue1());
    }

    @Test
    void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2025_10.solve(AocUtils.getStringList(Aoc2025_10.PATH));
        assertEquals(root.at("/adventOfCode/2025/day" + DAY + "/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2025/day" + DAY + "/part2").asText(), solutions.getValue1());
    }

    @Test
    void getLightTargetState() {
        assertEquals(Integer.parseInt("0110", 2), Aoc2025_10.getLightTargetState(".##."));
        assertEquals(Integer.parseInt("00010", 2), Aoc2025_10.getLightTargetState("...#."));
        assertEquals(Integer.parseInt("011101", 2), Aoc2025_10.getLightTargetState(".###.#"));
        assertEquals(Integer.parseInt("111111", 2), Aoc2025_10.getLightTargetState("######"));
        assertEquals(Integer.parseInt("1000010111", 2), Aoc2025_10.getLightTargetState("#....#.###"));
    }

    @Test
    void testGetWiringAsNumbers() {
        List<int[]> binary = new ArrayList<>();
        binary.add(new int[]{0, 1, 0, 1});
        binary.add(new int[]{1, 1, 1, 1});
        binary.add(new int[]{0, 0, 1});
        assertEquals(List.of(5, 15, 1), Aoc2025_10.getWiringAsNumbers(binary));
    }

}