package controller;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JTextField;
import view.*;
import model.*;
/**
 * This class will represent the Controller for the ManualIntegerInequalities Panel
 *
 */
public class ManualInequalitiesController implements ActionListener, MouseListener {

    private InequalitiesList inequalitiesList;
    private String enterInequality;
    private Parser parser;

    /**
     * Constructs a Controller for the ManualIntegerInequalities panel
     *
     * @param ManualInequalitiesGUI the ManualIntegerInequalities  JFrame that this class will control
     */
    public ManualInequalitiesController(InequalitiesList inequalitiesList, ManualInequalitiesGUI ManualInequalitiesGUI){

        this.inequalitiesList = inequalitiesList;

        ManualInequalitiesGUI.addControllers(this);
        ManualInequalitiesGUI.addMouseListener(this);
    }

    /**
     * Mouse listener for the ManualIntegerInequalities panel
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
     * The action listener for the ManualIntegerInequalities panel
     *
     * @param e the ActionEven object which will identify the performed action
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() instanceof JButton) {
//            JButton enter = (JButton) e.getSource();
        }
        else if (e.getSource() instanceof JTextField){
            JTextField enter = (JTextField) e.getSource();
            enterInequality = enter.getText();
            this.parser = new Parser(enterInequality);
            try {
                Inequality parsedExpression = parser.parse();
                inequalitiesList.addInequality(parsedExpression);
            }catch(Exception r){
                System.out.println(r.getMessage());
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


