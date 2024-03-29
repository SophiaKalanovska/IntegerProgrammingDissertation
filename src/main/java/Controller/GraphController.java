package Controller;

import Model.Inequalities.DecisionVariable;
import Model.Inequalities.Inequality;
import Model.SCC.SCCAlgorithm;
import Model.SCC.SCCClusterList;
import View.LayoutGUI;
import org.graphstream.algorithm.TarjanStronglyConnectedComponents;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.ViewerPipe;
import View.Graph.GraphGUI;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;


public class GraphController implements Serializable {
    private final GraphGUI graphGUI;
    private final Graph graph;
    private SCCAlgorithm algorithm ;
    private SCCClusterList SCCComponents;
    private int id;

    public GraphController(LayoutGUI layoutGUI) {
        this.graphGUI = layoutGUI.getGraphGUI();
        this.graph = graphGUI.getGraph();
        algorithm = new SCCAlgorithm();
        id = 0;

    }

    /**
     * This method takes a decision variable
     * checks if the node representation is in the graph.
     * If it is not it adds it with the necessary attributes
     */
    public void addNode(DecisionVariable decisionVariable){
        if (graph.getNode(decisionVariable.toString()) == null ){
            graph.addNode(decisionVariable.toString());
            Node node = graph.getNode(decisionVariable.toString());
            node.setAttribute("ui.label", node.getId());
            node.setAttribute("internal_weight", 0.0);
            node.addAttribute("decision_variable", decisionVariable);
            node.setAttribute("upper_bound", decisionVariable.getUpperBound());
            node.setAttribute("lower_bound", decisionVariable.getLowerBound());
            node.addAttribute("predecessor", new ArrayList<Map<Node, Double>>());
            node.addAttribute("successor", new ArrayList<Map.Entry<Node, Integer>>());
            node.addAttribute("SCC", 0);
        }else{
            Node node = graph.getNode(decisionVariable.toString());
            DecisionVariable original = node.getAttribute("decision_variable");
            original.setUpperBound(decisionVariable.getUpperBound());
            original.setLowerBound(decisionVariable.getLowerBound());
            node.setAttribute("upper_bound", original.getUpperBound());
            node.setAttribute("lower_bound", original.getLowerBound());
        }
    }

    /**
     * This takes an inequality and add an edge
     * if the inequality contains two decision variables
     * if it contains one, it adds the bound to the node
     */
    public void drawInequality(Inequality inequality) {
        String decision = inequality.getSecondDecisionVariableValue();
        if (decision != null) {
            addNode(inequality.getLeftDecisionVariable());
            addNode(inequality.getRightDecisionVariable());
            addEdge(inequality.getLeftDecisionVariable(), inequality.getRightDecisionVariable());
        } else {
            addNode(inequality.getLeftDecisionVariable());
        }
    }

    /**
     * This method runs the back-end to calculate the SCC
     */
    public void findStronglyConnectedComponents()
    {
        TarjanStronglyConnectedComponents trj = algorithm.calculateSCC(graph);
        algorithm.clear();
        SCCComponents = algorithm.cluster(trj);
    }


    /**
     * This method removes an inequality from the list
     */
    public void undrawInequality(Inequality x){
        removeNodes(x.getFirstDecisionVariableValue(), x.getSecondDecisionVariableValue());
    }

    /**
     * This method adds an edge given two decision variables
     */
    public void addEdge(DecisionVariable firstDecisionVariable, DecisionVariable secondDecisionVariable){
        Node node = graph.getNode(firstDecisionVariable.toString());
        Edge between = node.getEdgeToward(graph.getNode(secondDecisionVariable.toString()));
        if (between == null){
            Edge edge = graph.addEdge("" + id + "", firstDecisionVariable.toString(), secondDecisionVariable.toString(), true);
            double weightOfEdge = firstDecisionVariable.getWeight();
            edge.setAttribute("ui.label", String.format("%.2f", weightOfEdge));
            edge.addAttribute("weight", weightOfEdge);
            id ++;
        }else{
            double weightOfEdge = firstDecisionVariable.getWeight();
            if (weightOfEdge > (double)between.getAttribute("weight")){
                between.setAttribute("ui.label", String.format("%.2f", weightOfEdge));
                between.setAttribute("weight", weightOfEdge);
            }
        }
    }

    /**
     * This method removes the nodes if they are not
     * connected to any other component in the graph
     */
    public void removeNodes(String firstUnknownVariable, String secondUnknownVariable) {
        Node first = graph.getNode(firstUnknownVariable);
        Node second = graph.getNode(secondUnknownVariable);
        if (first.getDegree() == 0)
            graph.removeNode(first);

        if (second != null){
            graph.removeEdge(first, second);
            if (second.getDegree() == 0) {
                graph.removeNode(second);
            }
            if (first.getDegree() == 0)
                graph.removeNode(first);
        }
        getPipeIn().pump();
    }

    public ViewerPipe getPipeIn() {
        return graphGUI.getPipe();
    }

    public Graph getGraph(){
        return graph;
    }

    public SCCClusterList getSCCComponents() {
        return SCCComponents;
    }

    /**
     * This method removes all component from the graph
     */
    public void deleteGraph() {
        graph.clear();
        graphGUI.setUI();
    }
}