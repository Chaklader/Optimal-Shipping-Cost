package com.shipping.graph.dao;

import com.shipping.graph.ShippingTestCase;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Chaklader on 3/28/17.
 */
public interface GraphTestCaseDao {
    List<ShippingTestCase> loadGraphTestCases(int graphId) throws SQLException;
}

