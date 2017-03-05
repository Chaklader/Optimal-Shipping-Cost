package com.graph.test.service;

import com.graph.test.model.Shipping;
import com.graph.test.model.ShippingParameters;

import java.util.Optional;

/**
 * Created by Chaklader on 3/5/17.
 */
public interface ShippingService {
    Optional<Shipping> getShipping(ShippingParameters params);
}
