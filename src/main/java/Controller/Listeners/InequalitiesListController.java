package Controller.Listeners;
import java.awt.event.*;
import javax.swing.*;

import Controller.ConstarinsController;
import Controller.GraphController;
import Model.Inequalities.InequalitiesList;
import Model.Inequalities.Inequality;
import View.Inequalities.InequalitiesListGUI;

/**
 * This class will represent the Controller for the InequalitiesListGUI
 */
public class InequalitiesListController implements MouseListener {

    private InequalitiesList inequalitiesList;
    private View.Inequalities.InequalitiesListGUI InequalitiesListGUI;
    private ConstarinsController constarinsController;
    private GraphController graph;
    private RandomInequalitiesController randomInequalitiesController;


    /**
     * Constructs a Controller for the InequalityGUI panel
     **/
    public InequalitiesListController(InequalitiesList inequalitiesList, InequalitiesListGUI InequalitiesListGUI, GraphController graphController, ConstarinsController constarinsController, RandomInequalitiesController randomInequalitiesController){

        this.InequalitiesListGUI = InequalitiesListGUI;
        this.inequalitiesList = inequalitiesList;
        this.constarinsController = constarinsController;
        this.randomInequalitiesController = randomInequalitiesController;
        graph = graphController;
        InequalitiesListGUI.addMouseListener(this);
    }


    /**
     * Tiggered once the user manually clicks a button.
     * This method first checks which button is pressed.
     **/
    private void performTheAction(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            if (((JButton) e.getSource()).getName().equals("delete")){
                Inequality toBeDeleted = InequalitiesListGUI.getListSelectedValue();
                if (toBeDeleted != null){
                    ///a single inequality from the list is deleted
                    inequalitiesList.deleteInequality(toBeDeleted);
                    ///this inequality is removed from the list
                    graph.undrawInequality(toBeDeleted);
                    ///and the graph is reevaluated.
                    graph.findStronglyConnectedComponents();
                }
            }else if (((JButton) e.getSource()).getName().equals("deleteGraph")) {
                // the inequalityList container is emptied
                inequalitiesList.deleteAllInequalities();
                // all graph elements are deleted
                graph.deleteGraph();
                // the lists values are deleted
                constarinsController.delete();
                // the lists values are deleted
                randomInequalitiesController.resetRandomInequalitiesGenerator();
            }else{
                // the back-end is called for evaluation  of lambda plus and minus
                constarinsController.populate(graph);
            }
        }
    }

    public View.Inequalities.InequalitiesListGUI getInequalitiesListGUI() {
        return InequalitiesListGUI;
    }


    /**
     * Methods needed for the implementation of MouseListener
     **/
    @Override
    public void mouseClicked(MouseEvent e) {
        performTheAction(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        performTheAction(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}




