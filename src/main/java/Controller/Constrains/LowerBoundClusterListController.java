package Controller.Constrains;

import Controller.Listeners.GraphController;
import Model.SCC.ConstrainsLists.LowerBoundList;
import Model.SCC.BoundsListRender;
import View.SolutionPanel.LowerBoundClusterGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * This class will represent the Controller for the ManualIntegerInequalities Panel
 *
 */
public class LowerBoundClusterListController implements ActionListener{

    private LowerBoundClusterGUI LowerBoundClusterGUI;
    private LowerBoundList lbl;

    /**
     * Constructs a Controller for the ManualIntegerInequalities panel
     *
     * @param LowerBoundClusterGUI the ManualIntegerInequalities  JFrame that this class will control
     */
    public LowerBoundClusterListController( LowerBoundClusterGUI LowerBoundClusterGUI){
        this.LowerBoundClusterGUI = LowerBoundClusterGUI;
    }


    public void populate(GraphController graph){
        lbl = new LowerBoundList(graph.getSCCComponents());
        lbl.addObserver(LowerBoundClusterGUI);
        Map<Integer, ImageIcon> map = lbl.populate();
        BoundsListRender render = new BoundsListRender();
        render.setImageMap(map);
        LowerBoundClusterGUI.setRender(render);
        lbl.tryUpdate();

    }

    public void populate(){
        lbl = new LowerBoundList();
        lbl.addObserver(LowerBoundClusterGUI);
        Map<Integer, ImageIcon> map = lbl.populate();
        BoundsListRender render = new BoundsListRender();
        render.setImageMap(map);
        LowerBoundClusterGUI.setRender(render);
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


