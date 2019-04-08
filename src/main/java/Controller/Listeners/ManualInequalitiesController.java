package Controller.Listeners;

import java.awt.event.*;
import javax.swing.*;
import Controller.GraphController;
import Model.Inequalities.InequalitiesList;
import Model.Inequalities.Inequality;
import Model.Parser.Parser;
import View.Inequalities.ManualInequalitiesGUI;

/**
 * This class will represent the Controller for the ManualIntegerInequalities Panel
 *
 */
public class ManualInequalitiesController implements ActionListener, MouseListener {

    private InequalitiesList inequalitiesList;
    private String enterInequality;
    private ManualInequalitiesGUI manualInequalitiesGUI;
    private GraphController graph;
    private final Parser parser = new Parser();

    public ManualInequalitiesController(InequalitiesList inequalitiesList, ManualInequalitiesGUI ManualInequalitiesGUI,  GraphController graphController){
        this.inequalitiesList = inequalitiesList;
        this.manualInequalitiesGUI = ManualInequalitiesGUI;
        ManualInequalitiesGUI.addControllers(this);
        ManualInequalitiesGUI.addMouseListener(this);
        graph = graphController;
    }

    /**
     * Tiggered once the user presses the mouse the text in the JTextField is deleted
     **/
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JTextField) {
            JTextField enter = (JTextField) e.getSource();
            enter.setText("");
        }
    }

    /**
     * Tiggered once the user manually inputs an inequality and presses enter.
     * This method obtains the user input and sends the data to the Parser
     **/
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() instanceof JTextField) {
            JTextField enter = (JTextField) e.getSource();
            enterInequality = enter.getText();
            enter.setText("Enter inequality...");
            parser.setString(enterInequality);
            try {
                final Inequality inequality = parser.parse();


                if (inequality.getLeftDecisionVariable().getWeight() <1 && inequality.getLeftDecisionVariable().getWeight() >0){
                    JOptionPane.showMessageDialog(manualInequalitiesGUI.getParent().getParent(), "Coefficients between 0 and 1 are beyond the scope of this project.","Out of scope warning",
                            JOptionPane.WARNING_MESSAGE);
                }else if(inequality.getLeftDecisionVariable().getWeight() <= 0 ) {
                    //new thread that adds the inequality object to the list of inequalities
                    Thread addInequality = new Thread() {
                        @Override
                        public void run() {
                            inequalitiesList.addInequality(inequality);
                        }
                    };

                    addInequality.start();
                    ///new thread that presents the inequality object in the graph
                    //run in parallel This method is envoked when s negative weight is
                    //present, so the addNode() method is used instead of drawInequality()
                     Thread drawInequality = new Thread() {
                        @Override
                        public void run() {
                            graph.addNode(inequality.getLeftDecisionVariable());
                            graph.addNode(inequality.getRightDecisionVariable());
                        }

                    };
                    drawInequality.start();

                }else{
                    //new thread that adds the inequality object to the list of inequalities
                    Thread addInequality = new Thread() {
                        @Override
                        public void run() {
                            inequalitiesList.addInequality(inequality);
                        }
                    };
                    //new thread that presents the inequality object in
                    //the graph is run in parallel
                    //as a new dependency might have been created the
                    // findStronglyConnectedComponents is run as well
                    addInequality.start();
                    Thread drawInequality = new Thread() {
                        @Override
                        public void run() {
                            graph.drawInequality(inequality);
                            graph.findStronglyConnectedComponents();
                        }

                    };
                    drawInequality.start();
                }
            } catch (Exception r) {
                String message = r.getMessage();
                JOptionPane.showMessageDialog(manualInequalitiesGUI.getParent().getParent(), message , "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
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
