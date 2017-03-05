package com.graph.test.model;

/**
 * Created by Chaklader on 3/5/17.
 */
public class ShippingParameters {
    private static final int VOLUMETRIC_CONSTANT = 5000;

    private String target;
    private int width;
    private int length;
    private int height;
    private double weight;

    public ShippingParameters(final String target,
                              final int width,
                              final int length,
                              final int height,
                              final double weight) {
        this.target = target;
        this.width = width;
        this.length = length;
        this.height = height;
        this.weight = weight;
    }

    public String getTarget() {
        return target;
    }

    public double getNormalizedWeight() {
        double result = getVolumetricWeight();
        if (result < weight) {
            result = roundUp(weight);
        }
        return result;
    }

    protected double getVolumetricWeight() {
        double volumetricWeight = ((double)(width * length * height)) / VOLUMETRIC_CONSTANT;
        return roundUp(volumetricWeight);
    }

    protected double roundUp(final double value) {
        int val = (int) value;
        double fr = value - val;
        double result;
        if (fr > 0.5) {
            result = val + 1;
        } else {
            result = val + 0.5;
        }
        return result;
    }
}

