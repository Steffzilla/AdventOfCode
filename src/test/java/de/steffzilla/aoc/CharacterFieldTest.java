package de.steffzilla.aoc;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CharacterFieldTest {

    @Test
    public void testGetCharacterAt() {
        CharacterField characterField = new CharacterField(Arrays.asList("ABCD","EFGH","IJKL"));
        assertEquals("A",characterField.getCharacterAt(0,0));
        assertEquals("F",characterField.getCharacterAt(1,1));
        assertEquals("L",characterField.getCharacterAt(3,2));
    }

    @Test
    public void testGetCharacterAtWithMultiline() {
        String field = """
            ABCD
            EFGH
            IJKL
            """;
        CharacterField characterField = new CharacterField(field);
        assertEquals("A",characterField.getCharacterAt(0,0));
        assertEquals("F",characterField.getCharacterAt(1,1));
        assertEquals("L",characterField.getCharacterAt(3,2));
    }

    @Test
    public void testIsContained() {
        CharacterField characterField = new CharacterField(Arrays.asList("ABCD","EFGH","IJKL"));
        assertTrue(characterField.isContained(0, 0));
        assertTrue(characterField.isContained(1, 1));
        assertTrue(characterField.isContained(3, 2));
        assertFalse(characterField.isContained(-1, 2));
        assertFalse(characterField.isContained(3, -5));
        assertFalse(characterField.isContained(4, 2));
        assertFalse(characterField.isContained(3, 4));
        assertFalse(characterField.isContained(100, 100));
    }

    @Test
    public void testSearchCharacters() {
        CharacterField characterField = new CharacterField(Arrays.asList("....", "S...", "...."));
        List<Pair<Integer, Integer>> s = characterField.searchCharacters("S");
        assertTrue(s!=null && s.size()==1);
        assertEquals(new Pair<>(0,1), s.get(0));
        s = characterField.searchCharacters(".");
        assertTrue(s!=null && s.size()==11);
    }

    @Test
    public void testSetCharAt() {
        CharacterField characterField = new CharacterField(Arrays.asList("ABCD","EFGH","IJKL"));
        characterField.setCharacterAt("X", 1,2);
        assertEquals("X", characterField.getCharacterAt(1,2));
    }

    @Test
    public void testAreRowsEqual() {
        String field = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.
            """;
        CharacterField characterField = new CharacterField(field);
        assertTrue(characterField.areRowsEqual(2,3));
        assertTrue(characterField.areRowsEqual(1,4));
        assertFalse(characterField.areRowsEqual(0,5));
        assertFalse(characterField.areRowsEqual(-1,5));
        assertFalse(characterField.areRowsEqual(5,-1));
        assertFalse(characterField.areRowsEqual(1,7));
        assertFalse(characterField.areRowsEqual(7,1));
    }

    @Test
    public void testAreColumnsEqual() {
        String field = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.
            """;
        CharacterField characterField = new CharacterField(field);
        assertFalse(characterField.areColumnsEqual(-1,5));
        assertFalse(characterField.areColumnsEqual(5,-1));
        assertFalse(characterField.areColumnsEqual(1,9));
        assertFalse(characterField.areColumnsEqual(9,1));
        assertFalse(characterField.areColumnsEqual(0,1));
        assertTrue(characterField.areColumnsEqual(4,5));
        assertTrue(characterField.areColumnsEqual(3,6));
        assertTrue(characterField.areColumnsEqual(2,7));
        assertTrue(characterField.areColumnsEqual(1,8));
    }

    @Test
    public void testCopyConstructor() {
        String field = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.
            """;
        CharacterField characterField1 = new CharacterField(field);
        CharacterField characterField2 = new CharacterField(characterField1);
        assertEquals(characterField1, characterField2);
        characterField2.setCharacterAt("X",0,0);
        assertNotEquals(characterField1, characterField2);
    }

    @Test
    public void testCopyFieldPartially() {
        String field = """
            ABCD
            EFGH
            IJKL
            """;
        String field2 = """
            FG
            JK
            """;
        CharacterField characterField1 = new CharacterField(field);
        CharacterField characterField2 =
                characterField1.copyFieldPartially(0, (characterField1.getMaxX()-1),0, (characterField1.getMaxY()-1));
        assertEquals(characterField1, characterField2);
        CharacterField characterField3 = new CharacterField(field2);
        CharacterField characterField4 = characterField1.copyFieldPartially(1, 2,1, 2);
        assertEquals(characterField3, characterField4);
        CharacterField characterField5 = new CharacterField("L");
        CharacterField characterField6 = characterField1.copyFieldPartially(3, 3,2, 2);
        assertEquals(characterField5, characterField6);
    }

    @Test
    public void testGetFieldAsString() {
        String field = """
            ABCD
            EFGH
            IJKL
            """;
        CharacterField cf = new CharacterField(field);
        assertEquals(field, cf.getFieldAsString());
    }

}
