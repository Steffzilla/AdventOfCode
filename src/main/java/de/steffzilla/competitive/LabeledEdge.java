package de.steffzilla.competitive;

import org.jgrapht.graph.DefaultEdge;

/**
 * Edge in a JGrapht graph with a label.
 * Extends {@link DefaultEdge}.
 */
public class LabeledEdge extends DefaultEdge {
    private final String label;

    public LabeledEdge(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "(" + getSource() + " : " + getTarget() + " : " + label + ")";
    }
}
