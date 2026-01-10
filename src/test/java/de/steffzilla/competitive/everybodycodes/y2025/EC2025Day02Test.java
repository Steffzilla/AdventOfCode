package de.steffzilla.competitive.everybodycodes.y2025;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.steffzilla.competitive.Utils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class EC2025Day02Test {

    static final String DAY = "02";

    @Test
    void testAddNumber() {
        EC2025Day02.Number number = new EC2025Day02.Number(1, 1);
        EC2025Day02.Number otherNumber = new EC2025Day02.Number(2, 2);
        assertEquals(new EC2025Day02.Number(3, 3), number.add(otherNumber));

        number = new EC2025Day02.Number(2, 5);
        otherNumber = new EC2025Day02.Number(3, 7);
        assertEquals(new EC2025Day02.Number(5, 12), number.add(otherNumber));

        number = new EC2025Day02.Number(-2, 5);
        otherNumber = new EC2025Day02.Number(10, -1);
        assertEquals(new EC2025Day02.Number(8, 4), number.add(otherNumber));

        number = new EC2025Day02.Number(-1, -2);
        otherNumber = new EC2025Day02.Number(-3, -4);
        assertEquals(new EC2025Day02.Number(-4, -6), number.add(otherNumber));
    }

    @Test
    void testMultiplyNumber() {
        EC2025Day02.Number number = new EC2025Day02.Number(1, 1);
        EC2025Day02.Number otherNumber = new EC2025Day02.Number(2, 2);
        assertEquals(new EC2025Day02.Number(0, 4), number.multiply(otherNumber));

        number = new EC2025Day02.Number(2, 5);
        otherNumber = new EC2025Day02.Number(3, 7);
        assertEquals(new EC2025Day02.Number(-29, 29), number.multiply(otherNumber));

        number = new EC2025Day02.Number(-2, 5);
        otherNumber = new EC2025Day02.Number(10, -1);
        assertEquals(new EC2025Day02.Number(-15, 52), number.multiply(otherNumber));

        number = new EC2025Day02.Number(-1, -2);
        otherNumber = new EC2025Day02.Number(-3, -4);
        assertEquals(new EC2025Day02.Number(-5, 10), number.multiply(otherNumber));

        number = new EC2025Day02.Number(0, 0);
        otherNumber = new EC2025Day02.Number(0, 0);
        assertEquals(new EC2025Day02.Number(0, 0), number.multiply(otherNumber));
    }

    @Test
    void testDivideNumber() {
        EC2025Day02.Number number = new EC2025Day02.Number(10, 12);
        EC2025Day02.Number otherNumber = new EC2025Day02.Number(2, 2);
        assertEquals(new EC2025Day02.Number(5, 6), number.divide(otherNumber));

        number = new EC2025Day02.Number(11, 12);
        otherNumber = new EC2025Day02.Number(3, 5);
        assertEquals(new EC2025Day02.Number(3, 2), number.divide(otherNumber));

        number = new EC2025Day02.Number(-10, -12);
        otherNumber = new EC2025Day02.Number(2, 2);
        assertEquals(new EC2025Day02.Number(-5, -6), number.divide(otherNumber));

        number = new EC2025Day02.Number(-11, -12);
        otherNumber = new EC2025Day02.Number(3, 5);
        assertEquals(new EC2025Day02.Number(-3, -2), number.divide(otherNumber));
    }

    @Test
    void testEngravePoint() {
        EC2025Day02.Number number = new EC2025Day02.Number(35630,-64880);
        assertTrue(EC2025Day02.engravePoint(number));
        number = new EC2025Day02.Number(35460, -64910);
        assertFalse(EC2025Day02.engravePoint(number));
    }

    @Test
    void testSolveExample() {
        assertEquals("[357,862]", EC2025Day02.part1(EC2025Day02.EXAMPLE.lines().toList()));
        assertEquals("4076", EC2025Day02.part2(EC2025Day02.EXAMPLE2.lines().toList()));
        assertEquals("406954", EC2025Day02.part3(EC2025Day02.EXAMPLE3.lines().toList()));
    }

    @Test
    public void testSolvePuzzle() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("solutionsEC.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        assertEquals(root.at("/EverybodyCodes/2025/day" + DAY + "/part1").asText(),
                EC2025Day02.part1(Utils.getStringList(EC2025Day02.PATH + "1.txt")));
        assertEquals(root.at("/EverybodyCodes/2025/day" + DAY + "/part2").asText(),
                EC2025Day02.part2(Utils.getStringList(EC2025Day02.PATH + "2.txt")));
        assertEquals(root.at("/EverybodyCodes/2025/day" + DAY + "/part3").asText(),
                EC2025Day02.part3(Utils.getStringList(EC2025Day02.PATH + "3.txt")));
    }

}