package view;

import javax.swing.JPanel;
import java.util.Random;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;
import org.graphstream.graph.Graph;
import java.util.ArrayList;
import org.graphstream.algorithm.ConnectedComponents;
import org.graphstream.algorithm.TarjanStronglyConnectedComponents;
import java.util.*;
import java.awt.Color ;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import model.MyFullGenerator;

public class GraphGenerator extends JPanel implements ViewerListener{

	public ViewPanel view;
	public Viewer viewer;
	public Graph graph;
	public int i;
	public ViewerPipe pipeIn;
	public ConnectedComponents cc;
	public Map compCol;
	public Random rand;
//	public RandomGenerator gen;

	private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";

	public GraphGenerator(){

		graph = new MultiGraph("Strongly connected components");
		compCol = new HashMap();
		rand = new Random();

		String css = "edge .notintree {size:1px;fill-color:gray;} " +
		"edge .intree {size:3px;fill-color:black;}";

		graph.setAttribute("ui.stylesheet", css);

		String cssNode = "node {size: 30px;fill-color: black; stroke-color: black; text-mode: normal ;z-index: 0; text-color: black;}";
		graph.setAttribute("ui.stylesheet", cssNode);


		viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		pipeIn = viewer.newViewerPipe();
		pipeIn.addViewerListener(this);
		pipeIn.addSink(graph);
		view = viewer.addDefaultView(false);
		graph.setStrict(false);
		graph.setAutoCreate( true );
		viewer.enableAutoLayout();
		i = 0;
		cc = new ConnectedComponents();
		cc.init(graph);
		// pipeIn.pump();

	}

	public static String randomAlphaNumeric(int count, String alpha) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int)(Math.random()*alpha.length());
			builder.append(alpha.charAt(character));
		}
		return builder.toString();
	}

	public void addRandomNodes(int n) {
		String nodes = randomAlphaNumeric(n, ALPHA);


		System.out.println(nodes);
//		ArrayList<String> list = new ArrayList<String>();//Creating arraylist
			for (int i = 0; i < n -4 & i > 26 || i < n - 1; ) {
				if (i < 26) {
					ArrayList<String> list = new ArrayList<String>();
					System.out.println(nodes.charAt(i));
					System.out.println(nodes.charAt(i+1));
					list.add(Character.toString(nodes.charAt(i)));//Adding object in arraylist
					list.add("<");
					list.add(Character.toString(nodes.charAt(i + 1)));
					addNodes(list);
					i = i + 1;
			}else {

				ArrayList<String> list = new ArrayList<String>();
				StringBuilder sb = new StringBuilder();
				StringBuilder sb2 = new StringBuilder();
				sb.append(nodes.charAt(i));
				sb.append(nodes.charAt(i + 1));
				sb2.append(nodes.charAt(i+ 2));
				sb2.append(nodes.charAt(i + 3));
//
				list.add( sb.toString());//Adding object in arraylist
				list.add("<");
				list.add(sb2.toString());
				addNodes(list);
				i = i + 2;
			}
		}

	}



//		 	graph.addNode(nodes.substring(i, i + 1));

//		for(int i = 0; i < n; i++){
//			gen.nextEvents();
//		}
//
//		gen.end()

	public void addNodes(ArrayList<String> arrayEquation){
		System.out.println("I am here");

		graph.addEdge("" + i + "", arrayEquation.get(0), arrayEquation.get(2), true);
		Node first = graph.getNode(arrayEquation.get(0));
		Node second = graph.getNode(arrayEquation.get(2));
		first.setAttribute("ui.label",first.getId());
		first.setAttribute("weight", 1);
		second.setAttribute("ui.label",second.getId());
		second.setAttribute("weight", 1);
		i++;
		pipeIn.pump();


		// String nodes = "abcdefgh";
		// String edges = "abbccddccgdhhdhggffgbfefbeea";
		//


		TarjanStronglyConnectedComponents tscc = new TarjanStronglyConnectedComponents();
		tscc.init(graph);
		tscc.compute();


		for (Node n : graph.getEachNode()){
			// n.addAttribute("label", n.getAttribute(tscc.getSCCIndexAttribute()));
			if(compCol.containsKey(n.getAttribute(tscc.getSCCIndexAttribute()))){

				// double[] c = {(Math.random()), 0.8f, 0.9f};
				// compCol.put(n.getAttribute(tscc.getSCCIndexAttribute()), c);
				Color randomColor = (Color)
				compCol.get(n.getAttribute(tscc.getSCCIndexAttribute()));
				n.addAttribute("ui.style", "fill-color:rgba("+randomColor.getRed()+","+randomColor.getGreen()+","+randomColor.getBlue()+",200);" );

			}else{
				System.out.println("Random color");


				// If you want pleasing, pastel colors, it is best to use the HLS system.

				final float hue = rand.nextFloat();
				// Saturation between 0.1 and 0.3
				final float saturation = (rand.nextInt(2000) + 1000) / 10000f;
				final float luminance = 0.9f;
				final Color randomColor = Color.getHSBColor(hue, saturation, luminance);

				compCol.put(n.getAttribute(tscc.getSCCIndexAttribute()), randomColor);
				n.addAttribute("ui.style", "fill-color:rgba("+randomColor.getRed()+","+randomColor.getGreen()+","+randomColor.getBlue()+",200);" );
			}
			// }else{
			// 	double[] color = compCol.get(n.getAttribute(tscc.getSCCIndexAttribute()));
			// 	n.addAttribute("ui.style", "fill-color:rgba("+color[0]+","+color[1]+","+color[2]+",200);" );
			// }

			System.out.println( n.getAttribute(tscc.getSCCIndexAttribute()));
			// n.addAttribute("label", n.getAttribute(tscc.getSCCIndexAttribute()));
		}


	}

	// public someColor(){
	// 	Color.getHSBColor((float) (Math.random()), 0.8f, 0.9f);
	// }

	public ViewerPipe getPipe() {
		return pipeIn;
	}


	public void viewClosed(String id) {
		System.out.println("here");
	}

	public void buttonPushed(String id) {
		System.out.println("Button pushed on node "+id);
	}

	public void buttonReleased(String id) {
		System.out.println("Button released on node "+id);
	}


	public ViewPanel getView(){
		return view;
	}
}
