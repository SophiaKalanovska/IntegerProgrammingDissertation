package model.SCC;

import org.graphstream.algorithm.TarjanStronglyConnectedComponents;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.awt.*;

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
            System.out.println("" + list .getProjectWallet().contains(SCCIndex));
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
        }
        list.printInternalConstarins();
        return list;
    }

}
