package View.SolutionPanel;

import Controller.Constrains.LowerBoundClusterListController;
import Model.SCC.SCCClusterList;

import javax.swing.*;
import java.awt.*;

public class IntegerAssignmentGUI extends JPanel  {

    private SCCClusterList info;
    private SCCClusterList observer;
    private DefaultListModel intgerAssignementListModel;
    private JList intgerAssignementList;


    public IntegerAssignmentGUI(){
        this.intgerAssignementListModel = new DefaultListModel();
        this.intgerAssignementList = new JList(intgerAssignementListModel);
        this.setLayout(new GridLayout(1,1));
        this.add(intgerAssignementList);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(450, 150));
    }


    public void addMouseListener(LowerBoundClusterListController controller){
        System.out.println(" ManualInequalitiesGUI mouselistener added");
        intgerAssignementList.addMouseListener(controller);

    }

    public void changeView(boolean dark){
        if (dark){
            intgerAssignementList.setBackground(Color.BLACK);
            intgerAssignementList.setForeground(Color.WHITE);
        }else{
            intgerAssignementList.setBackground(Color.WHITE);
            intgerAssignementList.setForeground(Color.BLACK);
        }
    }


//    private void UpdateJList(ArrayList<SCCCluster> in){
//        intgerAssignementListModel.clear();
//        for(SCCCluster i : in){
//            intgerAssignementListModel.addElement(i);
//        }
//        intgerAssignementList.setModel(intgerAssignementListModel);
//    }
//
//
//    @Override
//    public void update(Observable obs, Object obj) {
//        observer = (SCCClusterList) obs;
//        UpdateJList(observer.getProjectWallet());
//        repaint();
//        revalidate();
//    }


}

