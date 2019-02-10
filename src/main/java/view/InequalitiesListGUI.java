package view;

import controller.InequalitiesListController;
import model.Inequality;
import model.InequalitiesList;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.*;
import java.util.Observer;

/**
 * Home is the class where the Home panel is built implements Observer
 */
public class InequalitiesListGUI extends JPanel implements Observer {

    private InequalitiesList info;
    private InequalitiesList observer;
    private JButton delete;
    private JButton deleteAll;

    private DefaultListModel listModel;
    private JList projectsList;

    /**
     * Constructs a new Home panel
     *
     */
    public InequalitiesListGUI() {

        this.listModel = new DefaultListModel();
        this.projectsList = new JList(listModel);
        delete = new JButton("Delete Inequality");
        delete.setName("delete");
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().setView(projectsList);
        projectsList.setName("projectsList");
        deleteAll = new JButton("Delete All Inequalities");
        deleteAll.setName("deleteAll");

        this.setLayout(new BorderLayout());
        this.add(projectsList, BorderLayout.CENTER);
        this.add(deleteAll, BorderLayout.SOUTH);
        this.add(delete, BorderLayout.EAST);


        this.setBackground(Color.WHITE);
    }





    public void addMouseListener(InequalitiesListController controller){
        System.out.println(" ManualInequalitiesGUI mouselistener added");
        delete.addMouseListener(controller);
        projectsList.addMouseListener(controller);
        deleteAll.addMouseListener(controller);
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
        repaint();
        revalidate();
    }




}
