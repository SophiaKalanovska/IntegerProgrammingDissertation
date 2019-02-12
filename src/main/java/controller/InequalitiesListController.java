package controller;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JTextField;
import view.*;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.*;
/**
 * This class will represent the Controller for the ManualIntegerInequalities Panel
 *
 */
public class InequalitiesListController implements ActionListener, MouseListener {

    //private ArrayList<Projects> projectData;
    private InequalitiesList inequalitiesList;
    private String enterInequality;
    private String jtfProjectTField;
    private InequalitiesListGUI InequalitiesListGUI;
    private JPanel currentPanel;
    private GraphController graph;

    /**
     * Constructs a Controller for the ManualIntegerInequalities panel
     *
     * @param InequalitiesListGUI the ManualIntegerInequalities  JFrame that this class will control
     */
    public InequalitiesListController(InequalitiesList inequalitiesList, InequalitiesListGUI InequalitiesListGUI, GraphController graphController){


        this.InequalitiesListGUI = InequalitiesListGUI;
        this.inequalitiesList = inequalitiesList;
        this.graph = graphController;

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
            }else{
                System.out.println(((JButton) e.getSource()).getName());
                inequalitiesList.deleteAllInequalities();
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


