package de.steffzilla.aoc.y2025;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.competitive.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2025_05Test {

    static final String DAY = "05";
    static final String sample = """
            3-5
            10-14
            16-20
            12-18
            
            1
            5
            8
            11
            17
            32
            """;
    static final String sample2 = """
            3-5
            10-14
            16-20
            12-18
            3-4
            8-8
            2950642929711-7840509071693
            84231261602243-85623539892872
            264589915959325-264589915959325
            8223372036854775807-9223372036854775807
            
            1
            5
            8
            9
            11
            14
            17
            32
            16
            2950642929718
            264589915959325
            8223372036854775806
            8223372036854775807
            9223372036854775806
            """;//Long.MAX_VALUE (9223372036854775807) doesn't work, yet the input doesn't contain it

    static final String sample3 = """
            10-14
            13-17
            16-20
            
            9
            12
            13
            14
            15
            16
            17
            20
            21
            """;

    static final String sample4 = """
            50-200
            80-150
            100-120
            
            50
            80
            90
            110
            150
            200
            201
            """;

    static final String sample5 = """
            5-10
            11-20
            105-110
            110-120
            1000000000000-1000000000000
            99999999999999-99999999999999
            
            10
            11
            12
            110
            999999999999
            1000000000000
            1000000000001
            99999999999998
            99999999999999
            100000000000000
            """;

    static final String sample6 = """
            5-10
            5-10
            5-10
            
            5
            7
            10
            """;

    static final String sample7 = """
            100-101
            101-102
            102-103
            103-104
            104-105
            105-106
            106-107
            
            99
            100
            101
            102
            103
            104
            105
            106
            107
            108
            """;

    static final String sample8 = """
            0-1000000000000000000
            500000000000000000-500000000000000000
            999999999999999999-999999999999999999
            
            1
            500000000000000000
            999999999999999999
            """;

    static final String duplicateStartTest = """
            10-15
            10-20
            10-25
            10-30
            20-25
            30-35
            
            9
            10
            15
            20
            25
            30
            31
            32
            36
            """;

    @Test
    public void testDuplicateStartIntervals() {
        Pair<String, String> solutions = Aoc2025_05.solve(duplicateStartTest.lines().toList());
        assertEquals("7", solutions.getValue0());
    }

    @Test
    public void testSolveExample() {
        Pair<String, String> solutions = Aoc2025_05.solve(sample.lines().toList());
        assertEquals("3", solutions.getValue0());
        //assertEquals("6", solutions.getValue1());
    }

    @Test
    public void testSolveExample2() {
        Pair<String, String> solutions = Aoc2025_05.solve(sample2.lines().toList());
        assertEquals("10", solutions.getValue0());
        //assertEquals("6", solutions.getValue1());
    }

    @Test
    public void testSolveExample3() {
        Pair<String, String> solutions = Aoc2025_05.solve(sample3.lines().toList());
        assertEquals("7", solutions.getValue0());
        //assertEquals("6", solutions.getValue1());
    }

    @Test
    public void testSolveExample4() {
        Pair<String, String> solutions = Aoc2025_05.solve(sample4.lines().toList());
        assertEquals("6", solutions.getValue0());
        //assertEquals("6", solutions.getValue1());
    }

    @Test
    public void testSolveExample5() {
        Pair<String, String> solutions = Aoc2025_05.solve(sample5.lines().toList());
        assertEquals("6", solutions.getValue0());
        //assertEquals("6", solutions.getValue1());
    }

    @Test
    public void testSolveExample6() {
        Pair<String, String> solutions = Aoc2025_05.solve(sample6.lines().toList());
        assertEquals("3", solutions.getValue0());
        //assertEquals("6", solutions.getValue1());
    }

    @Test
    public void testSolveExample7() {
        Pair<String, String> solutions = Aoc2025_05.solve(sample7.lines().toList());
        assertEquals("8", solutions.getValue0());
        //assertEquals("6", solutions.getValue1());
    }

    @Test
    public void testSolveExample8() {
        Pair<String, String> solutions = Aoc2025_05.solve(sample8.lines().toList());
        assertEquals("3", solutions.getValue0());
        //assertEquals("6", solutions.getValue1());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutionsAoC.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        //Pair<String, String> solutions = Aoc2025_05.solve(Utils.getStringList(Aoc2025_05.PATH));
        //assertEquals(root.at("/adventOfCode/2025/day" + DAY + "/part1").asText(), solutions.getValue0());
        //assertEquals(root.at("/adventOfCode/2025/day" + DAY + "/part2").asText(), solutions.getValue1());
    }

}