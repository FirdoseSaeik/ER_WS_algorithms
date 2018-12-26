# ER_WS_algorithms

Implementation of Erdos-Renyi and Watts-Strogatz algorithms in randomly generated graphs.

This repository uses the following libraries:

jcommon-1.0.0.jar

jfreechart-1.0.1.jar

# Erdos-Renyi

The edge between each two nodes i, j exists with probability of p.

The format to run the program is:

java -cp .:\* ERMain part<X>

Note that <X> can stand for:
  
  -a: p<1/n
  
  -b: p=1/n
  
  -c: p>1/n
  
  -d: p<ln(n)/n
  
  -e: p>ln(n)/n
  
  -f: calculating average path length with p=0.7 (value of p can be changed in initializing the ERGraph)
  
# Watts-Strogatz

The format to run the program is:

java -cp .:\* WSMain part<X>
  
<X> can stand for:
  
  -a: calculating node degrees for 0<=beta<=1
  
  -b: calculating average shortest path length vs number of nodes
  
  -c: calculating median of clustering coefficient for 0<=beta<=1
  
 
  
