package View.SolutionPanel;

import Controller.Constrains.LowerBoundClusterListController;
import Model.SCC.ConstrainsLists.IntegerAssignmentList;
import Model.SCC.SolutionListRender;
import javafx.util.Pair;
import Model.SCC.BoundsListRender;
import Model.SCC.SCCClusterList;
import Model.SCC.ConstrainsLists.InternalConstarinsList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class IntegerAssignmentGUI extends JPanel implements Observer {

    private SCCClusterList info;
    private IntegerAssignmentList observer;
    private DefaultListModel internelConstainsClusterListModel;
    private JList integerAssignmentList;

    public IntegerAssignmentGUI(){
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

    private void UpdateJList(ArrayList<Pair<Integer,Integer>> in){
        internelConstainsClusterListModel.clear();
        for(Pair<Integer,Integer> i : in){
            internelConstainsClusterListModel.addElement(i);
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

    public void setRender(SolutionListRender lbr){
        integerAssignmentList.setCellRenderer(lbr);
    }
}
