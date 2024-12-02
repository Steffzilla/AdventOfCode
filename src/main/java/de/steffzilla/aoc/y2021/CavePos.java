package de.steffzilla.aoc.y2021;

import java.util.*;

public class CavePos implements Comparable<CavePos> {

    private int riskLevel;
    private Set<CavePos> successors;
    private String name;
    /** Total costs from start to this node */
    private int totalCosts = Integer.MAX_VALUE;
    /** With lowes costs */
    private CavePos predecessor;

    public CavePos(String name, int riskLevel) {
        this.name = name;
        this.riskLevel = riskLevel;
        successors = new HashSet<>();
    }

    public void addSuccessor(CavePos successor) {
        successors.add(successor);
    }

    public CavePos getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(CavePos predecessor) {
        this.predecessor = predecessor;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Set<CavePos> getSuccessors() {
        return successors;
    }

    public int getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(int costs) {
        this.totalCosts = costs;
    }

    @Override
    public String toString() {
        return "CavePos{" +
                "riskLevel=" + riskLevel +
                ", name='" + name + '\'' +
                ", totalCosts='" + totalCosts + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CavePos cavePos = (CavePos) o;
        return riskLevel == cavePos.riskLevel && name.equals(cavePos.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(riskLevel, name);
    }

    @Override
    public int compareTo(CavePos other) {
        return Integer.compare(this.totalCosts, other.getTotalCosts());
    }
}
