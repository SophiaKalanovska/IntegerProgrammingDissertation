package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
* Home is the class where the Home panel is built implements Observer
*/
public class GraphGenerator extends JPanel  {

  private JTextField newProjectNameField;
  private DefaultListModel listModel;
  private JList projectsList;
  private Algorithm algorithm;

  /**
  * Constructs a new Home panel
  *
  * @param  frame  the frame containing the panel
  */
  public GraphGenerator(JFrame frame){

    this.listModel = new DefaultListModel();

    this.setLayout(new BorderLayout());

    JPanel welcomePanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel rightSouthPanel = new JPanel();
    algorithm = new Algorithm();

    JLabel welcomeLabel = new JLabel("Graph Generator");
    welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 34));

    this.projectsList = new JList(listModel);
    projectsList.setName("projectsList");

    this.newProjectNameField = new JTextField("Enter inequality...", 20);
    this.newProjectNameField.setName("jtfProjectNField");


    JPanel emptyPanel = new JPanel(new FlowLayout());
    JLabel emptyLabel = new JLabel("                    ");
    emptyPanel.add(emptyLabel);

    GridLayout LayoutaddProject  = new GridLayout(2,1);
    JPanel paneladdProject = new JPanel(LayoutaddProject);

    FlowLayout LayoutnewProjectNameField  = new FlowLayout(FlowLayout.LEFT);
    JPanel panelnewProjectNameField = new JPanel(LayoutnewProjectNameField);
    panelnewProjectNameField.add(newProjectNameField);

    paneladdProject.add(panelnewProjectNameField);
    paneladdProject.add(projectsList);

    BorderLayout projectAdding  = new BorderLayout();
    JPanel panelprojectAdding = new JPanel(projectAdding);
    panelprojectAdding.add(emptyPanel,BorderLayout.NORTH);
    panelprojectAdding.add(paneladdProject, BorderLayout.CENTER);


    TitledBorder title;
    Border thatBorder1 = new LineBorder(new Color(153, 218, 250));

    GridLayout operations  = new GridLayout(3,1);
    JPanel panelOperations = new JPanel(operations);

    panelOperations.add( panelprojectAdding);


    Border thatBorder2 = new TitledBorder(thatBorder1,"<html><b> Enter Inequalities:</html><b>" );
    panelOperations.setBorder(thatBorder2);

    GridLayout gridCent = new GridLayout(1,2);
    JPanel panelGrid = new JPanel(gridCent);
    panelGrid.add(panelOperations);
    panelGrid.add(algorithm.view);

    welcomePanel.setLayout(new FlowLayout());
    welcomePanel.add(welcomeLabel);

    this.add( welcomePanel, BorderLayout.NORTH);
    this.add(  panelGrid, BorderLayout.CENTER);

    frame.add(this);
  }

  /**
  * Adds controllers to the elements in the panel
  *
  * @param controller adds the supplied object as a controller for this view
  */
  // public void addControllers(HomeController controller){
  //
  // 	newProjectNameField.addActionListener(controller);
  // 	addNewProjectButton.addActionListener(controller);
  //   	deleteProjectButton.addActionListener(controller);
  //   	changeProjectButton.addActionListener(controller);
  // }
  //
  // /**
  // * Adds mouseListeners to the elements in the panel
  // *
  // * @param controller adds the supplied controller as a mouse listener
  // */
  // public void addMouseListener(HomeController controller){
  //
  // 	projectsList.addMouseListener(controller);
  // 	newProjectNameField.addMouseListener(controller);
  //
  //   	deleteProjectNameField.addMouseListener(controller);
  //  		newchangeProjectNameField.addMouseListener(controller);
  //   	oldchangeProjectNameField.addMouseListener(controller);
  // }

  /**
  * Sets the panel
  *
  * @param p the panel to be set
  */
  public void setPanel(JPanel p){
    removeAll();
    revalidate();
    repaint();
    add(p, BorderLayout.CENTER);
  }

  /**
  * Returns the selected project
  *
  * @return    the project
  */
  // public Projects getListSelectedValue(){
  // 	return (Projects) projectsList.getSelectedValue();
  // }
  //
  // /**
  // * Updates the JList containinf the list of projects
  // *
  // * @param  ps  the list of projects
  // */
  // private void UpdateJList(ArrayList<Projects> ps){
  // 	listModel.clear();
  //     for(Projects p : ps){
  //         listModel.addElement(p);
  //     }
  //     projectsList.setModel(listModel);
  // }

  /**
  * Clears the projectName field
  */
  public void clearProjectNameField(){
    newProjectNameField.setText("Enter inequality...");
  };

  /**
  * Returns the project name
  *
  * @return    the name
  */
  public String getProjectNameField(){
    return newProjectNameField.getText();
  };
}
