package View;

import javax.swing.JPanel;
import Controller.GraphController;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.View;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;
import org.graphstream.graph.Graph;
import org.graphstream.algorithm.ConnectedComponents;
import org.graphstream.graph.implementations.*;


public class GraphGUI extends JPanel implements ViewerListener{

	public final GraphController graphController ;
	private ViewPanel viewPanel;
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

        String cssNode = "node {size: 30px;fill-color: black; stroke-color: black; text-mode: normal ; text-color: black; shadow-mode: gradient-radial;  shadow-color: white, black; shadow-width: 10; shadow-offset:0;  z-index :2;}" +
                "graph { fill-color: black; }" +
                "edge {fill-color: white;  z-index :1; shadow-mode: gradient-radial;  shadow-color: white, black; shadow-width: 0.2; shadow-offset:0; }";



        String cssGraph = "graph { fill-color: black;}";
        graph.setAttribute("ui.stylesheet", cssGraph);
        graph.setAttribute("ui.stylesheet", cssNode);

        viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        pipeIn = viewer.newViewerPipe();
        pipeIn.addViewerListener(this);
        pipeIn.addSink(graph);
        viewPanel = viewer.addDefaultView(false);
        graph.setStrict(false);
        graph.setAutoCreate( true );
        viewer.enableAutoLayout();
        cc = new ConnectedComponents();

        cc.init(graph);

		///////////////////////////////

//        viewPanel = viewer.addDefaultView(false);
//
////        Viewer defaultView = graph.display();
////        View view = defaultView.getDefaultView();
////        ((ViewPanel) view).addMouseWheelListener();
//////        view.resizeFrame(800, 600);
//////        view.setViewCenter(440000, 2503000, 0);
//////        view.setViewPercent(0.25);
//
//
//
//
//        ///////////////////////////////
//
//		graph.setStrict(false);
//		graph.setAutoCreate( true );
//		viewer.enableAutoLayout();
//		cc = new ConnectedComponents();
//		cc.init(graph);
//		// pipeIn.pump();

	}

	public ViewerPipe getPipe() {
		return pipeIn;
	}

	public ViewPanel getView(){
		return viewPanel;
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
