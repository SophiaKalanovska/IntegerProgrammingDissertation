package Controller;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextField;

import Model.Inequalities.DecisionVariable;
import Model.Inequalities.InequalitiesList;
import Model.Inequalities.Inequality;
import Model.Inequalities.RandomInequalities.RandomInequalitiesGenerator;
import View.OperationsOnInequalities.RandomInequalitiesGUI;

/**
 * This class will represent the Controller for the LayoutGUI Panel
 *
 */
public class RandomInequalitiesController implements ActionListener, MouseListener {

    private RandomInequalitiesGUI randomInequalitiesGUI;
    private GraphController graph;
    private InequalitiesList inequalitiesList;
    private ArrayList<DecisionVariable> nodes;
    private JTextField enter;

    private RandomInequalitiesGenerator generator;
    /**
     * Constructs a Controller for the LayoutGUI panel
     *
     * @param randomInequalitiesGUI the LayoutGUI panel that this class will control
     */
    public RandomInequalitiesController(InequalitiesList inequalitiesList, RandomInequalitiesGUI randomInequalitiesGUI, GraphController graphController){
        this.inequalitiesList = inequalitiesList;
        this.randomInequalitiesGUI = randomInequalitiesGUI;
        this.graph = graphController;
        this.generator = new RandomInequalitiesGenerator(graph);
        randomInequalitiesGUI.addControllers(this);
        randomInequalitiesGUI.addMouseListener(this);
        nodes = new  ArrayList<>();
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
    public synchronized void actionPerformed(java.awt.event.ActionEvent e) {


        if (e.getSource() instanceof JButton) {
            JButton enter = (JButton) e.getSource();
        }
        if (e.getSource() instanceof JTextField) {


            if (((JTextField) e.getSource()).getName().equals("randomNumberNodes")) {
                enter = (JTextField) e.getSource();
                final ArrayList<Inequality> inequalities = new ArrayList<>();
                for(int i = 0; i < Integer.parseInt(enter.getText()); i ++) {
                    inequalities.add(generator.generateInequalityForNode(generator.generateNode()));
                }
//                enter.setText("Number of Decision Variables...");
                Thread thread = new Thread() {
                   @Override
                    public void run() {
                       for ( Inequality in : inequalities)
                            inequalitiesList.addInequality(in);
                        }
                    };

                thread.start();
                Thread thread2= new Thread() {
                    @Override
                    public void run() {
                        for ( Inequality in : inequalities)
                            inequalitiesList.drawInequality(in);
                    }
                };
                thread2.start();
            } else {
                JTextField enter = (JTextField) e.getSource();
                int numberOfRandomInequalities=  Integer.parseInt(enter.getText());
                enter.setText("Number of Inequalities...");
                for (int i= 0; i < numberOfRandomInequalities; i++){
                    Inequality inequality= generator.generateInequalities(nodes);
                    inequalitiesList.addInequality(inequality);
                }
            }
        }
    }

    private void generateNodes(RandomInequalitiesGenerator generator, int numberOfRandomNodes) {

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