package com.shipping.graph.model;


import org.junit.Test;

/**
 * Created by Chaklader on 3/6/17.
 */
public class ShippingParametersTest {

    @Test
    public void getVolumetricWeightSTest() {
        ShippingParameters parameters = new ShippingParameters(null, 1, 1, 1, 0.4);
        double v = parameters.getVolumetricWeight();
        assert (v == 0.5);
    }

    @Test
    public void getVolumetricWeightTest() {
        ShippingParameters parameters = new ShippingParameters(null, 26, 10, 11, 0.4);
        double v = parameters.getVolumetricWeight();
        assert (v == 1.0);
    }

    @Test
    public void getVolumetricWeightBTest() {
        ShippingParameters parameters = new ShippingParameters(null, 26, 20, 22, 0.4);
        double v = parameters.getVolumetricWeight();
        assert (v == 2.5);
    }

    @Test
    public void getNormalizedWeightTest() {
        ShippingParameters parameters = new ShippingParameters(null, 26, 10, 11, 0.4);
        double v = parameters.getNormalizedWeight();
        assert (v == 1);
    }

    @Test
    public void getNormalizedWeightBTest() {
        ShippingParameters parameters = new ShippingParameters(null, 26, 10, 11, 1.4);
        double v = parameters.getNormalizedWeight();
        assert (v == 1.5);
    }

}
