package com.shipping.graph.parser;

import au.com.bytecode.opencsv.CSVReader;
import com.shipping.graph.GraphCriteria;
import com.shipping.graph.ShippingTestCase;
import com.shipping.graph.model.Graph;
import com.shipping.graph.model.ShippingParameters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Chaklader on 3/6/17.
 */
public class CsvGraphDataParser implements GraphDataParser {

    private String inputFileEncoding = "UTF-8";
    private String ASSERTION_MARKER = "@";
    private String EDGE_DELIMITER = ":";
    private String DIMENSION_DELIMITER = "x";
    private String NO_RESULT = "~";

    @Override
    public GraphCriteria parse(final InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, inputFileEncoding));
        GraphCriteria criteria = new GraphCriteria();
        Graph.GraphBuilder gb = new Graph.GraphBuilder();
        CSVReader csv = new CSVReader(reader);
        String[] line;

        try {
            while (null != (line = csv.readNext())) {
                if (line.length > 0) {
                    if (line[0].equals(ASSERTION_MARKER)) {
                        ShippingTestCase testCase = parseShippingTestCase(line);
                        criteria.getTestCases().add(testCase);
                    } else {
                        parseAndAddEdge(line, gb);
                    }
                }
            }
        } finally {
            csv.close();
        }
        criteria.setGraph(gb.buildGraph());
        return criteria;
    }

    protected ShippingTestCase parseShippingTestCase(final String[] line) {
        if (line.length < 4) {
            throw new IllegalArgumentException("Invalid assertion data.");
        }
        String target = line[1];
        String dimension = line[2];
        ShippingParameters shippingParameters = parseShippingParameters(target, dimension);
        Double cost = null;
        if (!NO_RESULT.equals(line[3].trim())) {
            cost = Double.valueOf(line[3]);
        }
        ShippingTestCase testCase = new ShippingTestCase(shippingParameters, cost);
        return testCase;
    }

    private ShippingParameters parseShippingParameters(final String target, final String dimension) {

        String[] dim = dimension.split(DIMENSION_DELIMITER);

        if (dim.length < 4) {
            throw new IllegalArgumentException("Invalid dimension: " + dimension);
        }

        int width = Integer.parseInt(dim[0]);
        int length = Integer.parseInt(dim[1]);
        int height = Integer.parseInt(dim[2]);
        double weight = Double.parseDouble(dim[3]) / 1000;
        return new ShippingParameters(target, width, length, height, weight);
    }

    protected void parseAndAddEdge(final String[] line, final Graph.GraphBuilder gb) {
        final String source = line[0];
        for (int i = 1; i < line.length; i++) {
            String edgeData = line[i];
            addEdge(source, edgeData, gb);
        }
    }

    protected void addEdge(final String source, final String edgeData, final Graph.GraphBuilder gb) {
        String[] pair = edgeData.split(EDGE_DELIMITER);
        if (pair.length == 2) {
            String dest = pair[0];
            int hard = Integer.parseInt(pair[1]);
            gb.addEdge(source, dest, hard);
        } else {
            throw new IllegalArgumentException("Invalid network data: " + source);
        }
    }

    public void setInputFileEncoding(final String inputFileEncoding) {
        this.inputFileEncoding = inputFileEncoding;
    }
}

