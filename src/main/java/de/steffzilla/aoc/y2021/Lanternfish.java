package de.steffzilla.aoc.y2021;

public class Lanternfish {

    private int timer;

    public Lanternfish(int timer) {
        this.timer = timer;
    }

    public boolean age() {
        if (timer == 0) {
            timer = 6;
            return true;
        } else {
            timer--;
            return false;
        }
    }

    @Override
    public String toString() {
        return Integer.valueOf(timer).toString();
    }
}
