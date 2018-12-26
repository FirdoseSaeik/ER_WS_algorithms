import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WSMain {
    public static void main(String[] args){

        ChartDrawer drawer = new ChartDrawer();

        switch(args[0].charAt(4)){
            case 'a': {
                // part a
                 for (int i = 0 ; i <= 5 ; i++) {
                     int numOfNodes = 1000;
                     int k = 50;
                     double beta = i/5.0;
                     int size = 20;
                     HashMap<Integer, Double> map = new HashMap<Integer, Double>();
                     for (int j = 0 ; j < size ; j++) {
                         WSGraph graph = new WSGraph(numOfNodes, k, beta);
                         graph.generateGraph();
                         graph.floyedWarshal();
                         System.out.println("beta = " + beta + " Graph # " + j);
                         HashMap<Integer, Double> tempMap = graph.nodeDegrees();
                         for (int key: tempMap.keySet()){
                             if (map.containsKey(key))
                                 map.put(key, tempMap.get(key)+ map.get(key));
                             else
                                 map.put(key, tempMap.get(key));
                         }
                     }
                     for (Integer key : map.keySet())
                         map.put(key, map.get(key)/(numOfNodes*size));
                     drawer.drawDegreeDistributionChart(new TreeMap<Integer, Double>(map), i);
                 }
            }
            break;

            case 'b': {
                // part b
                 HashMap<Integer, Double> map21 = new HashMap<Integer, Double>();
                 HashMap<Integer, Double> map22 = new HashMap<Integer, Double>();
                 for (int num = 0 ; num < 10 ; num++) {
                     double size = 20.0;
                     double midOfAvgShortestPath = 0;
                     int k = new Double(Math.pow((num+1)*100, 1.0/3.0)).intValue();
                     if (k %2 == 1)
                         k += 1;
                     for (int i = 0; i < size; i++) {
                         WSGraph graph = new WSGraph((num+1)*100, k, 1.0);
                         graph.generateGraph();
                         graph.floyedWarshal();
                         double avgShortestPath = graph.getAvgShortestPaths();
                         midOfAvgShortestPath += avgShortestPath;
                         System.out.println("n = " + num + " Graph # " + i + " and mid Of Path Lengths is " + avgShortestPath);
                     }
                     map21.put((num+1)*100, midOfAvgShortestPath/size);
                     map22.put((num+1)*100, Math.log((num+1)*100.0));
                 }
                 drawer.drawAverageShortestPath(new TreeMap<Integer, Double>(map21), new TreeMap<Integer, Double>(map22));
            }
            break;

            case 'c': {
                // part c
                 HashMap<Double, Double> map31 = new HashMap<Double, Double>();
                 for (int i = 0 ; i <= 5 ; i++) {
                     int k = 4;
                     double beta = i/5.0;
                     double size = 20.0;
                     int numOfNodes = 1000;
                     double midOfClusteringCoefficient = 0;
                     for (int j = 0; j < size; j++) {
                         WSGraph graph = new WSGraph(numOfNodes, k, beta);
                         graph.generateGraph();
                         graph.floyedWarshal();
                         double coefficient = graph.calculateClusteringCoefficient();
                         midOfClusteringCoefficient += coefficient;
                         System.out.println("beta is "+ beta+" and Graph # " + j + " and clustering coefficient is " + coefficient);
                     }
                     map31.put(beta, midOfClusteringCoefficient/size);
                 }
                 drawer.drawClusteringCoefficient(new TreeMap<Double, Double>(map31));
            }
            break;
            default:{
                System.out.println("Please provide the part to be executed in the partX format");
            }
        }
    }
}