package com.shipping.graph.dao;

/**
 * Created by Chaklader on 3/28/17.
 */
public class EdgeData {
    private final String source;
    private final String target;
    private final int weight;

    public EdgeData(String source, String target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }
}
