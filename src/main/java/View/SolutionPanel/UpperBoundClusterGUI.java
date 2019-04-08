package View.SolutionPanel;

import javafx.util.Pair;
import Model.SCC.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class UpperBoundClusterGUI extends JPanel implements Observer {

    private CreateImageMap observer;
    private DefaultListModel upperBoundClusterListModel;
    private JList upperBoundClusterList;

    /**
     * This method creates a list that contains the upper Bound
     * corresponding to the cluster ID
     */
    public UpperBoundClusterGUI(){
        this.upperBoundClusterListModel = new DefaultListModel();
        this.upperBoundClusterList = new JList(upperBoundClusterListModel);
        JScrollPane scroll = new JScrollPane(upperBoundClusterList);
        scroll.setBorder( BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(153, 218, 250)));
        this.setLayout(new GridLayout(1,1));
        this.add(scroll);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(225, 150));

    }

    /**
     * This method loops through each pair of Pair<Integer,double>,
     * It adds the upper bound that is corresponding to the cluster ID
     */
    private void UpdateJList(ArrayList<Pair<Integer,Double>> in) {
        upperBoundClusterListModel.clear();
        if (in.size() != 0) {
            for (Pair<Integer, Double> i : in) {
                upperBoundClusterListModel.addElement(i);
            }
            upperBoundClusterList.setModel(upperBoundClusterListModel);
        } else {
            upperBoundClusterListModel.addElement(new Pair(0, "No IP Model Presented"));
            upperBoundClusterList.setModel(upperBoundClusterListModel);
        }
    }

    /**
     * This method overrides the update method and calls
     * UpdateJList with the list of upper bounds to cluster ID
     */
    @Override
    public void update(Observable obs, Object obj) {
        observer = (CreateImageMap) obs;
        UpdateJList(observer.getUpperBoundContainer());
        repaint();
        revalidate();
    }

    /**
     * This method overrides the default render
     */
    public void setRender(BoundsListRender lbr){
        upperBoundClusterList.setCellRenderer(lbr);
    }

    /**
     * This method calls changeView in all JFrames and
     * changes the color scheme to dark mode and back
     */
    public void changeView(boolean dark){
        if (dark){
            upperBoundClusterList.setBackground(Color.BLACK);
            upperBoundClusterList.setForeground(Color.WHITE);
        }else{
            upperBoundClusterList.setBackground(Color.WHITE);
            upperBoundClusterList.setForeground(Color.BLACK);
        }
    }
}
