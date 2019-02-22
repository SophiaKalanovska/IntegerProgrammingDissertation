package controller;

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

    public void populate(GraphController graphController){
        calculateBounds(graphController);

        lowerBoundClusterListController.populate(graphController);
        upperBoundClusterListController.populate(graphController);
        internalConstarinsClusterListController.populate(graphController);
    }


    public void calculateBounds(GraphController graphController){

        graphController.getSCCComponents().evaluate();
    }





}
