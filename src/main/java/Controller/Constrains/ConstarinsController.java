package controller;

import model.Inequalities.InequalitiesList;
import view.OperationsOnInequalities.InequalitiesListGUI;


public class ConstarinsController {

    private LowerBoundClusterListController lowerBoundClusterListController;
    private UpperBoundClusterListController upperBoundClusterListController;
    private InternalConstarinsClusterListController internalConstarinsClusterListController;

    public ConstarinsController(LowerBoundClusterListController lowerBoundClusterListController, UpperBoundClusterListController upperBoundClusterListController, InternalConstarinsClusterListController internalConstarinsClusterListController){

        this.lowerBoundClusterListController = lowerBoundClusterListController;
        this.upperBoundClusterListController = upperBoundClusterListController;
        this.internalConstarinsClusterListController = internalConstarinsClusterListController;
    }

    public void populate(GraphController graphController, InequalitiesList inequalitiesList){
        calculateBounds(inequalitiesList);

        lowerBoundClusterListController.populate(inequalitiesList);
        upperBoundClusterListController.populate(inequalitiesList);
        internalConstarinsClusterListController.populate(inequalitiesList);
    }


    public void calculateBounds( InequalitiesList inequalitiesList){

        inequalitiesList.getSCCComponents().evaluate();
    }





}
