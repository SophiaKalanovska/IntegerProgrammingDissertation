package view;

import javax.swing.JPanel;
import org.graphstream.ui.view.Viewer;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.algorithm.Kruskal;
import org.graphstream.algorithm.generator.DorogovtsevMendesGenerator;


public class Algorithm extends JPanel{

public JPanel view;


	public Algorithm(){

      DorogovtsevMendesGenerator gen = new DorogovtsevMendesGenerator();
      		Graph graph = new DefaultGraph("Kruskal Test");

      	  	String css = "edge .notintree {size:1px;fill-color:gray;} " +
      				 "edge .intree {size:3px;fill-color:black;}";

      	  	graph.addAttribute("ui.stylesheet", css);


      	 	gen.addEdgeAttribute("weight");
      	  	gen.setEdgeAttributesRange(1, 100);
      	  	gen.addSink(graph);
      	 	gen.begin();
      	 	for (int i = 0; i < 100 && gen.nextEvents(); i++)
      	  		;
      	 	gen.end();

      	 	Kruskal kruskal = new Kruskal("ui.class", "intree", "notintree");

      	 	kruskal.init(graph);
      	 	kruskal.compute();


          Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
          viewer.enableAutoLayout();
          view = viewer.addDefaultView(false);

    }




}
