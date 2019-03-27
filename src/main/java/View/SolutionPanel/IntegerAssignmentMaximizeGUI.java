package View.SolutionPanel;

import Controller.Constrains.LowerBoundClusterListController;
import Model.SCC.BoundsListRender;
import Model.SCC.ConstrainsLists.IntegerAssignmentList;
import Model.SCC.SCCCluster;
import Model.SCC.SCCClusterList;
import javafx.util.Pair;
import org.graphstream.graph.Node;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class IntegerAssignmentMaximizeGUI extends JPanel implements Observer {

    private SCCClusterList info;
    private IntegerAssignmentList observer;
    private DefaultListModel internelConstainsClusterListModel;
    private JList integerAssignmentList;

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

    public void addMouseListener(LowerBoundClusterListController controller){
        System.out.println(" ManualInequalitiesGUI mouselistener added");
        integerAssignmentList.addMouseListener(controller);
    }

    private void UpdateJList(ArrayList<Pair<Integer,SCCCluster>> in){
        internelConstainsClusterListModel.clear();
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
        observer = (IntegerAssignmentList) obs;
        UpdateJList(observer.getProjectWallet());
        repaint();
        revalidate();
    }

    public void setRender(BoundsListRender lbr){
        integerAssignmentList.setCellRenderer(lbr);
    }
}
