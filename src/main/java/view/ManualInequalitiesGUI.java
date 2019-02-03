package view;

import controller.ManualInequalitiesController;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


/**
 * Home is the class where the Home panel is built implements Observer
 */
public class ManualInequalitiesGUI extends JPanel {

    private JTextField enterInequality;

    /**
     * Constructs a new Home panel
     *
     */
    public ManualInequalitiesGUI() {

        //create the textField for inputting the inequalities
        this.enterInequality = new JTextField("Enter inequality...", 20);
        this.enterInequality.setName("inequalityField");

        this.setBackground(Color.WHITE);
        this.setLayout( new FlowLayout(FlowLayout.LEFT));
        this.add(enterInequality);

    }


    public void addControllers(ManualInequalitiesController controller){
        System.out.println(" ManualInequalitiesGUI controller added");
        enterInequality.addActionListener(controller);

    }

    public void addMouseListener(ManualInequalitiesController controller){
        System.out.println(" ManualInequalitiesGUI mouselistener added");
        enterInequality.addMouseListener(controller);
    }

//    public Inequalitiy getListSelectedValue(){
//        return (Inequalities) projectsList.getSelectedValue();
//    }

    public String getEnterInequality() {
        return enterInequality.getText();
    }

}
