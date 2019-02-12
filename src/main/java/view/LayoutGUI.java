package view;

import view.SolutionPanel.BoundsGUI;
import view.SolutionPanel.IntegerAssignmentGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


/**
 * Home is the class where the Home panel is built implements Observer
 */
public class LayoutGUI extends JPanel  {

    /**
     * Constructs a new Home panel
     * @param  frame  the frame containing the panel
     */


    public LayoutGUI(JFrame frame, GraphGUI graph, ManualInequalitiesGUI manualInequalitiesGUI, RandomInequalitiesGUI panelrandomNumberInequalities, InequalitiesListGUI inequalitiesListGUI, BoundsGUI boundGUI, IntegerAssignmentGUI integerAssignmentGUI){

        //welcome label
        JLabel welcomeLabel = new JLabel("Inequalities solver");
        welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 34));
        //welcome panel creation
        JPanel welcomePanel = new JPanel(new FlowLayout());
        welcomePanel.add(welcomeLabel);
        welcomePanel.setBackground(Color.white);

        //border color
        Border mainBorder = BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(153, 218, 250));
        //create border for manual inequalities
        Border thatBorder1 = new TitledBorder(mainBorder,"<html><b> Enter Inequalities:</html><b>" );


        //create border for graph
        JPanel graphPanel = new JPanel(new GridLayout(1,1));
        graphPanel.add(graph.getView());
        graphPanel.setBorder(new TitledBorder(mainBorder,"<html><b> Graph:</html><b>" ));
        graphPanel.setBackground(Color.white);


        //border color
        Border topBorder = BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(153, 218, 250));
        panelrandomNumberInequalities.setBorder(new TitledBorder(topBorder,"<html><b> Generate Random Inequalities:</html><b>" ));



        inequalitiesListGUI.setBorder(new TitledBorder(topBorder,"<html><b> List of Inequalities:</html><b>" ));


        JPanel controlPanel = new JPanel(new GridLayout(2,1));
        controlPanel.add(manualInequalitiesGUI);controlPanel.add(panelrandomNumberInequalities);



        //border color
        Border topBorder1 = BorderFactory.createMatteBorder(1, 0, 1, 1, new Color(153, 218, 250));
        integerAssignmentGUI.setBorder(new TitledBorder(topBorder1,"<html><b> Optimal Integer Assignment:</html><b>" ));



        JPanel solutionPanel = new JPanel(new GridLayout(1,2));
        solutionPanel.add(boundGUI);
        solutionPanel.add(integerAssignmentGUI);
        solutionPanel.setBackground(Color.WHITE);

        //put border around inequalities


        JPanel leftPanel = new JPanel(new GridLayout(2,1));
        leftPanel.add(controlPanel);
        leftPanel.add(inequalitiesListGUI);
        leftPanel.setBackground(Color.WHITE);

        leftPanel.setBorder(thatBorder1);


        // put the manual inequalities and the graph in a panel
        JPanel center = new JPanel(new GridLayout(1,2));
        center.add(leftPanel);
        center.add(graphPanel);
        center.setBackground(Color.WHITE);



        this.setLayout(new BorderLayout());
        this.add(welcomePanel, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(solutionPanel, BorderLayout.SOUTH);
        this.setBackground(Color.white);

        frame.add(this);

    }


//  public void setPanel(JPanel p){
//    removeAll();
//    revalidate();
//    repaint();
//    add(p, BorderLayout.CENTER);
//  }



}
