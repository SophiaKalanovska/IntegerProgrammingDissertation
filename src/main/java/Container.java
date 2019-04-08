
import javax.swing.JFrame;
import java.awt.Dimension;

import Controller.ConstarinsController;
import Controller.GraphController;
import Controller.Listeners.InequalitiesListController;
import Controller.Listeners.ManualInequalitiesController;
import Controller.Listeners.RandomInequalitiesController;
import Model.Inequalities.InequalitiesList;
import View.*;
import View.Inequalities.InequalitiesListGUI;
import View.Inequalities.ManualInequalitiesGUI;
import View.Inequalities.RandomInequalitiesGUI;
import View.SolutionPanel.*;

/**
* Container is the class where the frame of the app is built.
* We then load panels to this frame.
*/
public class Container extends JFrame {
    LayoutGUI layoutGUI;
    private ConstarinsController constarinsController;
    private InequalitiesList inequalitiesList;
    private GraphController graphController;
    private RandomInequalitiesController randomInequalitiesController;
    private InequalitiesListController inequalitiesListController;
	public Container(){

		// Create all the View  classes
		ManualInequalitiesGUI manualInequalitiesGUI = new ManualInequalitiesGUI();
		RandomInequalitiesGUI randomInequalitiesGUI = new RandomInequalitiesGUI();
		InequalitiesListGUI inequalitiesListGUI = new InequalitiesListGUI();
		LowerBoundClusterGUI lowerBoundClusterGUI = new LowerBoundClusterGUI();
		UpperBoundClusterGUI upperBoundClusterGUI = new UpperBoundClusterGUI();
		IntegerAssignmentMinimizeGUI integerAssignmentMinimizeGUI = new IntegerAssignmentMinimizeGUI();
		InternalConstarinsClusterGUI internalConstarinsClusterGUI = new InternalConstarinsClusterGUI();
		IntegerAssignmentMaximizeGUI integerAssignmentMaximizeGUI = new IntegerAssignmentMaximizeGUI();
		BoundsGUI boundGUI = new BoundsGUI(lowerBoundClusterGUI, upperBoundClusterGUI, integerAssignmentMinimizeGUI, internalConstarinsClusterGUI, integerAssignmentMaximizeGUI);
		layoutGUI = new LayoutGUI(this, manualInequalitiesGUI, randomInequalitiesGUI, inequalitiesListGUI,boundGUI);

		//Create Model Classes
        inequalitiesList = new InequalitiesList();
        inequalitiesList.addObserver(inequalitiesListGUI);
        inequalitiesList.tryUpdate();

		// Create all the Controller classes
		graphController = new GraphController(layoutGUI) ;
	    constarinsController = new ConstarinsController(lowerBoundClusterGUI, upperBoundClusterGUI,internalConstarinsClusterGUI, integerAssignmentMinimizeGUI, integerAssignmentMaximizeGUI);
        new ManualInequalitiesController(inequalitiesList,manualInequalitiesGUI, graphController);
        randomInequalitiesController = new RandomInequalitiesController(inequalitiesList,randomInequalitiesGUI, graphController);
        inequalitiesListController = new InequalitiesListController(inequalitiesList, inequalitiesListGUI, graphController, constarinsController, randomInequalitiesController);
    }

    public void setPanel(){
		this.getContentPane().invalidate();
		this.getContentPane().removeAll();
		this.getContentPane().add(layoutGUI);
		this.revalidate();
		this.repaint();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1137, 710));
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
	}



    // Creates getters used in the testing
    public LayoutGUI getLayoutGUI() {
        return layoutGUI;
    }

    public ConstarinsController getConstarinsController() {
        return constarinsController;
    }

    public InequalitiesList getInequalitiesList() {
        return inequalitiesList;
    }

    public GraphController getGraphController() {
        return graphController;
    }

    public RandomInequalitiesController getRandomInequalitiesController() {
        return randomInequalitiesController;
    }

    public InequalitiesListController getInequalitiesListController() {
        return inequalitiesListController;
    }
}
