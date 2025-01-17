package de.steffzilla.aoc;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MathUtilsTest {

    @Test
    public void testGetPrimeNumbers() {
        assertEquals(Arrays.asList(101L, 103L, 107L, 109L, 113L, 127L, 131L, 137L, 139L, 149L, 151L,
                157L, 163L, 167L, 173L, 179L, 181L, 191L, 193L, 197L, 199L), MathUtils.getPrimeNumbers(100, 200));
    }

    @Test
    public void testGetPrimeFactorization() {
        assertEquals(Arrays.asList(3L, 5L, 5L), MathUtils.getPrimeFactorization(75));
        assertEquals(Arrays.asList(3L, 3L, 5L, 7L), MathUtils.getPrimeFactorization(315));
        assertEquals(Arrays.asList(23L, 23L), MathUtils.getPrimeFactorization(529));
        assertEquals(Arrays.asList(2L, 2L, 2L, 2L, 3L, 3L, 5L, 7L), MathUtils.getPrimeFactorization(5040));
    }

    @Test
    public void testIsPrime() {
        assertTrue(MathUtils.isPrime(3));
        assertTrue(MathUtils.isPrime(101));
        assertFalse(MathUtils.isPrime(100));
    }

    @Test
    public void testLeastCommonMultiple() {
        assertEquals(new BigInteger("36"), MathUtils.leastCommonMultiple(new BigInteger("12"), new BigInteger("18")));
        assertEquals(36L, MathUtils.leastCommonMultiple(Arrays.asList(12L, 18L)));
        assertEquals(7969170, MathUtils.leastCommonMultiple(Arrays.asList(682L, 95L, 123L)));
    }

    @Test
    public void testGreatestCommonDivisor() {
        long[] numbers = {15L, 35L};
        assertEquals(5L, MathUtils.greatestCommonDivisor(numbers));
        long[] numbers2 = {35L, 15L};
        assertEquals(5L, MathUtils.greatestCommonDivisor(numbers2));
        long[] numbers3 = {2378L, 1769L};
        assertEquals(29L, MathUtils.greatestCommonDivisor(numbers3));
        long[] numbers4 = {35L, 266L, 182L};
        assertEquals(7L, MathUtils.greatestCommonDivisor(numbers4));
    }

}
