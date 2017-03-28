package com.shipping.graph.dao;

/**
 * Created by Chaklader on 3/28/17.
 */
public interface DataHandler <T> {
    void handle(T obj);
}