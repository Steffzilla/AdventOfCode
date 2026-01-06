package de.steffzilla.competitive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void testFindDiffChar() {
        Character diffChar = Utils.findDiffChar("abc", "ab");
        Assertions.assertEquals(Character.valueOf('c'), diffChar);

        diffChar = Utils.findDiffChar("cba", "ab");
        Assertions.assertEquals(Character.valueOf('c'), diffChar);

        diffChar = Utils.findDiffChar("ba", "abc");
        Assertions.assertEquals(Character.valueOf('c'), diffChar);

        diffChar = Utils.findDiffChar("$ü@ä", "$ä@");
        Assertions.assertEquals(Character.valueOf('ü'), diffChar);

        diffChar = Utils.findDiffChar("abc", "abc");
        Assertions.assertNull(diffChar);
    }

    @Test
    void testCountCharInString() {
        long l = Utils.countCharInString("aaAAAccA", 'A');
        Assertions.assertEquals(4, l);

        l = Utils.countCharInString("aaAAAccA", 'c');
        Assertions.assertEquals(2, l);
    }

    @Test
    void testAreCharactersUnique() {
        Assertions.assertFalse(Utils.areCharactersUnique("aa"));
        Assertions.assertTrue(Utils.areCharactersUnique("aC"));
        Assertions.assertTrue(Utils.areCharactersUnique(""));
        Assertions.assertTrue(Utils.areCharactersUnique("abcDÜÄ=@"));
        Assertions.assertFalse(Utils.areCharactersUnique("a2Cäölkja1"));
        Assertions.assertTrue(Utils.areCharactersUnique("abcdefghijklmnopqrstuvwxyz" + "abcdefghijklmnopqrstuvwxyz".toUpperCase()));
    }

}
