package de.steffzilla.aoc.y2022;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import de.steffzilla.competitive.AocUtils;
import org.javatuples.Pair;

public class Aoc2022_09 {

    private static final String DAY = "09";
    private static final String YEAR = "2022";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;
    //private static final String COMMANDS_PATTERN = "(\\w{1}) (\\d+)";
    private static Pair<Integer, Integer> headPos;
    private static Pair<Integer, Integer> tailPos;
    private static HashSet<Pair<Integer,Integer>> tailPositions;
    private static List<Pair<Integer, Integer>> rope;
    private static int NUMBER_OF_KNOTS = 10;

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);

        //part1(inputLines);
        //part1WithList(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines){
        //Pattern pattern = Pattern.compile(COMMANDS_PATTERN);
        tailPositions = new HashSet<>();
        tailPositions.add(new Pair<>(0,0));
        headPos = new Pair<>(0,0);
        tailPos = new Pair<>(0,0);

        for (String line : inputLines) {
            /*Matcher matcher = pattern.matcher(line);
            String direction = matcher.group(1);
            String steps = matcher.group(2);*/
            String[] strings = line.split(" ");
            String direction = strings[0];
            int steps = Integer.parseInt(strings[1]);
            System.out.println(direction+steps);
            for (int i = 0; i < steps; i++) {
                move(direction);
            }

        }
        System.out.println("\nPart 1 > Result: " + tailPositions.size()); // 6057
    }

    private static void move(String direction) {
        if("R".equals(direction)) {
            headPos = new Pair<>(headPos.getValue0()+1, headPos.getValue1());
        } else if("L".equals(direction)) {
            headPos = new Pair<>(headPos.getValue0()-1, headPos.getValue1());
        } else if("U".equals(direction)) {
            headPos = new Pair<>(headPos.getValue0(), headPos.getValue1()+1);
        } else if("D".equals(direction)) {
            headPos = new Pair<>(headPos.getValue0(), headPos.getValue1()-1);
        } else {
            throw new IllegalStateException("Unexpected direction: " + direction);
        }
        moveTail();
        //System.out.println("-->Head:"+headPos+"|Tail:"+tailPos);
    }

    private static void moveTail() {
        if(headPos.equals(tailPos)) {
            return;
        }
        if(headPos.getValue0().equals(tailPos.getValue0())) {
            // vertical away
            if (headPos.getValue1() - tailPos.getValue1() == 2) {
                tailPos = new Pair<>(tailPos.getValue0(), tailPos.getValue1()+1);
                tailPositions.add(tailPos);
                return;
            }
            if (headPos.getValue1() - tailPos.getValue1() == -2) {
                tailPos = new Pair<>(tailPos.getValue0(), tailPos.getValue1()-1);
                tailPositions.add(tailPos);
                return;
            }
        } else if(headPos.getValue1().equals(tailPos.getValue1())){
            // horizontal away
            if (headPos.getValue0() - tailPos.getValue0() == 2) {
                tailPos = new Pair<>(tailPos.getValue0()+1, tailPos.getValue1());
                tailPositions.add(tailPos);
                return;
            }
            if (headPos.getValue0() - tailPos.getValue0() == -2) {
                tailPos = new Pair<>(tailPos.getValue0()-1, tailPos.getValue1());
                tailPositions.add(tailPos);
                return;
            }
        } else {
            // diagonal
            // horizontal ahead
            if(headPos.getValue0() - tailPos.getValue0() == 2) {
                tailPos = new Pair<>(tailPos.getValue0()+1, headPos.getValue1());
                tailPositions.add(tailPos);
                return;
            }
            if(headPos.getValue0() - tailPos.getValue0() == -2) {
                tailPos = new Pair<>(tailPos.getValue0()-1, headPos.getValue1());
                tailPositions.add(tailPos);
                return;
            }
            // vertical ahead
            if(headPos.getValue1() - tailPos.getValue1() == 2) {
                tailPos = new Pair<>(headPos.getValue0(), tailPos.getValue1()+1);
                tailPositions.add(tailPos);
                return;
            }
            if(headPos.getValue1() - tailPos.getValue1() == -2) {
                tailPos = new Pair<>(headPos.getValue0(), tailPos.getValue1()-1);
                tailPositions.add(tailPos);
                return;
            }
        }
    }

    private static void movePart2(String direction) {
        Pair<Integer, Integer> headPosBefore = rope.get(0);
        if("R".equals(direction)) {
            rope.set(0, new Pair<>(headPosBefore.getValue0()+1, headPosBefore.getValue1()));
        } else if("L".equals(direction)) {
            rope.set(0, new Pair<>(headPosBefore.getValue0()-1, headPosBefore.getValue1()));
        } else if("U".equals(direction)) {
            rope.set(0, new Pair<>(headPosBefore.getValue0(), headPosBefore.getValue1()+1));
        } else if("D".equals(direction)) {
            rope.set(0, new Pair<>(headPosBefore.getValue0(), headPosBefore.getValue1()-1));
        } else {
            throw new IllegalStateException("Unexpected direction: " + direction);
        }
        for (int i = 1; i < NUMBER_OF_KNOTS; i++) {
            moveKnot(i);
            //printRope(6, 5, 11,5, true);
        }
        //printRope(6, 5, 11,5, true);
        //printRope(26, 21, 11,5, true);
    }

    private static void moveKnot(int posToBeMoved) {
        Pair<Integer, Integer> predecessor = rope.get(posToBeMoved - 1);
        Pair<Integer, Integer> currentKnot = rope.get(posToBeMoved);
        if(predecessor.equals(currentKnot)) {
            return;
        }
        if(predecessor.getValue0().equals(currentKnot.getValue0())) {
            // vertical away
            if (predecessor.getValue1() - currentKnot.getValue1() == 2) {
                rope.set(posToBeMoved, new Pair<>(currentKnot.getValue0(), currentKnot.getValue1()+1));
                if (posToBeMoved == NUMBER_OF_KNOTS-1) {
                    tailPositions.add(rope.get(posToBeMoved));
                }
                return;
            }
            if (predecessor.getValue1() - currentKnot.getValue1() == -2) {
                rope.set(posToBeMoved, new Pair<>(currentKnot.getValue0(), currentKnot.getValue1()-1));
                if (posToBeMoved == NUMBER_OF_KNOTS-1) {
                    tailPositions.add(rope.get(posToBeMoved));
                }
                return;
            }
        } else if(predecessor.getValue1().equals(currentKnot.getValue1())){
            // horizontal away
            if (predecessor.getValue0() - currentKnot.getValue0() == 2) {
                rope.set(posToBeMoved, new Pair<>(currentKnot.getValue0()+1, currentKnot.getValue1()));
                if (posToBeMoved == NUMBER_OF_KNOTS-1) {
                    tailPositions.add(rope.get(posToBeMoved));
                }
                return;
            }
            if (predecessor.getValue0() - currentKnot.getValue0() == -2) {
                rope.set(posToBeMoved, new Pair<>(currentKnot.getValue0()-1, currentKnot.getValue1()));
                if (posToBeMoved == NUMBER_OF_KNOTS-1) {
                    tailPositions.add(rope.get(posToBeMoved));
                }
                return;
            }
        } else {
            // diagonal detached; even 0,0 and 2,2 is possible!
            // move 1 step diagonal
            // horizontal ahead
            if(predecessor.getValue0() - currentKnot.getValue0() == 2) {
                if(predecessor.getValue1() > currentKnot.getValue1()) {
                    rope.set(posToBeMoved,
                            new Pair<>(currentKnot.getValue0()+1, currentKnot.getValue1()+1));
                } else {
                    rope.set(posToBeMoved,
                            new Pair<>(currentKnot.getValue0()+1, currentKnot.getValue1()-1));
                }
                if (posToBeMoved == NUMBER_OF_KNOTS-1) {
                    tailPositions.add(rope.get(posToBeMoved));
                }
            } else if(predecessor.getValue0() - currentKnot.getValue0() == -2) {
                // horizontal behind
                if(predecessor.getValue1() > currentKnot.getValue1()) {
                    rope.set(posToBeMoved,
                            new Pair<>(currentKnot.getValue0()-1, currentKnot.getValue1()+1));
                } else {
                    rope.set(posToBeMoved,
                            new Pair<>(currentKnot.getValue0()-1, currentKnot.getValue1()-1));
                }
                if (posToBeMoved == NUMBER_OF_KNOTS-1) {
                    tailPositions.add(rope.get(posToBeMoved));
                }
            } else if(predecessor.getValue1() - currentKnot.getValue1() == 2) {
                // vertical ahead
                if(predecessor.getValue0() > currentKnot.getValue0()) {
                    rope.set(posToBeMoved,
                            new Pair<>(currentKnot.getValue0()+1, currentKnot.getValue1()+1));
                } else {
                    rope.set(posToBeMoved,
                            new Pair<>(currentKnot.getValue0()-1, currentKnot.getValue1()+1));
                }
                if (posToBeMoved == NUMBER_OF_KNOTS-1) {
                    tailPositions.add(rope.get(posToBeMoved));
                }
            } else if(predecessor.getValue1() - currentKnot.getValue1() == -2) {
                // vertical behind
                if(predecessor.getValue0() > currentKnot.getValue0()) {
                    rope.set(posToBeMoved,
                            new Pair<>(currentKnot.getValue0()+1, currentKnot.getValue1()-1));
                } else {
                    rope.set(posToBeMoved,
                            new Pair<>(currentKnot.getValue0()-1, currentKnot.getValue1()-1));
                }
                if (posToBeMoved == NUMBER_OF_KNOTS-1) {
                    tailPositions.add(rope.get(posToBeMoved));
                }
            }
        }
    }

    private static void part2(List<String> inputLines) {
        rope = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_KNOTS; i++) {
            rope.add(new Pair<>(0,0));
        }
        tailPositions = new HashSet<>();
        tailPositions.add(new Pair<>(0,0));

        for (String line : inputLines) {
            String[] strings = line.split(" ");
            String direction = strings[0];
            int steps = Integer.parseInt(strings[1]);
            System.out.println(direction+steps);
            for (int i = 0; i < steps; i++) {
                movePart2(direction);
            }

        }
        System.out.println("\nPart 2 > Result: " + tailPositions.size()); // 2195 is too low
    }

    private static void part1WithList(List<String> inputLines) {
        rope = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_KNOTS; i++) {
            rope.add(new Pair<>(0,0));
        }
        tailPositions = new HashSet<>();
        tailPositions.add(new Pair<>(0,0));

        for (String line : inputLines) {
            String[] strings = line.split(" ");
            String direction = strings[0];
            int steps = Integer.parseInt(strings[1]);
            System.out.println(direction+steps);
            for (int i = 0; i < steps; i++) {
                movePart2(direction);
            }

        }
        System.out.println("\nPart 1 > Result: " + tailPositions.size());
    }

    private static void printRope(int sizeX, int sizeY, int offsetX, int offsetY, boolean yStartsAtBottom) {
        String[][] field = new String[sizeX][sizeX];
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                field[x][y] = ".";
            }
        }
        for (int i = NUMBER_OF_KNOTS-1; i >= 0; i--) {
            field[rope.get(i).getValue0()][rope.get(i).getValue1()]=String.valueOf(i);
        }
        // TODO Overhead berücksichtigen
        // print field
        if(yStartsAtBottom) {
            for (int y = sizeY-1; y >= 0; y--) {
                for (int x = 0; x < sizeX; x++) {
                    System.out.print(field[x][y]);
                }
                System.out.println();
            }
        } else {
            for (int y = 0; y < sizeY; y++) {
                for (int x = 0; x < sizeX; x++) {
                    System.out.print(field[x][y]);
                }
                System.out.println();
            }
        }
        System.out.println();
    }

}