package de.steffzilla.aoc;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MathUtils {

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
        for (int i = 1; i < input.length; i++) result = greatestCommonDivisor(result, input[i]);
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
        long result = numbers.getFirst();
        for (int i = 1; i < numbers.size(); i++) {
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
        for (long i = 3L; i <= Math.sqrt(number); i += 2L) {
            // While i divides number, collect i and divide n
            while (number % i == 0) {
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

    public static double euclideanDistance3D(Triplet<Integer, Integer, Integer> p1, Triplet<Integer, Integer, Integer> p2) {
        long dx = (long) p2.getValue0() - p1.getValue0();
        long dy = (long) p2.getValue1() - p1.getValue1();
        long dz = (long) p2.getValue2() - p1.getValue2();
        return Math.sqrt((double) dx * dx + (double) dy * dy + (double) dz * dz);
    }

    /**
     *  Solves a 2x2 system of linear Diophantine equations using Cramer's rule.
     *  (equations with integer coefficients that only allow integer solutions)
     *    a1*x + b1*y = c1
     *    a2*x + b2*y = c2
     *  Returns an exact integer solution (x, y) if it exists, otherwise (no solution or infinite solutions) null.
     */
    public static Pair<BigInteger, BigInteger> solveLinearDiophantineSystem2x2(BigInteger a1, BigInteger b1, BigInteger c1, BigInteger a2, BigInteger b2, BigInteger c2) {
        // System of linear equations:
        //   a1*x + b1*y = c1
        //   a2*x + b2*y = c2
        BigInteger det = a1.multiply(b2).subtract(a2.multiply(b1));
        if (!det.equals(BigInteger.ZERO)) {
            BigInteger[] xDivRem = (c1.multiply(b2).subtract(c2.multiply(b1))).divideAndRemainder(det);
            BigInteger[] yDivRem = (a1.multiply(c2).subtract(a2.multiply(c1))).divideAndRemainder(det);
            if (xDivRem[1].equals(BigInteger.ZERO) && yDivRem[1].equals(BigInteger.ZERO)) {
                // Integer solution found
                return new Pair<>(xDivRem[0], yDivRem[0]);
            }
        }
        return null; // No integer solution or no unambiguous solution found
    }

}
