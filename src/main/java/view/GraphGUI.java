package view;

import javax.swing.JPanel;
import java.util.Random;
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

public class GraphGUI extends JPanel implements ViewerListener{

	private ViewPanel view;
	private Viewer viewer;
	private Graph graph;
	private int i;
	private ViewerPipe pipeIn;
	private ConnectedComponents cc;
	private Map compCol;
	private Random rand;

	private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";

	public GraphGUI(){

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
		String nodes = randomAlphaNumeric(n+1, ALPHA);


		System.out.println(nodes);
			for (int i = 0;i < n - 1 && graph.getNodeCount() < 18; ) {

					ArrayList<String> list = new ArrayList<String>();
					list.add(Character.toString(nodes.charAt(i)));
					list.add("<");
					list.add(Character.toString(nodes.charAt(i + 1)));
					addNodes(list);

					i = i + 1;
			}
			// add if the number of inequalities are more than 30
			System.out.println("nodes so far" + graph.getNodeCount());
			if (graph.getNodeCount() > 17 || n > 30){
				for (int i = n-1; i > 26; ) {
					System.out.println(i);
					ArrayList<String> list = new ArrayList<String>();
					StringBuilder sb = new StringBuilder();
					StringBuilder sb2 = new StringBuilder();
					sb.append(nodes.charAt(i));
					sb.append(nodes.charAt(i - 1));
					sb2.append(nodes.charAt(i- 2));
					sb2.append(nodes.charAt(i - 3));
//
					list.add( sb.toString());
					list.add("<");
					if (i < 29){
						list.add(Character.toString(nodes.charAt(i - 10)));
					}else{
						list.add(sb2.toString());
					}
					addNodes(list);
					i = i - 2;
				}
			}

		}


	public void addNodes(ArrayList<String> arrayEquation){

		graph.addEdge("" + i + "", arrayEquation.get(0), arrayEquation.get(2), true);
		Node first = graph.getNode(arrayEquation.get(0));
		Node second = graph.getNode(arrayEquation.get(2));
		first.setAttribute("ui.label",first.getId());
		first.setAttribute("weight", 1);
		second.setAttribute("ui.label",second.getId());
		second.setAttribute("weight", 1);
		i++;
		pipeIn.pump();

		TarjanStronglyConnectedComponents tscc = calculateSCC();
		color(tscc);



	}

	public void removeNodes(ArrayList<String> arrayEquation){
		Node first = graph.getNode(arrayEquation.get(0));
		Node second = graph.getNode(arrayEquation.get(2));
		graph.removeEdge(first,second);
		pipeIn.pump();
		System.out.print("degree 1 :" + first.getDegree());
		System.out.print("degree 2 :" +second.getDegree());
		if (first.getDegree() == 0){
			graph.removeNode(first);
		}
		if (second.getDegree() == 0){
			graph.removeNode(second);
		}
		pipeIn.pump();
		TarjanStronglyConnectedComponents tscc = calculateSCC();
		color(tscc);


	}

	private TarjanStronglyConnectedComponents calculateSCC(){
		TarjanStronglyConnectedComponents tscc = new TarjanStronglyConnectedComponents();
		tscc.init(graph);
		tscc.compute();
		return tscc;
	}


	private void color(TarjanStronglyConnectedComponents tscc){
		for (Node n : graph.getEachNode()){
			if(compCol.containsKey(n.getAttribute(tscc.getSCCIndexAttribute()))){
				Color randomColor = (Color)
						compCol.get(n.getAttribute(tscc.getSCCIndexAttribute()));
				n.addAttribute("ui.style", "fill-color:rgba("+randomColor.getRed()+","+randomColor.getGreen()+","+randomColor.getBlue()+",200);" );
			}else{
				System.out.println("Random color");
				final float hue = rand.nextFloat();
				final float saturation = (rand.nextInt(2000) + 1000) / 10000f;
				final float luminance = 0.9f;
				final Color randomColor = Color.getHSBColor(hue, saturation, luminance);
				compCol.put(n.getAttribute(tscc.getSCCIndexAttribute()), randomColor);
				n.addAttribute("ui.style", "fill-color:rgba("+randomColor.getRed()+","+randomColor.getGreen()+","+randomColor.getBlue()+",200);" );
			}
		}
	}


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
