package com.shipping.graph.service;

import com.shipping.graph.dao.EdgeDao;
import com.shipping.graph.model.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by Chaklader on 3/28/17.
 */

@Service
public class DBGraphService implements GraphService {

    @Autowired
    private EdgeDao edgeDao;

    @Override
    public Graph loadGraph(int graphId) {
        final Graph.GraphBuilder gb = new Graph.GraphBuilder();
        try {
            edgeDao.loadGraphEdges(graphId, obj -> gb.addEdge(obj.getSource(), obj.getTarget(), obj.getWeight()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gb.buildGraph();
    }
}

