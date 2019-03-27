package Controller.Constrains;
import Controller.GraphController;

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

    public void populate(GraphController graph){
        calculateBounds(graph);
        lowerBoundClusterListController.populate(graph);
        upperBoundClusterListController.populate(graph);
        internalConstarinsClusterListController.populate(graph);
        calculateLambdas(graph);
    }


    public void calculateBounds( GraphController graph){
        graph.getSCCComponents().evaluate();
    }

    public void calculateLambdas( GraphController graph){
        graph.getSCCComponents().evaluateLambdas();
        if (graph.getSCCComponents().isSolvable()){
            integerAssignenmentMinListController.populate(graph);
            integerAssignenmentMaxListController.populate(graph);
            System.out.print(Double.POSITIVE_INFINITY/ -5);
        }else{
            integerAssignenmentMaxListController.populate();
            System.out.print("No solution");
        }

    }




}
