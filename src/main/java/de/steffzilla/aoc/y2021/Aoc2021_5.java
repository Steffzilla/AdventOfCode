package de.steffzilla.aoc.y2021;

import de.steffzilla.competitive.Utils;

import java.util.List;
import java.util.regex.Pattern;

public class Aoc2021_5 {

    private static final String DAY = "05";
    private static final String YEAR = "2021";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;

    static final int SIZE = 1001;
    static int[][] field = new int[SIZE][SIZE];

    static final Pattern LINEPATTERN = Pattern.compile("(\\d{1,3}),(\\d{1,3})( -> )(\\d{1,3}),(\\d{1,3})");

    public static void main(String[] args) {
        List<String> stringList = Utils.getStringList(PATH);
        //processLine("72,504 -> 422,154"/*stringList.get(5)*/,field);
        for (String line : stringList) {
            processLine(line, field);
        }
        int result = count(field);

        print(field);

        System.out.println("\nResult: "+result);

    }

    private static void processLine(String line, int[][] field) {
/*        Matcher matcher = LINEPATTERN.matcher(line);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }*/
        String[] split = line.split(" -> ");
        String[] startCoordinates = split[0].split(",");
        int startX = Integer.parseInt(startCoordinates[0]);
        int startY = Integer.parseInt(startCoordinates[1]);
        String[] endCoordinates = split[1].split(",");
        int endX = Integer.parseInt(endCoordinates[0]);
        int endY = Integer.parseInt(endCoordinates[1]);
        parseCoordinates(startX,startY,endX,endY, field);

        //System.out.println(startX);
        //System.out.println(startY);
        //System.out.println(endX);
        //System.out.println(endY);
        //System.out.println("---");
    }

    private static void parseCoordinates(int startX, int startY, int endX, int endY, int[][] field) {
        if(startX == endX) {
            System.out.print("Vertical:");
            System.out.println(startX+","+startY+"-->"+endX+","+endY);
            if(startY>endY) {
                int temp = startY;
                startY = endY;
                endY = temp;
            }
            for (int i = startY; i <= endY; i++) {
                System.out.println(startX+","+i);
                field[startX][i]++;
            }

        } else if (startY == endY) {
            System.out.print("Horizontal:");
            System.out.println(startX+","+startY+"-->"+endX+","+endY);
            if(startX>endX) {
                int temp = startX;
                startX = endX;
                endX = temp;
            }
            for (int i = startX; i <= endX; i++) {
                System.out.println(i+","+startY);
                field[i][startY]++;
            }
        } else {
            System.out.println("DIAGONAL: "+startX+","+startY+"-->"+endX+","+endY);
            if(startX < endX && startY < endY) {
                int y = startY;
                for (int i = startX; i <= endX; i++) {

                    field[i][y]++;
                    y++;
                }
            }
            if(startX > endX && startY < endY) {
                //startx 8 > 0 endx
                //starty 0 < 8 endy
                //System.out.println("CASE2");
                int y = startY;
                for (int i = startX; i >= endX; i--) {
                    field[i][y]++;
                    y++;
                }
            }
            if(startX < endX && startY > endY) {
                System.out.println("CASE 3");
                int y = startY;
                for (int i = startX; i <= endX; i++) {
                    field[i][y]++;
                    y--;
                }
            }
            if(startX > endX && startY > endY) {
                //System.out.println("CASE4");
                int y = startY;
                for (int i = startX; i >= endX; i--) {
                    field[i][y]++;
                    y--;
                }
            }

        }

    }

    private static int count(int[][] field) {
        int result = 0;
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                int value = field[x][y];
                if (value>1) {
                    result++;
                }
            }
        }
        return result;
    }

    private static void print(int[][] field) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                int value = field[x][y];
                if (value==0) {
                    sb.append(".");
                } else {
                    sb.append(value);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}