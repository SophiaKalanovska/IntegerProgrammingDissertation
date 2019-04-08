package View.SolutionPanel;

import Model.SCC.CreateImageMap;
import javafx.util.Pair;
import Model.SCC.BoundsListRender;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class LowerBoundClusterGUI extends JPanel implements Observer {

    private CreateImageMap observer;
    private DefaultListModel lowerBoundClusterListModel;
    private JList lowerBoundClusterList;


    /**
     * This method creates a list that contains the lower Bound
     * corresponding to the cluster ID
     */
    public LowerBoundClusterGUI(){
        this.lowerBoundClusterListModel = new DefaultListModel();
        this.lowerBoundClusterList = new JList(lowerBoundClusterListModel);
        JScrollPane scroll = new JScrollPane(lowerBoundClusterList);
        scroll.setBorder( BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(153, 218, 250)));
        this.setLayout(new GridLayout(1,1));
        this.add(scroll);
        this.setOpaque(false);

        this.setPreferredSize(new Dimension(225, 150));
    }


    /**
     * This method loops through each pair of Pair<Integer,double>,
     * It adds the lower bound that is corresponding to the cluster ID
     */
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

    /**
     * This method overrides the update method and calls
     * UpdateJList with the list of lower bounds to cluster ID
     */
    @Override
    public void update(Observable obs, Object obj) {
        observer = (CreateImageMap) obs;
        UpdateJList(observer.getLowerBoundContainer());
        repaint();
        revalidate();
    }

    /**
     * This method overrides the default render
     */
    public void setRender(BoundsListRender lbr){
        lowerBoundClusterList.setCellRenderer(lbr);
    }


    /**
     * This method calls changeView in all JFrames and
     * changes the color scheme to dark mode and back
     */
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

