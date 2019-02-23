package view;

import javax.swing.JPanel;
import controller.GraphController;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;
import org.graphstream.graph.Graph;
import org.graphstream.algorithm.ConnectedComponents;
import org.graphstream.graph.implementations.*;


public class GraphGUI extends JPanel implements ViewerListener{

	public final GraphController graphController ;
	private ViewPanel view;
	private Viewer viewer;
	private Graph graph;
	private ViewerPipe pipeIn;
	private ConnectedComponents cc;

	public GraphGUI(){

		graph = new MultiGraph("Strongly connected components");
		graphController = new GraphController(this, graph);

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
		cc = new ConnectedComponents();
		cc.init(graph);
		// pipeIn.pump();

	}

	public ViewerPipe getPipe() {
		return pipeIn;
	}

	public ViewPanel getView(){
		return view;
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



}
