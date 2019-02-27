package Controller.Constrains;

import Controller.GraphController;
import Model.Inequalities.InequalitiesList;


public class ConstarinsController {

    private LowerBoundClusterListController lowerBoundClusterListController;
    private UpperBoundClusterListController upperBoundClusterListController;
    private InternalConstarinsClusterListController internalConstarinsClusterListController;

    public ConstarinsController(LowerBoundClusterListController lowerBoundClusterListController, UpperBoundClusterListController upperBoundClusterListController, InternalConstarinsClusterListController internalConstarinsClusterListController){

        this.lowerBoundClusterListController = lowerBoundClusterListController;
        this.upperBoundClusterListController = upperBoundClusterListController;
        this.internalConstarinsClusterListController = internalConstarinsClusterListController;
    }

    public void populate(InequalitiesList inequalitiesList){
        calculateBounds(inequalitiesList);

        lowerBoundClusterListController.populate(inequalitiesList);
        upperBoundClusterListController.populate(inequalitiesList);
        internalConstarinsClusterListController.populate(inequalitiesList);

        calculateLambdas(inequalitiesList);
    }


    public void calculateBounds( InequalitiesList inequalitiesList){

        inequalitiesList.getSCCComponents().evaluate();
    }

    public void calculateLambdas( InequalitiesList inequalitiesList){
        inequalitiesList.getSCCComponents().evaluateLambdas();
    }




}
