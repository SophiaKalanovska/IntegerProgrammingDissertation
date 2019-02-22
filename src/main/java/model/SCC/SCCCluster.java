package model.SCC;

import org.graphstream.graph.Edge;
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

    public boolean getAttacked(){
        for ( Node n : nodes) {
//            if (!first.getAttributeKeySet().contains("internal_weight")){
//                first.setAttribute("internal_weight", 0.0);
//            }
//            if (second.getAttributeKeySet().contains("internal_weight") && (double)second.getAttribute("internal_weight") <  weightOfEdge){
//                second.setAttribute("internal_weight", weightOfEdge);
//            }else if (!second.getAttributeKeySet().contains("internal_weight")){
//                second.setAttribute("internal_weight", weightOfEdge);
//            }
//            n.setAttribute("upper_bound", );
        }
        return true;
    }



    public void evaluateUpperBound(){
        for ( Node n : nodes){
            double upperBoundNode = n.getAttribute("upper_bound");
            if ( upperBoundNode < upperbound){
                upperbound = upperBoundNode;
            }
            n.getEnteringEdgeSet();

        }
    }

    public void evaluateLowerBound(){
        for ( Node n : nodes){
            double lowerBoundNode = n.getAttribute("lower_bound");
            if ( lowerBoundNode > lowerbound){
                lowerbound = lowerBoundNode;
            }
            n.getEnteringEdgeSet();

        }
    }

    public void evaluateInternalConstarins() {
        for ( Node n : nodes){
            double internalWeightNode = n.getAttribute("internal_weight");
            if( internalWeightNode > internalConstartins){
                internalConstartins = internalWeightNode;
            }
        }
    }

    public void evaluate() {
        for ( Node n : nodes){
            ArrayList<Node> entering = getEnteringNodes(n);
            incommming.addAll(entering);
        }
    }

    private ArrayList<Node> getEnteringNodes(Node n) {
       ArrayList<Node> enteringNodes = new ArrayList<>();
       for (Edge edge :n.getEnteringEdgeSet()){
           enteringNodes.add(edge.getSourceNode());
       }
       return enteringNodes;
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

    public double getLowerbound() {
        evaluateLowerBound();
        return lowerbound;
    }

    public double getUpperbound() {
       evaluateUpperBound();
       return upperbound;
    }


    public double getInternalConstartins() {
        evaluateInternalConstarins();
        return internalConstartins;
    }

    public ArrayList<Node> getNodes(){
       return nodes;
    }

    public void setInternalConstartins(double internalConstartins) {
        this.internalConstartins = internalConstartins;
    }
}
