package controller;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JTextField;
import view.*;

/**
 * This class will represent the Controller for the LayoutGUI Panel
 *
 */
public class RandomInequalitiesController implements ActionListener, MouseListener {

    private RandomInequalitiesGUI randomInequalitiesGUI;
    private GraphGUI graph;

    /**
     * Constructs a Controller for the LayoutGUI panel
     *
     * @param randomInequalitiesGUI the LayoutGUI panel that this class will control
     */
    public RandomInequalitiesController(RandomInequalitiesGUI randomInequalitiesGUI,  GraphGUI graph){
        this.randomInequalitiesGUI = randomInequalitiesGUI;
        this.graph = graph;

        randomInequalitiesGUI.addControllers(this);
        randomInequalitiesGUI.addMouseListener(this);
    }

    /**
     * Mouse listener for the LayoutGUI panel
     *
     * @param e Mouse listener that will identify the actions that the user makes
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JTextField) {
            JTextField enter = (JTextField) e.getSource();
            enter.setText("");
        }
    }

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
            graph.addRandomNodes(numberOfRandomNodes);
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


}