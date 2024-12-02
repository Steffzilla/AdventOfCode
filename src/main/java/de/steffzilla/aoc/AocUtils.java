package de.steffzilla.aoc;

import org.javatuples.Pair;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class AocUtils {

    public static final String HEX_WEBCOLOR_PATTERN = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$";
    public static final String HEX_COLOR_PATTERN_6CHARS = "^#([a-fA-F0-9]{6})$";

    @Deprecated
    public static int[] splitStringToIntArray(String input, String regex) {
        String[] sInput = input.split(regex);
        int[] returnValue = new int[sInput.length];
        for (int i = 0; i < sInput.length; i++) {
            returnValue[i] = Integer.parseInt(sInput[i]);
        }
        return returnValue;
    }

    public static long[] splitStringToLongArray(String input, String regex) {
        String[] sInput = input.split(regex);
        long[] returnValue = new long[sInput.length];
        for (int i = 0; i < sInput.length; i++) {
            returnValue[i] = Long.parseLong(sInput[i]);
        }
        return returnValue;
    }

    /**
     * @param path File with 1 line
     */
    @Deprecated
    public static int[] getIntArrayFromFile(String path, String regex) {
        String [] strings;
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            strings = stream.toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            strings = new String[0];
        }
        return splitStringToIntArray(strings[0], regex);
    }

    /**
     * File contains one int per line
     * @param path
     * @return
     */
    public static int[] getIntArrayFromFile(String path) {
        int[] numbers;
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            numbers = stream.mapToInt(Integer::parseInt).toArray(); // Integer.parseInt(num)
        } catch (IOException e) {
            e.printStackTrace();
            numbers = new int[0];
        }
        return numbers;
    }

    /**
     * File contains one long per line
     * @param path
     * @return
     */
    public static long[] getLongArrayFromFile(String path) {
        long[] numbers;
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            numbers = stream.mapToLong(Long::parseLong).toArray(); // Long.parseLong(num)
        } catch (IOException e) {
            e.printStackTrace();
            numbers = new long[0];
        }
        return numbers;
    }


    public static String[] getStringArray(String path) {
        String[] strings;
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            strings = stream.toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            strings = new String[0];
        }
        return strings;
    }

    public static List<String> getStringList(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            return stream.toList();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static long countLines(String fileName) {
        Path path = Paths.get(fileName);
        long lines = 0;
        try {
            lines = Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /*public static long getMax(Long[] numbers) {
        List<Long> list = Arrays.asList(numbers);
        Collections.sort(list);
        return list.get(list.size());
    }*/

    public static Character findDiffChar(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        char[] biggerCharArray;
        char[] smallerCharArray;
        if(chars1.length > chars2.length) {
            biggerCharArray = chars1;
            smallerCharArray = chars2;
        } else {
            smallerCharArray = chars1;
            biggerCharArray = chars2;
        }
        for (Character char1 : biggerCharArray) {
            boolean found = false;
            for (Character char2 : smallerCharArray) {
                if (char1.equals(char2)) {
                    found = true;
                }
            }
            if (!found) {
                return char1;
            }
        }
        return null; // no different char
    }

    /**
     * Returns true if the given String has only unique characters.
     * String can be a Unicode String.
     * Time Complexity: O(nlogn)
     * Auxiliary Space: O(n)
     */
    public static boolean areCharactersUnique(String str) {
        HashSet<Character> char_set = new HashSet<>();

        // Inserting character of String into set
        for(int c  = 0; c< str.length();c++) {
            char_set.add(str.charAt(c));
        }

        // If length of set is equal to len of String
        // then it will have unique characters
        return char_set.size() == str.length();
    }

    /**
     * Returns true if the given ASCII String has only unique characters.
     * TODO VORSICHT! Überprüfen, ob das mit ASCII so stimmt - Scheint auch für Unicodezeichen zu funktionieren
     * TODO Achtung mit 256 zeichen begrenzung!
     * Time Complexity: O(n)
     * Auxiliary Space: O(n)
     */
    public static boolean areXXXAsciiCharactersUnique(String str) {
        int MAX_CHAR = 256;
        // If length is greater than 256, some characters must have been repeated
        if (str.length() > MAX_CHAR) {
            return false;
        }

        boolean[] chars = new boolean[MAX_CHAR];
        Arrays.fill(chars, false);

        for (int i = 0; i < str.length(); i++) {
            int index = (int)str.charAt(i);

            // If the value is already true, string has duplicate characters, return false
            if (chars[index] == true) {
                return false;
            }
            chars[index] = true;
        }
        return true; // No duplicates encountered
    }

    public static List<List<String>> createFilledDotMatrix(List<Pair<Integer, Integer>> points, int dimensionX, int dimensionY, String empty) {
        List<List<String>> twoDimList = createEmptyMatrix(dimensionX, dimensionY, empty);

        for (Pair<Integer,Integer> point : points) {
            int x = point.getValue0();
            int y = point.getValue1();
            List<String> list = twoDimList.get(y);
            list.set(x, "#");
        }
        return twoDimList;
    }

    public static List<List<String>> createEmptyMatrix(int dimensionX, int dimensionY, String empty) {
        List<List<String>> twoDimList = new ArrayList<>(dimensionY);
        for (int i = 0; i < dimensionY; i++) {
            ArrayList<String> list = new ArrayList<>(dimensionX);
            for (int j = 0; j < dimensionX; j++) {
                list.add(empty);
            }
            twoDimList.add(list);
        }
        return twoDimList;
    }

    public static void copyMatrix(List<List<String>> from, List<List<String>> to,
                                  int fromXStartPos, int xSize, int fromYStartPos, int ySize) {
        for (int y = fromYStartPos; y < ySize; y++) {
            List<String> fromLine = from.get(y);
            List<String> toLine = to.get(y);
            for (int x = fromXStartPos; x < xSize; x++) {
                toLine.set(x, fromLine.get(x));
            }
        }
    }

    public static long countCharInString(String input, char search) {
        return input.chars().filter(ch -> ch == search).count();
    }

    /**
     * reads an int matrix from the inputLines.
     * @param inputLines
     * @param xDimension
     * @param yDimension
     * @return
     */
    public static int[][] readIntMatrix(List<String> inputLines, int xDimension, int yDimension) {
        int[][] matrix = new int[xDimension][yDimension];
        int lineIndex = 0;
        for (String line : inputLines) {
            for (int x = 0; x < line.length(); x++) {
                matrix[x][lineIndex] = Integer.parseInt(line.substring(x, x+1));
            }
            lineIndex++;
        }
        return matrix;
    }

    /**
     * Returns the binary string <strong>without leading zeros</strong>
     * @param hex
     * @return
     */
    public static String hexToBinWithoutLeadingZeros(String hex) {
        return new BigInteger(hex, 16).toString(2);
    }

    public static String hexToBinWithLeadingZeros(String hex) {
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("A", "1010");
        hex = hex.replaceAll("B", "1011");
        hex = hex.replaceAll("C", "1100");
        hex = hex.replaceAll("D", "1101");
        hex = hex.replaceAll("E", "1110");
        hex = hex.replaceAll("F", "1111");
        return hex;
    }

    // Euclid's algorithm
    private static long greatestCommonDivisor(long number1, long number2) {
        while (number2 > 0) {
            long temp = number2;
            number2 = number1 % number2;
            number1 = temp;
        }
        return number1;
    }

    public static long greatestCommonDivisor(long[] input) {
        long result = input[0];
        for(int i = 1; i < input.length; i++) result = greatestCommonDivisor(result, input[i]);
        return result;
    }

    /*
     * There’s an interesting relation between the LCM and GCD (Greatest Common Divisor) of two numbers that says that
     * the absolute value of the product of two numbers is equal to the product of their GCD and LCM.
     * As stated, gcd(a, b) * lcm(a, b) = |a * b|.
     * Consequently, lcm(a, b) = |a * b|/gcd(a, b).
     */
    private static long leastCommonMultiple(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        } else {
            return Math.abs(number1 * number2) / greatestCommonDivisor(number1, number2);
        }
        //return a * (b / greatestCommonDivisor(a, b));
    }

    public static long leastCommonMultiple(List<Long> numbers) {
        long result = numbers.get(0);
        for(int i = 1; i < numbers.size(); i++) {
            result = leastCommonMultiple(result, numbers.get(i));
        }
        return result;
    }

    public static BigInteger leastCommonMultiple(BigInteger number1, BigInteger number2) {
        BigInteger gcd = number1.gcd(number2);
        BigInteger absProduct = number1.multiply(number2).abs();
        return absProduct.divide(gcd);
    }

    // Basic idea: find the smallest prime number, by which number is divisible without remainder
    // divide by it and continue with the result until finally all factors are found
    // optimized version:
    public static List<Long> getPrimeFactorization(long number) {
        List<Long> result = new ArrayList<>();
        // Print the number of 2s that divide n
        while (number % 2L == 0L) {
            result.add(2L);
            number /= 2L;
        }

        // number must be odd at this point. So we can skip one element (Note i = i +2)
        for (long i = 3L; i <= Math.sqrt(number); i+= 2L) {
            // While i divides number, collect i and divide n
            while (number %i == 0) {
                result.add(i);
                number /= i;
            }
        }

        // This condition is to handle the case when number is a prime number greater than 2
        if (number > 2L) {
            result.add(number);
        }
        return result;
    }

    public static boolean isPrime(long n) {
        if (n <= 1) return false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static List<Long> getPrimeNumbers(long start, long end) {
        ArrayList<Long> primes = new ArrayList<>();
        for (long i = start; i <= end; i++) {
            if (isPrime(i)) primes.add(i);
        }
        return primes;
    }

    public static String reverseString(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    public static int getManhattanDistance(Pair<Integer, Integer> endPoint, Pair<Integer, Integer> startPoint) {
        return Math.abs(endPoint.getValue0() - startPoint.getValue0()) + Math.abs(endPoint.getValue1() - startPoint.getValue1());
    }
}
