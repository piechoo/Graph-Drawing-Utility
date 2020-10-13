# Graph-Drawing-Utility
Graph drawing utility written in java to visually represent simple graphs
Opis Projektu
Algorytm Rysowania grafu oparty jest na obiektowej implementacji grafu, w której każdy wierzchołek posiada swój stopień, oraz numer ( lub jeśli wybrana została odpowiednia opcja, etykietę ).Każdy wierzchołek posiada natomiast numery wierzchołków, które łączy, oraz jeśli została wybrana taka opcja wagę. Graf składa się z list wierzchołków oraz krawędzi oraz flag dodatkowych opcji takich jak skierowanie grafu bądź etykietowanie wierzchołków i krawędzi.
Algorytm działa na zasadzie siatki, na której przecięciach mogą znajdować się wierzchołki. Przy każdym dodawanym wierzchołku algorytm oblicza najoptymalniejsze koordynaty dla wierzchołka ( takie, w których dzieli go jak najmniejsza odległość od jego sąsiadów )  oraz sprawdza czy w danym miejscu nie znajduje się już inny wierzchołek, lub czy w danym miejscu wierzchołek nie leżałby w jednej linii z więcej niż jednym sąsiadem.

Obsługa
Program obsługiwany jest za pomocą myszki oraz klawiatury. Po uruchomieniu możemy wybrać ilość wierzchołków oraz krawędzi, oraz zaznaczyć interesujące nas opcje w materii skierowania oraz etykietowania krawędzi oraz wierzchołków:
 
Jeśli wybierzemy etykietowane wierzchołki to program wyświetli okienko z miejscem na wpisanie kolejnym wierzchołkom odpowiednich etykiet(po wpisaniu należy je zatwierdzać enterem) :
 
Następnie ( lub jeśli nie wybraliśmy opcji etykietowania wierzchołków ) otrzymamy okno, w którym należy wprowadzić krawędzie (jeśli graf jest skierowany to pod uwagę brane jest źródło oraz cel krawędzi) oraz w przypadku wybrania tej funkcji należy wpisać wagi krawędzi (krawędzie zaczynają numerację od 0 ) :
 
Po zaakceptowaniu danych program rysuje graf na ich podstawie :
 
W przypadku większej ilości wierzchołków wygląda to na przykład tak:
 
