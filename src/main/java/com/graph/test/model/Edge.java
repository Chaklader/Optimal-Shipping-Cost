package com.graph.test.model;

/**
 * Created by Chaklader on 3/5/17.
 */
public class Edge {

    private final Vertex source;
    private final Vertex destination;
    private final int weight;

    public Edge(final Vertex source, final Vertex destination, final int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }
}
