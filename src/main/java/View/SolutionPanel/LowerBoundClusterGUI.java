package View.SolutionPanel;

import javafx.util.Pair;
import Model.SCC.ConstrainsLists.LowerBoundList;
import Model.SCC.BoundsListRender;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class LowerBoundClusterGUI extends JPanel implements Observer {

    private LowerBoundList observer;
    private DefaultListModel lowerBoundClusterListModel;
    private JList lowerBoundClusterList;


    public LowerBoundClusterGUI(){
        this.lowerBoundClusterListModel = new DefaultListModel();
        this.lowerBoundClusterList = new JList(lowerBoundClusterListModel);
        JScrollPane scroll = new JScrollPane(lowerBoundClusterList);
        scroll.setBorder( BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(153, 218, 250)));
        this.setLayout(new GridLayout(1,1));
        this.add(scroll);
        this.setOpaque(false);

        this.setPreferredSize(new Dimension(225, 150));

//
//
//        scroll.setPreferredSize(new Dimension(300, 400));
    }



    private void UpdateJList(ArrayList<Pair<Integer,Double>> in) {
        lowerBoundClusterListModel.clear();
        if (in.size() != 0) {
            for (Pair<Integer, Double> i : in) {
                lowerBoundClusterListModel.addElement(i);
            }
            lowerBoundClusterList.setModel(lowerBoundClusterListModel);
        } else {
            lowerBoundClusterListModel.addElement(new Pair(0, "No IP Model Presented"));
            lowerBoundClusterList.setModel(lowerBoundClusterListModel);
        }
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


    public void changeView(boolean dark){
        if (dark){
            lowerBoundClusterList.setBackground(Color.BLACK);
            lowerBoundClusterList.setForeground(Color.WHITE);
        }else{
            lowerBoundClusterList.setBackground(Color.WHITE);
            lowerBoundClusterList.setForeground(Color.BLACK);
        }
    }
}

