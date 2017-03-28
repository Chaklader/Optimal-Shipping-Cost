package com.shipping.graph.database;

import com.shipping.graph.model.Graph;
import com.shipping.graph.service.GraphService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


/**
 * Created by Chaklader on 3/28/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test_config.xml")
public class DBGraphServiceTest {

    @Autowired
    private GraphService graphService;

    @Test
    public void loadGraphTest() {
        Graph graph = graphService.loadGraph(1);
        assertNotNull(graph);
        assertFalse(graph.getVertexes().isEmpty());
    }
}

