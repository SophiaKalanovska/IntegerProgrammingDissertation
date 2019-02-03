package controller;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import view.*;

import javax.swing.JPanel;
import javax.swing.JList;
import model.*;
/**
 * This class will represent the Controller for the LayoutGUI Panel
 *
 */
public class GUILayoutController implements ActionListener, MouseListener {

    //private ArrayList<Projects> projectData;
    // private InequalitiesList projectWallet;
    private String enterInequality;
    private RandomInequalitiesGUI randomInequalitiesGUI;
    private JFrame container;
    private JPanel currentPanel;
    private Parser parser;
    private GraphGUI alg;

    /**
     * Constructs a Controller for the LayoutGUI panel
     *
     * @param randomInequalitiesGUI the LayoutGUI panel that this class will control
     * @param container the main frame
     */
    public GUILayoutController(RandomInequalitiesGUI randomInequalitiesGUI, JFrame container, GraphGUI alg){


        this.randomInequalitiesGUI = randomInequalitiesGUI;
        this.container = container;
        this.alg = alg;


        randomInequalitiesGUI.addControllers(this);
        randomInequalitiesGUI.addMouseListener(this);


    }

    private String geInequality(){
        return this.enterInequality;
    }
    /**
     * Returns a String conatining the input of the user
     *
     * @return string with the input
     */
    // private String geJTFProjectTField(){
    //   return this.enterInequality;
    // }

    /**
     * Mouse listener for the LayoutGUI panel
     *
     * @param e Mouse listener that will identify the actions that the user makes
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() instanceof JTextField) {
            JTextField enter = (JTextField) e.getSource();
            System.out.println("You clicked me and I know");
            enter.setText("");
        }

        if (e.getSource() instanceof JList) {
            JList enter = (JList) e.getSource();

            if(enter.getName().equals("projectsList")) {

                //System.out.println("hello openning a project");
                // Projects projectData = LayoutGUI.getListSelectedValue();
                // this.project = new Project(container, projectData);
                // ProjectController pc = new ProjectController(projectData, project, LayoutGUI, container);
                // container.getContentPane().invalidate();
                // container.getContentPane().removeAll();
                // container.getContentPane().add(project);
                // container.revalidate();
                // container.repaint();
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
     * The action listener for the LayoutGUI panel
     *
     * @param e the ActionEven object which will identify the performed action
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {


        if (e.getSource() instanceof JButton) {
            JButton enter = (JButton) e.getSource();

        }

       if (e.getSource() instanceof JTextField){
            JTextField enter = (JTextField) e.getSource();
                int numberOfRandomNodes =  Integer.parseInt(enter.getText());
                alg.addRandomNodes(numberOfRandomNodes);
        }

    }

}