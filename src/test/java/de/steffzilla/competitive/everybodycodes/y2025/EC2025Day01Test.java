package de.steffzilla.competitive.everybodycodes.y2025;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.competitive.Utils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EC2025Day01Test {

    static final String DAY = "01";

    @Test
    void testSolveExample() {
        assertEquals("Fyrryn", EC2025Day01.part1(EC2025Day01.EXAMPLE.lines().toList()));
        assertEquals("Elarzris", EC2025Day01.part2(EC2025Day01.EXAMPLE.lines().toList()));
        assertEquals("Drakzyph", EC2025Day01.part3(EC2025Day01.EXAMPLE3.lines().toList()));
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutionsEC.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        assertEquals(root.at("/EverybodyCodes/2025/day" + DAY + "/part1").asText(),
                EC2025Day01.part1(Utils.getStringList(EC2025Day01.PATH + "1.txt")));
        assertEquals(root.at("/EverybodyCodes/2025/day" + DAY + "/part2").asText(),
                EC2025Day01.part2(Utils.getStringList(EC2025Day01.PATH + "2.txt")));
        assertEquals(root.at("/EverybodyCodes/2025/day" + DAY + "/part3").asText(),
                EC2025Day01.part3(Utils.getStringList(EC2025Day01.PATH + "3.txt")));
    }

}