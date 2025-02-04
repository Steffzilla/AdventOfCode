package de.steffzilla.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AocUtilsTest {

    @Test
    void testFindDiffChar() {
        Character diffChar = AocUtils.findDiffChar("abc", "ab");
        Assertions.assertEquals(Character.valueOf('c'), diffChar);

        diffChar = AocUtils.findDiffChar("cba", "ab");
        Assertions.assertEquals(Character.valueOf('c'), diffChar);

        diffChar = AocUtils.findDiffChar("ba", "abc");
        Assertions.assertEquals(Character.valueOf('c'), diffChar);

        diffChar = AocUtils.findDiffChar("$ü@ä", "$ä@");
        Assertions.assertEquals(Character.valueOf('ü'), diffChar);

        diffChar = AocUtils.findDiffChar("abc", "abc");
        Assertions.assertNull(diffChar);
    }

    @Test
    void testCountCharInString() {
        long l = AocUtils.countCharInString("aaAAAccA", 'A');
        Assertions.assertEquals(4, l);

        l = AocUtils.countCharInString("aaAAAccA", 'c');
        Assertions.assertEquals(2, l);
    }

    @Test
    void testAreCharactersUnique() {
        Assertions.assertFalse(AocUtils.areCharactersUnique("aa"));
        Assertions.assertTrue(AocUtils.areCharactersUnique("aC"));
        Assertions.assertTrue(AocUtils.areCharactersUnique(""));
        Assertions.assertTrue(AocUtils.areCharactersUnique("abcDÜÄ=@"));
        Assertions.assertFalse(AocUtils.areCharactersUnique("a2Cäölkja1"));
        Assertions.assertTrue(AocUtils.areCharactersUnique("abcdefghijklmnopqrstuvwxyz" + "abcdefghijklmnopqrstuvwxyz".toUpperCase()));
    }

}
