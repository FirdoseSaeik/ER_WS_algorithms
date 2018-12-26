import java.util.*;

/**
 * Created by hadis on 10/16/16.
 */
public class WSGraph extends Graph {
    private int k;
    private double beta;
    private double[] clusteringCoefficients;

    public WSGraph(int n, int edgeDegree, double rewiringProbability){
        super(n);
        k = edgeDegree;
        beta = rewiringProbability;
        clusteringCoefficients = new double[numOfNodes];

    }

    public void generateGraph(){
        for (int i = 0 ; i < numOfNodes ; i++) {
            for (int j = 0; j < numOfNodes; j++)
                for (int v = 0; v <= k / 2; v++)
                    if (i != j && (Math.abs(i - j) == v % numOfNodes)) {
                        if (!adjList.get(i).contains(j))
                            adjList.get(i).add(j);
                        if (!adjList.get(j).contains(i))
                            adjList.get(j).add(i);
                    }
        }

        ArrayList<Integer> notCondensedNodes = new ArrayList<Integer>();
        for (int i = 0 ; i < adjList.size() ; i++){
            if (adjList.get(i).size() < k)
                notCondensedNodes.add(i);
        }

        for (int i = 0 ; i < notCondensedNodes.size() ; i++){
            for (int j = 0 ; j < notCondensedNodes.size() ; j++){
                if (notCondensedNodes.get(i) != notCondensedNodes.get(j)
                        && !adjList.get(notCondensedNodes.get(i)).contains(notCondensedNodes.get(j))
                        && !adjList.get(notCondensedNodes.get(j)).contains(notCondensedNodes.get(i))
                        && adjList.get(notCondensedNodes.get(i)).size() < k
                        && adjList.get(notCondensedNodes.get(j)).size() < k
                        ){
                    adjList.get(notCondensedNodes.get(i)).add(notCondensedNodes.get(j));
                    adjList.get(notCondensedNodes.get(j)).add(notCondensedNodes.get(i));
                }
            }
        }

        for (int i = 0 ; i < numOfNodes ; i++){
            for (int j = i+1 ; j < numOfNodes ; j++){
                Random generator = new Random();
                double number = generator.nextDouble();
                if (number <= beta){
                    if (adjList.get(i).contains(j) && adjList.get(j).contains(i)) {
                        adjList.get(i).remove(adjList.get(i).indexOf(j));
                        adjList.get(j).remove(adjList.get(j).indexOf(i));
                        int v = 0;
                        while (true) {
                            Random rand = new Random();
                            v = rand.nextInt(numOfNodes);
                            if (v != i && !adjList.get(i).contains(v)) {
                                adjList.get(i).add(v);
                                adjList.get(v).add(i);
                                break;
                            }
                        }
                    }
                }
            }
        }

    }

    public HashMap<Integer, Double> nodeDegrees(){
        HashMap<Integer, Double> degrees = new HashMap<Integer, Double>();
        for (int i = 0; i < adjList.size(); i++) {
            int degree = adjList.get(i).size();
            if (degrees.containsKey(degree))
                degrees.put(degree, degrees.get(degree) + 1.0 );
            else
                degrees.put(degree, 1.0 );
        }
        return degrees;
    }


    public double calculateClusteringCoefficient(){
        for (int i = 0 ; i < numOfNodes ; i++){
            int sum = 0;
            for (int j = 0 ; j < adjList.get(i).size() ; j++){
                for (int k = j+1 ; k < adjList.get(i).size() ; k++){
                    if (adjList.get(j).contains(k))
                        sum++;
                }
            }
            int degree = adjList.get(i).size();
            if (degree > 1)
                clusteringCoefficients[i] = sum*2.0/(degree*(degree-1));

        }

        double cc = 0.0;
        for (int i = 0 ; i < numOfNodes ; i++)
            cc += clusteringCoefficients[i];
        double value = cc/new Double(numOfNodes);
        return value;
    }

    public void printClusteringCoefficients(){
        System.out.println("Clustering Coefficients");
        for (int i = 0 ; i < numOfNodes ;i++)
            System.out.println(i+ ": " + clusteringCoefficients[i]);
    }
}