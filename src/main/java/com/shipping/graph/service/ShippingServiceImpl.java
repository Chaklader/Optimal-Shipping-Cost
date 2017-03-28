package com.shipping.graph.service;

import com.shipping.graph.model.Shipping;
import com.shipping.graph.model.ShippingParameters;
import com.shipping.graph.model.Vertex;

import java.util.List;
import java.util.Optional;

/**
 * Created by Chaklader on 3/5/17.
 */
public class ShippingServiceImpl implements ShippingService {

    private PathFinder pathFinder;

    public Optional<Shipping> getShipping(final ShippingParameters params) {
        String target = params.getTarget();
        List<Vertex> path = pathFinder.getPath(target);
        if (path.isEmpty()) {
            return Optional.empty();
        }
        Shipping shipping = new Shipping(params);
        shipping.setPath(path);
        return Optional.of(shipping);
    }

    public void setPathFinder(final PathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }
}

