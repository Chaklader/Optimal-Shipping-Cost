package com.graph.test;

import com.graph.test.model.ShippingParameters;

/**
 * Created by Chaklader on 3/6/17.
 */
public class ShippingTestCase {

    private ShippingParameters shippingParameters;
    private Double expectedCost;

    public ShippingTestCase(ShippingParameters shippingParameters, Double expectedCost) {
        this.shippingParameters = shippingParameters;
        this.expectedCost = expectedCost;
    }

    public ShippingParameters getShippingParameters() {
        return shippingParameters;
    }

    public Double getExpectedCost() {
        return expectedCost;
    }
}

