/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baggagefinalans.structure.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mycompany.baggagefinalans.structure.GraphAlgo;
import com.mycompany.baggagefinalans.structure.Lane;
import com.mycompany.baggagefinalans.structure.Node;
//import sun.security.provider.certpath.Vertex;

/**
 *
 * @author R KRISHNA KRIPA
 */
public class AlgoMap {
   //private Node settledVertexs;

    //public AlgoMap(List<Vertex> nodes, List<Lane> lanes) {
      //  this.nodes = nodes;
        //this.lanes = lanes;
    //}
    
        private final List<Node> nodes;
	private final List<Lane> lanes;
	private Set<Node> finalisedVertexs;
	private Set<Node> notfinalisedVertexs;
	private Map<Node, Node> prenodes;
	private Map<Node, Integer> dist;  
        
        
 public AlgoMap(GraphAlgo graph){
   this.nodes = new ArrayList<Node> (graph.getNodes());
   this.lanes = new ArrayList<Lane> (graph.getLanes());
 } 
 
 public void execute(Node source) {
		finalisedVertexs = new HashSet<Node>();
		notfinalisedVertexs = new HashSet<Node>();
		dist = new HashMap<Node, Integer>();
		prenodes = new HashMap<Node, Node>();
		dist.put(source, 0);
		notfinalisedVertexs.add(source);
		while (notfinalisedVertexs.size() > 0) {
			Node node = getMinimum(notfinalisedVertexs);
			finalisedVertexs.add(node);
			notfinalisedVertexs.remove(node);
			shortestLane(node);
		}
	}
        private void shortestLane(Node node) {
		List<Node> adjacentVertexs = (List<Node>) getAdjacent(node);
		for (Node target : adjacentVertexs) {
			if (getShortestDistance(target) > getShortestDistance(node)
					+ getDist(node, target)) {
				dist.put(target,
						getShortestDistance(node) + getDist(node, target));
				prenodes.put(target, node);
				notfinalisedVertexs.add(target);
			}
		}

	}
        
        private int getDist(Node node, Node target) {
		for (Lane edge : lanes) {
			if (edge.getStart_node().equals(node)
					&& edge.getEnd_node().equals(target)) {
				return edge.getWeight();
			}
		}
		throw new RuntimeException("Should not happen");
	}

	
         List<Node> getAdjacent(Node node) {
		List<Node> adjacent = new ArrayList<>();
		for (Lane edge : lanes) {
			if (edge.getStart_node().<Node>equals(node)
					&& !isSettled(edge.getEnd_node())) 
                        {
				adjacent.add(edge.getEnd_node()); 
			}
	
		}
		return adjacent;
	}

	private Node getMinimum(Set<Node> vertexes) {
		Node minimum = null;
		for (Node vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Node vertex) {
		return finalisedVertexs.contains(vertex);
	}

	private int getShortestDistance(Node destination) {
		Integer d = dist.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
	public Map<Integer,LinkedList<Node>> getPath(Node target) {
		LinkedList<Node> path = new LinkedList<Node>();
		Node step = target;
		int totalSum=0;
		// check if a path exists
		if (prenodes.get(step) == null) {
			return null;
		}
		path.add(step);
		while (prenodes.get(step) != null) {
			totalSum+=getDist(step, prenodes.get(step));
			step = prenodes.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		Map<Integer,LinkedList<Node>> map = new HashMap<>();
		map.put(Integer.valueOf(totalSum), path);
		return map;
	}

}
