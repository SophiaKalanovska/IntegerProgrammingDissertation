package View.SolutionPanel;

import javafx.util.Pair;
import Model.SCC.BoundsListRender;
import Model.SCC.SCCClusterList;
import Model.SCC.ConstrainsLists.InternalConstarinsList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class InternalConstarinsClusterGUI extends JPanel implements Observer {

    private InternalConstarinsList observer;
    private DefaultListModel internelConstainsClusterListModel;
    private JList internelConstainsClusterList;

    public InternalConstarinsClusterGUI(){
        this.internelConstainsClusterListModel = new DefaultListModel();
        this.internelConstainsClusterList = new JList(internelConstainsClusterListModel);
        JScrollPane scroll = new JScrollPane(internelConstainsClusterList);
        scroll.setBorder( BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(153, 218, 250)));
        this.setLayout(new GridLayout(1,1));
        this.add(scroll);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(225, 150));

    }

    private void UpdateJList(ArrayList<Pair<Integer,Double>> in){
        internelConstainsClusterListModel.clear();
        for(Pair<Integer,Double> i : in){
            internelConstainsClusterListModel.addElement(i);
        }
        internelConstainsClusterList.setModel(internelConstainsClusterListModel);
    }

    @Override
    public void update(Observable obs, Object obj) {
        observer = (InternalConstarinsList) obs;
        UpdateJList(observer.getProjectWallet());
        repaint();
        revalidate();
    }

    public void setRender(BoundsListRender lbr){
        internelConstainsClusterList.setCellRenderer(lbr);
    }


    public void changeView(boolean dark){
        if (dark){
            internelConstainsClusterList.setBackground(Color.BLACK);
            internelConstainsClusterList.setForeground(Color.WHITE);
        }else{
            internelConstainsClusterList.setBackground(Color.WHITE);
            internelConstainsClusterList.setForeground(Color.BLACK);
        }
    }

}
