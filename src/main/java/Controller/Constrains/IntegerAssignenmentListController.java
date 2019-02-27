package Controller.Constrains;

import Model.Inequalities.InequalitiesList;
import Model.SCC.BoundsListRender;
import Model.SCC.ConstrainsLists.IntegerAssignmentList;
import Model.SCC.ConstrainsLists.InternalConstarinsList;
import View.SolutionPanel.IntegerAssignmentGUI;
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
public class IntegerAssignenmentListController implements ActionListener, MouseListener {

    private IntegerAssignmentGUI IntegerAssignmentGUI;
    private IntegerAssignmentList lbl;


    public IntegerAssignenmentListController(IntegerAssignmentGUI IntegerAssignmentGUI){
        this.IntegerAssignmentGUI = IntegerAssignmentGUI;
        IntegerAssignmentGUI.addMouseListener(this);
    }


    public void populate(InequalitiesList inequalitiesList){
        lbl = new IntegerAssignmentList(inequalitiesList.getSCCComponents());
        lbl.addObserver(IntegerAssignmentGUI);
        Map<Integer, ImageIcon> map = lbl.populate();
        BoundsListRender render = new BoundsListRender();
        render.setImageMap(map);
        IntegerAssignmentGUI.setRender(render);
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


