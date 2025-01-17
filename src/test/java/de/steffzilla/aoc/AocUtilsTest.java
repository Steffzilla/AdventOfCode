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
