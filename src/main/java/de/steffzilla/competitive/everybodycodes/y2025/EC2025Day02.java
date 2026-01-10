package de.steffzilla.competitive.everybodycodes.y2025;

import de.steffzilla.competitive.Utils;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class EC2025Day02 {

    private static final String DAY = "02";
    private static final String YEAR = "2025";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//EC//" + YEAR + "//";
    public static final String FILENAME_BASE = "everybody_codes_e" + YEAR + "_q" + DAY + "_p";
    public static final String PATH = BASEDIR + FILENAME_BASE;
    private static final long LOWER_BOUNDARY = -1000000;
    private static final long UPPER_BOUNDARY = 1000000;

    static final String EXAMPLE = "A=[25,9]";
    static final String EXAMPLE2 = "A=[35300,-64910]";
    static final String EXAMPLE3 = "A=[35300,-64910]";

    static void main() {
        System.out.println("EC " + DAY + " " + YEAR);
        //List<String> inputLines = EXAMPLE.lines().toList();
        List<String> inputLines = Utils.getStringList(PATH + "1.txt");
        part1(inputLines);
        //inputLines = EXAMPLE2.lines().toList();
        inputLines = Utils.getStringList(PATH + "2.txt");
        part2(inputLines);
        //inputLines = EXAMPLE3.lines().toList();
        inputLines = Utils.getStringList(PATH + "3.txt");
        part3(inputLines);
    }

    static String part1(List<String> inputLines) {
        Number a = getStart(inputLines.getFirst());
        Number tenTen = new Number(10, 10);
        Number current = new Number(0, 0);
        for (int i = 0; i < 3; i++) {
            current = current.multiply(current);
            current = current.divide(tenTen);
            current = current.add(a);
        }
        System.out.println("\nPart 1 > Result: " + current);
        return current.toString();
    }

    static String part2(List<String> inputLines) {
        Number start = getStart(inputLines.getFirst());
        long count = 0;
        for (int y = 0; y <= 100; y++) {
            for (int x = 0; x <= 100; x++) {
                if (engravePoint(start.add(new Number(x * 10, y * 10)))) {
                    count++;
                }
            }
        }
        System.out.println("\nPart 2 > Result: " + count);
        return String.valueOf(count);
    }

    static String part3(List<String> inputLines) {
        Number start = getStart(inputLines.getFirst());
        long count = 0;
        for (int y = 0; y <= 1000; y++) {
            for (int x = 0; x <= 1000; x++) {
                if (engravePoint(start.add(new Number(x, y)))) {
                    count++;
                }
            }
        }
        System.out.println("\nPart 3 > Result: " + count);
        return String.valueOf(count);
    }

    static boolean engravePoint(Number current) {
        Number result = new Number(0, 0);
        Number divisor = new Number(100000, 100000);
        boolean engravePoint = true;
        for (int i = 0; i < 100; i++) {
            result = result.multiply(result);
            result = result.divide(divisor);
            result = result.add(current);
            if (result.x < LOWER_BOUNDARY || result.y < LOWER_BOUNDARY || result.x > UPPER_BOUNDARY || result.y > UPPER_BOUNDARY) {
                engravePoint = false;
                break;
            }
        }
        return engravePoint;
    }

    private static @NonNull Number getStart(String input) {
        input = input.substring(3, input.length() - 1);
        String[] split = input.split(",");
        return new Number(Long.parseLong(split[0]), Long.parseLong(split[1]));
    }

    record Number(long x, long y) {
        public Number add(Number other) {
            return new Number(this.x + other.x, this.y + other.y);
        }

        public Number multiply(Number other) {
            long newX = this.x * other.x - this.y * other.y;
            long newY = this.x * other.y + this.y * other.x;
            return new Number(newX, newY);
        }

        public Number divide(Number other) {
            return new Number(this.x / other.x, this.y / other.y);
        }

        @Override
        public @NonNull String toString() {
            return "[" + x + "," + y + "]";
        }
    }

}