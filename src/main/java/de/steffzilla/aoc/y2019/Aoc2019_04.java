package de.steffzilla.aoc.y2019;


public class Aoc2019_04 {

    private static final String DAY = "04";
    private static final String YEAR = "2019";

    static final int start = 165432;
    static final int end = 707912;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);

        //part1();
        part2();
    }

    private static void part1(){
        int counter = 0;
        for (int i = start; i <= end; i++) {
            String password = Integer.toString(i);
            //System.out.println(password);
            if(areTwoAdjacentDigitsSame(password) && digitsNeverDecrease(password)) {
                counter++;
            }
        }

        System.out.println("\nPart 1 > Result: " + counter);
    }

    private static boolean digitsNeverDecrease(String input) {
        boolean neverDecrease = true;
        for (int i = 0; i < input.length()-1; i++) {
            if (input.charAt(i) > input.charAt(i+1)) {
                neverDecrease = false;
            }
        }
        return neverDecrease;
    }

    private static boolean areTwoAdjacentDigitsSame(String input) {
        boolean twoSame = false;
        for (int i = 0; i < input.length()-1; i++) {
            if (input.charAt(i) == input.charAt(i+1)) {
                twoSame = true;
            }
        }
        return twoSame;
    }

    /** The two adjacent matching digits are not part of a larger group of matching digits. */
    private static boolean areExactlyTwoAdjacentDigitsSame(String input) {
        boolean twoSame = false;
        // test of digits

        for (int i = 0; i < 10; i++) {
            char c = Character.forDigit(i , 10);
            if(input.chars().filter(ch -> ch == c).count() == 2) {
                twoSame = true;
            }
        }
        return twoSame;
    }


    private static void part2() {
        int counter = 0;
        for (int i = start; i <= end; i++) {
            String password = Integer.toString(i);

            //System.out.println(password);
            if(digitsNeverDecrease(password) && areExactlyTwoAdjacentDigitsSame(password)) {
                counter++;
            }
        }

        System.out.println("\nPart 2 > Result: " + counter);
    }

}