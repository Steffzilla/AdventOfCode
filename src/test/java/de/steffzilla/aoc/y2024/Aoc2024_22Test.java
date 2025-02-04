package de.steffzilla.aoc.y2024;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.aoc.InputUtils;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class Aoc2024_22Test {

    static final String DAY = "22";
    static final String sample = """
            1
            10
            100
            2024
            """;

    @Test
    public void testMixAndPrune() {
        assertEquals(37, Aoc2024_22.mix(42, 15));
        assertEquals(1013579214, Aoc2024_22.mix(15887950, (15887950 * 64)));
        assertEquals(1041709600, Aoc2024_22.mix(16495136, (16495136 * 64)));
        assertEquals(16113920, Aoc2024_22.prune(100000000));
        assertEquals(10195840, Aoc2024_22.prune(1016828800));
        assertEquals(6946254, Aoc2024_22.prune(1013579214));
    }

    @Test
    public void testCalculateNextSecretNumber() {
        long nextNumber = Aoc2024_22.calculateNextSecretNumber(123);
        assertEquals(15887950, nextNumber);

        nextNumber = Aoc2024_22.calculateNextSecretNumber(15887950);
        assertEquals(16495136, nextNumber);

        nextNumber = Aoc2024_22.calculateNextSecretNumber(16495136);
        assertEquals(527345, nextNumber);

        nextNumber = 123;
        for (int i = 0; i < 10; i++) {
            nextNumber = Aoc2024_22.calculateNextSecretNumber(nextNumber);
        }
        assertEquals(5908254, nextNumber);
    }

    @Test
    public void testSolveExample() {
        Pair<String, String> solutions = Aoc2024_22.solve(sample.lines().mapToLong(Long::valueOf).boxed().toList());
        assertEquals("37327623", solutions.getValue0());
        //assertEquals("23", solutions.getValue1());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions =
                Aoc2024_22.solve(InputUtils.getListOfLongFromFile(Aoc2024_22.BASEDIR + Aoc2024_22.INPUT_FILENAME));
        assertEquals(root.at("/adventOfCode/2024/day" + DAY + "/part1").asText(), solutions.getValue0());
        //assertEquals(root.at("/adventOfCode/2024/day" + DAY + "/part2").asText(), solutions.getValue1());
    }

}