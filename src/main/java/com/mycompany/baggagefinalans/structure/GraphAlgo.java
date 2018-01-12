/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baggagefinalans.structure;

import java.util.List;

/**
 *
 * @author R KRISHNA KRIPA
 */
public class GraphAlgo {
    
    private final List<Node> nodes;
    private final List<Lane> lanes;
    
    public GraphAlgo(List<Node> nodes, List<Lane> lanes) {
        this.nodes = nodes;
        this.lanes = lanes;
    }
    
    public List<Node> getNodes() {
        return nodes;
    }

    public List<Lane> getLanes() {
        return lanes;
    }
   
    
}
