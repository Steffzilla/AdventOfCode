package de.steffzilla.aoc.y2022;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.competitive.Utils;
import de.steffzilla.competitive.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2022_21Test {

    static final String DAY = "21";
    static final String sample = """
            root: pppw + sjmn
            dbpl: 5
            cczh: sllz + lgvd
            zczc: 2
            ptdq: humn - dvpt
            dvpt: 3
            lfqf: 4
            humn: 5
            ljgn: 2
            sjmn: drzm * dbpl
            sllz: 4
            pppw: cczh / lfqf
            lgvd: ljgn * ptdq
            drzm: hmdt - zczc
            hmdt: 32
            """;


    @Test
    public void testSolveExample() {
        Pair<String, String> solutions = Aoc2022_21.solve(sample.lines().toList());
        assertEquals("152", solutions.getValue0());
        //assertEquals("301", solutions.getValue1());
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutionsAoC.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        Pair<String, String> solutions = Aoc2022_21.solve(Utils.getStringList(Aoc2022_21.PATH));
        assertEquals(root.at("/adventOfCode/2022/day" + DAY + "/part1").asText(), solutions.getValue0());
        //assertEquals(root.at("/adventOfCode/2022/day" + DAY + "/part2").asText(), solutions.getValue1());
    }

}