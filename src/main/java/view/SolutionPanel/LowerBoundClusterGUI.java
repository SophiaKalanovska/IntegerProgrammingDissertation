package view.SolutionPanel;

import controller.LowerBoundClusterListController;
import javafx.util.Pair;
import model.SCC.LowerBoundList;
import model.SCC.BoundsListRender;
import model.SCC.SCCClusterList;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class LowerBoundClusterGUI extends JPanel implements Observer {

    private SCCClusterList info;
    private LowerBoundList observer;
    private DefaultListModel lowerBoundClusterListModel;
    private JList lowerBoundClusterList;


    public LowerBoundClusterGUI(){
        this.lowerBoundClusterListModel = new DefaultListModel();
        this.lowerBoundClusterList = new JList(lowerBoundClusterListModel);
        this.setLayout(new GridLayout(1,1));
        this.add(lowerBoundClusterList);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(225, 150));
    }


    public void addMouseListener(LowerBoundClusterListController controller){
        System.out.println(" ManualInequalitiesGUI mouselistener added");
        lowerBoundClusterList.addMouseListener(controller);

    }

    private void UpdateJList(ArrayList<Pair<Integer,Double>> in){
        lowerBoundClusterListModel.clear();
        for(Pair<Integer,Double> i : in){
            lowerBoundClusterListModel.addElement(i);
        }
        lowerBoundClusterList.setModel(lowerBoundClusterListModel);
    }


    @Override
    public void update(Observable obs, Object obj) {
        observer = (LowerBoundList) obs;
        UpdateJList(observer.getProjectWallet());
        repaint();
        revalidate();
    }

    public void setRender(BoundsListRender lbr){
        lowerBoundClusterList.setCellRenderer(lbr);
    }


}

