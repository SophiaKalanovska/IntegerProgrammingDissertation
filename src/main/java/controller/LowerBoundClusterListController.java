package controller;

import model.InequalitiesList;
import model.SCCCluster;
import model.SCCClusterList;
import view.GraphGUI;
import view.InequalitiesListGUI;
import view.LowerBoundClusterGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class will represent the Controller for the ManualIntegerInequalities Panel
 *
 */
public class LowerBoundClusterListController implements ActionListener, MouseListener {

    private SCCClusterList SCCList;
    private LowerBoundClusterGUI LowerBoundClusterGUI;

//    private String enterInequality;
//    private String jtfProjectTField;
//    private InequalitiesListGUI InequalitiesListGUI;
//    private JPanel currentPanel;
//    private GraphGUI graph;

    /**
     * Constructs a Controller for the ManualIntegerInequalities panel
     *
     * @param LowerBoundClusterGUI the ManualIntegerInequalities  JFrame that this class will control
     */
    public LowerBoundClusterListController(SCCClusterList SCCList, LowerBoundClusterGUI LowerBoundClusterGUI){

        this.SCCList = SCCList;
        this.LowerBoundClusterGUI = LowerBoundClusterGUI;
//        this.graph = graph;
        LowerBoundClusterGUI.addMouseListener(this);
    }

    /**
     * Mouse listener for the ManualIntegerInequalities panel
     *
     * @param e Mouse listener that will identify the actions that the user makes
     */
    @Override
    public void mouseClicked(MouseEvent e) {
//
//        System.out.println("You clicked me manual and I don't know");
//        if (e.getSource() instanceof JButton) {
//            System.out.println(((JButton) e.getSource()).getName());
//            if (((JButton) e.getSource()).getName().equals("delete")){
//                System.out.println(((JButton) e.getSource()).getName());
//                inequalitiesList.deleteInequality(InequalitiesListGUI.getListSelectedValue());
//            }else{
//                System.out.println(((JButton) e.getSource()).getName());
//                inequalitiesList.deleteAllInequalities();
//            }
//
//        }
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
    public void actionPerformed(ActionEvent e) {

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


