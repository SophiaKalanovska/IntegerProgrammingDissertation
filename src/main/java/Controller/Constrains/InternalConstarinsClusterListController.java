package Controller.Constrains;

import Controller.Listeners.GraphController;
import Model.SCC.BoundsListRender;
import Model.SCC.ConstrainsLists.InternalConstarinsList;
import View.SolutionPanel.InternalConstarinsClusterGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * This class will represent the Controller for the ManualIntegerInequalities Panel
 *
 */
public class InternalConstarinsClusterListController implements ActionListener{

    private InternalConstarinsClusterGUI  InternalConstarinsClusterGUI;
    private InternalConstarinsList lbl;


    public InternalConstarinsClusterListController(InternalConstarinsClusterGUI InternalConstarinsClusterGUI){
        this.InternalConstarinsClusterGUI = InternalConstarinsClusterGUI;

    }


    public void populate(GraphController graph){
        lbl = new InternalConstarinsList(graph.getSCCComponents());
        lbl.addObserver(InternalConstarinsClusterGUI);
        Map<Integer, ImageIcon> map = lbl.populate();
        BoundsListRender render = new BoundsListRender();
        render.setImageMap(map);
        InternalConstarinsClusterGUI.setRender(render);
        lbl.tryUpdate();
    }


    public void populate(){
        lbl = new InternalConstarinsList();
        lbl.addObserver(InternalConstarinsClusterGUI);
        Map<Integer, ImageIcon> map = lbl.populate();
        BoundsListRender render = new BoundsListRender();
        render.setImageMap(map);
        InternalConstarinsClusterGUI.setRender(render);
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


