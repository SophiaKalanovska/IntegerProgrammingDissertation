import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import Controller.*;
import Controller.Constrains.ConstarinsController;
import Controller.Constrains.InternalConstarinsClusterListController;
import Controller.Constrains.LowerBoundClusterListController;
import Controller.Constrains.UpperBoundClusterListController;
import Model.Inequalities.InequalitiesList;
import Model.Inequalities.RandomInequalitiesGenerator;
import Model.SCC.SCCAlgorithm;
import Model.SCC.SCCClusterList;
import View.*;
import View.OperationsOnInequalities.InequalitiesListGUI;
import View.OperationsOnInequalities.ManualInequalitiesGUI;
import View.OperationsOnInequalities.RandomInequalitiesGUI;
import View.SolutionPanel.*;

/**
* Container is the class where the frame of the app is built.
* We then load panels to this frame.
*/
public class Container extends JFrame {

	public Container(){

		SCCClusterList clusterList = new SCCClusterList();
		GraphGUI graph = new GraphGUI();

		SCCAlgorithm algorithm = new SCCAlgorithm(graph.graphController.getGraph());
        final InequalitiesList inequalitiesList = new InequalitiesList(graph.graphController,algorithm);
		LowerBoundClusterGUI lowerBoundClusterGUI = new LowerBoundClusterGUI();
		RandomInequalitiesGUI randomInequalitiesGUI = new RandomInequalitiesGUI();
		InequalitiesListGUI inequalitiesListGUI = new InequalitiesListGUI();
		LowerBoundClusterListController lowerBoundClusterListController = new LowerBoundClusterListController(lowerBoundClusterGUI );

		UpperBoundClusterGUI upperBoundClusterGUI = new UpperBoundClusterGUI();
		UpperBoundClusterListController upperBoundClusterListController = new UpperBoundClusterListController(upperBoundClusterGUI);
        ManualInequalitiesGUI manualInequalitiesGUI = new ManualInequalitiesGUI();
        IntegerAssignmentGUI integerAssignmentGUI = new IntegerAssignmentGUI();
		InternalConstarinsClusterGUI internalConstarinsClusterGUI = new InternalConstarinsClusterGUI();
		InternalConstarinsClusterListController internalConstarinsClusterListController = new InternalConstarinsClusterListController(internalConstarinsClusterGUI);
		BoundsGUI boundGUI = new BoundsGUI(lowerBoundClusterGUI, upperBoundClusterGUI, integerAssignmentGUI, internalConstarinsClusterGUI);
        LayoutGUI graphgen = new LayoutGUI(this, graph, manualInequalitiesGUI, randomInequalitiesGUI, inequalitiesListGUI,boundGUI);
        new ManualInequalitiesController(inequalitiesList,manualInequalitiesGUI);

//        //Controller creation
		new RandomInequalitiesController(inequalitiesList,randomInequalitiesGUI, graph.graphController);
		ConstarinsController constarinsController = new ConstarinsController(lowerBoundClusterListController, upperBoundClusterListController, internalConstarinsClusterListController);
		new InequalitiesListController(inequalitiesList, inequalitiesListGUI, graph.graphController, constarinsController);



        inequalitiesList.addObserver(inequalitiesListGUI);
        inequalitiesList.tryUpdate();
        setPanel(graphgen);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1137, 710));
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
