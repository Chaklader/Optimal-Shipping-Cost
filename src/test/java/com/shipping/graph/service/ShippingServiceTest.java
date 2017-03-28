package com.shipping.graph.service;

import com.shipping.graph.model.Graph;
import com.shipping.graph.model.Shipping;
import com.shipping.graph.model.ShippingParameters;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Chaklader on 3/6/17.
 */
public class ShippingServiceTest {

    private ShippingService shippingService;

    @Before
    public void before() {
        Graph graph = buildTestGraph();
        ShippingServiceImpl  shippingService = new ShippingServiceImpl();
        shippingService.setPathFinder(new DijkstraPathFinder(graph, "ME"));
        this.shippingService = shippingService;
    }

    @Test
    public void getShippingPhilippTest() {
        ShippingParameters parameters = new ShippingParameters("Philipp", 1, 1, 1, 0.4);

        Optional<Shipping> shipping = shippingService.getShipping(parameters);
        assertTrue(shipping.isPresent());
        double cost = shipping.get().calculateSippingCost();
        assert (cost == 2.06);
    }

    @Test
    public void getShippingMartinTest() {
        ShippingParameters parameters = new ShippingParameters("Martin", 20, 47, 12, 1.890);

        Optional<Shipping> shipping = shippingService.getShipping(parameters);
        assertTrue(shipping.isPresent());
        double cost = shipping.get().calculateSippingCost();
        assert (cost == 16.96);
    }

    @Test
    public void getShippingStefanTest() {
        ShippingParameters parameters = new ShippingParameters("Stefan", 10, 10, 2, 0.8);

        Optional<Shipping> shipping = shippingService.getShipping(parameters);
        assertTrue(shipping.isPresent());
        double cost = shipping.get().calculateSippingCost();
        assert (cost == 10.00);
    }


    private Graph buildTestGraph() {
        Graph.GraphBuilder gb = new Graph.GraphBuilder();
        String me = "ME";
        gb.addEdge(me, "Stefan", 100);
        gb.addEdge(me, "Amir", 1042);
        gb.addEdge(me, "Martin", 595);
        gb.addEdge(me, "Adam", 10);
        gb.addEdge(me, "Philipp", 128);
        gb.addEdge(me, "Philipp", 18);

        String stefan = "Stefan";
        gb.addEdge(stefan, "Amir", 850);
        gb.addEdge(stefan, "Adam", 85);

        String adam = "Adam";
        gb.addEdge(adam, "Philipp", 7);
        gb.addEdge(adam, "Martin", 400);
        gb.addEdge(adam, "Diana", 33);

        String diana = "Diana";
        gb.addEdge(diana, "Amir", 57);
        gb.addEdge(diana, "Martin", 3);

        return gb.buildGraph();
    }
}
