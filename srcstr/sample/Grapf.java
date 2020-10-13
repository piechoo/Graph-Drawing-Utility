package sample;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;



public class Grapf {
    int nodeNumber;
    int edgeNumber;
    boolean kierownica;
    boolean waga_k;
    boolean waga_w;
    ArrayList<Node> wezly= new ArrayList<>();
    ArrayList<Edge> krawedzie= new ArrayList<>();
    void addEdges()
    {
        for(int i=0;i<edgeNumber;i++)
        {
            krawedzie.add(new Edge(0,0,0," 1 "));
        }
    }

    void addNodes()
    {
        for(int i=0;i<nodeNumber;i++)
        {
            wezly.add(new Node((i+1)));
            wezly.get(i).setWage(String.valueOf(i+1));
        }
    }
    void setKierownica(boolean skier)
    {
        kierownica = skier;
    }
    void setWaga_k(boolean skier)
    {
        waga_k = skier;
    }
    void setWaga_w(boolean skier)
    {
        waga_w = skier;
    }
    void setNumbers(int nodes, int edges)
    {
        nodeNumber = nodes;
        edgeNumber = edges;
    }

}
