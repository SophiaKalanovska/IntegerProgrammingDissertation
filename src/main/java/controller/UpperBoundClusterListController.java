package controller;

import model.SCC.BoundsListRender;
import model.SCC.UpperBoundList;
import view.SolutionPanel.UpperBoundClusterGUI;

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
public class UpperBoundClusterListController implements ActionListener, MouseListener {

    private UpperBoundClusterGUI UpperBoundClusterGUI;
    private UpperBoundList lbl;

    /**
     * Constructs a Controller for the ManualIntegerInequalities panel
     *
     * @param UpperBoundClusterGUI the ManualIntegerInequalities  JFrame that this class will control
     */
    public UpperBoundClusterListController(UpperBoundClusterGUI UpperBoundClusterGUI){
        this.UpperBoundClusterGUI = UpperBoundClusterGUI;
        UpperBoundClusterGUI.addMouseListener(this);
    }


    public void populate(GraphController graphController){
        lbl = new UpperBoundList(graphController.getSCCComponents());
        lbl.addObserver(UpperBoundClusterGUI);
        Map<Integer, ImageIcon> map = lbl.populate();
        BoundsListRender render = new BoundsListRender();
        render.setImageMap(map);
        UpperBoundClusterGUI.setRender(render);
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


