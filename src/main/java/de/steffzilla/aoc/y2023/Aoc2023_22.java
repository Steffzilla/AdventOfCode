package de.steffzilla.aoc.y2023;

import de.steffzilla.aoc.AocUtils;
import org.javatuples.Triplet;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2023_22 {

    private static final String DAY = "22";
    private static final String YEAR = "2023";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;

    private static final HashMap<Triplet<Integer, Integer, Integer>, Brick> landed = new HashMap<>();
    private static final List<Brick> bricks = new ArrayList<>();

    private static class Brick {
        String name;
        List<Triplet<Integer, Integer, Integer>> blocks;
        int lowestZ;
        Set<Brick> supportingBricks;
        public Brick (String name, List<Triplet<Integer, Integer, Integer>> blocks, int lowestZ, Set<Brick> supportingBricks) {
            this.name = name;
            this.blocks = blocks;
            this.lowestZ = lowestZ;
            this.supportingBricks = supportingBricks;
        }

        @Override
        public String toString() {
            return "Brick{" +
                    "name='" + name + '\'' +
                    ", blocks=" + blocks +
                    ", lowestZ=" + lowestZ +
                    ", supportingBricks=" + supportingBricks +
                    '}';
        }

        public String getName() {
            return this.name;
        }
    }

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);

        HashMap<String, Brick> essentialBricks = part1(inputLines);
        part2(essentialBricks);
    }

    private static HashMap<String, Brick> part1(List<String> inputLines) {
        SortedMap<Integer, List<Brick>> bricksByZPosition = new TreeMap<>();
        Pattern pattern = Pattern.compile("(\\d+),(\\d+),(\\d+)~(\\d+),(\\d+),(\\d+)");
        for (String line : inputLines) {
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            Triplet<Integer, Integer, Integer> startPoint =
                    new Triplet<>(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)), Integer.valueOf(matcher.group(3)));
            Triplet<Integer, Integer, Integer> endPoint =
                    new Triplet<>(Integer.valueOf(matcher.group(4)), Integer.valueOf(matcher.group(5)), Integer.valueOf(matcher.group(6)));
            Brick brick = createBrick(line, startPoint, endPoint);
            bricks.add(brick);
            if (!bricksByZPosition.containsKey(brick.lowestZ)) {
                bricksByZPosition.put(brick.lowestZ, new ArrayList<>());
            }
            List<Brick> brickList = bricksByZPosition.get(brick.lowestZ);
            brickList.add(brick);
        }
        landBricks(bricksByZPosition);
        HashMap<String, Brick> essentialBricks = new HashMap<>();
        for (Brick brick : bricks) {
            if(brick.supportingBricks.size()==1) {
                Optional<Brick> optional = brick.supportingBricks.stream().findFirst();
                essentialBricks.put(optional.get().name, optional.get());
            }
          StringBuilder sb = new StringBuilder();
            for (Brick supportingBrick : brick.supportingBricks) {
                sb.append(supportingBrick.name);
                sb.append(" ");
            }
            System.out.println(brick.name + "|" + brick.lowestZ + " is supported by " + sb);
        }
        System.out.println("\nPart 1 > Result: " + (bricks.size() - essentialBricks.size()));
        return essentialBricks;
    }

    private static void landBricks(SortedMap<Integer, List<Brick>> bricksByZPosition) {
        for (List<Brick> bricks : bricksByZPosition.values()) {
            System.out.println(bricks.get(0).lowestZ);
            for (Brick brick : bricks) {
                System.out.println(brick);
                int movedBy = 0;
                // 1 is the lowest level
                while (brick.lowestZ > 1) {
                    // check if moving is possible

                    for (Triplet<Integer, Integer, Integer> block : brick.blocks) {
                        Triplet<Integer, Integer, Integer> movedBlock
                                = new Triplet<>(block.getValue0(), block.getValue1(), block.getValue2() - (movedBy + 1));
                        Brick otherBrick = landed.get(movedBlock);
                        // connect blocks
                        if (otherBrick!=null) {
                            brick.supportingBricks.add(otherBrick);
                        }
                    }
                    if (!brick.supportingBricks.isEmpty()) {
                        // brick has landed
                        break;
                    }
                    movedBy++;
                    brick.lowestZ--;
                }
                // final z pos found --> adjust z for blocks if necessary
                if (movedBy > 0) {
                    List<Triplet<Integer, Integer, Integer>> newBlocks = new ArrayList<>();
                    for (Triplet<Integer, Integer, Integer> block : brick.blocks) {
                        newBlocks.add(new Triplet<>(block.getValue0(), block.getValue1(), block.getValue2()-movedBy));
                    }
                    brick.blocks = newBlocks;
                }
                // add blocks to landed
                for (Triplet<Integer, Integer, Integer> block : brick.blocks) {
                    landed.put(block, brick);
                }
            }
        }
    }

    private static Brick createBrick(String name, Triplet<Integer, Integer, Integer> startPoint, Triplet<Integer, Integer, Integer> endPoint) {
        ArrayList<Triplet<Integer, Integer, Integer> > blocks = new ArrayList<>();
        if (startPoint.equals(endPoint)) {
            // 1-block cube
            blocks.add(startPoint);
        } else if ( !startPoint.getValue0().equals(endPoint.getValue0()) &&
                startPoint.getValue1().equals(endPoint.getValue1()) &&
                startPoint.getValue2().equals(endPoint.getValue2()) ) {
            // brick in x direction
            assert startPoint.getValue0() < endPoint.getValue0();
            for (int x = startPoint.getValue0(); x <= endPoint.getValue0(); x++) {
                blocks.add(new Triplet<>(x, startPoint.getValue1(), startPoint.getValue2()));
            }
        } else if ( startPoint.getValue0().equals(endPoint.getValue0()) &&
                !startPoint.getValue1().equals(endPoint.getValue1()) &&
                startPoint.getValue2().equals(endPoint.getValue2()) ) {
            // brick in y direction
            assert startPoint.getValue1() < endPoint.getValue1();
            for (int y = startPoint.getValue1(); y <= endPoint.getValue1(); y++) {
                blocks.add(new Triplet<>(startPoint.getValue0(), y, startPoint.getValue2()));
            }
        } else if ( startPoint.getValue0().equals(endPoint.getValue0()) &&
                startPoint.getValue1().equals(endPoint.getValue1()) &&
                !startPoint.getValue2().equals(endPoint.getValue2()) ) {
            // brick in z direction
            assert startPoint.getValue2() < endPoint.getValue2();
            for (int z = startPoint.getValue2(); z <= endPoint.getValue2(); z++) {
                blocks.add(new Triplet<>(startPoint.getValue0(), startPoint.getValue1(), z));
            }
        } else {
            throw new IllegalStateException(name);
        }
        assert !blocks.isEmpty();
        return new Brick(name, blocks, startPoint.getValue2(), new HashSet<>());
    }

    private static void part2(HashMap<String, Brick> essentialBricks) {
        // FIXME optimierung nicht nötig --> kann man sich sparen
        // essential bricks in reverse order
        /*SortedMap<Integer, List<Brick>> essentialBricksByZPosition = new TreeMap<>(Collections.reverseOrder());
        for (Brick brick : essentialBricks.values()) {
            if (!essentialBricksByZPosition.containsKey(brick.lowestZ)) {
                essentialBricksByZPosition.put(brick.lowestZ, new ArrayList<>());
            }
            List<Brick> brickList = essentialBricksByZPosition.get(brick.lowestZ);
            brickList.add(brick);
        }*/

        HashMap<String, List<String>> supported2Supporters = getSupported2Supporters();
        HashMap<String, List<String>> supporter2supported = getSupporter2supported();

        long count = 0;
        //for (List<Brick> bricks: essentialBricksByZPosition.values()) {
//            for (Brick essentialBrick : essentialBricks.values()) {
//                Set<String> supportedBricks = getSupportedBricksNames(essentialBrick.name, supporter2supported);
//                count+=supportedBricks.size();
//            }
        //}
        for (Brick essentialBrick : essentialBricks.values()) {
            HashMap<String, List<String>> graphAbove = new HashMap<>();
            getSubGraphAbove(graphAbove, essentialBrick.name, supporter2supported);
            count += (graphAbove.size() - 1);
        }
        System.out.println("\nPart 2 > Result: " + count);
    }

    private static Set<String> getSupportedBricksNames(String supportingBrickName, HashMap<String, List<String>> supporter2supported) {
        Set<String> supportedBricks = new HashSet<>();
        List<String> supported = supporter2supported.get(supportingBrickName);
        if (supported != null) {
            for (String nameOfSupportedBrick : supported) {
                supportedBricks.add(nameOfSupportedBrick);
                supportedBricks.addAll(getSupportedBricksNames(nameOfSupportedBrick, supporter2supported));
            }
        }
        return supportedBricks;
    }

    private static void getSubGraphAbove(HashMap<String, List<String>> graph, String supportingBrickName, HashMap<String, List<String>> supporter2supported) {
        List<String> supportedBlocksNames = supporter2supported.get(supportingBrickName);
        if (supportedBlocksNames != null) {
            for (String supportedBlockName : supportedBlocksNames) {
                if (!graph.containsKey(supportingBrickName)) {
                    graph.put(supportingBrickName, new ArrayList<>());
                }
                List<String> neighbours = graph.get(supportingBrickName);
                neighbours.add(supportedBlockName);
                // other direction
                if (!graph.containsKey(supportedBlockName)) {
                    graph.put(supportedBlockName, new ArrayList<>());
                }
                neighbours = graph.get(supportedBlockName);
                neighbours.add(supportingBrickName);
                // bricks/nodes above
                getSubGraphAbove(graph, supportedBlockName, supporter2supported);
            }
        }
    }

    private static HashMap<String, List<String>> getSupporter2supported() {
        HashMap<String, List<String>> supporterName2Bricks = new HashMap<>();
        for (Brick supportedBrick : bricks) {
            for (Brick supporter : supportedBrick.supportingBricks) {
                if (!supporterName2Bricks.containsKey(supporter.name)) {
                    supporterName2Bricks.put(supporter.name, new ArrayList<>());
                }
                List<String> supportedBricksNames = supporterName2Bricks.get(supporter.name);
                supportedBricksNames.add(supportedBrick.name);
            }
        }
        return supporterName2Bricks;
    }

    // FIXME remove it
    private static HashMap<String, List<String>> getSupported2Supporters() {
        HashMap<String, List<String>> name2Supporters = new HashMap<>();
        for (Brick brick : bricks) {
            List<String> brickNames = brick.supportingBricks.stream().map(Brick::getName).toList();
            name2Supporters.put(brick.name, brickNames);
        }
        return name2Supporters;
    }

}