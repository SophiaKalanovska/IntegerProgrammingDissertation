package model;

import view.GraphGUI;
import java.util.ArrayList;
import java.util.Observable;

/**
 * This class represents a portofolio of Projects
 */
public class SCCClusterList extends Observable implements java.io.Serializable {


    private ArrayList<SCCCluster> SCCContainer;
    private GraphGUI graph;

    /**
     * Creates a InequalitiesList object
     */
    public SCCClusterList(GraphGUI graph) {
        this.graph = graph;
        SCCContainer = new ArrayList<SCCCluster>();
        //projectMap = new HashMap<String, Projects>();
    }

    /**
     * Creates a InequalitiesList object that is to be populated with the data supplied
     *
     * @param data the ArrayList of Projects that will constitute the newly created InequalitiesList object
     */
    public SCCClusterList(ArrayList<SCCCluster> data) {
        storeProject(data);
        setChanged();
        notifyObservers();
    }

    /**
     * Adds a project to the wallet
     *
     * @param x the project that is to be added to the wallet
     */
    public void addCluster(Inequality x) {
//        boolean insert = true;
//        String decision = x.getSecondDecisionVariableValue();
//        for (int i = 0; i < inequalitiesContainer.size(); i++) {
//            if (((inequalitiesContainer.get(i)).getExpreission().equals(x.getExpreission()))) {
//                insert = false;
//            }
//        }
//        if( decision != null && insert == true){
//            this.inequalitiesContainer.add(x);
//            graph.addNodes(x.getFirstDecisionVariableValue(), x.getSecondDecisionVariableValue(), x.getFirstDecisionVariable().getWeight(), x.getSecondDecisionVariable().getWeight(), x.getSign());
//            graph.getPipe().pump();
//            setChanged();
//            notifyObservers();
//        }else{
//            this.inequalitiesContainer.add(x);
//            setChanged();
//            notifyObservers();
//        }
    }

    /**
     * Returns the wallet as an ArrayList of Projects
     *
     * @return an ArrayList of projects that are contained in this wallet
     */
    public ArrayList<SCCCluster> getProjectWallet() {
        return SCCContainer;
    }


    public void storeProject(ArrayList<SCCCluster> data) {
        this.SCCContainer = data;
    }

    /**
     * Sends signal to the observers to update the view
     */
    public void tryUpdate() {
        setChanged();
        notifyObservers();
    }

}