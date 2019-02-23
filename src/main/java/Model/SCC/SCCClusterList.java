package model.SCC;
import org.graphstream.graph.Node;

import java.util.ArrayList;

/**
 * This class represents a portofolio of Projects
 */
public class SCCClusterList {
    private ArrayList<SCCCluster> SCCContainer;

    /**
     * Creates a InequalitiesList object
     */
    public SCCClusterList() {

        SCCContainer = new ArrayList<SCCCluster>();
    }


    public void addCluster(SCCCluster cluster) {
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

}