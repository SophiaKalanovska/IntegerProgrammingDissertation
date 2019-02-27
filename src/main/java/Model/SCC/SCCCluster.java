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
    private int lambdaMinus = 0;
    private double lambdaPlus = Double.POSITIVE_INFINITY;
    private ArrayList<Map.Entry<Node, Double>> attackedBy;
    private ArrayList<Map.Entry<Node, Double>> attacking;
    private ArrayList<Map.Entry<Integer, Double>> attackedByClusters;
    private ArrayList<Map.Entry<Integer, Double>> attackingClusters;



   public SCCCluster(int id){
       nodes = new ArrayList<>();
       attackedBy = new ArrayList<>();
       attacking = new ArrayList<>();
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
            attackedBy.addAll((ArrayList<Map.Entry<Node, Double>>)n.getAttribute("attackedBy"));
            attacking.addAll((ArrayList<Map.Entry<Node, Double>>)n.getAttribute("attacking"));

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
        attackedByClusters = setAttackedByClusters();
        attackingClusters = setAttackingClusters();
        System.out.println("Cluster "+ id +"attacked by" + attackedByClusters);
        System.out.println("Cluster " + id + "attacking" +attackingClusters);
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


    public ArrayList<Map.Entry<Integer, Double>> setAttackedByClusters(){
       //clusterAttacking and the weight of the attack
       ArrayList<Map.Entry<Integer, Double>> attackedByCluster = new ArrayList<>();
       for (Map.Entry<Node, Double> node: attackedBy){
           int sccAttacking = node.getKey().getAttribute("SCC");
           if(!attackedByCluster.contains(sccAttacking)){
               attackedByCluster.add( new AbstractMap.SimpleEntry<>(sccAttacking,node.getValue()));
           }else{
                int indexOfAttacking = attackedByCluster.indexOf(sccAttacking);
                if (node.getValue() > attackedByCluster.get(indexOfAttacking).getValue()){
                    attackedByCluster.set(indexOfAttacking, new AbstractMap.SimpleEntry<>(sccAttacking,node.getValue()));
                }
           }
       }
       return attackedByCluster;
    }

    public ArrayList<Map.Entry<Integer, Double>> setAttackingClusters(){
        //clusterAttacked and the weight of the attack
        ArrayList<Map.Entry<Integer, Double>> attackingCluster = new ArrayList<>();
        for (Map.Entry<Node, Double> node: attacking){
            int sccAttacked = node.getKey().getAttribute("SCC");
            if(!attackingCluster.contains(sccAttacked)){
                attackingCluster.add( new AbstractMap.SimpleEntry<>(sccAttacked,node.getValue()));
            }else{
                int indexOfAttacking = attackingCluster.indexOf(sccAttacked);
                if (node.getValue() > attackingCluster.get(indexOfAttacking).getValue()){
                    attackingCluster.set(indexOfAttacking, new AbstractMap.SimpleEntry<>(sccAttacked,node.getValue()));
                }
            }
        }
        return attackingCluster;
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


    public ArrayList<Map.Entry<Integer, Double>> getAttackedByClusters() {
        return attackedByClusters;
    }

    public ArrayList<Map.Entry<Integer, Double>> getAttackingClusters() {

       return attackingClusters;
    }

    public void setLambdaMinus(int lambdaMinus) {
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

    public int getLambdaMinus(){
       return lambdaMinus;
    }

    public double getLambdaPlus(){
        return lambdaPlus;
    }

}
