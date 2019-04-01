
import javax.swing.JFrame;
import java.awt.Dimension;

import Controller.Constrains.*;
import Controller.Listeners.GraphController;
import Controller.Listeners.InequalitiesListController;
import Controller.Listeners.ManualInequalitiesController;
import Controller.Listeners.RandomInequalitiesController;
import Model.Inequalities.InequalitiesList;
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
//        setPanel(layoutGUI);

		// Create all the Controller classes
		graphController = new GraphController(layoutGUI) ;
		LowerBoundClusterListController lowerBoundClusterListController = new LowerBoundClusterListController(lowerBoundClusterGUI);
		UpperBoundClusterListController upperBoundClusterListController = new UpperBoundClusterListController(upperBoundClusterGUI);
		IntegerAssignenmentMinListController integerAssignenmentMinListController = new IntegerAssignenmentMinListController(integerAssignmentMinimizeGUI);
		IntegerAssignenmentMaxListController integerAssignenmentMaxListController = new IntegerAssignenmentMaxListController(integerAssignmentMaximizeGUI);
		InternalConstarinsClusterListController internalConstarinsClusterListController = new InternalConstarinsClusterListController(internalConstarinsClusterGUI);
		constarinsController = new ConstarinsController(lowerBoundClusterListController, upperBoundClusterListController, internalConstarinsClusterListController, integerAssignenmentMinListController, integerAssignenmentMaxListController);
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
