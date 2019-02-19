package controller;

import model.Inequalities.DecisionVariable;
import model.SCC.SCCAlgorithm;
import model.SCC.SCCClusterList;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.ViewerPipe;
import view.GraphGUI;
import java.io.Serializable;
import java.util.ArrayList;


public class GraphController implements Serializable {
    private final GraphGUI graphGUI;
    private final Graph graph;
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private SCCAlgorithm algoritm;
    private SCCClusterList SCCComponents;

    private int id;


    public GraphController(GraphGUI graphGUI, Graph graph) {
        this.graphGUI = graphGUI;
        this.graph = graph;
        algoritm = new SCCAlgorithm(graph);
        id = 0;

    }

    public static String randomAlphaNumeric(int count, String alpha) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * alpha.length());
            builder.append(alpha.charAt(character));
        }
        return builder.toString();
    }

    public void addRandomNodes(int n) {
        String nodes = randomAlphaNumeric(n + 1, ALPHA);


        System.out.println(nodes);
        for (int i = 0; i < n - 1 && graph.getNodeCount() < 18; ) {

            ArrayList<String> list = new ArrayList<String>();
            list.add(Character.toString(nodes.charAt(i)));
            list.add("<");
            list.add(Character.toString(nodes.charAt(i + 1)));
//					addNodes(list);

            i = i + 1;
        }
        // add if the number of inequalities are more than 30
        System.out.println("nodes so far" + graph.getNodeCount());
        if (graph.getNodeCount() > 17 || n > 30) {
            for (int i = n - 1; i > 26; ) {
                System.out.println(i);
                ArrayList<String> list = new ArrayList<String>();
                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                sb.append(nodes.charAt(i));
                sb.append(nodes.charAt(i - 1));
                sb2.append(nodes.charAt(i - 2));
                sb2.append(nodes.charAt(i - 3));
                list.add(sb.toString());
                list.add("<");
                if (i < 29) {
                    list.add(Character.toString(nodes.charAt(i - 10)));
                } else {
                    list.add(sb2.toString());
                }
                i = i - 2;
            }
        }

    }

    public void addNode(DecisionVariable firstUnknownVariable){

        if (graph.getNode(firstUnknownVariable.toString()) == null ){
            graph.addNode(firstUnknownVariable.toString());
            Node first = graph.getNode(firstUnknownVariable.toString());
            first.setAttribute("ui.label", first.getId());
            first.setAttribute("internal_weight", 0.0);
            id ++;
            getPipeIn().pump();
            algoritm.calculateSCC();
            SCCComponents = algoritm.cluster();
        }
        Node first = graph.getNode(firstUnknownVariable.toString());
        first.setAttribute("upper_bound", firstUnknownVariable.getUpperBound());
        first.setAttribute("lower_bound", firstUnknownVariable.getLowerBound());
    }
//
//    public void addNodes(DecisionVariable firstUnknownVariable, DecisionVariable secondUnknownVariable, int firstWeight, int secondWeigh) {
//        Edge edge = graph.addEdge("" + id + "", firstUnknownVariable.toString(), secondUnknownVariable.toString(), true);
//        double weightOfEdge = (double) firstWeight / secondWeigh;
//        edge.setAttribute("ui.label", String.format("%.2f", weightOfEdge));
//        edge.addAttribute("ui.style", "text-alignment:above;");
//        edge.addAttribute("weight", weightOfEdge);
//        Node first = graph.getNode(firstUnknownVariable.toString());
//        Node second = graph.getNode(secondUnknownVariable.toString());
//        first.setAttribute("ui.label", first.getId());
//        second.setAttribute("ui.label", second.getId());
//        first.setAttribute("upper_bound", firstUnknownVariable.getUpperBound());
//        first.setAttribute("lower_bound", firstUnknownVariable.getLowerBound());
//        second.setAttribute("upper_bound", secondUnknownVariable.getUpperBound());
//        second.setAttribute("lower_bound", secondUnknownVariable.getLowerBound());
//        id ++;
//        getPipeIn().pump();
//        algoritm.calculateSCC();
//        SCCComponents = algoritm.cluster();
//
//    }

    public void addNodes(DecisionVariable firstUnknownVariable, DecisionVariable secondUnknownVariable, int firstWeight, int secondWeigh) {
        Edge edge = graph.addEdge("" + id + "", firstUnknownVariable.toString(), secondUnknownVariable.toString(), true);
        double weightOfEdge = (double) firstWeight / secondWeigh;
        edge.setAttribute("ui.label", String.format("%.2f", weightOfEdge));
        edge.addAttribute("ui.style", "text-alignment:above;");
        Node first = graph.getNode(firstUnknownVariable.toString());
        Node second = graph.getNode(secondUnknownVariable.toString());
        first.setAttribute("ui.label", first.getId());
        second.setAttribute("ui.label", second.getId());
        first.setAttribute("upper_bound", firstUnknownVariable.getUpperBound());
        first.setAttribute("lower_bound", firstUnknownVariable.getLowerBound());
        second.setAttribute("upper_bound", secondUnknownVariable.getUpperBound());
        second.setAttribute("lower_bound", secondUnknownVariable.getLowerBound());
        if (!first.getAttributeKeySet().contains("internal_weight")){
            first.setAttribute("internal_weight", 0.0);
        }
        if (second.getAttributeKeySet().contains("internal_weight") && (double)second.getAttribute("internal_weight") <  weightOfEdge){
            second.setAttribute("internal_weight", weightOfEdge);
        }else if (!second.getAttributeKeySet().contains("internal_weight")){
            second.setAttribute("internal_weight", weightOfEdge);
        }
        id ++;
        getPipeIn().pump();
        algoritm.calculateSCC();
        SCCComponents = algoritm.cluster();

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
        getPipeIn().pump();
        algoritm.calculateSCC();
        SCCComponents = algoritm.cluster();


    }

    public ViewerPipe getPipeIn() {
        return graphGUI.getPipe();
    }

    public SCCClusterList getSCCComponents() {
        return SCCComponents;
    }
}