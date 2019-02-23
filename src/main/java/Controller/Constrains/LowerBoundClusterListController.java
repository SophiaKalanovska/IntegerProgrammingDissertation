package Controller.Constrains;

import Model.Inequalities.InequalitiesList;
import Model.SCC.ConstrainsLists.LowerBoundList;
import Model.SCC.BoundsListRender;
import View.SolutionPanel.LowerBoundClusterGUI;

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
public class LowerBoundClusterListController implements ActionListener, MouseListener {

    private LowerBoundClusterGUI LowerBoundClusterGUI;
    private LowerBoundList lbl;

    /**
     * Constructs a Controller for the ManualIntegerInequalities panel
     *
     * @param LowerBoundClusterGUI the ManualIntegerInequalities  JFrame that this class will control
     */
    public LowerBoundClusterListController( LowerBoundClusterGUI LowerBoundClusterGUI){
        this.LowerBoundClusterGUI = LowerBoundClusterGUI;
        LowerBoundClusterGUI.addMouseListener(this);
    }


    public void populate(InequalitiesList inequalitiesList){
        lbl = new LowerBoundList(inequalitiesList.getSCCComponents());
        lbl.addObserver(LowerBoundClusterGUI);
        Map<Integer, ImageIcon> map = lbl.populate();
        BoundsListRender render = new BoundsListRender();
        render.setImageMap(map);
        LowerBoundClusterGUI.setRender(render);
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


