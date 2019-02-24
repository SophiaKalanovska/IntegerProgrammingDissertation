package Controller;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTextField;

import Model.Inequalities.DecisionVariable;
import Model.Inequalities.InequalitiesList;
import Model.Inequalities.Inequality;
import Model.Inequalities.RandomInequalitiesGenerator;
import View.OperationsOnInequalities.RandomInequalitiesGUI;
import org.graphstream.graph.Node;

/**
 * This class will represent the Controller for the LayoutGUI Panel
 *
 */
public class RandomInequalitiesController implements ActionListener, MouseListener {

    private RandomInequalitiesGUI randomInequalitiesGUI;
    private GraphController graph;
    private InequalitiesList inequalitiesList;

    /**
     * Constructs a Controller for the LayoutGUI panel
     *
     * @param randomInequalitiesGUI the LayoutGUI panel that this class will control
     */
    public RandomInequalitiesController(InequalitiesList inequalitiesList, RandomInequalitiesGUI randomInequalitiesGUI, GraphController graphController){
        this.inequalitiesList = inequalitiesList;
        this.randomInequalitiesGUI = randomInequalitiesGUI;
        this.graph = graphController;
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

        if (e.getSource() instanceof JTextField) {
            JTextField enter = (JTextField) e.getSource();
            ArrayList<DecisionVariable> nodes = new ArrayList<>();
            RandomInequalitiesGenerator generator = new RandomInequalitiesGenerator(graph);
            if (((JTextField) e.getSource()).getName().equals("randomNumberNodes")) {
                int numberOfRandomNodes = Integer.parseInt(enter.getText());
                nodes = generator.generateNodes(numberOfRandomNodes);
            } else {
//                int numberOfRandomInequalities=  Integer.parseInt(enter.getText());
//                ArrayList<Inequality>  inequalities = generator.generateInequalities(nodes);
//                for (Inequality inequality : inequalities) {
//                    inequalitiesList.addInequality(inequality);
//                }
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


}