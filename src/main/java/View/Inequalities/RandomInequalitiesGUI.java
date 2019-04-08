package View.Inequalities;

import Controller.Listeners.RandomInequalitiesController;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class RandomInequalitiesGUI extends JPanel {
    private JLabel randomNumberDecisionVariableLabel;
    private JTextField randomNumberDecisionVariables;
    private JLabel randomNumberInequalitiesLabel;
    private JTextField randomNumberInequalities;


    /**
     * Two JTextField is created, which
     * allows the user to import a the number of
     * random decision variables and inequalities they wish to create
     */
    public RandomInequalitiesGUI(){
        this.randomNumberDecisionVariableLabel = new JLabel("Enter the number of random decision variables to be generated:");
        Border border = BorderFactory.createLineBorder( new Color(153, 218, 250),1,  true);
        this.randomNumberDecisionVariables = new JTextField("Number of Decision Variables...", 20);
        this.randomNumberDecisionVariables.setBorder(border);
        this.randomNumberDecisionVariables.setName("randomNumberNodes");
        this.randomNumberInequalitiesLabel = new JLabel("Enter the number of random inequalities to be generated:");
        this.randomNumberInequalities = new JTextField("Number of Inequalities...", 20);
        this.randomNumberInequalities.setBorder(border);
        this.randomNumberInequalities.setName("randomNumberInequalities");

        JPanel randomNumberDecisionVariablesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        randomNumberDecisionVariablesPanel.add(randomNumberDecisionVariables);
        randomNumberDecisionVariablesPanel.setOpaque(false);

        JPanel randomNumberInequalitiesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        randomNumberInequalitiesPanel.add(randomNumberInequalities);
        randomNumberInequalitiesPanel.setOpaque(false);

        this.setLayout(new GridLayout(4,1));
        this.add(randomNumberDecisionVariableLabel);
        this.add(randomNumberDecisionVariablesPanel);
        this.add(randomNumberInequalitiesLabel);
        this.add(randomNumberInequalitiesPanel);
        this.setOpaque(false);

    }

    /**
     * ManualInequalitiesController is added
     * as an ActionListener tothe randomNumberDecisionVariables field and
     * randomNumberInequalities field
     */
    public void addControllers(RandomInequalitiesController controller){
        randomNumberDecisionVariables.addActionListener(controller);
        randomNumberInequalities.addActionListener(controller);
    }

    /**
     * RandomInequalitiesController is added
     * as a MouseListener to the randomNumberDecisionVariables field and
     * randomNumberInequalities field
     */
    public void addMouseListener(RandomInequalitiesController controller){
        randomNumberDecisionVariables.addMouseListener(controller);
        randomNumberInequalities.addMouseListener(controller);
    }



    /**
     * Changes the color scheme to dark mode and back
     */
    public void changeView(boolean dark) {
        if (dark){
            randomNumberDecisionVariableLabel.setForeground(Color.WHITE);
            randomNumberInequalitiesLabel.setForeground(Color.WHITE);
            randomNumberDecisionVariables.setForeground(Color.WHITE);
            randomNumberDecisionVariables.setBackground(Color.BLACK);
            randomNumberInequalities.setForeground(Color.WHITE);
            randomNumberInequalities.setBackground(Color.BLACK);

        }else{
            randomNumberDecisionVariableLabel.setForeground(Color.BLACK);
            randomNumberInequalitiesLabel.setForeground(Color.BLACK);
            randomNumberDecisionVariables.setForeground(Color.BLACK);
            randomNumberDecisionVariables.setBackground(Color.WHITE);
            randomNumberInequalities.setForeground(Color.BLACK);
            randomNumberInequalities.setBackground(Color.WHITE);
        }
    }





}
