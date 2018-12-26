import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.DomainInfo;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import java.io.File;
import java.io.IOException;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by hadis on 10/17/16.
 */
public class ChartDrawer{


    public void drawDegreeDistributionChart(TreeMap<Integer, Double> data, int num){
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (Integer key : data.keySet()){
            dataSet.addValue(data.get(key), "P(k)",  key);
        }

        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "k Vs P(k)","k",
                "P(k)",
                dataSet,PlotOrientation.VERTICAL,
                true,true,false);

        int width = 640;
        int height = 480;
        File lineChart = new File( "degree distribution"+num+".jpeg" );
        try {
            ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawClusteringCoefficient(TreeMap<Double, Double> data){
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (Double key : data.keySet()){
            dataSet.addValue(data.get(key), "clustering coefficient",  key);
        }

        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "n Vs clustering coefficient","n",
                "clustering coefficient",
                dataSet,PlotOrientation.VERTICAL,
                true,true,false);

        int width = 640;
        int height = 480;
        File lineChart = new File( "clustering coefficient.jpeg" );
        try {
            ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawAverageShortestPath(TreeMap<Integer, Double> data, TreeMap<Integer, Double> logValues){
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (Integer key : data.keySet()){
            dataSet.addValue(data.get(key), "avg shortest path",  key);
        }
        for (Integer key : logValues.keySet()){
            dataSet.addValue(logValues.get(key), "log n",  key);
        }

        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "n Vs avgShortestPath / log n","n",
                "avgShortestPath/log n",
                dataSet,PlotOrientation.VERTICAL,
                true,true,false);

        int width = 640;
        int height = 480;
        File lineChart = new File( "avg shortest path.jpeg" );
        try {
            ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawIsolatedNodes(TreeMap<Integer, Double> data, TreeMap<Integer, Double> connectedGraphs){
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (Integer key : data.keySet()){
            dataSet.addValue(data.get(key), "number of isolated nodes",  key);
        }
        for (Integer key : connectedGraphs.keySet()){
            dataSet.addValue(connectedGraphs.get(key), "number of connected Graphs",  key);
        }

        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "n Vs number of isolated nodes or connected graphs","n",
                "number of isolated nodes / connected graphs",
                dataSet,PlotOrientation.VERTICAL,
                true,true,false);

        int width = 640;
        int height = 480;
        File lineChart = new File( "isolated nodes.jpeg" );
        try {
            ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawBiggestComponentSize(TreeMap<Integer, Double> data,
                                         TreeMap<Integer, Double> biggestData,
                                         TreeMap<Integer, Double> logBValues){
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (Integer key : data.keySet()){
            dataSet.addValue(data.get(key), "meanSizeOfOtherComponents", new Integer(key));
        }
        for (Integer key : biggestData.keySet()){
            dataSet.addValue(biggestData.get(key), "biggestComponentSizesIn30Samples", new Integer(key));
        }
        for (Integer key : logBValues.keySet()){
            dataSet.addValue(logBValues.get(key), "log n", new Integer(key));
        }

        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "n Vs Sizes",
                "n",
                "sizes",
                dataSet,PlotOrientation.VERTICAL,
                true,true,false);

        int width = 640;
        int height = 480;
        File lineChart = new File( "biggest component size.jpeg" );
        try {
            ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
