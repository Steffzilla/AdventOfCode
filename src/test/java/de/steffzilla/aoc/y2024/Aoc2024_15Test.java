package de.steffzilla.aoc.y2024;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.aoc.AocUtils;
import de.steffzilla.aoc.CharacterField;
import org.javatuples.Pair;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class Aoc2024_15Test {

    static final String smallSample = """
            ########
            #..O.O.#
            ##@.O..#
            #...O..#
            #.#.O..#
            #...O..#
            #......#
            ########
            
            <^^>>>vv<v>>v<<
            """;
    static final String sample = """
            ##########
            #..O..O.O#
            #......O.#
            #.OO..O.O#
            #..O@..O.#
            #O#..O...#
            #O..O..O.#
            #.OO.O.OO#
            #....O...#
            ##########
            
            <vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^
            vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v
            ><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<
            <<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^
            ^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><
            ^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^
            >^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^
            <><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>
            ^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>
            v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^
            """;

    static final String resultingFieldSmallSamplePart1 = """
            ########
            #....OO#
            ##.....#
            #.....O#
            #.#O@..#
            #...O..#
            #...O..#
            ########
            """;
    static final String resultingFieldSamplePart1 = """
            ##########
            #.O.O.OOO#
            #........#
            #OO......#
            #OO@.....#
            #O#.....O#
            #O.....OO#
            #O.....OO#
            #OO....OO#
            ##########
            """;

    @Test
    public void testSumOfGPSCoordinates() {
        CharacterField cf = new CharacterField(resultingFieldSmallSamplePart1);
        assertEquals(2028, Aoc2024_15.sumOfGPSCoordinates(cf));
        cf = new CharacterField(resultingFieldSamplePart1);
        assertEquals(10092, Aoc2024_15.sumOfGPSCoordinates(cf));
    }

    @Test
    public void testSolveSmallExample() {
        Pair<String, String> solutions = Aoc2024_15.solve(smallSample.lines().toList());
        assertEquals("2028", solutions.getValue0());
        //assertEquals("6", solutions.getValue1());
    }

    @Test
    public void testSolveExample() {
        Pair<String, String> solutions = Aoc2024_15.solve(sample.lines().toList());
        assertEquals("10092", solutions.getValue0());
        //assertEquals("9021", solutions.getValue1());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutions.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2024_15.solve(AocUtils.getStringList(Aoc2024_15.PATH));
        assertEquals(root.at("/adventOfCode/2024/day15/part1").asText(), solutions.getValue0());
        //assertEquals(root.at("/adventOfCode/2024/day15/part2").asText(), solutions.getValue1());
    }

}