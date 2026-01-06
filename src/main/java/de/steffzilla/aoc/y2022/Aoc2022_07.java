package de.steffzilla.aoc.y2022;

import de.steffzilla.competitive.AocUtils;

import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2022_07 {

    private static final String DAY = "07";
    private static final String YEAR = "2022";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;
    private static final String PATTERN_FILE = "(\\d)+ ([a-zA-Z.]+)";
    private static final String PATTERN_DIR = "dir ([a-zA-Z]+)";
    private static final String PATTERN_CD_DIR = "\\$ cd ([a-zA-Z]+)";
    static long sumOfAtMost100kSizes=0;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);

        part1(inputLines);
        //part2(inputLines);
    }

    private static void part1(List<String> inputLines){
        Pattern patternDir = Pattern.compile(PATTERN_DIR);
        Pattern patternFile = Pattern.compile(PATTERN_FILE);
        Pattern patternCdDir = Pattern.compile(PATTERN_CD_DIR);
        boolean lsMode = false;
        Directory rootDir = null;
        Directory currentDir = null;
        for (String line : inputLines) {
            if ("$ cd /".equals(line)) {
                lsMode = false;
                currentDir = new Directory(Directory.ROOT, null);
                if(null!=rootDir) {
                    throw new IllegalStateException("2nd root dir! "+line);
                } else {
                    rootDir = currentDir;
                }
            } else if ("$ ls".equals(line)) {
                lsMode = true;
            } else if("$ cd ..".equals(line)) {
                lsMode = false;
                if(null==currentDir.getUpperDir()) {
                    throw new IllegalStateException("Tried to go up in root! "+line);
                } else {
                    currentDir=currentDir.getUpperDir();
                }
            } else {
                Matcher matcherDir = patternDir.matcher(line);
                boolean dirFound = matcherDir.find();
                Matcher matcherFile = patternFile.matcher(line);
                boolean fileFound = matcherFile.find();
                Matcher matcherCdDir = patternCdDir.matcher(line);
                boolean cdDir = matcherCdDir.find();
                if(dirFound) {
                    String newDirName = matcherDir.group(1);
                    currentDir.addDir(new Directory(newDirName, currentDir));
                } else if(fileFound) {
                    long size = Long.parseLong(matcherFile.group(1));
                    String fileName = matcherFile.group(2);
                    Aoc22File newFile = new Aoc22File(fileName, size, currentDir);
                    currentDir.addFile(newFile);
                } else if(cdDir) {
                    lsMode = false;
                    String dirName = matcherCdDir.group(1);
                    Directory toDir = currentDir.getDir(dirName);
                    if(null==toDir) {
                        throw new IllegalStateException("Can't CD to this dir: "+line);
                    } else {
                        currentDir=toDir;
                    }
                } else {
                    throw new IllegalStateException("Unexpected line: "+line);
                }
            }
        }
        // PARSING DONE
        parseTreeAtMost100k(rootDir);
        System.out.println("\nPart 1 > Result: " + sumOfAtMost100kSizes);
    }

    private static void parseTreeAtMost100k(Directory node) {
        if(node.size <= 100000) {
            sumOfAtMost100kSizes+=node.size;
        }
        Collection<Directory> dirs = node.getDirs();
        for (Directory dir : dirs) {
            parseTreeAtMost100k(dir);
        }
    }


    private static void part2(List<String> inputLines) {
        for (String line : inputLines) {

        }
        System.out.println("\nPart 2 > Result: " + "x");
    }

}