package Controller.Constrains;

import Controller.Listeners.GraphController;
import Model.SCC.BoundsListRender;
import Model.SCC.ConstrainsLists.UpperBoundList;
import View.SolutionPanel.UpperBoundClusterGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * This class will represent the Controller for the ManualIntegerInequalities Panel
 *
 */
public class UpperBoundClusterListController implements ActionListener {

    private UpperBoundClusterGUI UpperBoundClusterGUI;
    private UpperBoundList lbl;

    /**
     * Constructs a Controller for the ManualIntegerInequalities panel
     *
     * @param UpperBoundClusterGUI the ManualIntegerInequalities  JFrame that this class will control
     */
    public UpperBoundClusterListController(UpperBoundClusterGUI UpperBoundClusterGUI){
        this.UpperBoundClusterGUI = UpperBoundClusterGUI;
    }


    public void populate(GraphController graph){
        lbl = new UpperBoundList(graph.getSCCComponents());
        lbl.addObserver(UpperBoundClusterGUI);
        Map<Integer, ImageIcon> map = lbl.populate();
        BoundsListRender render = new BoundsListRender();
        render.setImageMap(map);
        UpperBoundClusterGUI.setRender(render);
        lbl.tryUpdate();

    }

    public void populate(){
        lbl = new UpperBoundList();
        lbl.addObserver(UpperBoundClusterGUI);
        Map<Integer, ImageIcon> map = lbl.populate();
        BoundsListRender render = new BoundsListRender();
        render.setImageMap(map);
        UpperBoundClusterGUI.setRender(render);
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


