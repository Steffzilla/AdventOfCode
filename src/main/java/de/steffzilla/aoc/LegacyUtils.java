package de.steffzilla.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LegacyUtils {
    @Deprecated
    public static int[] splitStringToIntArray(String input, String regex) {
        String[] sInput = input.split(regex);
        int[] returnValue = new int[sInput.length];
        for (int i = 0; i < sInput.length; i++) {
            returnValue[i] = Integer.parseInt(sInput[i]);
        }
        return returnValue;
    }

    /**
     * @param path File with 1 line
     */
    @Deprecated
    public static int[] getIntArrayFromFile(String path, String regex) {
        String[] strings;
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            strings = stream.toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return splitStringToIntArray(strings[0], regex);
    }
}
