package Controller.Constrains;

import Controller.GraphController;
import Model.Inequalities.InequalitiesList;
import Model.SCC.BoundsListRender;
import Model.SCC.ConstrainsLists.InternalConstarinsList;
import View.SolutionPanel.InternalConstarinsClusterGUI;

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
public class InternalConstarinsClusterListController implements ActionListener, MouseListener {

    private InternalConstarinsClusterGUI  InternalConstarinsClusterGUI;
    private InternalConstarinsList lbl;


    public InternalConstarinsClusterListController(InternalConstarinsClusterGUI InternalConstarinsClusterGUI){
        this.InternalConstarinsClusterGUI = InternalConstarinsClusterGUI;
        InternalConstarinsClusterGUI.addMouseListener(this);
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

    /**
     * Mouse listener for the ManualIntegerInequalities panel
     *
     * @param e Mouse listener that will identify the actions that the user makes
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * The action listener for the ManualIntegerInequalities panel
     *
     * @param e the ActionEven object which will identify the performed action
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        }
}


