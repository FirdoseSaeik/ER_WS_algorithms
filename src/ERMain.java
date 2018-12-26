import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ERMain {
    public static void main(String[] args){

        ChartDrawer drawer = new ChartDrawer();

        int []numOfNodes = new int[10];
        for(int i = 0 ; i < 10 ; i++){
            numOfNodes[i] = (i+1)*1000;
        }

        switch(args[0].charAt(4)){
            // for p < 1/n
            case 'a': {
                HashMap<Integer, Double> p11Map = new HashMap<Integer, Double>();
                HashMap<Integer, Double> p12Map = new HashMap<Integer, Double>();
                HashMap<Integer, Double> p13Map = new HashMap<Integer, Double>();
                for(int it = 0 ; it < 10 ; it++) {
                    int midOfBiggestComponentSizes = 0;
                    double p = 0.4/numOfNodes[it];
                    int biggest = 0;
                    for (int i = 0; i < 30; i++) {
                        ERGraph graph = new ERGraph(numOfNodes[it], p);
                        graph.generateGraph();
                        graph.DFS();
                        int sizeOfBiggestComponent = graph.getSizeOfBiggestComponent();
                        if (sizeOfBiggestComponent > biggest)
                            biggest = sizeOfBiggestComponent;
                        midOfBiggestComponentSizes += sizeOfBiggestComponent;
                        System.out.println("n = " + numOfNodes[it] + " Graph # " + i + " and size of biggest component is " + sizeOfBiggestComponent);
                    }
                    p11Map.put(numOfNodes[it], new Double(midOfBiggestComponentSizes/30));
                    p12Map.put(numOfNodes[it], new Double(biggest));
                    p13Map.put(numOfNodes[it], new Double(Math.log(numOfNodes[it])));
                }
                drawer.drawBiggestComponentSize(new TreeMap<Integer, Double>(p11Map),
                        new TreeMap<Integer, Double>(p12Map),
                        new TreeMap<Integer, Double>(p13Map));
            }
            break;

            // for p = 1/n
            case 'b': {
                for(int it = 0 ; it < 10 ; it++) {
                    int midOfBiggestComponentSizes = 0;
                    for (int i = 0; i < 30; i++) {
                        ERGraph graph = new ERGraph(numOfNodes[it], 1.0/numOfNodes[it]);
                        graph.generateGraph();
                        graph.DFS();
                        int sizeOfBiggestComponent = graph.getSizeOfBiggestComponent();
                        midOfBiggestComponentSizes += sizeOfBiggestComponent;
                        System.out.println("n = " + numOfNodes[it] + " Graph # " + i + " and size of biggest component is " + sizeOfBiggestComponent);
                    }
                    System.out.println("mid is " + midOfBiggestComponentSizes/30);
                }
            }
            break;

            // for p > 1/n
            case 'c':{
                HashMap<Integer, Double> p31Map = new HashMap<Integer, Double>();
                HashMap<Integer, Double> p32Map = new HashMap<Integer, Double>();
                HashMap<Integer, Double> p33Map = new HashMap<Integer, Double>();
                for(int it = 0 ; it < 10 ; it++) {
                    int midOfBiggestComponentSizes = 0;
                    int midOfOtherComponentSize = 0;
                    int biggest = 0;
                    for (int i = 0; i < 30; i++) {
                        ERGraph graph = new ERGraph(numOfNodes[it], 5.0/numOfNodes[it]);
                        graph.generateGraph();
                        graph.DFS();
                        int sizeOfBiggestComponent = graph.getSizeOfBiggestComponent();
                        if (biggest < sizeOfBiggestComponent)
                            biggest = sizeOfBiggestComponent;
                        midOfBiggestComponentSizes += sizeOfBiggestComponent;
                        midOfOtherComponentSize += graph.getMeanSizeOfOtherComponents(sizeOfBiggestComponent);
                        System.out.println("n = " + numOfNodes[it] + " Graph # " + i + " and size of biggest component is " + sizeOfBiggestComponent);
                    }
                    System.out.println("mid of biggest is " + midOfBiggestComponentSizes/30 + " and mid of other is " + midOfOtherComponentSize/30.0);
                    p31Map.put(numOfNodes[it], new Double(midOfOtherComponentSize/30.0));
                    p32Map.put(numOfNodes[it], new Double(biggest));
                    p33Map.put(numOfNodes[it], new Double(Math.log(numOfNodes[it])));
                }
                drawer.drawBiggestComponentSize(new TreeMap<Integer, Double>(p31Map),
                        new TreeMap<Integer, Double>(p32Map),
                        new TreeMap<Integer, Double>(p33Map));
            }
            break;

            // for  p < ln n/n
            case 'd':{
                 HashMap<Integer, Double> p41Map = new HashMap<Integer, Double>();
                 HashMap<Integer, Double> p42Map = new HashMap<Integer, Double>();
                 for (int it = 0 ; it < 10 ; it++) {
                     int meanNumOfIsolatedNodes = 0;
                     int numOfConnectedGraphs = 0;
                     double p = ((Math.log(numOfNodes[it]))-1)/ numOfNodes[it];
                     for (int i = 0; i < 30; i++) {
                         ERGraph graph = new ERGraph(numOfNodes[it], p);
                         graph.generateGraph();
                         graph.DFS();
                         if (graph.hasIsolatedVertices()) {
                             int numOfIsolatedVertices = graph.numOfIsolatedVertices();
                             System.out.println("n = " + numOfNodes[it] + " Graph # " + i + " and num of isolated vertices is " + numOfIsolatedVertices);
                             meanNumOfIsolatedNodes += numOfIsolatedVertices;
                         } else {
                             System.out.println("The graph doesn't have isolated vertices");
                             numOfConnectedGraphs++;
                         }
                     }
                     p41Map.put(numOfNodes[it], meanNumOfIsolatedNodes/30.0);
                     p42Map.put(numOfNodes[it], new Double(numOfConnectedGraphs));
                 }
                 drawer.drawIsolatedNodes(new TreeMap<Integer, Double>(p41Map), new TreeMap<Integer, Double>(p42Map));
            }
            break;

            // for  p > ln n/n
            case 'e':{
                 HashMap<Integer, Double> p51Map = new HashMap<Integer, Double>();
                 HashMap<Integer, Double> p52Map = new HashMap<Integer, Double>();
                 for (int it = 0 ; it < 10 ; it++) {
                     int meanNumOfIsolatedNodes = 0;
                     int numOfConnectedGraphs = 0;
                     double p = ((Math.log(numOfNodes[it]))*2.0)/ numOfNodes[it];
                     for (int i = 0; i < 30; i++) {
                         ERGraph graph = new ERGraph(numOfNodes[it], p);
                         graph.generateGraph();
                         graph.DFS();
                         if (graph.hasIsolatedVertices()) {
                             int numOfIsolatedVertices = graph.numOfIsolatedVertices();
                             System.out.println("n = " + numOfNodes[it] + " Graph # " + i + " and num of isolated vertices is " + numOfIsolatedVertices);
                             meanNumOfIsolatedNodes += numOfIsolatedVertices;
                         } else {
                             System.out.println("n = " + numOfNodes[it] + " Graph # " + i + " The graph doesn't have isolated vertices");
                             numOfConnectedGraphs++;
                         }
                     }
                     p51Map.put(numOfNodes[it], meanNumOfIsolatedNodes/30.0);
                     p52Map.put(numOfNodes[it], new Double(numOfConnectedGraphs));
                 }
                 drawer.drawIsolatedNodes(new TreeMap<Integer, Double>(p51Map), new TreeMap<Integer, Double>(p52Map));
            }
            break;

            // for average path length
            case 'f':{
                 HashMap<Integer, Double> p61Map = new HashMap<Integer, Double>();
                 HashMap<Integer, Double> p62Map = new HashMap<Integer, Double>();
                 for (int it = 0 ; it < 10 ; it++) {
                     double midOfAvgShortestPath = 0;
                     int size = 10;
                     for (int i = 0; i < size; i++) {
                         ERGraph graph = new ERGraph(numOfNodes[it], 0.7);
                         graph.generateGraph();
                         graph.floyedWarshal();
                         double avgShortestPath = graph.getAvgShortestPaths();
                         midOfAvgShortestPath += avgShortestPath;
                         System.out.println("n = " + numOfNodes[it] + " Graph # " + i + " and mid of shortest path is " + avgShortestPath);
                     }
                     p61Map.put(numOfNodes[it], midOfAvgShortestPath/size);
                     p62Map.put(numOfNodes[it], Math.log(numOfNodes[it]));
                 }
                 drawer.drawAverageShortestPath(new TreeMap<Integer, Double>(p61Map), new TreeMap<Integer, Double>(p62Map));
            }
            break;
            default:{
                System.out.println("Please provide the part to be executed in the partX format");
            }
        }
    }
}
