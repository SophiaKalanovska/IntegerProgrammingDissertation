package controller;
import java.awt.event.*;
import java.util.Map;
import javax.swing.*;

import model.Inequalities.InequalitiesList;
import model.SCC.LowerBoundList;
import model.SCC.LowerBoundListRender;
import view.OperationsOnInequalities.InequalitiesListGUI;
import view.SolutionPanel.LowerBoundClusterGUI;

/**
 * This class will represent the Controller for the ManualIntegerInequalities Panel
 *
 */
public class InequalitiesListController implements ActionListener, MouseListener {

    //private ArrayList<Projects> projectData;
    private InequalitiesList inequalitiesList;
    private String enterInequality;
    private String jtfProjectTField;
    private view.OperationsOnInequalities.InequalitiesListGUI InequalitiesListGUI;
    private JPanel currentPanel;
    private GraphController graphContoller;
    private LowerBoundClusterListController lowerBoundClusterListController;

    /**
     * Constructs a Controller for the ManualIntegerInequalities panel
     *
     * @param InequalitiesListGUI the ManualIntegerInequalities  JFrame that this class will control
     */
    public InequalitiesListController(InequalitiesList inequalitiesList, InequalitiesListGUI InequalitiesListGUI, GraphController graphController, LowerBoundClusterListController lowerBoundClusterListController){

        this.InequalitiesListGUI = InequalitiesListGUI;
        this.inequalitiesList = inequalitiesList;
        this.graphContoller = graphController;
        this.lowerBoundClusterListController = lowerBoundClusterListController;

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
                lowerBoundClusterListController.populate(graphContoller);
//                inequalitiesList.deleteAllInequalities();

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



    //
    // @Override
    // public void keyPressed(KeyEvent e) {
    //     if (e.getKeyCode()==KeyEvent.VK_ENTER){
    //         System.out.println("Hello");
    //
    //         JOptionPane.showMessageDialog(null , "You've Submitted the name " + nameInput.getText());
    //     }
    //
    // }

    /**
     * The action listener for the ManualIntegerInequalities panel
     *
     * @param e the ActionEven object which will identify the performed action
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {

//
//        if (e.getSource() instanceof JButton) {
//            JButton enter = (JButton) e.getSource();
//
//        }
//        else if (e.getSource() instanceof JTextField){
//            JTextField enter = (JTextField) e.getSource();
//                enterInequality = enter.getText();
//                this.parser = new Parser(enterInequality);
//                try {
//                    Inequality parsedExpression = new Inequality(parser.parse());
//                    alg.addNodes(parsedExpression.getParsedExpression());
//                    alg.getPipe().pump();
//                    this.jtfProjectTField = InequalitiesListGUI.getEnterInequality();
//                    inequalitiesList.addInequality(parsedExpression);
//                }catch(Exception r){
//                    System.out.println(r.getMessage());
//                }
//                // parser.parse();
//                System.out.println("enter in text field");
//                //  inequalitiesList.createNewProject(enter.getText());
//
//            }

        }
}


