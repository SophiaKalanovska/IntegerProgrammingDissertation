package View.Graph;

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
	private String cssLight;
    private String cssDark;
	private ViewerPipe pipeIn;
	private ConnectedComponents cc;

	public GraphGUI(){

        graph = new MultiGraph("Strongly connected components");
        graphController = new GraphController(this, graph);


         cssLight = "node {size: 30px;fill-color: black; stroke-color: black; text-mode: normal ; text-color: black; shadow-mode: gradient-radial;  shadow-color:black, white; shadow-width: 5; shadow-offset:0;  z-index :2;}" +
                "graph { fill-color: white; }" +
                "edge {fill-color: black;  z-index :1; text-color: black; }";

         cssDark = "node {size: 30px;fill-color: black; stroke-color: black; text-mode: normal ; text-color: black; shadow-mode: gradient-radial;  shadow-color: white, black; shadow-width: 5; shadow-offset:0;  z-index :2;}" +
                "graph { fill-color: black; }" +
                "edge {fill-color: white; text-color: white; z-index :1; }";

        graph.setAttribute("ui.stylesheet", cssLight);

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

	}

	public void changeView(boolean dark){
	    if (dark){
            graph.setAttribute("ui.stylesheet", cssDark);
        }else{
            graph.setAttribute("ui.stylesheet", cssLight);
        }
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
