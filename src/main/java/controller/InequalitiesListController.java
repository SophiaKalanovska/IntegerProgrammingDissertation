package controller;
import java.awt.event.*;
import javax.swing.*;

import model.Inequalities.InequalitiesList;
import view.OperationsOnInequalities.InequalitiesListGUI;
import view.SolutionPanel.InternalConstarinsClusterGUI;

/**
 * This class will represent the Controller for the ManualIntegerInequalities Panel
 *
 */
public class InequalitiesListController implements ActionListener, MouseListener {

    private InequalitiesList inequalitiesList;
    private String enterInequality;
    private String jtfProjectTField;
    private view.OperationsOnInequalities.InequalitiesListGUI InequalitiesListGUI;
    private JPanel currentPanel;
    private GraphController graphController;
    private ConstarinsController constarinsController;


    /**
     * Constructs a Controller for the ManualIntegerInequalities panel
     *
     * @param InequalitiesListGUI the ManualIntegerInequalities  JFrame that this class will control
     */
    public InequalitiesListController(InequalitiesList inequalitiesList, InequalitiesListGUI InequalitiesListGUI, GraphController graphController, ConstarinsController constarinsController){

        this.InequalitiesListGUI = InequalitiesListGUI;
        this.inequalitiesList = inequalitiesList;
        this.graphController = graphController;
        this.constarinsController = constarinsController;
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
                inequalitiesList.deleteInequality(InequalitiesListGUI.getListSelectedValue());
            }else if (((JButton) e.getSource()).getName().equals("deleteAll")){
                System.out.println(((JButton) e.getSource()).getName());
                inequalitiesList.deleteAllInequalities();
            }else{
                constarinsController.populate(graphController);
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
    public void actionPerformed(java.awt.event.ActionEvent e) {
        }
}


