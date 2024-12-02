package de.steffzilla.aoc.y2022;

import org.javatuples.Pair;

import java.math.BigInteger;
import java.util.LinkedList;

public class Monkey {

    /**
     * Worry level for the items the monkey holds
     */
    LinkedList<BigInteger> worryLevels;
    private int trueMonkey;
    private int falseMonkey;
    private BigInteger divisor;
    private BigInteger releaveDivisor = BigInteger.valueOf(3);
    private long inspectionCounter = 0;
    private MonkeyNewCalculator calculator;

    public Monkey(int trueMonkey, int falseMonkey, int divisor, MonkeyNewCalculator calculator) {
        this.trueMonkey = trueMonkey;
        this.falseMonkey = falseMonkey;
        this.divisor = BigInteger.valueOf(divisor);
        this.calculator = calculator;
    }

    public Pair<BigInteger, Integer> throwFirst() {
        if(worryLevels.isEmpty()) {
            return null;
        }
        BigInteger worryLevel = worryLevels.poll();
        worryLevel = investigate(worryLevel);
        worryLevel = worryLevel.divide(releaveDivisor); // round down
        int newMonkeyIndex;
        if (worryLevel.mod(divisor).equals(BigInteger.ZERO)) {
            newMonkeyIndex = trueMonkey;
        } else {
            newMonkeyIndex = falseMonkey;
        }

        long xxxx = 23*19*13*17;
        if (worryLevel.mod(BigInteger.valueOf(xxxx)).equals(BigInteger.ZERO)) {
            System.out.println("########### IT HAPPEND! ##############");
            worryLevel = BigInteger.valueOf(xxxx);
        }

        //System.out.println("Monkey throws " + worryLevel + " to " + newMonkeyIndex);
        return new Pair<>(worryLevel, newMonkeyIndex);
    }

    private BigInteger investigate(BigInteger worryLevel) {
        //System.out.println("Before investigation: " + worryLevel);
        BigInteger calculatedLevel = this.calculator.calculateNew(worryLevel);
        //System.out.println("After investigation: " + calculatedLevel);
        inspectionCounter++;
        return calculatedLevel;
    }

    public long getInspectionCounter() {
        return inspectionCounter;
    }

    public void setReleaveDivisor(long releaveDivisor) {
        this.releaveDivisor = BigInteger.valueOf(releaveDivisor);
    }

}
