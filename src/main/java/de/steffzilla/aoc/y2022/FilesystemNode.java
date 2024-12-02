package de.steffzilla.aoc.y2022;

public interface FilesystemNode {

    public String getName();
    public long getSize();
    public void addSize(long size);

}
