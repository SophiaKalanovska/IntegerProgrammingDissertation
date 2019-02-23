package View.SolutionPanel;

import Controller.Constrains.LowerBoundClusterListController;
import javafx.util.Pair;
import Model.SCC.*;
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
        JScrollPane scroll = new JScrollPane(upperBoundClusterList);
        scroll.setBorder( BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(153, 218, 250)));
        this.setLayout(new GridLayout(1,1));
        this.add(scroll);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(225, 150));

    }

    public void addMouseListener(LowerBoundClusterListController controller){
        System.out.println(" ManualInequalitiesGUI mouselistener added");
        upperBoundClusterList.addMouseListener(controller);
    }

    private void UpdateJList(ArrayList<Pair<Integer,Double>> in){
        upperBoundClusterListModel.clear();
        for(Pair<Integer,Double> i : in){
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

    public void setRender(BoundsListRender lbr){
        upperBoundClusterList.setCellRenderer(lbr);
    }
}
