package view;

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


/**
* Home is the class where the Home panel is built implements Observer
*/
public class GUILayout extends JPanel  {

  /**
  * Constructs a new Home panel
  * @param  frame  the frame containing the panel
  */
  public GUILayout(JFrame frame, GraphGenerator graph, ManualInequalitiesGUI manualInequalitiesGUI, RandomInequalitiesGUI panelrandomNumberInequalities){


    //welcome label
    JLabel welcomeLabel = new JLabel("Graph Generator");
    welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 34));
    //welcome panel creation
    JPanel welcomePanel = new JPanel(new FlowLayout());
    welcomePanel.add(welcomeLabel);
    welcomePanel.setBackground(Color.white);

    //border color
    Border thatBorder = new LineBorder(new Color(153, 218, 250));
    //create border for manual inequalities
    Border thatBorder1 = new TitledBorder(thatBorder,"<html><b> Enter Inequalities:</html><b>" );

    //put border around inequalities
    manualInequalitiesGUI.setBorder(thatBorder1);

    //create border for graph
    Border thatBorder2 = new TitledBorder(thatBorder,"<html><b> Graph:</html><b>" );
    JPanel graphPanel = new JPanel(new GridLayout(1,1));
    graphPanel.add(graph.getView());
    graphPanel.setBorder(thatBorder2);
    graphPanel.setBackground(Color.white);

    Border thatBorder3 = new TitledBorder(thatBorder,"<html><b> Generate Random Inequalities:</html><b>" );
    panelrandomNumberInequalities.setBorder(thatBorder3);


    // put the manual inequalities and the graph in a panel
    JPanel center = new JPanel(new GridLayout(1,2));
    center.add(manualInequalitiesGUI);
    center.add(graphPanel);
    center.setBackground(Color.WHITE);



    this.setLayout(new BorderLayout());
    this.add(welcomePanel, BorderLayout.NORTH);
    this.add(center, BorderLayout.CENTER);
    this.add( panelrandomNumberInequalities, BorderLayout.SOUTH);
    this.setBackground(Color.white);

    frame.add(this);

  }


  public void setPanel(JPanel p){
    removeAll();
    revalidate();
    repaint();
    add(p, BorderLayout.CENTER);
  }

}
