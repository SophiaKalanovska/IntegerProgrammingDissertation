package controller;

import org.graphstream.algorithm.TarjanStronglyConnectedComponents;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.ViewerPipe;
import view.GraphGUI;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GraphController implements Serializable {
    private final GraphGUI graphGUI;
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private Random rand;
    private Map compCol;
    private int id;


    public GraphController(GraphGUI graphGUI) {
        this.graphGUI = graphGUI;
        this.rand = new Random();
        compCol = new HashMap();
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
        for (int i = 0; i < n - 1 && graphGUI.getGraph().getNodeCount() < 18; ) {

            ArrayList<String> list = new ArrayList<String>();
            list.add(Character.toString(nodes.charAt(i)));
            list.add("<");
            list.add(Character.toString(nodes.charAt(i + 1)));
//					addNodes(list);

            i = i + 1;
        }
        // add if the number of inequalities are more than 30
        System.out.println("nodes so far" + graphGUI.getGraph().getNodeCount());
        if (graphGUI.getGraph().getNodeCount() > 17 || n > 30) {
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
            edge = graphGUI.getGraph().addEdge("" + id + "", firstUnknownVariable, secondUnknownVariable, true);
        } else {
            edge = graphGUI.getGraph().addEdge("" + id + "", secondUnknownVariable, firstUnknownVariable, true);
        }
//		edge.setAttribute(String.valueOf(firstWeight/secondWeigh));
        double weightOfEdge = (double) firstWeight / secondWeigh;
        edge.setAttribute("ui.label", String.format("%.2f", weightOfEdge));
        edge.addAttribute("ui.style", "text-alignment:above;");
        Node first = graphGUI.getGraph().getNode(firstUnknownVariable);
        Node second = graphGUI.getGraph().getNode(secondUnknownVariable);
        first.setAttribute("ui.label", first.getId());
        first.setAttribute("weight", firstWeight);
        second.setAttribute("ui.label", second.getId());
        second.setAttribute("weight", secondWeigh);
        id ++;
        getPipeIn().pump();

        TarjanStronglyConnectedComponents tscc = calculateSCC();
        color(tscc);

    }

    public void removeNodes(String firstUnknownVariable, String secondUnknownVariable, String sign) {
        Node first = graphGUI.getGraph().getNode(firstUnknownVariable);
        Node second = graphGUI.getGraph().getNode(secondUnknownVariable);
        if (sign.equals("<") || sign.equals("<=")) {
            graphGUI.getGraph().removeEdge(first, second);
        } else {
            graphGUI.getGraph().removeEdge(second, first);
        }
        getPipeIn().pump();
        System.out.print("degree 1 :" + first.getDegree());
        System.out.print("degree 2 :" + second.getDegree());
        if (first.getDegree() == 0) {
            graphGUI.getGraph().removeNode(first);
        }
        if (second.getDegree() == 0) {
            graphGUI.getGraph().removeNode(second);
        }
        getPipeIn().pump();
        TarjanStronglyConnectedComponents tscc = calculateSCC();
        color(tscc);


    }

    TarjanStronglyConnectedComponents calculateSCC() {
        TarjanStronglyConnectedComponents tscc = new TarjanStronglyConnectedComponents();
        tscc.init(graphGUI.getGraph());
        tscc.compute();
        return tscc;
    }

    void color(TarjanStronglyConnectedComponents tscc) {
        for (Node n : graphGUI.getGraph().getEachNode()) {
            if (compCol.containsKey(n.getAttribute(tscc.getSCCIndexAttribute()))) {
                Color randomColor = (Color)
                        compCol.get(n.getAttribute(tscc.getSCCIndexAttribute()));
                n.addAttribute("ui.style", "fill-color:rgba(" + randomColor.getRed() + "," + randomColor.getGreen() + "," + randomColor.getBlue() + ",200);");
            } else {
                System.out.println("Random color");
                final float hue = rand.nextFloat();
                final float saturation = (rand.nextInt(2000) + 1000) / 10000f;
                final float luminance = 0.9f;
                final Color randomColor = Color.getHSBColor(hue, saturation, luminance);
                compCol.put(n.getAttribute(tscc.getSCCIndexAttribute()), randomColor);
                n.addAttribute("ui.style", "fill-color:rgba(" + randomColor.getRed() + "," + randomColor.getGreen() + "," + randomColor.getBlue() + ",200);");
            }
        }
    }


    public ViewerPipe getPipeIn() {
        return graphGUI.getPipe();
    }

}