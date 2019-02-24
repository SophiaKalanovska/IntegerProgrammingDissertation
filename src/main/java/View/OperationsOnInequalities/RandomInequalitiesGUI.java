package View.OperationsOnInequalities;

import Controller.RandomInequalitiesController;
import javax.swing.*;
import java.awt.*;


public class RandomInequalitiesGUI extends JPanel {
    private JTextField randomNumberNodes;
    private JTextField randomNumberInequalities;

    public RandomInequalitiesGUI(){

        this.setLayout(new GridLayout(2,1));

        this.randomNumberNodes = new JTextField("Number of Nodes...", 20);
        this.randomNumberNodes.setName("randomNumberNodes");

        this.randomNumberInequalities = new JTextField("Number of Inequalities...", 20);
        this.randomNumberInequalities.setName("randomNumberInequalities");


        JPanel operations = new JPanel(new GridLayout(2,1));
        operations.add(randomNumberNodes);
        operations.add(randomNumberInequalities);
        operations.setBackground(Color.WHITE);

        JPanel panelrandomNumberInequality = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelrandomNumberInequality.add(operations);
        panelrandomNumberInequality.setBackground(Color.white);

        add(panelrandomNumberInequality);
        setBackground(Color.white);

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
