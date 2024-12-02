package de.steffzilla.aoc.y2023;

import de.steffzilla.aoc.CharacterField;
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertEquals(63045, Aoc2023_03.getGearRatio(3,1));
        Aoc2023_03.field = new CharacterField(field2);
        Assert.assertEquals(451490, Aoc2023_03.getGearRatio(5,2));
        Aoc2023_03.field = new CharacterField(field3);
        Assert.assertEquals(2864, Aoc2023_03.getGearRatio(0, 0));
        Assert.assertEquals(30745, Aoc2023_03.getGearRatio(11, 1));
        Assert.assertEquals(972, Aoc2023_03.getGearRatio(30, 2));

        /*Aoc2023_03.field = new CharacterField(field4);
        Assert.assertEquals(9922, Aoc2023_03.getGearRatio(0, 0));
        Assert.assertEquals(644490, Aoc2023_03.getGearRatio(12, 0));
        Assert.assertEquals(76307, Aoc2023_03.getGearRatio(22,1));
        Assert.assertEquals(0, Aoc2023_03.getGearRatio(6,1));
        Assert.assertEquals(14, Aoc2023_03.getGearRatio(2,3));
        Assert.assertEquals(18, Aoc2023_03.getGearRatio(24,3));
        Assert.assertEquals(12, Aoc2023_03.getGearRatio(4,3));
        Assert.assertEquals(20, Aoc2023_03.getGearRatio(2,5));
        Aoc2023_03.field = new CharacterField(field5);
        Assert.assertEquals(108344, Aoc2023_03.getGearRatio(27, 0));
        Assert.assertEquals(66, Aoc2023_03.getGearRatio(16, 1));
        Assert.assertEquals(40, Aoc2023_03.getGearRatio(0, 2));
        Assert.assertEquals(24, Aoc2023_03.getGearRatio(29, 3));*/
    }

}