package de.steffzilla.aoc.y2021;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cave {

    public static final String START = "start";
    public static final String END = "end";
    private final String name;
    private final boolean isBig;
    private final boolean isStart;
    private final boolean isEnd;
    private Set<Cave> paths;

    public Cave(String name) {
        if(name.equals(START)) {
            isStart = true;
        } else {
            isStart = false;
        }
        if(name.equals(END)) {
            isEnd = true;
        } else {
            isEnd = false;
        }
        this.name = name;
        this.isBig = name.chars().allMatch(Character::isUpperCase);
        paths = new HashSet<>();
    }

    public void setUnidirectionalPath(Cave cave) {
        paths.add(cave);
    }

    public Set<Cave> getPaths() {
        return paths;
    }

    public boolean isBig() {
        return isBig;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public boolean isStart() {
        return isStart;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cave cave = (Cave) o;
        return isBig == cave.isBig && isStart == cave.isStart && isEnd == cave.isEnd && name.equals(cave.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isBig, isStart, isEnd);
    }
}
