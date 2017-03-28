package com.shipping.graph.dao;

import java.sql.SQLException;

/**
 * Created by Chaklader on 3/28/17.
 */
public interface EdgeDao {
    void loadGraphEdges(int graphId, DataHandler<EdgeData> handler) throws SQLException;
}
