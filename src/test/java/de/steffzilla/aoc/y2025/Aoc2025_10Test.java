package de.steffzilla.aoc.y2025;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.aoc.AocUtils;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2025_10Test {

    static final String DAY = "10";

    @Test
    void testSolveExample() {
        Pair<String, String> solutions = Aoc2025_10.solve(Aoc2025_10.example.lines().toList());
        assertEquals("7", solutions.getValue0());
        //assertEquals("x", solutions.getValue1());
    }

    @Test
    void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2025_10.solve(AocUtils.getStringList(Aoc2025_10.PATH));
        assertEquals(root.at("/adventOfCode/2025/day" + DAY + "/part1").asText(), solutions.getValue0());
        //assertEquals(root.at("/adventOfCode/2025/day" + DAY + "/part2").asText(), solutions.getValue1());
    }

    @Test
    void getButtonWiringList() {
        assertEquals(List.of(Integer.parseInt("0001", 2)),
                Aoc2025_10.getButtonWiringList(4,"(3)"));
        List<Integer> results = List.of(
                Integer.parseInt("0001", 2),
                Integer.parseInt("0101", 2),
                Integer.parseInt("0010", 2),
                Integer.parseInt("0011", 2),
                Integer.parseInt("1010", 2),
                Integer.parseInt("1100", 2)
                );
        assertEquals(results,
                Aoc2025_10.getButtonWiringList(4,"(3) (1,3) (2) (2,3) (0,2) (0,1)"));
        results = List.of(
                Integer.parseInt("111110", 2),
                Integer.parseInt("100110", 2),
                Integer.parseInt("111011", 2),
                Integer.parseInt("011000", 2)
        );
        assertEquals(results,
                Aoc2025_10.getButtonWiringList(6,"(0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2)"));
    }

    @Test
    void getLightTargetState() {
        assertEquals(Integer.parseInt("0110",2), Aoc2025_10.getLightTargetState(".##."));
        assertEquals(Integer.parseInt("00010",2), Aoc2025_10.getLightTargetState("...#."));
        assertEquals(Integer.parseInt("011101",2), Aoc2025_10.getLightTargetState(".###.#"));
        assertEquals(Integer.parseInt("111111", 2),Aoc2025_10.getLightTargetState("######"));
        assertEquals(Integer.parseInt("1000010111", 2),Aoc2025_10.getLightTargetState("#....#.###"));
    }
}