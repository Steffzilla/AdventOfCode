package de.steffzilla.aoc.y2022;

import org.apache.commons.lang3.NotImplementedException;

import java.util.Objects;

public class Aoc22File implements FilesystemNode {
    private Directory parentDir;
    String name;
    long size = 0;

    public Aoc22File(String name, long size, Directory parentDir) {
        this.name = name;
        this.size = size;
        this.parentDir = parentDir;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public void addSize(long size) {
        throw new NotImplementedException("");
    }

    @Override
    public String toString() {
        return "Aoc22File{" +
                "name='" + name + '\'' +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aoc22File aoc22File = (Aoc22File) o;
        return size == aoc22File.size && name.equals(aoc22File.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
