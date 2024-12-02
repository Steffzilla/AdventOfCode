package de.steffzilla.aoc.y2021;

public class Laternfish {

    private int timer;

    public Laternfish(int timer) {
        this.timer = timer;
    }

    public boolean age() {
        if(timer==0) {
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
