package com.graph.test.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by Chaklader on 3/5/17.
 */
public class Shipping {
    private ShippingParameters params;
    private List<Vertex> path = Collections.emptyList();

    public Shipping(ShippingParameters params) {
        this.params = params;
    }

    public List<Vertex> getPath() {
        return path;
    }

    public void setPath(final List<Vertex> path) {
        this.path = path;
    }

    public double calculateSippingCost() {
        return calculateSippingCost(params.getNormalizedWeight());
    }

    protected double calculateSippingCost(double normalizedWeight) {
        int hard = getTotalHard();
        double cost = Math.sqrt(hard) * normalizedWeight;
        return round(cost);
    }

    protected int getTotalHard() {
        int hardSum = 0;
        Vertex src = null;
        for (Vertex vertex : path) {
            if (src == null) {
                src = vertex;
            } else {
                Edge edge = src.getEdge(vertex);
                if (edge == null) {
                    throw new IllegalStateException("Edge not found. Destination: " + vertex);
                }
                hardSum += edge.getWeight();
                src = vertex;
            }
        }
        return hardSum;
    }

    public static double round(double value) {
        return round(value, 2);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}

