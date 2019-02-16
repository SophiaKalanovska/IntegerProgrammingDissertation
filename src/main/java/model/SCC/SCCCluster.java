package model.SCC;

import org.graphstream.graph.Node;

import java.awt.*;
import java.util.ArrayList;


public class SCCCluster {
    private Color color;
    private int id;
    private ArrayList<Node> nodes;
    private ColorGenerator colorGenerator;
    private double lowerbound = Double.NEGATIVE_INFINITY;
    private double upperbound = Double.POSITIVE_INFINITY;

   public SCCCluster(int id){
       nodes = new ArrayList<Node>();
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

    public void evaluateUpperBound(){

    }

    public void evaluateLowerBound() {
        for ( Node n : nodes){

//            int SCCIndex = n.;
        }
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
        return lowerbound;
    }

    public double getUpperbound() {
        return upperbound;
    }


}
