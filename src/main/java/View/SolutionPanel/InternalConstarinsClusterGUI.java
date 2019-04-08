package View.SolutionPanel;

import Model.SCC.CreateImageMap;
import javafx.util.Pair;
import Model.SCC.BoundsListRender;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class InternalConstarinsClusterGUI extends JPanel implements Observer {

    private CreateImageMap observer;
    private DefaultListModel internelConstainsClusterListModel;
    private JList internelConstainsClusterList;


    /**
     * This method creates a list that contains the internal Bound
     * corresponding to the cluster ID
     */
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

    /**
     * This method loops through each pair of Pair<Integer,double>,
     * It adds the internal bound that is corresponding to the cluster ID
     */
    private void UpdateJList(ArrayList<Pair<Integer,Double>> in) {
        internelConstainsClusterListModel.clear();
        if (in.size() != 0) {
            for (Pair<Integer, Double> i : in) {
                internelConstainsClusterListModel.addElement(i);
            }
            internelConstainsClusterList.setModel(internelConstainsClusterListModel);
        } else {
            internelConstainsClusterListModel.addElement(new Pair(0, "No IP Model Presented"));
            internelConstainsClusterList.setModel(internelConstainsClusterListModel);
        }
    }

    /**
     * This method overrides the update method and calls
     * UpdateJList with the list of internal bounds to cluster ID
     */
    @Override
    public void update(Observable obs, Object obj) {
        observer = (CreateImageMap) obs;
        UpdateJList(observer.getInternalBoundContainer());
        repaint();
        revalidate();
    }

    /**
     * This method overrides the default render
     */
    public void setRender(BoundsListRender lbr){
        internelConstainsClusterList.setCellRenderer(lbr);
    }


    /**
     * This method calls changeView in all JFrames and
     * changes the color scheme to dark mode and back
     */
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
