package com.graph.test.service;

import com.graph.test.GraphCriteria;
import com.graph.test.ShippingTestCase;
import com.graph.test.model.Graph;
import com.graph.test.model.Shipping;
import com.graph.test.model.ShippingParameters;
import com.graph.test.parser.CsvGraphDataParser;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Created by Chaklader on 3/6/17.
 */
public class ShippingServiceFullTest {

    private CsvGraphDataParser parser = new CsvGraphDataParser();

    @Test
    public void fullTest() throws IOException {

        String testDir = System.getProperty("graphTestDir");
        File[] testFiles = getTestFiles(testDir);
        for (File testFile : testFiles) {
            doTest(testFile);
        }
    }

    protected void doTest(final File file) throws IOException {
        GraphCriteria criteria = parser.parse(getTestData(file));
        Graph graph = criteria.getGraph();
        ShippingServiceImpl  shippingService = new ShippingServiceImpl();
        shippingService.setPathFinder(new DijkstraPathFinder(graph, "ME"));

        List<ShippingTestCase> testCases = criteria.getTestCases();
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

    protected InputStream getTestData(final File file) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(file);
        return inputStream;
    }

    protected File[] getTestFiles(final String directory) {
        File dir = new File(directory);
        if (!dir.exists()) {
            throw new IllegalArgumentException(directory + " not found.");
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(directory + " is no a directory.");
        }
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.toLowerCase().endsWith(".csv")) {
                    return true;
                }
                return false;
            }
        });
        return files;
    }

}

