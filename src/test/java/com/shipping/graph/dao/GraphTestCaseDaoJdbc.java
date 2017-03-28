package com.shipping.graph.dao;


import com.shipping.graph.ShippingTestCase;
import com.shipping.graph.model.ShippingParameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Chaklader on 3/28/17.
 */
@Repository
public class GraphTestCaseDaoJdbc implements GraphTestCaseDao {

    @Autowired
    private DataSource dataSource;

    private String query = "select target, width, length, height, weight, " +
            "expected_cost from test_case where graph_id = ?";

    @Override
    public List<ShippingTestCase> loadGraphTestCases(int graphId) throws SQLException {
        PreparedStatement ps = dataSource.getConnection().prepareStatement(query);
        ps.setInt(1, graphId);
        ResultSet rs = ps.executeQuery();
        List<ShippingTestCase> result = new ArrayList<>();
        while (rs.next()) {
            double weight = ((double) rs.getInt("weight")) / 1000;
            ShippingParameters parameters = new ShippingParameters(
                    rs.getString("target"),
                    rs.getInt("width"),
                    rs.getInt("length"),
                    rs.getInt("height"),
                    weight);
            Object eCost = rs.getObject("expected_cost");
            ShippingTestCase testCase = new ShippingTestCase(parameters, eCost == null ? null : (Double)eCost);
            result.add(testCase);
        }
        rs.close();
        ps.close();
        return result;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
