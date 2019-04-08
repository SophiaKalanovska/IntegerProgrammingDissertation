package Controller.Listeners;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JTextField;

import Controller.GraphController;
import Model.Inequalities.DecisionVariable;
import Model.Inequalities.InequalitiesList;
import Model.Inequalities.Inequality;
import Model.Inequalities.RandomInequalities.RandomInequalitiesGenerator;
import View.Inequalities.RandomInequalitiesGUI;

/**
 * This class will represent the Controller for the LayoutGUI Panel
 *
 */
public class RandomInequalitiesController implements ActionListener, MouseListener{

    private GraphController graph;
    private InequalitiesList inequalitiesList;
    private ArrayList<DecisionVariable> nodes;
    private JTextField enter;

    private RandomInequalitiesGenerator generator;

    public RandomInequalitiesController(InequalitiesList inequalitiesList, RandomInequalitiesGUI randomInequalitiesGUI, GraphController graphController){
        this.inequalitiesList = inequalitiesList;
        this.graph = graphController;
        this.generator = new RandomInequalitiesGenerator();
        nodes = new ArrayList<>();
        randomInequalitiesGUI.addControllers(this);
        randomInequalitiesGUI.addMouseListener(this);
    }


    /**
     * This method is triggered once the user presses enter.
     * It checks whether the number corresponds to creating
     * random decision variables or random inequalities.
     * The information is then send to the RandomInequalities generator.
     **/
    @Override
    public synchronized void actionPerformed(java.awt.event.ActionEvent e) {

        if (e.getSource() instanceof JTextField) {


            if (((JTextField) e.getSource()).getName().equals("randomNumberNodes")) {
                enter = (JTextField) e.getSource();
                final HashSet<Inequality> inequalities = new HashSet<>();
                while(inequalities.size() < Integer.parseInt(enter.getText())){
                    DecisionVariable variable = generator.generateDecisionVariable();
                    nodes.add(variable);
                    inequalities.add(generator.generateInequalityForNode(variable));
                }
                enter.setText("Number of Decision Variables...");
                ///a separate thread that represents the inequality object in the list
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        addInequalities(inequalities);
                    }
                };
                thread.start();
                ///new thread that represents the inequality object in the graph is
                //run in parallel.
                Thread thread2= new Thread() {
                    @Override
                    public void run() {
                        visualizeInequality(inequalities);
                    }

                };
                thread2.start();
            } else {
                JTextField enter = (JTextField) e.getSource();
                int numberOfRandomInequalities=  Integer.parseInt(enter.getText());
                final HashSet<Inequality> inequalities = new HashSet<>();
                for (int i= 0; i < numberOfRandomInequalities; i++){
                    inequalities.add(generator.generateInequality(nodes));
                }
                enter.setText("Number of Inequalities...");

                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        addInequalities(inequalities);
                    }
                };
                thread.start();

                Thread thread2= new Thread() {
                    @Override
                    public void run() {
                        visualizeInequality(inequalities);
                    }

                };
                thread2.start();
            }
        }
    }

    public synchronized void addInequalities(HashSet<Inequality> inequalities) {
        for ( Inequality in : inequalities)
            inequalitiesList.addInequality(in);
    }

    public synchronized void visualizeInequality(HashSet<Inequality> inequalities) {
        for ( Inequality in : inequalities)
            graph.drawInequality(in);
        graph.findStronglyConnectedComponents();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JTextField) {
            JTextField enter = (JTextField) e.getSource();
            enter.setText("");
        }
    }

    public InequalitiesList getInequalitiesList() {
        return inequalitiesList;
    }

    public void resetRandomInequalitiesGenerator(){
        generator =  new RandomInequalitiesGenerator();
        nodes = new ArrayList<>();
    }

    /**
     * Methods needed for the implementation of MouseListener
     **/
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}