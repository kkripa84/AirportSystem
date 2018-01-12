/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baggagefinalans.structure;

/**
 *
 * @author R KRISHNA KRIPA
 */
public class Lane {
    
    private final String route_num;
    private final Node start_node;
    private final Node end_node;
    private final int weight;

    public Lane(String route_num, Node start_node, Node end_node, int weight) {
        this.route_num = route_num;
        this.start_node = start_node;
        this.end_node = end_node;
        this.weight = weight;
    }

    public String getRoute_num() {
        return route_num;
    }

    public Node getStart_node() {
        return start_node;
    }

    public Node getEnd_node() {
        return end_node;
    }

    public int getWeight() {
        return weight;
    }      
 
    @Override
	public String toString() {
		return start_node + " " + end_node;
	}
}
