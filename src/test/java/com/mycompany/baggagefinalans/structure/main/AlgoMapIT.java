/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baggagefinalans.structure.main;

import com.mycompany.baggagefinalans.structure.GraphAlgo;
import com.mycompany.baggagefinalans.structure.Lane;
import com.mycompany.baggagefinalans.structure.Node;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import junit.framework.TestCase;

/**
 *
 * @author R KRISHNA KRIPA
 */
public class AlgoMapIT extends TestCase{

  private List<Node> nodes;
  private List<Lane> lane;
  private GraphAlgo graph;
  private  AlgoMap Algo;
  private Map<String,Node[]> baggage;
  private Map<String,Node> depart_time;

  
  public void solution() {
    nodes = new ArrayList<Node>();
    lane = new ArrayList<Lane>();
      int i;
    
    // Finding the paths
    while(i<=11)
    {
      switch(i)
      {
        case 1: 
          Node path= new Node("Concourse_A_Ticketing", "Concourse_A_Ticketing");
          nodes.add(path);
          System.out.println("Case 1: The path value is:"+i);
        case 11:
          Node path = new Node("BaggageClaim", "BaggageClaim");
        nodes.add(path);
        System.out.println("Case 11: The path value is:"+i);
          
        default:
          Node path = new Node("A" + i, "A" + i);
          nodes.add(path);
          
      }
      i++;
    }
    
    
    // initializing test array 
    
    calculatePath("Lane_0", 0, 5, 5);
    calculatePath("Lane_1", 5, 11,5);
    calculatePath("Lane_2", 5, 10,4);
    calculatePath("Lane_3", 5, 1, 6);
    calculatePath("Lane_4", 1, 2, 1);
    calculatePath("Lane_5", 2, 3, 1);
    calculatePath("Lane_6", 3, 4, 1);
    calculatePath("Lane_7", 10, 9,1);
    calculatePath("Lane_8", 9, 8, 1);
    calculatePath("Lane_9", 8, 7, 1);
    calculatePath("Lane_10", 7, 6,1);
    

    
    graph = new GraphAlgo(nodes, lane);
    Algo = new AlgoMap(graph);
    
    depart_time = new HashMap<>();
    depart_time.put("UA11", nodes.get(1));
    depart_time.put("UA12", nodes.get(1));
    depart_time.put("UA13", nodes.get(2));
    depart_time.put("UA14", nodes.get(2));
    depart_time.put("UA10", nodes.get(1));
    depart_time.put("UA15", nodes.get(2));
    depart_time.put("UA16", nodes.get(3));
    depart_time.put("UA17", nodes.get(4));
    depart_time.put("UA18", nodes.get(5));
    depart_time.put("ARRIVAL", nodes.get(11));
    
    baggage = new LinkedHashMap<>(); 
    baggage.put("0001", new Node[]{nodes.get(0), depart_time.get("UA12")} );
    baggage.put("0002", new Node[]{nodes.get(5), depart_time.get("UA17")} );
    baggage.put("0003", new Node[]{nodes.get(2), depart_time.get("UA10")} );
    baggage.put("0004", new Node[]{nodes.get(8), depart_time.get("UA18")} );
    baggage.put("0005", new Node[]{nodes.get(7), depart_time.get("ARRIVAL")} );
    
    for (Map.Entry<String,Node[]> bag : baggage.entrySet()) {
      getRoute(baggage);
    }

  }
  
  private void getRoute(Map.Entry<String,Node[]> bag) {
    String bagName=bag.getKey();
    Node[] vertexes=bag.getValue();
    Algo.execute(vertexes[0]);
    Map<Integer,LinkedList<Node>> path = Algo.getPath(vertexes[1]);

    assertNotNull(path);
    assertTrue(path.size() > 0);

    for (Map.Entry<Integer,LinkedList<Node>> routeExtent : path.entrySet()) {
      System.out.println(bagName+"\t"+routeExtent .getValue().toString()+"\t"+routeExtent.getKey());
    }
  }

  private void calculatePath(String route_no, int start_node, int end_node,
      int duration) {
    
    //Multiple pathway calculation
    Lane pathway = new Lane(route_no, nodes.get(start_node),
        nodes.get(end_node), duration);
    lane.add(pathway);
    
    pathway = new Lane(route_no, nodes.get(end_node),
        nodes.get(start_node), duration);
    lane.add(pathway);
  }
}

