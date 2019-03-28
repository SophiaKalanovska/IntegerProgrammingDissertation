package Controller.Constrains;

import Controller.GraphController;
import Model.SCC.BoundsListRender;
import Model.SCC.ConstrainsLists.IntegerAssignmentList;
import Model.SCC.SCCClusterList;
import View.SolutionPanel.IntegerAssignmentMaximizeGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

/**
 * This class will represent the Controller for the ManualIntegerInequalities Panel
 *
 */
public class IntegerAssignenmentMaxListController implements ActionListener {

    private IntegerAssignmentMaximizeGUI IntegerAssignmentMaximizeGUI ;
    private IntegerAssignmentList lbl = new IntegerAssignmentList(new SCCClusterList());

    public IntegerAssignenmentMaxListController(IntegerAssignmentMaximizeGUI IntegerAssignmentMaximizeGUI){
        this.IntegerAssignmentMaximizeGUI = IntegerAssignmentMaximizeGUI;

    }

    public void populate(GraphController graph){
        lbl = new IntegerAssignmentList(graph.getSCCComponents());
        lbl.addObserver(IntegerAssignmentMaximizeGUI);
        Map<Integer, ImageIcon> map = lbl.populate();
        BoundsListRender render = new BoundsListRender();
        render.setImageMap(map);
        IntegerAssignmentMaximizeGUI.setRender(render);
        lbl.tryUpdate();
    }

    public void populate(){
        lbl.addObserver(IntegerAssignmentMaximizeGUI);
//        Map<Integer, ImageIcon> map = lbl.populate();
//        BoundsListRender render = new BoundsListRender();
//        render.setImageMap(map);
//        IntegerAssignmentMaximizeGUI.setRender(render);
        lbl.tryUpdate();
    }

    /**
     * The action listener for the ManualIntegerInequalities panel
     *
     * @param e the ActionEven object which will identify the performed action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        }
}


