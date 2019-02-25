package View.OperationsOnInequalities;

import Controller.RandomInequalitiesController;
import javax.swing.*;
import java.awt.*;


public class RandomInequalitiesGUI extends JPanel {
    private JLabel randomNumberNodesLabel;
    private JTextField randomNumberNodes;
    private JLabel randomNumberInequalitiesLabel;
    private JTextField randomNumberInequalities;

    public RandomInequalitiesGUI(){


        this.randomNumberNodesLabel = new JLabel("Enter the number of random decision variables to be generated:");
        this.randomNumberNodes = new JTextField("Number of Decision Variables...", 20);
        this.randomNumberNodes.setName("randomNumberNodes");
        this.randomNumberInequalitiesLabel = new JLabel("Enter the number of random inequalities to be generated:");
        this.randomNumberInequalities = new JTextField("Number of Inequalities...", 20);
        this.randomNumberInequalities.setName("randomNumberInequalities");


        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(randomNumberNodesLabel);
        this.add(randomNumberNodes);
        this.add(randomNumberInequalitiesLabel);
        this.add(randomNumberInequalities);
        this.setOpaque(false);

    }

    public void addControllers(RandomInequalitiesController controller){
        System.out.println("Controller added");
        randomNumberNodes.addActionListener(controller);
        randomNumberInequalities.addActionListener(controller);
    }

    public void addMouseListener(RandomInequalitiesController controller){
        System.out.println("mouselistener added");
        randomNumberNodes.addMouseListener(controller);
        randomNumberInequalities.addMouseListener(controller);
    }










}
