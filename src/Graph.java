import java.util.ArrayList;

/**
 * Created by hadis on 10/17/16.
 */
public class Graph {
    protected int numOfNodes;
    protected ArrayList<ArrayList<Integer>> adjList;
    protected int[][] distances;

    public Graph(int n){
        numOfNodes = n;
        this.distances = new int [numOfNodes][numOfNodes];
        this.adjList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0 ; i < numOfNodes ; i++)
            this.adjList.add(new ArrayList<Integer>());
    }

    public void floyedWarshal(){
        for (int i = 0 ; i < numOfNodes ; i++)
            for (int j = 0 ; j < numOfNodes ; j++)
                if (i != j)
                    distances[i][j] = Integer.MAX_VALUE/2-100000;

        for (int i = 0 ; i < numOfNodes ; i++){
            for (int j = 0 ; j < numOfNodes ; j++){
                if (adjList.get(i).contains(j)){
                    distances[i][j] = 1;
                    distances[j][i] = 1;
                }
            }
        }
        for (int k = 0 ; k < numOfNodes ; k++)
            for (int i = 0 ; i < numOfNodes ; i++)
                for (int j = 0 ; j < numOfNodes ; j++)
                    if (distances[i][k] + distances[k][j] < distances[i][j])
                        distances[i][j] = distances[i][k] + distances[k][j];
    }

    public double getAvgShortestPaths(){
        int sum = 0;
        for (int i = 0 ; i < numOfNodes ; i++){
            for (int j = 0 ; j < numOfNodes ; j++){
                if (i != j && distances[i][j] < Integer.MAX_VALUE/2-100000){
                    sum += distances[i][j];
                }
            }
        }
        return new Double(sum)/new Double(numOfNodes*(numOfNodes-1));
    }

    public void printDistances(){
        System.out.println("Distances");
        for (int i = 0 ; i < numOfNodes ; i++){
            for (int j = 0 ; j < numOfNodes ; j++)
                System.out.print(distances[i][j] + " ");
            System.out.println("");
        }
    }

    public void printGraph(){
        System.out.println("Graph");
        for (int i = 0 ; i < adjList.size() ; i++){
            System.out.print(i + ": ");
            for (int j = 0 ; j < adjList.get(i).size() ; j++)
                System.out.print(adjList.get(i).get(j) +" ");
            System.out.println("");
        }
    }

}

