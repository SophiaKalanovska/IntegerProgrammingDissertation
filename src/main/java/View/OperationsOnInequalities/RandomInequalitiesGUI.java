package view.OperationsOnInequalities;

import controller.RandomInequalitiesController;
import javax.swing.*;
import java.awt.*;


public class RandomInequalitiesGUI extends JPanel {
    private JTextField randomNumberInequality;

    public RandomInequalitiesGUI(){

        this.setLayout(new GridLayout(2,1));

        this.randomNumberInequality = new JTextField("Number of Nodes...", 20);
        this.randomNumberInequality.setName("randomNumberInequality");

        JPanel panelrandomNumberInequality = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelrandomNumberInequality.add(randomNumberInequality);
        panelrandomNumberInequality.setBackground(Color.white);

        add(panelrandomNumberInequality);
        setBackground(Color.white);

    }

    public void addControllers(RandomInequalitiesController controller){
        System.out.println("controller added");
        randomNumberInequality.addActionListener(controller);
    }

    public void addMouseListener(RandomInequalitiesController controller){
        System.out.println("mouselistener added");
        randomNumberInequality.addMouseListener(controller);
    }









}
