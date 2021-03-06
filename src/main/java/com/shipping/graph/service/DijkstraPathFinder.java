package com.shipping.graph.service;

import com.shipping.graph.model.Edge;
import com.shipping.graph.model.Graph;
import com.shipping.graph.model.Vertex;

import java.util.*;

/**
 * Created by Chaklader on 3/5/17.
 */


/*
* Dijkstra's Algorithm is a graph search algorithm that solves the single-source
* shortest path problem for a graph with non-negative edge path costs, producing
* a shortest path tree.
* */
public class DijkstraPathFinder implements PathFinder {

    private final Graph graph;
    private final List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;

    public DijkstraPathFinder(final Graph graph, final String sourceName) {
        this.graph = graph;
        edges = new ArrayList<>(graph.getEdges());
        Vertex source = graph.getVertex(sourceName);
        execute(source);
    }

    private void execute(Vertex source) {
        settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();

        distance.put(source, 0);
        unSettledNodes.add(source);

        while (unSettledNodes.size() > 0) {
            Vertex node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }


    // put all the unsettled nodes to get the minimum
    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private int getShortestDistance(Vertex destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    private void findMinimalDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private int getDistance(Vertex node, Vertex target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }

    @Override
    public List<Vertex> getPath(final String target) {

        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = graph.getVertex(target);

        // check if a path exists
        if (predecessors.get(step) == null) {
            return Collections.emptyList();
        }

        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }

        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }
}

