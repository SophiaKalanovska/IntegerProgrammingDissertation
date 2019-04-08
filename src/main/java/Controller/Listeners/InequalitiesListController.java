package Controller.Listeners;
import java.awt.event.*;
import javax.swing.*;

import Controller.ConstarinsController;
import Controller.GraphController;
import Model.Inequalities.InequalitiesList;
import Model.Inequalities.Inequality;
import View.Inequalities.InequalitiesListGUI;

/**
 * This class will represent the Controller for the ManualIntegerInequalities Panel
 *
 */
public class InequalitiesListController implements MouseListener {

    private InequalitiesList inequalitiesList;
    private View.Inequalities.InequalitiesListGUI InequalitiesListGUI;
    private ConstarinsController constarinsController;
    private GraphController graph;
    private RandomInequalitiesController randomInequalitiesController;


    /**
     * Constructs a Controller for the ManualIntegerInequalities panel
     *
     * @param InequalitiesListGUI the ManualIntegerInequalities  JFrame that this class will control
     */
    public InequalitiesListController(InequalitiesList inequalitiesList, InequalitiesListGUI InequalitiesListGUI, GraphController graphController, ConstarinsController constarinsController, RandomInequalitiesController randomInequalitiesController){

        this.InequalitiesListGUI = InequalitiesListGUI;
        this.inequalitiesList = inequalitiesList;
        this.constarinsController = constarinsController;
        this.randomInequalitiesController = randomInequalitiesController;
        graph = graphController;
        InequalitiesListGUI.addMouseListener(this);
    }

    private void performTheAction(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            if (((JButton) e.getSource()).getName().equals("delete")){
                Inequality toBeDeleted = InequalitiesListGUI.getListSelectedValue();
                if (toBeDeleted != null){
                    inequalitiesList.deleteInequality(toBeDeleted);
                    graph.undrawInequality(toBeDeleted);
                    graph.findStronglyConnectedComponents();
                }
            }else if (((JButton) e.getSource()).getName().equals("deleteGraph")) {
                inequalitiesList.deleteAllInequalities();
                graph.deleteGraph();
                constarinsController.delete();
                randomInequalitiesController.resetRandomInequalitiesGenerator();
            }else{
                constarinsController.populate(graph);
            }
        }
    }

    /**
     * Mouse listener for the ManualIntegerInequalities panel
     *
     * @param e Mouse listener that will identify the actions that the user makes
     */
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

    public View.Inequalities.InequalitiesListGUI getInequalitiesListGUI() {
        return InequalitiesListGUI;
    }
}




