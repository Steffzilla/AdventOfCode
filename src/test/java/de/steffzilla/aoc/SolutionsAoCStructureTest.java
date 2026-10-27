package de.steffzilla.aoc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolutionsAoCStructureTest {

    private static final Set<String> OBSOLETE_PLACEHOLDERS = Set.of("?", "y", "c", "xxx", "todo");

    private static JsonNode root;

    @BeforeAll
    static void loadSolutions() throws IOException {
        try (InputStream inputStream = SolutionsAoCStructureTest.class.getClassLoader()
                .getResourceAsStream("solutionsAoC.json")) {
            root = new ObjectMapper().readTree(inputStream);
        }
    }

    @ParameterizedTest
    @CsvSource({"2019, 25", "2020, 25", "2021, 25", "2022, 25", "2023, 25", "2024, 25", "2025, 12"})
    public void testYearHasAllDays(String year, int numberOfDays) {
        JsonNode yearNode = root.at("/adventOfCode/" + year);
        assertEquals(numberOfDays, yearNode.size());
        for (int day = 1; day <= numberOfDays; day++) {
            String dayKey = "day" + String.format("%02d", day);
            JsonNode dayNode = yearNode.get(dayKey);
            assertEquals(2, dayNode.size(), year + "/" + dayKey);
            for (String part : new String[]{"part1", "part2"}) {
                assertTrue(dayNode.has(part), year + "/" + dayKey + "/" + part);
                assertFalse(OBSOLETE_PLACEHOLDERS.contains(dayNode.get(part).asText()), year + "/" + dayKey + "/" + part);
            }
        }
        String lastDayKey = "day" + String.format("%02d", numberOfDays);
        assertEquals("NONE", yearNode.at("/" + lastDayKey + "/part2").asText());
    }

}
