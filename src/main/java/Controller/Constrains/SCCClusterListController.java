package Controller.Constrains;

import Model.SCC.SCCCluster;

import java.util.*;
import java.lang.Math;

/**
 * This class represents a portofolio of Projects
 */
public class SCCClusterListController {
    private Map<Integer,SCCCluster> SCCContainerMapId;
    private ArrayList<SCCCluster> SCCContainer;

    /**
     * Creates a InequalitiesList object
     */
    public SCCClusterListController() {

        SCCContainerMapId = new HashMap();
        SCCContainer = new ArrayList<>();

    }


    public void addCluster(SCCCluster cluster) {
            this.SCCContainerMapId.put(cluster.getId(), cluster);
            this.SCCContainer.add(cluster);

    }

    /**
     * Returns the wallet as an ArrayList of Projects
     *
     * @return an ArrayList of projects that are contained in this wallet
     */
    public ArrayList<SCCCluster> getProjectWallet() {
        return SCCContainer;
    }

    public void evaluate() {
        for (SCCCluster cluster : SCCContainer){
            cluster.evaluate();
        }
    }


    public int  lambdaMinus(SCCCluster cluster){
        double clusterLowerBound= cluster.getLowerbound();
        int clusterLowerCeil = (int) Math.ceil(clusterLowerBound);
        if(cluster.getAttackedByClusters().isEmpty()){
            return clusterLowerCeil;
        }else{
            ArrayList<Integer> lambdas = new ArrayList<>();
            lambdas.add(clusterLowerCeil);
            for (Map.Entry<Integer, Double> entry : cluster.getAttackedByClusters()){
                int lambdaOfAttacker = lambdaMinus(SCCContainerMapId.get(entry.getKey()));
                lambdas.add((int) Math.ceil(entry.getValue() * lambdaOfAttacker));
            }
            return Collections.max(lambdas);
        }
    }

    private double lambdaPlus(SCCCluster cluster) {
        double clusterUpperBound= cluster.getUpperbound();
        double clusterUpperCeil =  Math.floor(clusterUpperBound);
        if(cluster.getAttackingClusters().isEmpty()){
            return clusterUpperCeil;
        }else{
            if (cluster.getInternalConstartins() > 1 && cluster.getUpperbound() != Double.POSITIVE_INFINITY){
                return 0;
            }else{
                ArrayList<Double> lambdas = new ArrayList<>();
                lambdas.add(clusterUpperCeil);
                for (Map.Entry<Integer, Double> entry : cluster.getAttackingClusters()){
                    double lambdaOfAttacker = lambdaPlus(SCCContainerMapId.get(entry.getKey()));
                    lambdas.add(Math.floor( lambdaOfAttacker/entry.getValue()));
                }
                return Collections.min(lambdas);
            }
        }
    }

    public void evaluateLambdas(){
        for (SCCCluster cluster : SCCContainer){
            cluster.setLambdaMinus(lambdaMinus(cluster));
            cluster.setLambdaPlus(lambdaPlus(cluster));
        }
    }


    public boolean isSolvable() {
        for (SCCCluster cluster : SCCContainer){
            if (!cluster.isSolvable()){
                return false;
            }
        }
        return true;
    }
}