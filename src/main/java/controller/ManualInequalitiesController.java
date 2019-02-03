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
    // private ProjectWallet projectWallet;
    private String enterInequality;
    private ManualInequalitiesGUI ManualInequalitiesGUI;
    private JPanel currentPanel;
    private Parser parser;
    private GraphGenerator alg;

    /**
     * Constructs a Controller for the ManualIntegerInequalities panel
     *
     * @param ManualInequalitiesGUI the ManualIntegerInequalities  JFrame that this class will control
     */
    public ManualInequalitiesController(ManualInequalitiesGUI ManualInequalitiesGUI, GraphGenerator alg){


        this.ManualInequalitiesGUI = ManualInequalitiesGUI;
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
            //
            //   if(enter.getName().equals("jbAddProject")) {
            //     //System.out.println("button press");
            //     this.jtfProjectTField = ManualIntegerInequalities.getProjectNameField();
            //     projectWallet.createNewProject(this.jtfProjectTField);
            //     // System.out.println("We have these in the wallet now added");
            //     // for (Projects x: projectWallet.getProjectWallet())
            //     // {
            //     //   System.out.println(x.getName());
            //     // }
            //     ManualIntegerInequalities.clearProjectNameField();
            //   }
            //
            //   if(enter.getName().equals("jbDeleteProject")) {
            //     //System.out.println("button press");
            //     this.jtfProjectTField = ManualIntegerInequalities.getProjectDeleteField();
            //     System.out.println(jtfProjectTField);
            //     projectWallet.deleteProject(this.jtfProjectTField);
            //     // System.out.println("We have these in the wallet now");
            //     //
            //     // for (Projects x: projectWallet.getProjectWallet())
            //     // {
            //     //   System.out.println(x.getName());
            //     // }
            //     ManualIntegerInequalities.clearProjectDeleteField();
            //   }
            //
            //   if (enter.getName().equals("jbChangeProject")){
            //
            //     this.oldField = ManualIntegerInequalities.getOldChangeProjectNameField();
            //     this.newField = ManualIntegerInequalities.getNewChangeProjectNameField();
            //     projectWallet.change(oldField, newField);
            //     ManualIntegerInequalities.clearOldProjectNameField();
            //     ManualIntegerInequalities.clearDesiredProjectNameField();
            //   }
        }



        else if (e.getSource() instanceof JTextField){
            JTextField enter = (JTextField) e.getSource();
            if (enter.getName().equals("randomNumberInequality")){
//                RandomGraphGenerator rg = new RandomGraphGenerator();
                int numberOfRandomNodes =  Integer.parseInt(enter.getText());
                alg.addRandomNodes(numberOfRandomNodes);
//                rg.addNode();
                // alg.addNodesRandom(numberOfRandomNodes);
            }else{
                enterInequality = enter.getText();
                this.parser = new Parser(enterInequality);
                try {
                    ArrayList<String> parsedExpression = parser.parse();
                    alg.addNodes(parsedExpression);
                    alg.getPipe().pump();
                }catch(Exception r){
                    System.out.println(r.getMessage());
                }
                // parser.parse();
                System.out.println("enter in text field");
                //  projectWallet.createNewProject(enter.getText());

            }

        }

    }

}
