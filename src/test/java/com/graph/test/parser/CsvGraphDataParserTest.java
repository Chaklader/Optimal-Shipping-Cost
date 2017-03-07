package com.graph.test.parser;

import com.graph.test.GraphCriteria;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Chaklader on 3/6/17.
 */
public class CsvGraphDataParserTest {

    private CsvGraphDataParser parser = new CsvGraphDataParser();

    @Test
    public void simpleDataTest() throws IOException {
        GraphCriteria criteria = parser.parse(getTestData());
        assertNotNull(criteria);
        assertNotNull(criteria.getGraph());
        assertNotNull(criteria.getTestCases());
        assertNotNull(criteria.getGraph().getEdges());
        assertNotNull(criteria.getGraph().getVertexes());
        assertEquals(criteria.getGraph().getEdges().size(), 12);
        assertEquals(criteria.getGraph().getVertexes().size(), 7);
        assertEquals(criteria.getTestCases().size(), 3);
    }

    protected InputStream getTestData() {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("01.csv");
        return inputStream;
    }
}

