package controller;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JTextField;
import view.*;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JList;
import model.*;
/**
 * This class will represent the Controller for the ManualIntegerInequalities Panel
 *
 */
public class ManualInequalitiesController implements ActionListener, MouseListener {

    //private ArrayList<Projects> projectData;
    private InequalitiesList inequalitiesList;
    private String enterInequality;
    private String jtfProjectTField;
    private ManualInequalitiesGUI ManualInequalitiesGUI;
    private JPanel currentPanel;
    private Parser parser;
    private GraphGenerator alg;

    /**
     * Constructs a Controller for the ManualIntegerInequalities panel
     *
     * @param ManualInequalitiesGUI the ManualIntegerInequalities  JFrame that this class will control
     */
    public ManualInequalitiesController(InequalitiesList inequalitiesList,ManualInequalitiesGUI ManualInequalitiesGUI, GraphGenerator alg){


        this.ManualInequalitiesGUI = ManualInequalitiesGUI;
        this.inequalitiesList = inequalitiesList;
        this.alg = alg;

        ManualInequalitiesGUI.addControllers(this);
        ManualInequalitiesGUI.addMouseListener(this);


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
     * Mouse listener for the ManualIntegerInequalities panel
     *
     * @param e Mouse listener that will identify the actions that the user makes
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("You clicked me manual and I don't know");
        if (e.getSource() instanceof JTextField) {
            JTextField enter = (JTextField) e.getSource();
            System.out.println("You clicked me manual and I know");
            enter.setText("");
        }

        if (e.getSource() instanceof JList) {
            JList enter = (JList) e.getSource();

            if(enter.getName().equals("projectsList")) {

                //System.out.println("hello openning a project");
                // Projects projectData = ManualIntegerInequalities.getListSelectedValue();
                // this.project = new Project(container, projectData);
                // ProjectController pc = new ProjectController(projectData, project, ManualIntegerInequalities, container);
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
     * The action listener for the ManualIntegerInequalities panel
     *
     * @param e the ActionEven object which will identify the performed action
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {

        // if(e.getKeyCode() == KeyEvent.VK_ENTER) {





        if (e.getSource() instanceof JButton) {
            JButton enter = (JButton) e.getSource();

        }
        else if (e.getSource() instanceof JTextField){
            JTextField enter = (JTextField) e.getSource();
                enterInequality = enter.getText();
                this.parser = new Parser(enterInequality);
                try {
                    Inequality parsedExpression = new Inequality(parser.parse());
                    alg.addNodes(parsedExpression.getParsedExpression());
                    alg.getPipe().pump();
                    this.jtfProjectTField = ManualInequalitiesGUI.getEnterInequality();
                    inequalitiesList.addInequality(parsedExpression);
                }catch(Exception r){
                    System.out.println(r.getMessage());
                }
                // parser.parse();
                System.out.println("enter in text field");
                //  inequalitiesList.createNewProject(enter.getText());

            }

        }

    }


