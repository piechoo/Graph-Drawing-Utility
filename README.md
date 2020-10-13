# Graph-Drawing-Utility
Graph drawing utility written in java to visually represent simple graphs

Project Description:
This graph drawing algorithm is based on object implementation of  graph, in which every node contains its degree and number (or if naming option was selected its name).
Every edge contains numbers of nodes that it is connecting, and if option of weighted edges was selected it also contains its weight.
Graph is made of list of edges, list of nodes and additional flags such as is the graph directed or naming nodes and weighting edges.
Algorithm is working on the basis of two dimensional mesh, where only on its nodes can graph nodes occur.
With every new node added algorithm computes the most optimal coordiantes for this node ( the ones in which it is as close as possible to its neighbours), and checks if chosen coordinates are not already used by other node,
or if node would be in one line with more than one of its neighbours.

Obsługa
Program obsługiwany jest za pomocą myszki oraz klawiatury. Po uruchomieniu możemy wybrać ilość wierzchołków oraz krawędzi, oraz zaznaczyć interesujące nas opcje w materii skierowania oraz etykietowania krawędzi oraz wierzchołków:

![Alt text](img/MainMenu.png?raw=true "Menu")
 
Jeśli wybierzemy etykietowane wierzchołki to program wyświetli okienko z miejscem na wpisanie kolejnym wierzchołkom odpowiednich etykiet(po wpisaniu należy je zatwierdzać enterem) :

->![Alt text](img/NamingNodes.png?raw=true "Naming nodes")<-
 
Następnie ( lub jeśli nie wybraliśmy opcji etykietowania wierzchołków ) otrzymamy okno, w którym należy wprowadzić krawędzie (jeśli graf jest skierowany to pod uwagę brane jest źródło oraz cel krawędzi) oraz w przypadku wybrania tej funkcji należy wpisać wagi krawędzi (krawędzie zaczynają numerację od 0 ) :
 
 ![Alt text](img/ConnectingEdges.png?raw=true "Connecting and naming edges")
 
Po zaakceptowaniu danych program rysuje graf na ich podstawie :

![Alt text](img/SimpleExample.png?raw=true "Simple graph example")
 
W przypadku większej ilości wierzchołków wygląda to na przykład tak:
 
![Alt text](img/ComplicatedExample.png?raw=true "A little less simple graph example")