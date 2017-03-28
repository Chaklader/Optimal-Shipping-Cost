package com.shipping.graph.parser;

import com.shipping.graph.GraphCriteria;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Chaklader on 3/6/17.
 */
public interface GraphDataParser {
    GraphCriteria parse(InputStream inputStream) throws IOException;
}

