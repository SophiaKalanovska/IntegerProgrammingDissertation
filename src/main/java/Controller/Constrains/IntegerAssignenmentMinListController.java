package Controller.Constrains;

import Controller.Listeners.GraphController;
import Model.SCC.BoundsListRender;
import Model.SCC.ConstrainsLists.IntegerAssignmentList;
import View.SolutionPanel.IntegerAssignmentMinimizeGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * This class will represent the Controller for the ManualIntegerInequalities Panel
 *
 */
public class IntegerAssignenmentMinListController implements ActionListener{

    private IntegerAssignmentMinimizeGUI IntegerAssignmentMinimizeGUI;
    private IntegerAssignmentList lbl;


    public IntegerAssignenmentMinListController(IntegerAssignmentMinimizeGUI IntegerAssignmentMinimizeGUI){
        this.IntegerAssignmentMinimizeGUI = IntegerAssignmentMinimizeGUI;
    }


    public void populate(GraphController graph){
        lbl = new IntegerAssignmentList(graph.getSCCComponents());
        lbl.addObserver(IntegerAssignmentMinimizeGUI);
        Map<Integer, ImageIcon> map = lbl.populate();
        BoundsListRender render = new BoundsListRender();
        render.setImageMap(map);
        IntegerAssignmentMinimizeGUI.setRender(render);
        lbl.tryUpdate();
    }



    public void populate(){
        lbl = new IntegerAssignmentList();
        lbl.addObserver(IntegerAssignmentMinimizeGUI);
        Map<Integer, ImageIcon> map = lbl.populate();
        BoundsListRender render = new BoundsListRender();
        render.setImageMap(map);
        IntegerAssignmentMinimizeGUI.setRender(render);
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


