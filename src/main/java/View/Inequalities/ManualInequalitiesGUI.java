package View.Inequalities;

import Controller.Listeners.ManualInequalitiesController;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Home is the class where the Home panel is built implements Observer
 */
public class ManualInequalitiesGUI extends JPanel {

    private JTextField enterInequality;
    private JLabel enterInequalityLabel;

    /**
     * A JTextField is created, which
     * allows the user to import a new inequality into the IP model
     */
    public ManualInequalitiesGUI() {

        //create the textField for inputting the inequalities
        this.enterInequalityLabel = new JLabel("Enter Inequality of the form:");
        Border border = BorderFactory.createLineBorder( new Color(153, 218, 250),1,  true);
        this.enterInequality = new JTextField("Enter inequality...", 20);
        this.enterInequality.setBorder(border);
        this.enterInequality.setName("inequalityField");


        JPanel enterText = new JPanel(new FlowLayout(FlowLayout.LEFT));
        enterText.add(enterInequality);
        enterText.setOpaque(false);


        this.setLayout(new GridLayout(2,1));
        this.add(enterInequalityLabel);
        this.add(enterText);
        this.setOpaque(false);

    }

    /**
     * ManualInequalitiesController is added
     * as an ActionListener to the enterInequality field
     */
    public void addControllers(ManualInequalitiesController controller){
        enterInequality.addActionListener(controller);

    }
    /**
     * ManualInequalitiesController is added
     * as a MouseListener to the enterInequality field
     */
    public void addMouseListener(ManualInequalitiesController controller){
        enterInequality.addMouseListener(controller);
    }

    /**
     * Changes the color scheme to dark mode and back
     */
    public void changeView(boolean dark) {
        if (dark){
            enterInequalityLabel.setForeground(Color.WHITE);
            enterInequality.setForeground(Color.WHITE);
            enterInequality.setBackground(Color.BLACK);

        }else{
            enterInequalityLabel.setForeground(Color.BLACK);
            enterInequality.setForeground(Color.BLACK);
            enterInequality.setBackground(Color.WHITE);
        }
    }
}
