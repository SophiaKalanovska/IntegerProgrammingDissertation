package view;

import controller.ManualInequalitiesController;
//import model.Inequalities;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;




/**
 * Home is the class where the Home panel is built implements Observer
 */
public class ManualInequalities extends JPanel {

    private JTextField enterInequality;
    private DefaultListModel listModel;
    private JList projectsList;

    /**
     * Constructs a new Home panel
     *
     */
    public ManualInequalities() {

        //create the list of the represented inequalities
        this.listModel = new DefaultListModel();
        this.projectsList = new JList(listModel);
        projectsList.setName("projectsList");

        //create the textField for inputting the inequalities
        this.enterInequality = new JTextField("Enter inequality...", 20);
        this.enterInequality.setName("inequalityField");


        FlowLayout LayoutenterInequality  = new FlowLayout(FlowLayout.LEFT);
        JPanel panelenterInequality = new JPanel(LayoutenterInequality);
        panelenterInequality.add(enterInequality);
        panelenterInequality.setBackground(Color.white);


        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(2, 1));
        this.add(panelenterInequality);
        this.add(projectsList);

    }



    public void addControllers(ManualInequalitiesController controller){
        System.out.println(" ManualInequalities controller added");
        enterInequality.addActionListener(controller);

    }

    public void addMouseListener(ManualInequalitiesController  controller){
        System.out.println(" ManualInequalities mouselistener added");
        projectsList.addMouseListener(controller);
        enterInequality.addMouseListener(controller);
    }

//
//
//    private void UpdateJList(ArrayList<Inequalities> in){
//        listModel.clear();
//        for(Inequalities i : in){
//            listModel.addElement(i);
//        }
//        projectsList.setModel(listModel);
//    }




}
