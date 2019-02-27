package Model.SCC;

import java.util.*;
import java.lang.Math;

/**
 * This class represents a portofolio of Projects
 */
public class SCCClusterList {
    private Map<Integer,SCCCluster> SCCContainerMapId;
    private ArrayList<SCCCluster> SCCContainer;

    /**
     * Creates a InequalitiesList object
     */
    public SCCClusterList() {

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

    private int lambdaPlus(SCCCluster cluster) {
        double clusterUpperBound= cluster.getUpperbound();
        int clusterUpperCeil = (int) Math.floor(clusterUpperBound);
        if(cluster.getAttackingClusters().isEmpty()){
            return clusterUpperCeil;
        }else{
            ArrayList<Integer> lambdas = new ArrayList<>();
            lambdas.add(clusterUpperCeil);
            for (Map.Entry<Integer, Double> entry : cluster.getAttackedByClusters()){
                int lambdaOfAttacker = lambdaPlus(SCCContainerMapId.get(entry.getKey()));
                lambdas.add((int) Math.floor( lambdaOfAttacker/entry.getValue()));
            }
            return Collections.min(lambdas);
        }
    }

    public void evaluateLambdas(){
        for (SCCCluster cluster : SCCContainer){
            cluster.setLambdaMinus(lambdaMinus(cluster));
            System.out.println("cluster id " + cluster.getId()+ "lambda minus" + lambdaMinus(cluster));
            cluster.setLambdaPlus(lambdaPlus(cluster));
            System.out.println("cluster id " + cluster.getId()+ "lambda plus" + lambdaPlus(cluster));

        }
    }




}