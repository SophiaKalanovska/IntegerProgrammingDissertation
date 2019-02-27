package Controller.Constrains;

import Model.Inequalities.InequalitiesList;


public class ConstarinsController {

    private LowerBoundClusterListController lowerBoundClusterListController;
    private UpperBoundClusterListController upperBoundClusterListController;
    private InternalConstarinsClusterListController internalConstarinsClusterListController;
    private IntegerAssignenmentMinListController integerAssignenmentMinListController;
    private IntegerAssignenmentMaxListController integerAssignenmentMaxListController;

    public ConstarinsController(LowerBoundClusterListController lowerBoundClusterListController, UpperBoundClusterListController upperBoundClusterListController, InternalConstarinsClusterListController internalConstarinsClusterListController, IntegerAssignenmentMinListController integerAssignenmentMinListController, IntegerAssignenmentMaxListController integerAssignenmentMaxListController){

        this.lowerBoundClusterListController = lowerBoundClusterListController;
        this.upperBoundClusterListController = upperBoundClusterListController;
        this.internalConstarinsClusterListController = internalConstarinsClusterListController;
        this.integerAssignenmentMinListController = integerAssignenmentMinListController;
        this.integerAssignenmentMaxListController = integerAssignenmentMaxListController;
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
            integerAssignenmentMinListController.populate(inequalitiesList);
            integerAssignenmentMaxListController.populate(inequalitiesList);
        }else{

        }

    }




}
