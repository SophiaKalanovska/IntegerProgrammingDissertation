package View.SolutionPanel;

import Model.SCC.CreateImageMap;
import Model.SCC.SCCCluster;
import javafx.util.Pair;
import Model.SCC.BoundsListRender;
import org.graphstream.graph.Node;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class IntegerAssignmentMinimizeGUI extends JPanel implements Observer {

    private CreateImageMap observer;
    private DefaultListModel internelConstainsClusterListModel;
    private JList integerAssignmentList;

    public IntegerAssignmentMinimizeGUI(){
        this.internelConstainsClusterListModel = new DefaultListModel();
        this.integerAssignmentList = new JList(internelConstainsClusterListModel);
        JScrollPane scroll = new JScrollPane(integerAssignmentList);
        scroll.setBorder( BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(153, 218, 250)));
        this.setLayout(new GridLayout(1,1));
        this.add(scroll);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(225, 150));

    }



    private void UpdateJList(ArrayList<Pair<Integer,SCCCluster>> in) {
        internelConstainsClusterListModel.clear();
        if (in.size() != 0) {
            for (Pair<Integer, SCCCluster> i : in) {
                String assignmentOfIntegerValues = "";
                for (Node node : i.getValue().getNodes()) {
                    assignmentOfIntegerValues += " " + node.getAttribute("ui.label") + " = ";
                    assignmentOfIntegerValues += i.getValue().getLambdaMinus() + ";";
                }
                internelConstainsClusterListModel.addElement(new Pair(i.getKey(), assignmentOfIntegerValues));
            }
            integerAssignmentList.setModel(internelConstainsClusterListModel);
        } else {
            internelConstainsClusterListModel.addElement(new Pair(0, "No Solution or No IP Model Presented"));
            integerAssignmentList.setModel(internelConstainsClusterListModel);
        }
    }


    public void changeView(boolean dark){
        if (dark){
            integerAssignmentList.setBackground(Color.BLACK);
            integerAssignmentList.setForeground(Color.WHITE);
        }else{
            integerAssignmentList.setBackground(Color.WHITE);
            integerAssignmentList.setForeground(Color.BLACK);
        }
    }

    @Override
    public void update(Observable obs, Object obj) {
        observer = (CreateImageMap) obs;
        UpdateJList(observer.getProjectWalletCluster());
        repaint();
        revalidate();
    }

    public void setRender(BoundsListRender lbr){
        integerAssignmentList.setCellRenderer(lbr);
    }
}
