package com.graph.test;

import com.graph.test.model.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaklader on 3/6/17.
 */
public class GraphCriteria {

    private Graph graph;
    private List<ShippingTestCase> testCases = new ArrayList<>();

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public List<ShippingTestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<ShippingTestCase> testCases) {
        this.testCases = testCases;
    }
}

