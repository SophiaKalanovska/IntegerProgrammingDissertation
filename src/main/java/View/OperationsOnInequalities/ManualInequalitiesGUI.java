package View.OperationsOnInequalities;

import Controller.ManualInequalitiesController;

import java.awt.*;
import javax.swing.*;

/**
 * Home is the class where the Home panel is built implements Observer
 */
public class ManualInequalitiesGUI extends JPanel {

    private JTextField enterInequality;
    private JLabel enterInequalityLabel;

    /**
     * Constructs a new Home panel
     *
     */
    public ManualInequalitiesGUI() {

        //create the textField for inputting the inequalities
        this.enterInequalityLabel = new JLabel("Enter Inequality of the form:");
        this.enterInequality = new JTextField("Enter inequality...", 20);
        this.enterInequality.setName("inequalityField");


        JPanel enterlabel = new JPanel(new GridLayout(1,1));
        enterlabel.add(enterInequalityLabel);
        enterlabel.setOpaque(false);

        JPanel enterText = new JPanel(new FlowLayout(FlowLayout.LEFT));
        enterText.add(enterInequality);
        enterText.setOpaque(false);


        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(enterlabel);
        this.add(enterText);
        this.setOpaque(false);

    }

    public void addControllers(ManualInequalitiesController controller){
        System.out.println(" ManualInequalitiesGUI Controller added");
        enterInequality.addActionListener(controller);

    }

    public void addMouseListener(ManualInequalitiesController controller){
        System.out.println(" ManualInequalitiesGUI mouselistener added");
        enterInequality.addMouseListener(controller);
    }
}
