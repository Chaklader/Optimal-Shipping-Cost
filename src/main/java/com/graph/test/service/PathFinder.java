package com.graph.test.service;

import com.graph.test.model.Vertex;

import java.util.List;

/**
 * Created by Chaklader on 3/5/17.
 */
public interface PathFinder {
    List<Vertex> getPath(String target);
}
