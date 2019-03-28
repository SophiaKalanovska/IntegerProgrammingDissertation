package Model.SCC;

import Model.Inequalities.DecisionVariable;
import org.graphstream.algorithm.TarjanStronglyConnectedComponents;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.awt.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class SCCAlgorithm {
    private Graph graph;
    private TarjanStronglyConnectedComponents tscc;
    private SCCClusterList list;

    public TarjanStronglyConnectedComponents calculateSCC(Graph graph) {
        this.graph = graph;
        tscc = new TarjanStronglyConnectedComponents();

        tscc.init(graph);
        tscc.compute();
        return tscc;
    }

    public SCCClusterList cluster(TarjanStronglyConnectedComponents trj) {
        this.list = new SCCClusterList();
        for (Node n : graph.getEachNode()) {
            int SCCIndex = n.getAttribute(trj.getSCCIndexAttribute());
            n.setAttribute("SCC", SCCIndex);
            SCCCluster other = new SCCCluster(SCCIndex);
            if (list.getProjectWallet().contains(other)){
                int index = list.getProjectWallet().indexOf(other);
                SCCCluster cluster = list.getProjectWallet().get(index);
                cluster.addNodesToCluster(n);
                Color randomColor = cluster.getColor();
                n.addAttribute("ui.style", "fill-color:rgba(" + randomColor.getRed() + "," + randomColor.getGreen() + "," + randomColor.getBlue() + ",200);");
            }else{
                SCCCluster cluster = new SCCCluster((Integer) n.getAttribute(trj.getSCCIndexAttribute()));
                list.addCluster(cluster);
                cluster.addNodesToCluster(n);
                cluster.setColor();
                Color randomColor = cluster.getColor();
                n.addAttribute("ui.style", "fill-color:rgba(" + randomColor.getRed() + "," + randomColor.getGreen() + "," + randomColor.getBlue() + ",200);");
            }
            setAttackedNodes(n, trj);
        }
        return list;
    }

    private void setAttackedNodes(Node n,TarjanStronglyConnectedComponents trj) {
        Iterator i$ = n.getEachLeavingEdge().iterator();
        while(i$.hasNext()){
            Edge vw = (Edge)i$.next();
            Node w = vw.getOpposite(n);
            if (w.getAttribute(trj.getSCCIndexAttribute()) == n.getAttribute(trj.getSCCIndexAttribute())){
                if ((double)w.getAttribute("internal_weight") < (double) vw.getAttribute("weight") ) {
                    w.setAttribute("internal_weight", vw.getAttribute("weight"));
                }
            }else{
                ArrayList<Map.Entry<Node, Double>> attacked = w.getAttribute("attackedBy");
                ArrayList<Map.Entry<Node, Double>> attacking = n.getAttribute("attacking");
                Map.Entry<Node,Double> attackedBy = new AbstractMap.SimpleEntry<>(n, (double)vw.getAttribute("weight"));
                Map.Entry<Node,Double> attack = new AbstractMap.SimpleEntry<>(w,  (double) vw.getAttribute("weight"));
                attacked.add(attackedBy);
                attacking.add(attack);
                w.setAttribute("attackedBy", attacked);
                n.setAttribute("attacking", attacking);
            }

        }
    }

    public void clear(){
        for (Node node : graph.getEachNode()) {
            node.setAttribute("internal_weight", 0.0);
            node.setAttribute("upper_bound", ((DecisionVariable)node.getAttribute("decision_variable")).getUpperBound());
            node.setAttribute("lower_bound", ((DecisionVariable)node.getAttribute("decision_variable")).getLowerBound());
            node.setAttribute("attackedBy", new ArrayList<Map.Entry<Node, Double>>());
            node.setAttribute("attacking", new ArrayList<Map.Entry<Node, Double>>());
            node.setAttribute("SCC", 0);
        }
    }

}
