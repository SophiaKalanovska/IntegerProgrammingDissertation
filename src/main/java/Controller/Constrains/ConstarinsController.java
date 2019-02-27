package Controller.Constrains;

import Controller.GraphController;
import Model.Inequalities.InequalitiesList;


public class ConstarinsController {

    private LowerBoundClusterListController lowerBoundClusterListController;
    private UpperBoundClusterListController upperBoundClusterListController;
    private InternalConstarinsClusterListController internalConstarinsClusterListController;
    private IntegerAssignenmentListController integerAssignenmentListController;

    public ConstarinsController(LowerBoundClusterListController lowerBoundClusterListController, UpperBoundClusterListController upperBoundClusterListController, InternalConstarinsClusterListController internalConstarinsClusterListController, IntegerAssignenmentListController integerAssignenmentListController){

        this.lowerBoundClusterListController = lowerBoundClusterListController;
        this.upperBoundClusterListController = upperBoundClusterListController;
        this.internalConstarinsClusterListController = internalConstarinsClusterListController;
        this.integerAssignenmentListController = integerAssignenmentListController;
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
        if (inequalitiesList.getSCCComponents().isSolvable()){
            integerAssignenmentListController.populate(inequalitiesList);
        }

    }




}
