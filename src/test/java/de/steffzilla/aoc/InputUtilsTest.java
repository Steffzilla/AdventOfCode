package de.steffzilla.aoc;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InputUtilsTest {

    @Test
    public void testReadLineAsBigIntegers() {
        List<BigInteger> bigIntegers =
                InputUtils.readLineAsBigIntegers("125 17 0 -42 9223372036854775808 -9223372036854775810", " ");
        assertEquals(BigInteger.valueOf(125), bigIntegers.getFirst());
        assertEquals(BigInteger.valueOf(17), bigIntegers.get(1));
        assertEquals(BigInteger.valueOf(0), bigIntegers.get(2));
        assertEquals(BigInteger.valueOf(-42), bigIntegers.get(3));
        assertEquals(new BigInteger("9223372036854775808"), bigIntegers.get(4));
        assertEquals(new BigInteger("-9223372036854775810"), bigIntegers.get(5));
    }

}
