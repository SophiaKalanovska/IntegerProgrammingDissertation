package Controller;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import Controller.Constrains.ConstarinsController;
import Model.Inequalities.InequalitiesList;
import Model.Inequalities.Inequality;
import Model.SCC.SCCClusterList;
import View.OperationsOnInequalities.InequalitiesListGUI;

/**
 * This class will represent the Controller for the ManualIntegerInequalities Panel
 *
 */
public class InequalitiesListController implements ActionListener, MouseListener {

    private InequalitiesList inequalitiesList;
    private View.OperationsOnInequalities.InequalitiesListGUI InequalitiesListGUI;
    private ConstarinsController constarinsController;
    private GraphController graph;


    /**
     * Constructs a Controller for the ManualIntegerInequalities panel
     *
     * @param InequalitiesListGUI the ManualIntegerInequalities  JFrame that this class will control
     */
    public InequalitiesListController(InequalitiesList inequalitiesList, InequalitiesListGUI InequalitiesListGUI, GraphController graphController, ConstarinsController constarinsController){

        this.InequalitiesListGUI = InequalitiesListGUI;
        this.inequalitiesList = inequalitiesList;
        this.constarinsController = constarinsController;
        graph = graphController;
        InequalitiesListGUI.addMouseListener(this);
    }

    /**
     * Mouse listener for the ManualIntegerInequalities panel
     *
     * @param e Mouse listener that will identify the actions that the user makes
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println("You clicked me manual and I don't know");
        if (e.getSource() instanceof JButton) {
            System.out.println(((JButton) e.getSource()).getName());
            if (((JButton) e.getSource()).getName().equals("delete")){
                System.out.println(((JButton) e.getSource()).getName());
                Inequality toBeDeleted = InequalitiesListGUI.getListSelectedValue();
                inequalitiesList.deleteInequality(toBeDeleted);
                graph.undrawInequality(toBeDeleted);
                graph.calculateInequalities();
            }else if (((JButton) e.getSource()).getName().equals("deleteGraph")) {
                inequalitiesList.deleteAllInequalities();
                graph.deleteGraph();
            }else{
                constarinsController.populate(graph);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}


    /**
     * The action listener for the ManualIntegerInequalities panel
     *
     * @param e the ActionEven object which will identify the performed action
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) { }
}


