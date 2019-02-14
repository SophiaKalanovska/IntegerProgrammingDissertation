package view.OperationsOnInequalities;

import controller.InequalitiesListController;
import model.Inequalities.Inequality;
import model.Inequalities.InequalitiesList;
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
    private JButton evaluateAll;
    private DefaultListModel listModel;
    private JList projectsList;

    /**
     * Constructs a new Home panel
     *
     */
    public InequalitiesListGUI() {

        this.listModel = new DefaultListModel();
        this.projectsList = new JList(listModel);
        JScrollPane scroll = new JScrollPane(projectsList);
        scroll.setBorder( BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(153, 218, 250)));
        delete = new JButton("Delete Inequality");
        delete.setName("delete");
        evaluateAll = new JButton("Evaluate Inequality");
        evaluateAll.setName("evaluate");
        projectsList.setName("projectsList");
        deleteAll = new JButton("Delete All Inequalities");
        deleteAll.setName("deleteAll");


        JPanel operationsSingle = new JPanel(new GridLayout(1,2));
        operationsSingle.add(scroll);
        operationsSingle.add(delete);
        operationsSingle.setBackground(Color.WHITE);

        JPanel operations = new JPanel(new GridLayout(1,2));
        operations.add(deleteAll);
        operations.add(evaluateAll);
        operations.setBackground(Color.WHITE);

        this.setLayout(new BorderLayout());
        this.add(operationsSingle, BorderLayout.CENTER);
        this.add(operations, BorderLayout.SOUTH);
//        this.add(delete, BorderLayout.EAST);


        this.setBackground(Color.WHITE);
    }

    public void addMouseListener(InequalitiesListController controller){
        System.out.println(" ManualInequalitiesGUI mouselistener added");
        delete.addMouseListener(controller);
        evaluateAll.addMouseListener(controller);
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
