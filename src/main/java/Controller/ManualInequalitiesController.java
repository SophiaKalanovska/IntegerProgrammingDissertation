package Controller;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import Model.Inequalities.InequalitiesList;
import Model.Inequalities.Inequality;
import Model.Parser.Parser;
import View.OperationsOnInequalities.ManualInequalitiesGUI;

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
    /**
     * Constructs a Controller for the ManualIntegerInequalities panel
     *
     * @param ManualInequalitiesGUI the ManualIntegerInequalities  JFrame that this class will control
     */
    public ManualInequalitiesController(InequalitiesList inequalitiesList, ManualInequalitiesGUI ManualInequalitiesGUI,  GraphController graphController){
        this.inequalitiesList = inequalitiesList;
        this.manualInequalitiesGUI = ManualInequalitiesGUI;
        ManualInequalitiesGUI.addControllers(this);
        ManualInequalitiesGUI.addMouseListener(this);
        graph = graphController;
    }

    /**
     * Mouse listener for the ManualIntegerInequalities panel
     *
     * @param e Mouse listener that will identify the actions that the user makes
     */

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JTextField) {
            JTextField enter = (JTextField) e.getSource();
            enter.setText("");
        }
    }

    /**
     * The action listener for the ManualIntegerInequalities panel
     *
     * @param e the ActionEven object which will identify the performed action
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() instanceof JTextField) {
            JTextField enter = (JTextField) e.getSource();
            enterInequality = enter.getText();
            enter.setText("Enter inequality...");
            parser.setString(enterInequality);
            try {
                final Inequality parsedExpression = parser.parse();
                if (parsedExpression.getFirstDecisionVariable().getWeight() <1 && parsedExpression.getFirstDecisionVariable().getWeight() >0){
                    JOptionPane.showMessageDialog(manualInequalitiesGUI.getParent().getParent(), "Coefficients between 0 and 1 are beyond the scope of this project.","Out of scope warning",
                            JOptionPane.WARNING_MESSAGE);
                }else{
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            inequalitiesList.addInequality(parsedExpression);
                        }
                    };

                    thread.start();
                    Thread thread2 = new Thread() {
                        @Override
                        public void run() {
                            graph.drawInequality(parsedExpression);
                            graph.calculateInequalities();
                        }

                    };
                    thread2.start();
                }
            } catch (Exception r) {
                System.out.println(r.getMessage());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}
