package de.steffzilla.aoc.y2022;

import java.util.Collection;
import java.util.HashMap;

public class Directory implements FilesystemNode {

    String name;
    long size = 0 ;
    private Directory upperDir;
    private HashMap<String, Directory> directories;
    private HashMap<String, Aoc22File> files;
    public static final String ROOT = "/";

    public Directory(String name, Directory upper) {
        this.name=name;
        this.upperDir = upper;
        directories = new HashMap<>();
        files = new HashMap<>();
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    public void addSize(long addSize) {
        this.size += addSize;
        if(this.upperDir!=null){
            this.upperDir.addSize(addSize);
        }
    }

    public Directory getUpperDir() {
        return this.upperDir;
    }

    public void addDir(Directory node) {
        this.directories.put(node.getName(), node);
    }

    public Directory getDir(String dirName) {
        return this.directories.get(dirName);
    }

    public void addFile(Aoc22File newFile) {
        addSize(newFile.getSize());
        this.files.put(newFile.getName(), newFile);
    }

    public Collection<Directory> getDirs() {
        return this.directories.values();
    }
}
