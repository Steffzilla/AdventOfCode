package de.steffzilla.aoc.y2023;

import de.steffzilla.aoc.CharacterField;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class Aoc2023_13Test {

    @Test
    public void processColumnsPart2() {
    }

    //@Test // Part 2 is unsolved so far
    public void processRowsPart2() {
        String field = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.
            """;
        CharacterField characterField = new CharacterField(field);
        assertEquals(300, Aoc2023_13.processRowsPart2(characterField));
        String field2 = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.
            """;
        CharacterField characterField2 = new CharacterField(field2);
        assertEquals(100, Aoc2023_13.processRowsPart2(characterField2));
    }
}