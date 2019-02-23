package Model.SCC;

import org.graphstream.graph.Node;

import java.awt.*;
import java.util.ArrayList;


public class SCCCluster {
    private Color color;
    private int id;
    private ArrayList<Node> nodes;
    private ColorGenerator colorGenerator;
    private double lowerbound = 0;
    private double upperbound = Double.POSITIVE_INFINITY;
    private double internalConstartins = 0;
    private boolean notAttacked;
    private ArrayList<Node> incommming;
    private ArrayList<Integer> dependant;


   public SCCCluster(int id){
       nodes = new ArrayList<>();
       incommming = new ArrayList<>();
       dependant = new ArrayList<>();
       colorGenerator = new ColorGenerator();
       this.id = id;
       notAttacked = true;
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
    }
//
//    private ArrayList<Node> getEnteringNodes(Node n) {
//       ArrayList<Node> enteringNodes = new ArrayList<>();
//       for (Edge edge :n.getEnteringEdgeSet()){
//           enteringNodes.add(edge.getSourceNode());
//       }
//       return enteringNodes;
//    }


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

    public void setInternalConstartins(double internalConstartins) {
        this.internalConstartins = internalConstartins;
    }
}
