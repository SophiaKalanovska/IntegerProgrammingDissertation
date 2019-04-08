package View.SolutionPanel;

import Model.SCC.BoundsListRender;
import Model.SCC.CreateImageMap;
import Model.SCC.SCCCluster;
import javafx.util.Pair;
import org.graphstream.graph.Node;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class IntegerAssignmentMaximizeGUI extends JPanel implements Observer {
    private CreateImageMap observer;
    private DefaultListModel internelConstainsClusterListModel;
    private JList integerAssignmentList;

    /**
     * This method creates a list that contains the Lambda Plus
     * corresponding to the decision valiables
     */
    public IntegerAssignmentMaximizeGUI(){
        this.internelConstainsClusterListModel = new DefaultListModel();
        this.integerAssignmentList = new JList(internelConstainsClusterListModel);
        JScrollPane scroll = new JScrollPane(integerAssignmentList);
        scroll.setBorder( BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(153, 218, 250)));
        this.setLayout(new GridLayout(1,1));
        this.add(scroll);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(225, 150));

    }

    /**
     * This method loops through each pair of Pair<Integer,SCCCluster>,
     * which contains the ID of the cluster and the cluster
     * The elements label is obtained and the Lambda Plus Value is assigned.
     * If the list is empty "No Solution or No IP Model Presented" is printed
     */
    private void UpdateJList(ArrayList<Pair<Integer,SCCCluster>> in){
        internelConstainsClusterListModel.clear();
        if(in.size() != 0){
            for(Pair<Integer,SCCCluster> i : in){
                String assignmentOfIntegerValues = "";
                for (Node node: i.getValue().getNodes()){
                    assignmentOfIntegerValues += " " + node.getAttribute("ui.label") + " = ";
                    if (i.getValue().getLambdaPlus() == Double.POSITIVE_INFINITY){
                        assignmentOfIntegerValues += i.getValue().getLambdaPlus() + ";";
                    }else{
                        assignmentOfIntegerValues += (int) i.getValue().getLambdaPlus() + ";";
                    }

                }
                internelConstainsClusterListModel.addElement(new Pair(i.getKey(),assignmentOfIntegerValues));
            }
            integerAssignmentList.setModel(internelConstainsClusterListModel);
        }else{
            internelConstainsClusterListModel.addElement(new Pair(0,"No Solution or No IP Model Presented"));
            integerAssignmentList.setModel(internelConstainsClusterListModel);
        }

    }

    /**
     * This method calls changeView in all JFrames and
     * changes the color scheme to dark mode and back
     */
    public void changeView(boolean dark){
        if (dark){
            integerAssignmentList.setBackground(Color.BLACK);
            integerAssignmentList.setForeground(Color.WHITE);
        }else{
            integerAssignmentList.setBackground(Color.WHITE);
            integerAssignmentList.setForeground(Color.BLACK);
        }
    }

    /**
     * This method overrides the update method and calls
     * UpdateJList with the appropriate value
     */
    @Override
    public void update(Observable obs, Object obj) {
        observer = (CreateImageMap) obs;
        UpdateJList(observer.getProjectWalletCluster());
        repaint();
        revalidate();
    }


    /**
     * This method overrides the default render
     */
    public void setRender(BoundsListRender lbr){
        integerAssignmentList.setCellRenderer(lbr);
    }


}
