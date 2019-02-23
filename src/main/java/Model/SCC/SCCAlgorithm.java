package Model.SCC;

import Model.Inequalities.DecisionVariable;
import org.graphstream.algorithm.TarjanStronglyConnectedComponents;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.awt.*;
import java.util.Iterator;

public class SCCAlgorithm {
    private Graph graph;
    private TarjanStronglyConnectedComponents tscc;
    private SCCClusterList list;

    public SCCAlgorithm(Graph graph){
        this.graph = graph;

    }

    public TarjanStronglyConnectedComponents calculateSCC() {
        tscc = new TarjanStronglyConnectedComponents();
        tscc.init(graph);
        tscc.compute();
        return tscc;
    }

    public SCCClusterList cluster() {
        this.list = new SCCClusterList();
        for (Node n : graph.getEachNode()) {
            int SCCIndex = n.getAttribute(tscc.getSCCIndexAttribute());
            SCCCluster other = new SCCCluster(SCCIndex);
            if (list.getProjectWallet().contains(other)){
                int index = list.getProjectWallet().indexOf(other);
                SCCCluster cluster = list.getProjectWallet().get(index);
                cluster.addNodesToCluster(n);
                Color randomColor = cluster.getColor();
                n.addAttribute("ui.style", "fill-color:rgba(" + randomColor.getRed() + "," + randomColor.getGreen() + "," + randomColor.getBlue() + ",200);");
            }else{
                SCCCluster cluster = new SCCCluster((Integer) n.getAttribute(tscc.getSCCIndexAttribute()));
                list.addCluster(cluster);
                cluster.addNodesToCluster(n);
                cluster.setColor();
                Color randomColor = cluster.getColor();
                n.addAttribute("ui.style", "fill-color:rgba(" + randomColor.getRed() + "," + randomColor.getGreen() + "," + randomColor.getBlue() + ",200);");
            }
            setAttackedNodes(n);
        }
        return list;
    }

    private void setAttackedNodes(Node n) {
        Iterator i$ = n.getEachLeavingEdge().iterator();
        while(i$.hasNext()){
            Edge vw = (Edge)i$.next();
            Node w = vw.getOpposite(n);
            if (w.getAttribute(tscc.getSCCIndexAttribute()) == n.getAttribute(tscc.getSCCIndexAttribute())){
                if ((double)w.getAttribute("internal_weight") < (double) vw.getAttribute("weight") ) {
                    w.setAttribute("internal_weight", vw.getAttribute("weight"));
                }
            }else{
                if ((double)w.getAttribute("upper_bound") > (double) vw.getAttribute("weight") ){
                    w.setAttribute("upper_bound", vw.getAttribute("weight"));
                }
            }

        }
    }

    public void clear(){
        for (Node node : graph.getEachNode()) {
            node.setAttribute("internal_weight", 0.0);
            node.setAttribute("upper_bound", ((DecisionVariable)node.getAttribute("decision_variable")).getUpperBound());
            node.setAttribute("lower_bound", ((DecisionVariable)node.getAttribute("decision_variable")).getLowerBound());
        }
    }

}
