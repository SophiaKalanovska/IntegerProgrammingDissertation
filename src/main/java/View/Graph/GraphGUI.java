package View.Graph;

import javax.swing.JPanel;

import View.Settings.DarkMode;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.*;


public class GraphGUI extends JPanel implements ViewerListener{
	private ViewPanel viewPanel;
	private Viewer viewer;
	private Graph graph;
	private String cssLight;
    private String cssDark;
    private DarkMode darkMode;
	private ViewerPipe pipeIn;

	public GraphGUI(DarkMode darkMode){
        this.darkMode = darkMode;
        graph = new MultiGraph("Strongly connected components");
        // create own implementation of the Viewer
        viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        //connect the viewer to the graph back-end
        pipeIn = viewer.newViewerPipe();
        // install a viewer listener to the graphic events
        pipeIn.addViewerListener(this);
        // the graph becomes a sink for the viewer.
        pipeIn.addSink(graph);
        // don't create a separate JFrame for the graph
        viewPanel = viewer.addDefaultView(false);
        //don't create nodes with the same identifier in the graph
        graph.setStrict(false);
        graph.setAutoCreate(true);

        //format nodes, graph and edges when in normal node
        cssLight = "node {size: 20px;fill-color: white; stroke-color: black; text-mode: normal ; text-color: black; shadow-mode: gradient-radial;  shadow-color:black, white; shadow-width: 5; shadow-offset:0;  z-index :2;}" +
                "graph { fill-color: white; }" +
                "edge {fill-color: black;  z-index :1; text-color: black; }";

        //format nodes, graph and edges when in dark node
        cssDark = "node {size: 20px;fill-color: black; stroke-color: black; text-mode: normal ; text-color: black; shadow-mode: gradient-radial;  shadow-color: white, black; shadow-width: 10; shadow-offset:0;  z-index :2;}" +
                "graph { fill-color: black; }" +
                "edge {fill-color: white; text-color: white; z-index :1; }";

        graph.setAttribute("ui.stylesheet", cssLight);
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");

        // distribute the nodes around the graph
        viewer.enableAutoLayout();
	}

	public void changeView(boolean dark){
	    if (dark){
            graph.setAttribute("ui.stylesheet", cssDark);
        }else{
            graph.setAttribute("ui.stylesheet", cssLight);
        }
    }

    public void setUI(){
	    if (darkMode.isSelected()){
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

	public Graph getGraph(){
	    return graph;
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
