import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import Controller.*;
import Controller.Constrains.ConstarinsController;
import Controller.Constrains.InternalConstarinsClusterListController;
import Controller.Constrains.LowerBoundClusterListController;
import Controller.Constrains.UpperBoundClusterListController;
import Model.Inequalities.InequalitiesList;
import Model.SCC.SCCAlgorithm;
import Model.SCC.SCCClusterList;
import View.*;
import View.Graph.GraphGUI;
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

		// Create all the View  classes

		ManualInequalitiesGUI manualInequalitiesGUI = new ManualInequalitiesGUI();
		RandomInequalitiesGUI randomInequalitiesGUI = new RandomInequalitiesGUI();
		InequalitiesListGUI inequalitiesListGUI = new InequalitiesListGUI();
		LowerBoundClusterGUI lowerBoundClusterGUI = new LowerBoundClusterGUI();
		UpperBoundClusterGUI upperBoundClusterGUI = new UpperBoundClusterGUI();
		IntegerAssignmentGUI integerAssignmentGUI = new IntegerAssignmentGUI();
		InternalConstarinsClusterGUI internalConstarinsClusterGUI = new InternalConstarinsClusterGUI();
		BoundsGUI boundGUI = new BoundsGUI(lowerBoundClusterGUI, upperBoundClusterGUI, integerAssignmentGUI, internalConstarinsClusterGUI);
		LayoutGUI layoutGUI = new LayoutGUI(this, manualInequalitiesGUI, randomInequalitiesGUI, inequalitiesListGUI,boundGUI);



		// Create all the Controller classes
		GraphController graphController = new GraphController(layoutGUI) ;
		LowerBoundClusterListController lowerBoundClusterListController = new LowerBoundClusterListController(lowerBoundClusterGUI);
		UpperBoundClusterListController upperBoundClusterListController = new UpperBoundClusterListController(upperBoundClusterGUI);
		InternalConstarinsClusterListController internalConstarinsClusterListController = new InternalConstarinsClusterListController(internalConstarinsClusterGUI);
		ConstarinsController constarinsController = new ConstarinsController(lowerBoundClusterListController, upperBoundClusterListController, internalConstarinsClusterListController);
		SCCAlgorithm algorithm = new SCCAlgorithm(graphController.getGraph());
		InequalitiesList inequalitiesList = new InequalitiesList(graphController, algorithm);
		new InequalitiesListController(inequalitiesList, inequalitiesListGUI, graphController, constarinsController);
		new ManualInequalitiesController(inequalitiesList,manualInequalitiesGUI);
		new RandomInequalitiesController(inequalitiesList,randomInequalitiesGUI, graphController);


		inequalitiesList.addObserver(inequalitiesListGUI);
		inequalitiesList.tryUpdate();
		setPanel(layoutGUI);

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
