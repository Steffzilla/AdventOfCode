package de.steffzilla.aoc;

public class NodeWrapper<N> implements Comparable<NodeWrapper<N>> {

    private final N node;
    private int totalDistance;
    private NodeWrapper<N> predecessor;

    public NodeWrapper(N node, int totalDistance, NodeWrapper<N> predecessor) {
        this.node = node;
        this.totalDistance = totalDistance;
        this.predecessor = predecessor;
    }

    public N getNode() {
        return node;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public NodeWrapper<N> getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(NodeWrapper<N> predecessor) {
        this.predecessor = predecessor;
    }

    @Override
    public int compareTo(NodeWrapper<N> o) {
        return Integer.compare(this.totalDistance, o.totalDistance);
    }

    // Using identity for equals and hashcode here, which is much faster.
    // It's sufficient as within the algorithm, we have only one NodeWrapper instance per node.

    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "NodeWrapper{" +
                "node=" + node +
                ", totalDistance=" + totalDistance +
                '}';
    }
}
