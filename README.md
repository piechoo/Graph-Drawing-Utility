# Graph-Drawing-Utility
Graph drawing utility written in java to visually represent simple graphs

Project Description:
This graph drawing algorithm is based on object implementation of  graph, in which every node contains its degree and number (or if naming option was selected its name).
Every edge contains numbers of nodes that it is connecting, and if option of weighted edges was selected it also contains its weight.
Graph is made of list of edges, list of nodes and additional flags such as is the graph directed or naming nodes and weighting edges.
Algorithm is working on the basis of two dimensional mesh, where only on its nodes can graph nodes occur.
With every new node added algorithm computes the most optimal coordiantes for this node ( the ones in which it is as close as possible to its neighbours), and checks if chosen coordinates are not already used by other node,
or if node would be in one line with more than one of its neighbours.


Using the program
Program is operated with mouse and keyboard. After launching user can choose number of nodes and edges, and choose options in term of if the graph is directed and if nodes or edges should be named:

![Alt text](img/MainMenu.png?raw=true "Menu")
 
If user chooses named nodes program will show window with table to write names for subsequent nodes ( after writing the name user has to accept it by pressing enter) :

->![Alt text](img/NamingNodes.png?raw=true "Naming nodes")<-
 
Afterwards ( or if user did not choose to name nodes) user see window in which he should enter edges ( if graph is directed then it is important to write source and goal of the edge correctly) and if option of naming edges was selected there is also place to write edge names (IMPORTANT: in writing source and goal of edge please remember that nodes numerations begins from 0):
 
 ![Alt text](img/ConnectingEdges.png?raw=true "Connecting and naming edges")
 
After accepting all data program will draw graph:


![Alt text](img/SimpleExample.png?raw=true "Simple graph example")
 
In case of a little more complicated data the result is as follows:
 
![Alt text](img/ComplicatedExample.png?raw=true "A little less simple graph example")