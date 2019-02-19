import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import controller.*;
import model.Inequalities.InequalitiesList;
import model.SCC.SCCClusterList;
import view.*;
import view.OperationsOnInequalities.InequalitiesListGUI;
import view.OperationsOnInequalities.ManualInequalitiesGUI;
import view.OperationsOnInequalities.RandomInequalitiesGUI;
import view.SolutionPanel.*;

/**
* Container is the class where the frame of the app is built.
* We then load panels to this frame.
*/
public class Container extends JFrame {

	public Container(){

		SCCClusterList clusterList = new SCCClusterList();
		GraphGUI graph = new GraphGUI();

        final InequalitiesList inequalitiesList = new InequalitiesList(graph.graphController);
		LowerBoundClusterGUI lowerBoundClusterGUI = new LowerBoundClusterGUI();
		RandomInequalitiesGUI randomInequalitiesGUI = new RandomInequalitiesGUI();
		InequalitiesListGUI inequalitiesListGUI = new InequalitiesListGUI();
		LowerBoundClusterListController lowerBoundClusterListController = new LowerBoundClusterListController(lowerBoundClusterGUI );

		UpperBoundClusterGUI upperBoundClusterGUI = new UpperBoundClusterGUI();
		UpperBoundClusterListController upperBoundClusterListController = new UpperBoundClusterListController(upperBoundClusterGUI);
		BoundsGUI boundGUI = new BoundsGUI(lowerBoundClusterGUI, upperBoundClusterGUI);
        ManualInequalitiesGUI manualInequalitiesGUI = new ManualInequalitiesGUI();
        IntegerAssignmentGUI integerAssignmentGUI = new IntegerAssignmentGUI();
		InternalConstarinsClusterGUI internalConstarinsClusterGUI = new InternalConstarinsClusterGUI();
		InternalConstarinsClusterListController internalConstarinsClusterListController = new InternalConstarinsClusterListController(internalConstarinsClusterGUI);
        LayoutGUI graphgen = new LayoutGUI(this, graph, manualInequalitiesGUI, randomInequalitiesGUI, inequalitiesListGUI,boundGUI, integerAssignmentGUI, internalConstarinsClusterGUI);
        new ManualInequalitiesController(inequalitiesList,manualInequalitiesGUI);

        //controller creation
		new RandomInequalitiesController(randomInequalitiesGUI, graph.graphController);
		new InequalitiesListController(inequalitiesList, inequalitiesListGUI, graph.graphController, lowerBoundClusterListController, upperBoundClusterListController, internalConstarinsClusterListController);



        inequalitiesList.addObserver(inequalitiesListGUI);
        inequalitiesList.tryUpdate();
        setPanel(graphgen);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(900, 600));
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}

	/**
	* Loads a new panel to the frame
	*
	* @param  p  the panel to be loaded into the frame
	*/
    private void setPanel(JPanel p){
		this.getContentPane().invalidate();
		this.getContentPane().removeAll();
		this.getContentPane().add(p);
		this.revalidate();
		this.repaint();
	}

}
