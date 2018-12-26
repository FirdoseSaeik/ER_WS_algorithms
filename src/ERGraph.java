import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ERGraph extends Graph{
    private double probability;
    private ArrayList<ArrayList <Integer>> connectedComponents;
    private ArrayList<Integer> colors;

    public ERGraph(int n, double p){
        super(n);
        probability = p;
        connectedComponents = new ArrayList<ArrayList<Integer>>();
        colors = new ArrayList<Integer>();

    }

    public void generateGraph(){
        for (int i = 0 ; i < numOfNodes ; i++){
            for (int j = i+1 ; j < numOfNodes ; j++){
                Random generator = new Random();
                double number = generator.nextDouble();
                if (number <= probability){
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                }
            }
        }
    }

    public void DFS(){
        for (int i = 0 ; i < adjList.size() ; i++)
            colors.add(0);
        for (int i = 0 ; i < adjList.size() ; i++){
            if (colors.get(i).equals(new Integer(0))){
                connectedComponents.add(new ArrayList<Integer>());
                DFSVisit(i);
            }
        }
    }

    public void DFSVisit(int node){
        colors.set(node, 1);
        connectedComponents.get(connectedComponents.size()-1).add(node);
        for (int i = 0 ; i < adjList.get(node).size(); i++){
            int adjNode = adjList.get(node).get(i);
            if (colors.get(adjNode).equals(new Integer(0)))
                DFSVisit(adjNode);
        }
        colors.set(node, 2);
    }

    public int getSizeOfBiggestComponent(){
        int max = Integer.MIN_VALUE;
        for (int i = 0 ; i < connectedComponents.size() ; i++)
            if (connectedComponents.get(i).size() > max)
                max = connectedComponents.get(i).size();
        return max;
    }

    public boolean hasIsolatedVertices(){
        for (int i = 0 ; i < connectedComponents.size() ; i++)
            if (connectedComponents.get(i).size() == 1)
                return true;
        return false;
    }

    public int numOfIsolatedVertices(){
        int num = 0;
        for (int i = 0 ; i < connectedComponents.size() ; i++)
            if (connectedComponents.get(i).size() == 1)
                num++;
        return num;
    }

    public void printConnectedComponents(){
        System.out.println("Connected Components");
        for (int i = 0 ; i < connectedComponents.size() ; i++){
            for (int j = 0 ; j < connectedComponents.get(i).size() ; j++)
                System.out.print(connectedComponents.get(i).get(j) +" ");
            System.out.println("");
        }
    }

    public int getMeanSizeOfOtherComponents(int biggest){
        int mid = 0;
        for (int i = 0 ; i < connectedComponents.size() ; i++)
            if (connectedComponents.get(i).size() != biggest)
                mid += connectedComponents.get(i).size();
        return mid/connectedComponents.size();
    }

    public void printSizeOfComponents(int sizeOfBiggestComponent){
        ArrayList<Integer> componentSizes = new ArrayList<Integer>(Collections.nCopies(sizeOfBiggestComponent, 0));
        for (int i = 0 ; i < connectedComponents.size() ; i++){
            int size = connectedComponents.get(i).size();
            int component = componentSizes.get(size-1);
            component++;
            componentSizes.set(size-1, component);
        }
        for (int i = 0 ; i < componentSizes.size() ; i++)
            if (componentSizes.get(i) > 0)
                System.out.println( componentSizes.get(i) + " components are of size " + (new Integer(i+1)).toString());
    }
}
