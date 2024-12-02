package de.steffzilla.aoc.y2022;

import java.math.BigInteger;

@FunctionalInterface
public interface MonkeyNewCalculator {

    public BigInteger calculateNew(BigInteger old);

}
