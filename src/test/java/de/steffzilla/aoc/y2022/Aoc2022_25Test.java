package de.steffzilla.aoc.y2022;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class Aoc2022_25Test {

    @Test
    public void testGetMax() {

        String s = Aoc2022_25.decimalToSnafu(3);
        assertEquals("1=", s);

        s = Aoc2022_25.decimalToSnafu(7);
        assertEquals("12", s);

        s = Aoc2022_25.decimalToSnafu(11);
        assertEquals("21", s);

        s = Aoc2022_25.decimalToSnafu(198);
        assertEquals("2=0=", s);

        s = Aoc2022_25.decimalToSnafu(4890);
        assertEquals("2=-1=0", s);

    }

}
