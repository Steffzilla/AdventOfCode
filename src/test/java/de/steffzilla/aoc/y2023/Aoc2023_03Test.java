package de.steffzilla.aoc.y2023;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.competitive.CharacterField;
import de.steffzilla.competitive.Pair;
import de.steffzilla.competitive.Utils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2023_03Test {

    static final String DAY = "03";

    private static final String field1 = """
        467.
        ...*
        .135
        """;

    private static final String field2 = """
        ..........
        ......755.
        ...$.*....
        .664.598..
        """;

    private static final String field3 = """
            *1432.....*****....................971*
            2.......#43*715..723...56...81.12......
            ..........*****...............*.....971
            """;

    private static final String field4 = """
            902.........837...........
            .11*.*92.......*770..*991*
            ....................77....
            .*7.3*..................9*
            2...4....................2
            ..4.......................
            .*........................
            5.........................
            """;

    private static final String field5 = """
            *..42...13*687...*.........934
            #...............3.22...116*...
            5.............................
            *............................6
            8...........................4*
            """;


    @Test
    public void testGetGearRatio() {
        Aoc2023_03.field = new CharacterField(field1);
        assertEquals(63045, Aoc2023_03.getGearRatio(3,1));
        Aoc2023_03.field = new CharacterField(field2);
        assertEquals(451490, Aoc2023_03.getGearRatio(5,2));
        Aoc2023_03.field = new CharacterField(field3);
        assertEquals(2864, Aoc2023_03.getGearRatio(0, 0));
        assertEquals(30745, Aoc2023_03.getGearRatio(11, 1));
        assertEquals(972, Aoc2023_03.getGearRatio(30, 2));
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutionsAoC.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2023_03.solve(Utils.getStringList(Aoc2023_03.PATH));
        assertEquals(root.at("/adventOfCode/2023/day" + DAY + "/part1").asText(), solutions.getValue0());
        assertEquals(root.at("/adventOfCode/2023/day" + DAY + "/part2").asText(), solutions.getValue1());
    }

}