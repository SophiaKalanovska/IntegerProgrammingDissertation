package view;

import model.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.ArrayList;

import controller.GUILayoutController;


/**
* Home is the class where the Home panel is built implements Observer
*/
public class GUILayout extends JPanel  {

  private JTextField enterInequality;
  private JTextField randomNumberInequality;
  private DefaultListModel listModel;
  private JList projectsList;
  private GraphGenerator graphGenerator;

  /**
  * Constructs a new Home panel
  *
  * @param  frame  the frame containing the panel
  */
  public GUILayout(JFrame frame, GraphGenerator alg){

    this.listModel = new DefaultListModel();

    this.setLayout(new BorderLayout());

    JPanel welcomePanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel rightSouthPanel = new JPanel();
    graphGenerator = alg;

    JLabel welcomeLabel = new JLabel("Graph Generator");
    welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 34));
    this.projectsList = new JList(listModel);
    projectsList.setName("projectsList");

    this.enterInequality = new JTextField("Enter inequality...", 20);
    this.enterInequality.setName("inequalityField");


    JPanel emptyPanel = new JPanel(new FlowLayout());
    JLabel emptyLabel = new JLabel("                    ");
    emptyPanel.add(emptyLabel);
    emptyPanel.setBackground(Color.white);

    GridLayout LayoutaddProject  = new GridLayout(2,1);
    JPanel paneladdProject = new JPanel(LayoutaddProject);

    FlowLayout LayoutenterInequality  = new FlowLayout(FlowLayout.LEFT);
    JPanel panelenterInequality = new JPanel(LayoutenterInequality);
    panelenterInequality.add(enterInequality);
    panelenterInequality.setBackground(Color.white);

    paneladdProject.add(panelenterInequality);
    paneladdProject.add(projectsList);

    BorderLayout projectAdding  = new BorderLayout();
    JPanel panelprojectAdding = new JPanel(projectAdding);
    panelprojectAdding.add(emptyPanel,BorderLayout.NORTH);
    panelprojectAdding.add(paneladdProject, BorderLayout.CENTER);


    Border thatBorder1 = new LineBorder(new Color(153, 218, 250));

    GridLayout operations  = new GridLayout(3,1);
    JPanel panelOperations = new JPanel(operations);

    panelOperations.add( panelprojectAdding);
    panelOperations.setBackground(Color.white);


    Border thatBorder2 = new TitledBorder(thatBorder1,"<html><b> Enter Inequalities:</html><b>" );
    panelOperations.setBorder(thatBorder2);

    GridLayout gridCent = new GridLayout(1,2);
    JPanel panelGrid = new JPanel(gridCent);
    panelGrid.add(panelOperations);

    GridLayout graphSolution  = new GridLayout(1,1);
    JPanel panelgraphSolution = new JPanel(graphSolution);
    panelgraphSolution.setBackground(Color.white);


    Border thatBorder3 = new TitledBorder(thatBorder1,"<html><b> Graph:</html><b>" );


    GridLayout LayoutrandomNumber  = new GridLayout(2,1);
    JPanel panelrandomNumber = new JPanel(LayoutrandomNumber);

    this.randomNumberInequality = new JTextField("Number of Nodes...", 20);
    this.randomNumberInequality.setName("randomNumberInequality");


    FlowLayout LayoutrandomNumberInequality  = new FlowLayout(FlowLayout.LEFT);
    JPanel panelrandomNumberInequality = new JPanel(LayoutrandomNumberInequality);
    panelrandomNumberInequality.add(randomNumberInequality);
    panelrandomNumberInequality.setBackground(Color.white);
    Border thatBorder4 = new TitledBorder(thatBorder1,"<html><b> Generate Random Inequalities:</html><b>" );
    panelrandomNumberInequality.setBorder(thatBorder4);


    panelrandomNumber.add(panelrandomNumberInequality);
    panelrandomNumber.setBackground(Color.white);


    panelgraphSolution.add(graphGenerator.getView());

    panelgraphSolution.setBorder(thatBorder3);

    panelGrid.add(panelgraphSolution);


    panelGrid.setBackground(Color.white);
    rightPanel.setBackground(Color.white);


    welcomePanel.setLayout(new FlowLayout());
    welcomePanel.add(welcomeLabel);
    welcomePanel.setBackground(Color.white);

    this.add( welcomePanel, BorderLayout.NORTH);
    this.add(  panelGrid, BorderLayout.CENTER);
    this.add(panelrandomNumber, BorderLayout.SOUTH);
    this.setBackground(Color.white);

    frame.add(this);

  }


  private void UpdateJList(ArrayList<Inequalities> in){
    listModel.clear();
    for(Inequalities i : in){
      listModel.addElement(i);
    }
    projectsList.setModel(listModel);
  }


  public void addControllers(GUILayoutController controller){
    System.out.println("controlller added");
    enterInequality.addActionListener(controller);
    randomNumberInequality.addActionListener(controller);
  }


  public void addMouseListener(GUILayoutController controller){
    System.out.println("mouselistener added");
    projectsList.addMouseListener(controller);
    enterInequality.addMouseListener(controller);
    randomNumberInequality.addMouseListener(controller);
  }



  public void setPanel(JPanel p){
    removeAll();
    revalidate();
    repaint();
    add(p, BorderLayout.CENTER);
  }


  // @Override
  // public void update(Observable obs, Object obj) {
  // 			observer = (ProjectWallet) obs;
  // 			UpdateJList(observer.getProjectWallet());
  // 			//this.clearProjectNameField();
  //
  // 			repaint();
  // 			revalidate();
  // }

}
