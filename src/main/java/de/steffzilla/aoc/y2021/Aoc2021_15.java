package de.steffzilla.aoc.y2021;

import java.util.*;
import com.google.common.graph.*;
import de.steffzilla.competitive.AocUtils;
import de.steffzilla.competitive.DijkstraWithPriorityQueue;
import de.steffzilla.competitive.NodeWrapper;

public class Aoc2021_15 {

    private static final String DAY = "15";
    private static final String YEAR = "2021";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    //public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;
    private final static int FACTOR = 5;

    public static void main(String[] args) {
        List<String> inputLines = AocUtils.getStringList(PATH);

        //part1(inputLines);
        part2(inputLines);
    }

    private static void part1(List<String> inputLines) {
        HashMap<String, CavePos> cavePositions = new HashMap<>();
        int lineCount = 0;
        int maxX = inputLines.get(0).length()-1;
        for (String line : inputLines) {
            for (int x = 0; x < line.length(); x++) {
                int riskLevel = Integer.parseInt(line.substring(x,x+1));
                String name = x+","+lineCount;
                CavePos cavePos = new CavePos(name, riskLevel);
                cavePositions.put(name, cavePos);
                if(lineCount > 0) {
                    CavePos predecessor = cavePositions.get(x + "," + (lineCount - 1));
                    predecessor.addSuccessor(cavePos);
                }
                if(x > 0) {
                    CavePos predecessor = cavePositions.get((x-1) + "," + lineCount);
                    predecessor.addSuccessor(cavePos);
                }
            }
            lineCount++;
        }

        calculateCosts(cavePositions);
        String x = maxX + "," + (lineCount-1);
        System.out.println(x);
        CavePos endCavePos = cavePositions.get(x);
        System.out.println(endCavePos.getTotalCosts());
    }

    private static void calculateCosts(HashMap<String, CavePos> cavePositions) {
        Set<CavePos> visited = new HashSet<>();
        Queue<CavePos> queue = new PriorityQueue<>();//new LinkedList<>();
        CavePos start = cavePositions.get("0,0");
        start.setTotalCosts(0);
        queue.add(start);
        while(!queue.isEmpty()) {
            CavePos selected = queue.poll();
            if(!visited.contains(selected)) {
                int totalCostsSelected = selected.getTotalCosts();
                Set<CavePos> successors = selected.getSuccessors();
                for (CavePos successor : successors) {
                    int oldTotalCosts = successor.getTotalCosts();
                    int newTotalCosts = totalCostsSelected + successor.getRiskLevel();
                    if (!queue.contains(successor)) {
                        successor.setTotalCosts(newTotalCosts);
                        successor.setPredecessor(selected);
                        queue.add(successor);
                    } else if (newTotalCosts < oldTotalCosts) {
                        successor.setTotalCosts(newTotalCosts);
                        successor.setPredecessor(selected);
                        // The position in the PriorityQueue won't change automatically;
                        // we have to remove and reinsert the node
                        queue.remove(successor);
                        queue.add(successor);
                    }

                }
                visited.add(selected);
            }
        }
        System.out.println("Visited:"+visited.size());
    }

    private static void part2(List<String> inputLines) {
        MutableValueGraph<String, Integer> graph = ValueGraphBuilder.undirected().build();

        int lineCount = 0;
        int maxXorg = inputLines.get(0).length();
        int maxYorg = inputLines.size();
        for (String line : inputLines) {
            for (int xOrg = 0; xOrg < line.length(); xOrg++) {
                int riskLevelOrg = Integer.parseInt(line.substring(xOrg,xOrg+1));

                // create the field with FACTOR * FACTOR times
                for (int x = 0; x < FACTOR; x++) {
                    for (int y = 0; y < FACTOR; y++) {
                        int xCoordinate = xOrg + x * maxXorg;
                        int yCoordinate = lineCount + y * maxYorg;

                        String cavePositionName = xCoordinate+","+yCoordinate;
                        int newRiskLevel = riskLevelOrg + x + y;
                        if(newRiskLevel > 9) {
                            newRiskLevel = newRiskLevel - 9;
                        }

                        if(yCoordinate > 0) {
                            String predecessorName = xCoordinate + "," + (yCoordinate - 1);
                            graph.putEdgeValue(predecessorName, cavePositionName, newRiskLevel);
                            //System.out.println(predecessorName+"|"+cavePositionName+"|"+newRiskLevel);
                        }
                        if(xCoordinate > 0) {
                            String predecessorName = (xCoordinate-1) + "," + yCoordinate;
                            graph.putEdgeValue(predecessorName, cavePositionName, newRiskLevel);
                            //System.out.println(predecessorName+"|"+cavePositionName+"|"+newRiskLevel);
                        }
                    }
                }
            }
            lineCount++;
        }

        String nameOfEndNode = ((inputLines.get(0).length() * FACTOR)-1) + "," + ((lineCount*FACTOR)-1);

        List<NodeWrapper<String>> shortestPath = DijkstraWithPriorityQueue.findShortestPath(graph, "0,0", nameOfEndNode);
        int tempOlddistance = 0;
        for (NodeWrapper<String> nodeWrapper : shortestPath) {
            System.out.println(nodeWrapper.getNode()+"|"+(nodeWrapper.getTotalDistance()-tempOlddistance));
            tempOlddistance = nodeWrapper.getTotalDistance();
        }
    }

    private static void part2_old(List<String> inputLines) {
        HashMap<String, CavePos> cavePositions = new HashMap<>();
        int lineCount = 0;
        int maxXorg = inputLines.get(0).length();
        int maxYorg = inputLines.size();
        for (String line : inputLines) {
            for (int xOrg = 0; xOrg < line.length(); xOrg++) {
                int riskLevelOrg = Integer.parseInt(line.substring(xOrg,xOrg+1));

                // create the field with FACTOR * FACTOR times
                for (int x = 0; x < FACTOR; x++) {
                    for (int y = 0; y < FACTOR; y++) {
                        int xCoordinate = xOrg + x * maxXorg;
                        int yCoordinate = lineCount + y * maxYorg;

                        String cavePositionName = xCoordinate+","+yCoordinate;
                        int newRiskLevel = riskLevelOrg + x + y;
                        if(newRiskLevel > 9) {
                            newRiskLevel = newRiskLevel - 9;
                        }

                        //System.out.println(cavePositionName+"|"+newRiskLevel);

                        CavePos cavePos = cavePositions.get(cavePositionName);
                        if (null == cavePos) {
                            cavePos = new CavePos(cavePositionName, newRiskLevel);
                            cavePositions.put(cavePositionName, cavePos);
                        } else {
                            cavePos.setRiskLevel(newRiskLevel);
                        }

                        if(yCoordinate > 0) {
                            String predecessorName = xCoordinate + "," + (yCoordinate - 1);
                            setLinks(cavePositions, cavePos, predecessorName);
                        }
                        if(xCoordinate > 0) {
                            String predecessorName = (xCoordinate-1) + "," + yCoordinate;
                            setLinks(cavePositions, cavePos, predecessorName);
                        }
                    }
                }
            }
            lineCount++;
        }

        // Ausgabe des samples
        /*for (int y = 0; y < 50; y++) {
            for (int x = 0; x < 50; x++) {
                CavePos cavePos = cavePositions.get(x + "," + y);
                System.out.print(cavePos.getRiskLevel());
            }
            System.out.println();
        }*/

        calculateCosts(cavePositions);

        String nameOfEndNode = ((inputLines.get(0).length() * FACTOR)-1) + "," + ((lineCount*FACTOR)-1);
        CavePos endCavePos = cavePositions.get(nameOfEndNode);
        Set<CavePos> shortedPath = new HashSet<>();
        CavePos temp = endCavePos;
        shortedPath.add(endCavePos);
        while (true) {
            temp = temp.getPredecessor();
            if (null==temp) {
                break;
            }
            shortedPath.add(temp);
            //System.out.println(temp);
        }

        // print shortest path
        for (int y = 0; y < 50; y++) {
            for (int x = 0; x < 50; x++) {
                CavePos cavePos = cavePositions.get(x + "," + y);
                if(shortedPath.contains(cavePos)) {
                    System.out.print(cavePos.getRiskLevel());
                } else {
                    System.out.print("#");
                }

            }
            System.out.println();
        }

        System.out.println("Costs to "+ nameOfEndNode + ": " + endCavePos.getTotalCosts());
    }

    private static void setLinks(HashMap<String, CavePos> cavePositions, CavePos cave, String predecessorName) {
        CavePos predecessor = cavePositions.get(predecessorName);
        if (predecessor == null) {
            predecessor = new CavePos(predecessorName, Integer.MAX_VALUE);
            cavePositions.put(predecessorName, predecessor);
        }
        predecessor.addSuccessor(cave);
    }

}