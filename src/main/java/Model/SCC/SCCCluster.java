package Model.SCC;

import org.graphstream.graph.Node;
import java.awt.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

public class SCCCluster {
    private Color color;
    private int id;
    private ArrayList<Node> nodes;
    private ColorGenerator colorGenerator;
    private double lowerbound = 0;
    private double upperbound = Double.POSITIVE_INFINITY;
    private double internalConstartins = 0;
    private double lambdaMinus = 0;
    private double lambdaPlus = Double.POSITIVE_INFINITY;
    private ArrayList<Map.Entry<Node, Double>> predecessor;
    private ArrayList<Map.Entry<Node, Double>> successor;
    private ArrayList<Map.Entry<Integer, Double>> predecessorClusters;
    private ArrayList<Map.Entry<Integer, Double>> successorClusters;


   public SCCCluster(int id){
       nodes = new ArrayList<>();
       predecessor = new ArrayList<>();
       successor = new ArrayList<>();
       colorGenerator = new ColorGenerator();
       this.id = id;
   }

    public String toString(){
        return ""+id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor() {
       color = colorGenerator.createColor();
    }

    public void addNodesToCluster(Node node){
       nodes.add(node);
    }

    public int getId(){
       return id;
    }


    public void evaluate() {
        for ( Node n : nodes){
            double upperBoundNode = n.getAttribute("upper_bound");
            double lowerBoundNode = n.getAttribute("lower_bound");
            double internalWeightNode = n.getAttribute("internal_weight");
            predecessor.addAll((ArrayList<Map.Entry<Node, Double>>)n.getAttribute("predecessor"));
            successor.addAll((ArrayList<Map.Entry<Node, Double>>)n.getAttribute("successor"));

            if( internalWeightNode > internalConstartins){
                internalConstartins = internalWeightNode;
            }
            if ( upperBoundNode < upperbound){
                upperbound = upperBoundNode;
            }
            if ( lowerBoundNode > lowerbound){
                lowerbound = lowerBoundNode;
            }

        }
        predecessorClusters = setpredecessorClusters();
        successorClusters = setsuccessorClusters();
    }

    @Override
    public boolean equals(Object other) {
        boolean retVal = false;
        if (other == null) return false;
        if (other == this) return true;
        if (other instanceof SCCCluster){
            SCCCluster otherCluster = (SCCCluster) other;
            retVal = (otherCluster.getId() == this.id);
        }
        return retVal;
    }


    public ArrayList<Map.Entry<Integer, Double>> setpredecessorClusters(){
       //clustersuccessor and the weight of the attack
       ArrayList<Map.Entry<Integer, Double>> predecessorCluster = new ArrayList<>();
       for (Map.Entry<Node, Double> node: predecessor){
           int sccsuccessor = node.getKey().getAttribute("SCC");
           if(!predecessorCluster.contains(sccsuccessor)){
               predecessorCluster.add( new AbstractMap.SimpleEntry<>(sccsuccessor,node.getValue()));
           }else{
                int indexOfsuccessor = predecessorCluster.indexOf(sccsuccessor);
                if (node.getValue() > predecessorCluster.get(indexOfsuccessor).getValue()){
                    predecessorCluster.set(indexOfsuccessor, new AbstractMap.SimpleEntry<>(sccsuccessor,node.getValue()));
                }
           }
       }
       return predecessorCluster;
    }

    public ArrayList<Map.Entry<Integer, Double>> setsuccessorClusters(){
        //clusterAttacked and the weight of the attack
        ArrayList<Map.Entry<Integer, Double>> successorCluster = new ArrayList<>();
        for (Map.Entry<Node, Double> node: successor){
            int sccAttacked = node.getKey().getAttribute("SCC");
            if(!successorCluster.contains(sccAttacked)){
                successorCluster.add( new AbstractMap.SimpleEntry<>(sccAttacked,node.getValue()));
            }else{
                int indexOfsuccessor = successorCluster.indexOf(sccAttacked);
                if (node.getValue() > successorCluster.get(indexOfsuccessor).getValue()){
                    successorCluster.set(indexOfsuccessor, new AbstractMap.SimpleEntry<>(sccAttacked,node.getValue()));
                }
            }
        }
        return successorCluster;
    }
    public double getLowerbound() {
        return lowerbound;
    }

    public double getUpperbound() {
       return upperbound;
    }


    public double getInternalConstartins() {
        return internalConstartins;
    }

    public ArrayList<Node> getNodes(){
       return nodes;
    }


    public ArrayList<Map.Entry<Integer, Double>> getpredecessorClusters() {
        return predecessorClusters;
    }

    public ArrayList<Map.Entry<Integer, Double>> getsuccessorClusters() {

       return successorClusters;
    }

    public void setLambdaMinus(double lambdaMinus) {
       if (lambdaMinus > this.lambdaMinus){
           this.lambdaMinus = lambdaMinus;
       }
    }

    public void setLambdaPlus(double lambdaPlus) {
        if (lambdaPlus < this.lambdaPlus) {
            this.lambdaPlus = lambdaPlus;
        }
    }

    public boolean isSolvable() {
       if (lambdaMinus <= lambdaPlus){
           return true;
       }else{
           return false;
       }
    }

    public double getLambdaMinus(){
       return lambdaMinus;
    }

    public double getLambdaPlus(){
        return lambdaPlus;
    }

}
