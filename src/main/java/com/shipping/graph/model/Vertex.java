package com.shipping.graph.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Chaklader on 3/5/17.
 */
public class Vertex {

    final private String name;

    // destination vortex for the respective edge of the network
    final private Map<Vertex, Edge> edges = new HashMap<>();

    public Vertex(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Vertex) {
            Vertex other = (Vertex) obj;
            return Objects.equals(name, other.name);
        }
        return false;
    }

    /* Join the edge to the destination vortex
    * of the previous edge in the graph network*/
    public void addEdge(final Edge edge) {
        Vertex src = edge.getSource();
        if (this.equals(src)) {
            Vertex dest = edge.getDestination();
            edges.put(dest, edge);
        }
    }

    public Edge getEdge(final Vertex dest) {
        return edges.get(dest);
    }

    // get the edge by the destination vortex name
    public Edge getEdge(final String dest) {
        return edges.get(new Vertex(dest));
    }

    @Override
    public String toString() {
        return name;
    }
}

