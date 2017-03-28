package com.shipping.graph.database;

import com.shipping.graph.ShippingTestCase;
import com.shipping.graph.dao.GraphTestCaseDao;
import com.shipping.graph.model.Graph;
import com.shipping.graph.model.Shipping;
import com.shipping.graph.model.ShippingParameters;
import com.shipping.graph.service.DijkstraPathFinder;
import com.shipping.graph.service.GraphService;
import com.shipping.graph.service.ShippingServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;



/**
 * Created by Chaklader on 3/28/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test_config.xml")
public class DBIntegrationTest {

    @Autowired
    private GraphService graphService;

    @Autowired
    private GraphTestCaseDao testCaseDao;

    @Test
    public void loadGraphTest() throws SQLException {
        doLoadGraphTest(1);
    }

    @Test
    public void loadGraphTest2() throws SQLException {
        doLoadGraphTest(2);
    }

    public void doLoadGraphTest(int graphId) throws SQLException {
        Graph graph = graphService.loadGraph(graphId);
        ShippingServiceImpl shippingService = new ShippingServiceImpl();
        shippingService.setPathFinder(new DijkstraPathFinder(graph, "ME"));

        List<ShippingTestCase> testCases = testCaseDao.loadGraphTestCases(graphId);
        for (ShippingTestCase testCase : testCases) {
            Double expectedCost = testCase.getExpectedCost();
            ShippingParameters params = testCase.getShippingParameters();
            Optional<Shipping> shipping = shippingService.getShipping(params);
            if (expectedCost == null) {
                assertFalse(shipping.isPresent());
            } else {
                double cost = shipping.get().calculateSippingCost();
                assertEquals(expectedCost, cost, 0.001);
            }
        }
    }
}
