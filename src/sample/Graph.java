package sample;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;



public class Graph {
    int nodeNumber;
    int edgeNumber;
    boolean directed;
    boolean edgeWeight;
    boolean nodeWeight;
    ArrayList<Node> nodes = new ArrayList<>();
    ArrayList<Edge> edges = new ArrayList<>();
    void addEdges()
    {
        for(int i=0;i<edgeNumber;i++)
        {
            edges.add(new Edge(0,0,0," 1 "));
        }
    }

    void addNodes()
    {
        for(int i=0;i<nodeNumber;i++)
        {
            nodes.add(new Node((i+1)));
            nodes.get(i).setWage(String.valueOf(i+1));
        }
    }
    void setDirected(boolean skier)
    {
        directed = skier;
    }
    void setEdgeWeight(boolean skier)
    {
        edgeWeight = skier;
    }
    void setNodeWeight(boolean skier)
    {
        nodeWeight = skier;
    }
    void setNumbers(int nodes, int edges)
    {
        nodeNumber = nodes;
        edgeNumber = edges;
    }

}
