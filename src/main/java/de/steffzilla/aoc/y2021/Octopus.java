package de.steffzilla.aoc.y2021;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class Octopus {

    private final int xPos;
    private final int yPos;
    private int energyLevel;
    boolean flashesInCurrentRound;

    public Octopus(int xPos, int yPos, int energyLevel) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.energyLevel = energyLevel;
        flashesInCurrentRound = false;
    }

    public boolean doNormalEnergyLevelInc() {
        if(energyLevel>9) {
            energyLevel=0; // resetBeforeRound
        }
        flashesInCurrentRound = false;
        return incrementEnergyLevel();
    }

    public boolean getsIncremented() {
        return incrementEnergyLevel();
    }

    private boolean incrementEnergyLevel() {
        energyLevel++;
        if(energyLevel > 9 && !flashesInCurrentRound) {
            flashesInCurrentRound = true;
            return true;
        } else {
            return false;
        }
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public List<Pair<Integer, Integer>> getNeighbours() {
        List<Pair<Integer, Integer>> neighbours = new ArrayList<>();
        for (int y = yPos-1; y <= yPos+1; y++) {
            for (int x = xPos-1; x <= xPos+1; x++) {
                if(x>=0 && y>=0 && x< Aoc2021_11.X_DIMENSION && y<Aoc2021_11.X_DIMENSION) {
                    if(x != xPos || y != yPos) {
                        neighbours.add(new Pair<>(x,y));
                    }
                }
            }
        }
        return neighbours;
    }

    @Override
    public String toString() {
        return "Octopus{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                '}';
    }
}
