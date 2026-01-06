package de.steffzilla.competitive;

import org.javatuples.Triplet;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        long[] numbers5 = {3L, 4L, 5L};
        assertEquals(1L, MathUtils.greatestCommonDivisor(numbers5));
    }

    @Test
    public void testEuclideanDistance3D() {
        Triplet<Integer, Integer, Integer> p1 = new Triplet<>(10, 10, 10);
        assertEquals(0d, MathUtils.euclideanDistance3D(p1, p1));

        p1 = new Triplet<>(0, 0, 0);
        Triplet<Integer, Integer, Integer> p2 = new Triplet<>(5, 0, 0);

        assertEquals(5d, MathUtils.euclideanDistance3D(p1, p2));

        p1 = new Triplet<>(1, 2, 3);
        p2 = new Triplet<>(4, 6, 15);

        assertEquals(13d, MathUtils.euclideanDistance3D(p1, p2));

        p1 = new Triplet<>(3, -4, 1);
        p2 = new Triplet<>(-3, 4, 1);

        assertEquals(10d, MathUtils.euclideanDistance3D(p1, p2));

        // test large coordinates overflow risk
        p1 = new Triplet<>(1_000_000_000, 0, 0);
        p2 = new Triplet<>(-1_000_000_000, 0, 0);

        // dx = -2000000000, Abstand = 2000000000
        assertEquals(2_000_000_000d, MathUtils.euclideanDistance3D(p1, p2), 0.000001);

        // test with Integer Max and Min
        p1 = new Triplet<>(Integer.MAX_VALUE, 0, 0);
        p2 = new Triplet<>(Integer.MIN_VALUE, 0, 0);

        long dx = (long) Integer.MIN_VALUE - (long) Integer.MAX_VALUE;
        double expected = Math.sqrt((double) dx * dx);

        assertEquals(expected, MathUtils.euclideanDistance3D(p1, p2), 0.000001);

        // test symmetric negative positive
        p1 = new Triplet<>(-1000, -1000, -1000);
        p2 = new Triplet<>(1000, 1000, 1000);

        expected = Math.sqrt(
                2000d * 2000d +
                        2000d * 2000d +
                        2000d * 2000d);

        assertEquals(expected, MathUtils.euclideanDistance3D(p1, p2), 0.000001);
    }

}
