package com.shipping.graph.service;

import com.shipping.graph.model.Vertex;

import java.util.List;

/**
 * Created by Chaklader on 3/5/17.
 */
public interface PathFinder {
    List<Vertex> getPath(String target);
}
