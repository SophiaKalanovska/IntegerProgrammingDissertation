package controller;

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
//
                list.add(sb.toString());
                list.add("<");
                if (i < 29) {
                    list.add(Character.toString(nodes.charAt(i - 10)));
                } else {
                    list.add(sb2.toString());
                }
//					addNodes(list);
                i = i - 2;
            }
        }

    }

    public void addNodes(String firstUnknownVariable, String secondUnknownVariable, int firstWeight, int secondWeigh, String sign) {

        Edge edge = null;
        if (sign.equals("<") || sign.equals("<=")) {
            edge = graph.addEdge("" + id + "", firstUnknownVariable, secondUnknownVariable, true);
        } else {
            edge = graph.addEdge("" + id + "", secondUnknownVariable, firstUnknownVariable, true);
        }
//		edge.setAttribute(String.valueOf(firstWeight/secondWeigh));
        double weightOfEdge = (double) firstWeight / secondWeigh;
        edge.setAttribute("ui.label", String.format("%.2f", weightOfEdge));
        edge.addAttribute("ui.style", "text-alignment:above;");
        Node first = graph.getNode(firstUnknownVariable);
        Node second = graph.getNode(secondUnknownVariable);
        first.setAttribute("ui.label", first.getId());
        second.setAttribute("ui.label", second.getId());
        id ++;
        getPipeIn().pump();
        algoritm.calculateSCC();
        SCCComponents = algoritm.cluster();

    }

    public void removeNodes(String firstUnknownVariable, String secondUnknownVariable, String sign) {
        Node first = graph.getNode(firstUnknownVariable);
        Node second = graph.getNode(secondUnknownVariable);
        if (sign.equals("<") || sign.equals("<=")) {
            graph.removeEdge(first, second);
        } else {
            graph.removeEdge(second, first);
        }
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