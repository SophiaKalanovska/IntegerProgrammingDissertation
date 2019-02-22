package model.SCC;


import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class StronglyConnectedComponents implements Algorithm {
    protected HashMap<Node, Integer> data = new HashMap();
    protected int index;
    protected Stack<Node> S = new Stack();
    protected org.graphstream.algorithm.TarjanStronglyConnectedComponents.IndexGenerator sccIndex = new org.graphstream.algorithm.TarjanStronglyConnectedComponents.IntegerIndexGenerator();
    protected String sccAttribute = "scc";
    protected Graph graph;

    public StronglyConnectedComponents() {
    }

    public void init(Graph graph) {
        this.graph = graph;
    }

    public void compute() {
        this.data.clear();
        this.index = 0;
        this.S.clear();
        Iterator i$ = this.graph.getEachNode().iterator();

        while(i$.hasNext()) {
            Node v = (Node)i$.next();
            if (!this.data.containsKey(v)) {
                this.strongConnect(v);
            }
        }

    }

    public void setIndexGenerator(org.graphstream.algorithm.TarjanStronglyConnectedComponents.IndexGenerator gen) {
        if (gen == null) {
            throw new NullPointerException();
        } else {
            this.sccIndex = gen;
        }
    }

    public void setSCCIndexAttribute(String key) {
        if (key == null) {
            throw new NullPointerException();
        } else {
            this.sccAttribute = key;
        }
    }

    public String getSCCIndexAttribute() {
        return this.sccAttribute;
    }

//    protected void strongConnect(Node v) {
//        org.graphstream.algorithm.TarjanStronglyConnectedComponents.NodeData nd = new org.graphstream.algorithm.TarjanStronglyConnectedComponents.NodeData();
//        this.data.put(v, nd);
//        nd.index = this.index;
//        nd.lowlink = this.index++;
//        this.S.push(v);
//        Iterator i$ = v.getEachLeavingEdge().iterator();
//
//        while(i$.hasNext()) {
//            Edge vw = (Edge)i$.next();
//            Node w = vw.getOpposite(v);
//            if (!this.data.containsKey(w)) {
//                this.strongConnect(w);
//                nd.lowlink = Math.min(nd.lowlink, ((org.graphstream.algorithm.TarjanStronglyConnectedComponents.NodeData)this.data.get(w)).lowlink);
//            } else if (this.S.contains(w)) {
//                nd.lowlink = Math.min(nd.lowlink, ((org.graphstream.algorithm.TarjanStronglyConnectedComponents.NodeData)this.data.get(w)).index);
//            }
//        }
//
//        if (nd.index == nd.lowlink) {
//            Object currentSCCIndex = this.sccIndex.nextIndex();
//
//            Node w;
//            do {
//                w = (Node)this.S.pop();
//                w.setAttribute(this.sccAttribute, new Object[]{currentSCCIndex});
//            } while(w != v);
//        }
//
//    }

    public static class IntegerIndexGenerator implements org.graphstream.algorithm.TarjanStronglyConnectedComponents.IndexGenerator {
        private int index = 0;

        public IntegerIndexGenerator() {
        }

        public Object nextIndex() {
            return this.index++;
        }
    }

    public interface IndexGenerator {
        Object nextIndex();
    }

    protected static class NodeData {
        int index;
        int lowlink;

        protected NodeData() {
        }
    }
}
