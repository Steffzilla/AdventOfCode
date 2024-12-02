package de.steffzilla.aoc;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class AocUtilsTest {

    @Test
    public void testFindDiffChar() {
        Character diffChar = AocUtils.findDiffChar("abc", "ab");
        assertEquals(Character.valueOf('c'),diffChar);

        diffChar = AocUtils.findDiffChar("cba", "ab");
        assertEquals(Character.valueOf('c'),diffChar);

        diffChar = AocUtils.findDiffChar("ba", "abc");
        assertEquals(Character.valueOf('c'),diffChar);

        diffChar = AocUtils.findDiffChar("$ü@ä", "$ä@");
        assertEquals(Character.valueOf('ü'),diffChar);

        diffChar = AocUtils.findDiffChar("abc", "abc");
        Assert.assertNull(diffChar);
    }

    @Test
    public void testCountCharInString() {
        long l = AocUtils.countCharInString("aaAAAccA", 'A');
        Assert.assertEquals(4, l);

        l = AocUtils.countCharInString("aaAAAccA", 'c');
        Assert.assertEquals(2, l);
    }

    @Test
    public void testAreCharactersUnique() {
        Assert.assertEquals(false, AocUtils.areCharactersUnique("aa"));
        Assert.assertEquals(true, AocUtils.areCharactersUnique("aC"));
        Assert.assertEquals(true, AocUtils.areCharactersUnique(""));
        Assert.assertEquals(true, AocUtils.areCharactersUnique("abcDÜÄ=@"));
        Assert.assertEquals(false, AocUtils.areCharactersUnique("a2Cäölkja1"));
        Assert.assertEquals(true,
                AocUtils.areCharactersUnique("abcdefghijklmnopqrstuvwxyz" + "abcdefghijklmnopqrstuvwxyz".toUpperCase()));
    }

    @Test
    public void testGetPrimeNumbers() {
        assertEquals(Arrays.asList(101L, 103L, 107L, 109L, 113L, 127L, 131L, 137L, 139L, 149L, 151L,
                157L, 163L, 167L, 173L, 179L, 181L, 191L, 193L, 197L, 199L), AocUtils.getPrimeNumbers(100, 200));
    }

    @Test
    public void testGetPrimeFactorization() {
        assertEquals(Arrays.asList(3L, 5L, 5L), AocUtils.getPrimeFactorization(75));
        assertEquals(Arrays.asList(3L, 3L, 5L, 7L), AocUtils.getPrimeFactorization(315));
        assertEquals(Arrays.asList(23L, 23L), AocUtils.getPrimeFactorization(529));
        assertEquals(Arrays.asList(2L, 2L, 2L, 2L, 3L, 3L, 5L, 7L), AocUtils.getPrimeFactorization(5040));
    }

    @Test
    public void testIsPrime() {
        assertTrue(AocUtils.isPrime(3));
        assertTrue(AocUtils.isPrime(101));
        assertFalse(AocUtils.isPrime(100));
    }

    @Test
    public void testLeastCommonMultiple() {
        assertEquals(new BigInteger("36"), AocUtils.leastCommonMultiple(new BigInteger("12"), new BigInteger("18")));
        assertEquals(36L, AocUtils.leastCommonMultiple(Arrays.asList(12L, 18L)));
        assertEquals(7969170, AocUtils.leastCommonMultiple(Arrays.asList(682L, 95L, 123L)));
    }

/*
    @Test
    public void testAreAsciiCharactersUnique() {
        Assert.assertEquals(false, AocUtils.areAsciiCharactersUnique("aa"));
        Assert.assertEquals(true, AocUtils.areAsciiCharactersUnique("aC"));
        Assert.assertEquals(true, AocUtils.areAsciiCharactersUnique(""));
        Assert.assertEquals(true, AocUtils.areAsciiCharactersUnique("abcDÜÄ=@"));
        Assert.assertEquals(true, AocUtils.areAsciiCharactersUnique("abcdefghijklmnopqrstuvwxyz" + "abcdefghijklmnopqrstuvwxyz".toUpperCase()));
    }*/

}
