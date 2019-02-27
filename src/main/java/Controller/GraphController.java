package Controller;

import Model.Inequalities.DecisionVariable;
import View.LayoutGUI;
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
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private int id;

    public GraphController(LayoutGUI layoutGUI) {
        this.graphGUI = layoutGUI.getGraphGUI();
        this.graph = graphGUI.getGraph();
        id = 0;

    }

    public void addNode(DecisionVariable firstUnknownVariable){
        if (graph.getNode(firstUnknownVariable.toString()) == null ){
            graph.addNode(firstUnknownVariable.toString());
            Node node = graph.getNode(firstUnknownVariable.toString());
            node.setAttribute("ui.label", node.getId());
            node.setAttribute("internal_weight", 0.0);
            node.addAttribute("decision_variable", firstUnknownVariable);
            node.setAttribute("upper_bound", firstUnknownVariable.getUpperBound());
            node.setAttribute("lower_bound", firstUnknownVariable.getLowerBound());
            node.addAttribute("attackedBy", new ArrayList<Map<Node, Double>>());

            node.addAttribute("attacking", new ArrayList<Map.Entry<Node, Integer>>());
            node.addAttribute("SCC", 0);
        }else{
            Node node = graph.getNode(firstUnknownVariable.toString());
            DecisionVariable original = node.getAttribute("decision_variable");
            original.setUpperBound(firstUnknownVariable.getUpperBound());
            original.setLowerBound(firstUnknownVariable.getLowerBound());
            node.setAttribute("upper_bound", original.getUpperBound());
            node.setAttribute("lower_bound", original.getLowerBound());
        }

    }

    public void addEdge(DecisionVariable firstUnknownVariable, DecisionVariable secondUnknownVariable){
        Node node = graph.getNode(firstUnknownVariable.toString());
        Edge between = node.getEdgeToward(graph.getNode(secondUnknownVariable.toString()));
        if (between == null){
            Edge edge = graph.addEdge("" + id + "", firstUnknownVariable.toString(), secondUnknownVariable.toString(), true);
//            double weightOfEdge = (double) firstUnknownVariable.getWeight() / secondUnknownVariable.getWeight();
            double weightOfEdge = (double) firstUnknownVariable.getWeight();
            edge.setAttribute("ui.label", String.format("%.2f", weightOfEdge));
            edge.addAttribute("weight", weightOfEdge);
            id ++;
        }else{
//          // double weightOfEdge = (double) firstUnknownVariable.getWeight() / secondUnknownVariable.getWeight();
            double weightOfEdge = firstUnknownVariable.getWeight();
            if (weightOfEdge > (double)between.getAttribute("weight")){
                between.setAttribute("ui.label", String.format("%.2f", weightOfEdge));
                between.setAttribute("weight", weightOfEdge);
            }
        }


    }

    public void removeNodes(String firstUnknownVariable, String secondUnknownVariable) {
        Node first = graph.getNode(firstUnknownVariable);
        Node second = graph.getNode(secondUnknownVariable);
        graph.removeEdge(first, second);
        getPipeIn().pump();
        System.out.print("degree 1 :" + first.getDegree());
        System.out.print("degree 2 :" + second.getDegree());
        if (first.getDegree() == 0) {
            graph.removeNode(first);
        }
        if (second.getDegree() == 0) {
            graph.removeNode(second);
        }
    }

    public ViewerPipe getPipeIn() {
        return graphGUI.getPipe();
    }

    public Graph getGraph(){
        return graph;
    }

    public void deleteGraph() {
        graph.clear();
        graphGUI.setUI();
    }
}