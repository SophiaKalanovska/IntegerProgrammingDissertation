package view;

import controller.LowerBoundClusterListController;
import model.SCCCluster;
import model.SCCClusterList;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class UpperBoundClusterGUI extends JPanel implements Observer {

    private SCCClusterList info;
    private SCCClusterList observer;
    private DefaultListModel upperBoundClusterListModel;
    private JList upperBoundClusterList;

    public UpperBoundClusterGUI(){
        this.upperBoundClusterListModel = new DefaultListModel();
        this.upperBoundClusterList = new JList(upperBoundClusterListModel);
    }

    public void addMouseListener(LowerBoundClusterListController controller){
        System.out.println(" ManualInequalitiesGUI mouselistener added");
        upperBoundClusterList.addMouseListener(controller);
    }

    private void UpdateJList(ArrayList<SCCCluster> in){
        upperBoundClusterListModel.clear();
        for(SCCCluster i : in){
            upperBoundClusterListModel.addElement(i);
        }
        upperBoundClusterList.setModel(upperBoundClusterListModel);
    }

    @Override
    public void update(Observable obs, Object obj) {
        observer = (SCCClusterList) obs;
        UpdateJList(observer.getProjectWallet());
        repaint();
        revalidate();
    }
}
