package com.graph.test.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chaklader on 3/5/17.
 */
public class Graph {

    private final List<Vertex> vertexes;
    private final List<Edge> edges;
    private final Map<String, Vertex> vertexMap;

    private Graph(List<Vertex> vertexes, List<Edge> edges, Map<String, Vertex> vertexMap) {
        this.vertexes = vertexes;
        this.edges = edges;
        this.vertexMap = vertexMap;
    }

    public Vertex getVertex(final String name) {
        return vertexMap.get(name);
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public static class GraphBuilder {

        private final Map<String, Vertex> vertexMap = new HashMap<>();
        private final List<Vertex> vertexes = new ArrayList<>();
        private final List<Edge> edges = new ArrayList<>();
        private final Map<String, Integer> differentiators = new HashMap<>();

//        public void addVertex(final String name) {
//            if (!vertexMap.containsKey(name)) {
//                Vertex vertex = new Vertex(name);
//                vertexes.add(vertex);
//                vertexMap.put(name, vertex);
//            }
//        }

        public void addEdge(final String source, final String destination, final int hard) {

            Vertex src = getOrCreateVertex(source);
            Vertex dest = vertexMap.get(destination);

            if (dest == null) {
                dest = addVertex(destination);
            } else {
                if (src.getEdge(destination) != null) {
                    dest = createDiffVertex(destination);
                }
            }

            Edge edge = new Edge(src, dest, hard);
            src.addEdge(edge);
            edges.add(edge);
        }

        protected Vertex getOrCreateVertex(final String name) {
            Vertex vertex = vertexMap.get(name);
            if (vertex == null) {
                vertex = addVertex(name);
            }
            return vertex;
        }

        protected Vertex createDiffVertex(final String name) {
            Integer diff = differentiators.get(name);
            if (diff == null) {
                diff = Integer.valueOf(1);
            }
            differentiators.put(name, diff);
            Vertex vertex = addVertex(name + diff);

            return vertex;
        }

        protected Vertex addVertex(final String name) {
            Vertex vertex = new Vertex(name);
            vertexes.add(vertex);
            vertexMap.put(name, vertex);
            return vertex;
        }

        public Graph buildGraph() {
            return new Graph(vertexes, edges, vertexMap);
        }
    }
}
