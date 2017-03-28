package com.shipping.graph.dao;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Chaklader on 3/28/17.
 */
@Repository
public class EdgeDaoJdbc implements EdgeDao {

    @Autowired
    private DataSource dataSource;

    private String query = "select v.name as source, v2.name as target, e.weight from edge e, vertex v, vertex v2 "
            + " where e.vertex_src_id = v.id and e.vertex_dst_id = v2.id and v.graph_id = v2.graph_id and v2.graph_id = ?";

    @Override
    public void loadGraphEdges(int graphId, DataHandler<EdgeData> handler) throws SQLException {
        PreparedStatement ps = dataSource.getConnection().prepareStatement(query);
        ps.setInt(1, graphId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            EdgeData edgeData = new EdgeData(rs.getString("source"), rs.getString("target"), rs.getInt("weight"));
            handler.handle(edgeData);
        }
        rs.close();
        ps.close();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
