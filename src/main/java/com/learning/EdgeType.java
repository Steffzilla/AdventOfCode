package com.learning;

public class EdgeType
{
    private String nodeU;
    private String nodeV;
    private String label;

    public EdgeType(String nodeU, String nodeV) {
        this(nodeU, nodeV, nodeU+"-"+nodeV);
    }

    public EdgeType(String nodeU, String nodeV, String label) {
        this.nodeU = nodeU;
        this.nodeV = nodeV;
        this.label = label;
    }

    public String getNodeU() {
        return this.nodeU;
    }

    public String getNodeV() {
        return this.nodeV;
    }

    @Override
    public String toString() {
        return "EdgeType{" +
                "label='" + label + '\'' +
                '}';
    }

    public String getLabel() {
        return label;
    }
}
