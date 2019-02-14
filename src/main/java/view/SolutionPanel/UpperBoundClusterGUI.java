package view.SolutionPanel;

import controller.LowerBoundClusterListController;
import model.SCC.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class UpperBoundClusterGUI extends JPanel implements Observer {

    private SCCClusterList info;
    private UpperBoundList observer;
    private DefaultListModel upperBoundClusterListModel;
    private JList upperBoundClusterList;

    public UpperBoundClusterGUI(){
        this.upperBoundClusterListModel = new DefaultListModel();
        this.upperBoundClusterList = new JList(upperBoundClusterListModel);
        this.setLayout(new GridLayout(1,1));
        this.add(upperBoundClusterList);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(225, 150));
    }

    public void addMouseListener(LowerBoundClusterListController controller){
        System.out.println(" ManualInequalitiesGUI mouselistener added");
        upperBoundClusterList.addMouseListener(controller);
    }

    private void UpdateJList(ArrayList<Double> in){
        upperBoundClusterListModel.clear();
        for(Double i : in){
            upperBoundClusterListModel.addElement(i);
        }
        upperBoundClusterList.setModel(upperBoundClusterListModel);
    }


    @Override
    public void update(Observable obs, Object obj) {
        observer = (UpperBoundList) obs;
        UpdateJList(observer.getProjectWallet());
        repaint();
        revalidate();
    }

    public void setRender(LowerBoundListRender lbr){
        upperBoundClusterList.setCellRenderer(lbr);
    }

}
