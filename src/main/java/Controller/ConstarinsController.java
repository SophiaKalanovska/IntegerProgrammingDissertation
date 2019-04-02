package Controller;
import Model.SCC.BoundsListRender;
import Model.SCC.CreateImageMap;
import Model.SCC.SCCClusterList;
import View.SolutionPanel.*;
import javax.swing.*;
import java.util.Map;

public class ConstarinsController {

    private LowerBoundClusterGUI lowerBoundClusterGUI;
    private UpperBoundClusterGUI upperBoundClusterGUI;
    private InternalConstarinsClusterGUI internalConstarinsClusterGUI;
    private IntegerAssignmentMinimizeGUI integerAssignmentMinimizeGUI;
    private IntegerAssignmentMaximizeGUI integerAssignmentMaximizeGUI;


    public ConstarinsController(LowerBoundClusterGUI lowerBoundClusterGUI, UpperBoundClusterGUI upperBoundClusterGUI, InternalConstarinsClusterGUI internalConstarinsClusterGUI, IntegerAssignmentMinimizeGUI integerAssignmentMinimizeGUI, IntegerAssignmentMaximizeGUI integerAssignmentMaximizeGUI){
        this.lowerBoundClusterGUI = lowerBoundClusterGUI;
        this.upperBoundClusterGUI = upperBoundClusterGUI;
        this.internalConstarinsClusterGUI = internalConstarinsClusterGUI;
        this.integerAssignmentMinimizeGUI = integerAssignmentMinimizeGUI;
        this.integerAssignmentMaximizeGUI = integerAssignmentMaximizeGUI;

    }

    public void populate(GraphController graph){
        calculateBounds(graph);
        createMap(graph.getSCCComponents());
        calculateLambdas(graph.getSCCComponents());
    }

    public void delete() {
        createMap(new SCCClusterList());
        calculateLambdas(new SCCClusterList());
    }

    public void createMap(SCCClusterList components){
        CreateImageMap createImageMap = new CreateImageMap(components);
        createImageMap.addObserver(lowerBoundClusterGUI);
        createImageMap.addObserver(upperBoundClusterGUI);
        createImageMap.addObserver(internalConstarinsClusterGUI);
        Map<Integer, ImageIcon> map = createImageMap.populate();
        BoundsListRender render = new BoundsListRender();
        render.setImageMap(map);
        lowerBoundClusterGUI.setRender(render);
        upperBoundClusterGUI.setRender(render);
        internalConstarinsClusterGUI.setRender(render);
        createImageMap.tryUpdate();
    }

    public void calculateBounds( GraphController graph){
        graph.getSCCComponents().evaluate();
    }

    public void calculateLambdas( SCCClusterList components) {
        components.evaluateLambdas();
        CreateImageMap createImageMap = new CreateImageMap(components);
        createImageMap.addObserver(integerAssignmentMinimizeGUI);
        createImageMap.addObserver(integerAssignmentMaximizeGUI);
        Map<Integer, ImageIcon> map = createImageMap.populateInteger();
        BoundsListRender render = new BoundsListRender();
        render.setImageMap(map);
        integerAssignmentMinimizeGUI.setRender(render);
        integerAssignmentMaximizeGUI.setRender(render);
        createImageMap.tryUpdate();
    }
}
