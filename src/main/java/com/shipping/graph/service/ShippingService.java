package com.shipping.graph.service;

import com.shipping.graph.model.Shipping;
import com.shipping.graph.model.ShippingParameters;

import java.util.Optional;

/**
 * Created by Chaklader on 3/5/17.
 */
public interface ShippingService {
    Optional<Shipping> getShipping(ShippingParameters params);
}
