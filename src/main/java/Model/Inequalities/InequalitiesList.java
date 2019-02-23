package model.Inequalities;

import controller.GraphController;
import model.SCC.SCCAlgorithm;
import model.SCC.SCCClusterList;

import java.util.ArrayList;
import java.util.Observable;
/**
 * This class represents a portofolio of Projects
 */
public class InequalitiesList extends Observable implements java.io.Serializable {

    private ArrayList<Inequality> inequalitiesContainer;
    private GraphController graphController;
    private SCCAlgorithm algoritm;
    private SCCClusterList SCCComponents;

    /**
     * Creates a InequalitiesList object
     */
    public InequalitiesList(GraphController graphController) {
        this.graphController = graphController;
        inequalitiesContainer = new ArrayList<Inequality>();
        algoritm = new SCCAlgorithm(graphController.getGraph());
        //projectMap = new HashMap<String, Projects>();
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
            graphController.addNode(x.getFirstDecisionVariable());
            graphController.addNode(x.getSecondDecisionVariable());
            graphController.addEdge(x.getFirstDecisionVariable(), x.getSecondDecisionVariable());
            graphController.getPipeIn().pump();
            algoritm.calculateSCC();
            SCCComponents = algoritm.cluster();
            setChanged();
            notifyObservers();
        }else{
            this.inequalitiesContainer.add(x);
            graphController.addNode(x.getFirstDecisionVariable());
            graphController.getPipeIn().pump();
            algoritm.calculateSCC();
            SCCComponents = algoritm.cluster();
            setChanged();
            notifyObservers();
        }
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
        tryUpdate();
    }

    /**
     * Changes the name of a project to the new one
     *
     * @param oldInequality the current name of the project
     * @param newInequality the new name for the project
     */
//    public void changeInequality(String oldInequality, String newInequality)
//    {
//        for (int i = 0; i <inequalitiesContainer.size() ; i++)
//        {
//            if (((inequalitiesContainer.get(i)).getExpreission().equals(oldInequality)))
//            {
//                (inequalitiesContainer.get(i)).changeExpression(newInequality);
//            }
//        }
//
//        setChanged();
//        notifyObservers();
//    }

    public SCCClusterList getSCCComponents() {
        return SCCComponents;
    }
    /**
     * Returns the wallet as an ArrayList of Projects
     *
     * @return an ArrayList of projects that are contained in this wallet
     */
    public ArrayList<Inequality> getProjectWallet() {
        return inequalitiesContainer;
    }


    public void storeProject(ArrayList<Inequality> data) {
        this.inequalitiesContainer = data;

    }

    /**
     * Sends signal to the observers to update the view
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
}
