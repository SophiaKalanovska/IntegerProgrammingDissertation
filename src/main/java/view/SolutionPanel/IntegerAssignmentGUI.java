package view.SolutionPanel;

import controller.LowerBoundClusterListController;
import model.SCCCluster;
import model.SCCClusterList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class IntegerAssignmentGUI extends JPanel implements Observer {

    private SCCClusterList info;
    private SCCClusterList observer;
    private DefaultListModel intgerAssignementListModel;
    private JList intgerAssignementList;


    public IntegerAssignmentGUI(){
        this.intgerAssignementListModel = new DefaultListModel();
        this.intgerAssignementList = new JList(intgerAssignementListModel);
        this.setLayout(new GridLayout(1,1));
        this.add(intgerAssignementList);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(450, 150));
    }


    public void addMouseListener(LowerBoundClusterListController controller){
        System.out.println(" ManualInequalitiesGUI mouselistener added");
        intgerAssignementList.addMouseListener(controller);

    }

    private void UpdateJList(ArrayList<SCCCluster> in){
        intgerAssignementListModel.clear();
        for(SCCCluster i : in){
            intgerAssignementListModel.addElement(i);
        }
        intgerAssignementList.setModel(intgerAssignementListModel);
    }


    @Override
    public void update(Observable obs, Object obj) {
        observer = (SCCClusterList) obs;
        UpdateJList(observer.getProjectWallet());
        repaint();
        revalidate();
    }


}

