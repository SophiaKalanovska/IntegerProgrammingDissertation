package view;

import controller.InequalitiesListController;
import model.Inequality;
import model.InequalitiesList;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.util.Observer;



/**
 * Home is the class where the Home panel is built implements Observer
 */
public class InequalitiesListGUI extends JPanel implements Observer {

    private InequalitiesList info;
    private InequalitiesList observer;
    private JButton delete;

    private DefaultListModel listModel;
    private JList projectsList;

    /**
     * Constructs a new Home panel
     *
     */
    public InequalitiesListGUI() {

        //create the list of the represented inequalities
        this.listModel = new DefaultListModel();
        this.projectsList = new JList(listModel);
        delete = new JButton("Delete Inequality");
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().setView(projectsList);
        projectsList.setName("projectsList");


        //border color

        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(1, 2));
        this.add(projectsList);
        this.add(delete);

    }



    public void addMouseListener(InequalitiesListController controller){
        System.out.println(" ManualInequalitiesGUI mouselistener added");
        delete.addMouseListener(controller);
        projectsList.addMouseListener(controller);
    }

    public Inequality getListSelectedValue(){
        return (Inequality) projectsList.getSelectedValue();
    }

    private void UpdateJList(ArrayList<Inequality> in){
        listModel.clear();
        for(Inequality i : in){
            listModel.addElement(i);
        }
        projectsList.setModel(listModel);
    }


    @Override
    public void update(Observable obs, Object obj) {
        observer = (InequalitiesList) obs;
        UpdateJList(observer.getProjectWallet());
        //this.clearProjectNameField();

        repaint();
        revalidate();
    }




}
