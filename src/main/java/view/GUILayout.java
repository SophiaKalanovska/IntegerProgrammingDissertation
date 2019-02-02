package view;
import controller.GUILayoutController;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




/**
* Home is the class where the Home panel is built implements Observer
*/
public class GUILayout extends JPanel  {

  private JTextField randomNumberInequality;
  private GraphGenerator graphGenerator;
  private ManualInequalities manualInequalities;

  /**
  * Constructs a new Home panel
  *
  * @param  frame  the frame containing the panel
  */
  public GUILayout(JFrame frame, GraphGenerator alg, ManualInequalities manualInequalities){

    this.manualInequalities = manualInequalities;

      JLabel welcomeLabel = new JLabel("Graph Generator");
      welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 34));


    this.setLayout(new BorderLayout());

    JPanel welcomePanel = new JPanel();
    JPanel rightPanel = new JPanel();
    graphGenerator = alg;


    Border thatBorder1 = new LineBorder(new Color(153, 218, 250));

    Border thatBorder2 = new TitledBorder(thatBorder1,"<html><b> Enter Inequalities:</html><b>" );
    manualInequalities.setBorder(thatBorder2);

    GridLayout gridCent = new GridLayout(1,2);
    JPanel panelGrid = new JPanel(gridCent);
    panelGrid.add(manualInequalities);

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


  public void addControllers(GUILayoutController controller){
      System.out.println("controller added");
      randomNumberInequality.addActionListener(controller);
  }


  public void addMouseListener(GUILayoutController controller){
    System.out.println("mouselistener added");
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
