package de.steffzilla.aoc.y2021;

import de.steffzilla.aoc.AocUtils;

import java.util.List;

public class Aoc2021_2 {

    private static final String DAY = "02";
    private static final String YEAR = "2021";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;

    static int horizontal = 0;
    static int depth = 0;
    static int aim = 0;


    public static void main(String[] args) {
        List<String> stringList = AocUtils.getStringList(PATH);
        for (String move: stringList) {
            String[] strings = move.split(" ");
            //doMove(strings[0], Integer.parseInt(strings[1]));
            doMovePart2(strings[0], Integer.parseInt(strings[1]));
        }
        int finalPos = depth * horizontal;
        System.out.println("\nFinal Pos: "+finalPos);
    }

    private static void doMovePart2(String befehl, int number) {
        System.out.println(befehl+"|"+number);
        if(befehl.equals("forward")){
            horizontal+=number;
            setDepthPart2(aim * number);
        } else if(befehl.equals("down")) {
            aim += number;
        } else if(befehl.equals("up")) {
            aim -= number;
        } else {
            throw new IllegalStateException("Illegal move");
        }
    }

    private static void setDepthPart2(int number) {
        depth += number;
        if(depth < 0) {
            depth = 0;
        }
    }

    private static void doMove(String befehl, int number) {
        System.out.println(befehl+"|"+number);
        if(befehl.equals("forward")){
            horizontal+=number;
        } else if(befehl.equals("down")) {
            depth += number;
        } else if(befehl.equals("up")) {
            depth -= number;
        } else {
            throw new IllegalStateException("Illegal move");
        }
    }

}
