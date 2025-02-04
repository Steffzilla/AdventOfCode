package de.steffzilla.aoc.y2023;

import de.steffzilla.aoc.CharacterField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2023_03Test {

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

}