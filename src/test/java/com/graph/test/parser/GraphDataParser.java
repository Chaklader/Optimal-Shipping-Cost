package com.graph.test.parser;

import com.graph.test.GraphCriteria;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Chaklader on 3/6/17.
 */
public interface GraphDataParser {
    GraphCriteria parse(InputStream inputStream) throws IOException;
}

