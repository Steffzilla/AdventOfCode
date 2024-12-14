package de.steffzilla.aoc;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Field of String Characters.
 * 0 based indices.
 * always false: field.isContained(field.getMaxX(), field.getMaxY())
 * E.g.:
 * Sabq
 * abcr
 * accs
 */
public class CharacterField {

    private final List<String> field;
    private final int maxY; // 1 based -> Number of lines
    private final int maxX; // 1 based -> Number of characters in line

    public static void main(String[] args) {
        String field = """
                111111111
                122222221
                123333321
                123444321
                123454321
                123444321
                123333321
                122222221
                111111111
                """;
        CharacterField cf = new CharacterField(field);
        cf.prettyPrint();
    }

    /**
     *
     * @param input all lines need to have the same length
     */
    public CharacterField(String input) {
        String[] lines = input.split("\r\n|\r|\n");
        maxY = lines.length;
        maxX = lines[0].length();
        for (String line : lines) {
            if(line.length() != maxX) {
                throw new IllegalStateException("All lines need to have the same length!");
            }
        }
        this.field = Arrays.asList(lines);
    }

    public CharacterField(List<String> input) {
        maxY = input.size();
        maxX = input.getFirst().length();
        for (String line : input) {
            if(line.length() != maxX) {
                throw new IllegalStateException("All lines need to have the same length!");
            }
        }
        // ensure that the list is modifiable
        this.field = new ArrayList<>(input);
    }

    /** Copy constructor */
    public CharacterField(CharacterField otherField) {
        this.maxX = otherField.maxX;
        this.maxY = otherField.maxY;
        this.field = new ArrayList<>(otherField.field);
    }

    /**
     * Creates a new CharacterField prefilled with the same character.
     */
    public CharacterField(int maxX, int maxY, String filler) {
        if(filler.length() != 1) {
            throw new IllegalStateException("filler needs to be a single character!");
        }
        field = new ArrayList<>();
        for (int y = 0; y < maxY; y++) {
            field.add(filler.repeat(maxX)); // initialize with space chars
        }
        this.maxX = maxX;
        this.maxY = maxY;
    }

    private CharacterField(int maxX, int maxY) {
        field = new ArrayList<>();
        for (int y = 0; y < maxY; y++) {
            field.add(" ".repeat(maxX)); // initialize with space chars
        }
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public List<Pair<Integer, Integer>> searchCharacters(String character) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        if(character != null) {
            for (int y = 0; y < maxY; y++) {
                for (int x = 0; x < maxX; x++) {
                    if(character.equals(getCharacterAt(x, y))) {
                        list.add(new Pair<>(x, y));
                    }
                }
            }
        }
        return list;
    }

    public boolean areRowsEqual(int lineNo1, int lineNo2) {
        if (lineNo1 < 0 || lineNo1 >= maxY || lineNo2 < 0 || lineNo2 >= maxY) {
            return false;
        } else return this.field.get(lineNo1).equals(this.field.get(lineNo2));
    }

    public boolean areColumnsEqual(int colNo1, int colNo2) {
        if (colNo1 < 0 || colNo1 >= maxX || colNo2 < 0 || colNo2 >= maxX) {
            return false;
        } else {
            for (int y = 0; y < maxY; y++) {
                if (!getCharacterAt(colNo1, y).equals(getCharacterAt(colNo2, y))) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean isContained(Pair<Integer, Integer> pos) {
        return isContained(pos.getValue0(), pos.getValue1());
    }

    public boolean isContained(int x, int y) {
        return (x >= 0 && y >= 0 && x < maxX && y < maxY);
    }

    public String getCharacterAt(Pair<Integer, Integer> pos) {
        return getCharacterAt(pos.getValue0(), pos.getValue1());
    }

    public String getCharacterAt(int x, int y) {
        return field.get(y).substring(x, x + 1);
    }

    public void setCharacterAt(String newCharacter, Pair<Integer, Integer> pos) {
        setCharacterAt(newCharacter, pos.getValue0(), pos.getValue1());
    }

    public void setCharacterAt(String newCharacter, int x, int y) {
        if (x < 0 || x >= maxX || y < 0 || y >= maxY) {
            throw new IllegalStateException("Coordinates are outside of the field:" + x + "/" + y);
        }
        if (newCharacter == null || newCharacter.length() != 1) {
            throw new IllegalStateException("Character needs to be a single character:" + newCharacter);
        }
        StringBuilder builder = new StringBuilder(field.get(y));
        builder.setCharAt(x, newCharacter.charAt(0));
        field.set(y, builder.toString());
    }

    /**
     * Returns a partial copy of the field.
     * @param fromX Horizontal "from". 0 based. is included in the copy.
     * @param toX Horizontal "to". 0 based. is included in the copy.
     * @param fromY Vertical "from". 0 based. is included in the copy.
     * @param toY Vertical "to". 0 based. is included in the copy.
     * @return a copy of a part of the field
     */
    public CharacterField copyFieldPartially(int fromX, int toX, int fromY, int toY) {
        if (fromX < 0 || fromX >= maxX || fromY < 0 || toY >= maxY) {
            throw new IllegalStateException("Parameters are not in the boundaries of the field!");
        }
        CharacterField partialField = new CharacterField(toX - fromX + 1, toY - fromY + 1);
        int destY = 0;
        for (int y = fromY; y <= toY; y++) {
            int destX = 0;
            for (int x = fromX; x <= toX; x++) {
                partialField.setCharacterAt(getCharacterAt(x, y), destX, destY);
                destX++;
            }
            destY++;
        }
        return partialField;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void prettyPrint() {
        for (String line : this.field) {
            System.out.println(line);
        }
    }

    @Override
    public String toString() {
        return "CharacterField{" +
                "field=" + field +
                ", maxY=" + maxY +
                ", maxX=" + maxX +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacterField that = (CharacterField) o;

        if (maxY != that.maxY) return false;
        if (maxX != that.maxX) return false;
        return field.equals(that.field);
    }

    @Override
    public int hashCode() {
        int result = field.hashCode();
        result = 31 * result + maxY;
        result = 31 * result + maxX;
        return result;
    }


}
