package Model.Inequalities;

import Controller.GraphController;
import Model.SCC.SCCAlgorithm;
import Model.SCC.SCCClusterList;

import java.util.ArrayList;
import java.util.Observable;
/**
 * This class represents a portofolio of Projects
 */
public class InequalitiesList extends Observable implements java.io.Serializable {

    private ArrayList<Inequality> inequalitiesContainer;
    private GraphController graphController;
    private SCCAlgorithm algorithm;
    private Inequality lastAdded;
    private SCCClusterList SCCComponents;

    /**
     * Creates a InequalitiesList object
     */
    public InequalitiesList(GraphController graphController, SCCAlgorithm algorithm) {
        this.graphController = graphController;
        inequalitiesContainer = new ArrayList<>();
        this.algorithm = algorithm;
    }


    /**
     * Adds a project to the wallet
     *
     * @param x the project that is to be added to the wallet
     */
    public SCCClusterList addInequality(Inequality x) {
        String decision = x.getSecondDecisionVariableValue();
        if( decision != null){
            this.inequalitiesContainer.add(x);
//            graphController.addNode(x.getFirstDecisionVariable());
//            graphController.addNode(x.getSecondDecisionVariable());
//            graphController.addEdge(x.getFirstDecisionVariable(), x.getSecondDecisionVariable());
        }else{
            this.inequalitiesContainer.add(x);
            graphController.addNode(x.getFirstDecisionVariable());
        }
        lastAdded = x;
//        graphController.getPipeIn().pump();
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                algorithm.clear();
//                algorithm.calculateSCC();
//                SCCComponents = algorithm.cluster();
//            }
//        };
//        thread.start();
        setChanged();
        notifyObservers();

        return SCCComponents;
    }
//

    /**
     * Deletes a project that has the same name as the one supplied
     *
     * @param x the name of the projects that has to be deleted
     */
    public void deleteInequality(Inequality x) {
        graphController.removeNodes(x.getFirstDecisionVariableValue(), x.getSecondDecisionVariableValue());
        inequalitiesContainer.remove(x);
        graphController.getPipeIn().pump();
        algorithm.clear();
        algorithm.calculateSCC();
        SCCComponents = algorithm.cluster();
        setChanged();
        notifyObservers();
    }

    public SCCClusterList getSCCComponents() {
        return SCCComponents;
    }
    /**
     * Returns the wallet as an ArrayList of Projects
     *
     * @return an ArrayList of projects that are contained in this wallet
     */
    public Inequality getProjectWallet() {
        return lastAdded;
    }


    public void storeProject(ArrayList<Inequality> data) {
        this.inequalitiesContainer = data;

    }

    /**
     * Sends signal to the observers to update the View
     */
    public void tryUpdate() {
        setChanged();
        notifyObservers();
    }

    public void deleteAllInequalities() {
        for (int i = 0; i < inequalitiesContainer.size(); i++) {
            graphController.removeNodes(inequalitiesContainer.get(i).getFirstDecisionVariableValue(), inequalitiesContainer.get(i).getSecondDecisionVariableValue());
        }
        inequalitiesContainer.removeAll(inequalitiesContainer);
        tryUpdate();
    }

    public void deleteGraph() {
        graphController.deleteGraph();
        inequalitiesContainer.removeAll(inequalitiesContainer);
        tryUpdate();
    }
}
