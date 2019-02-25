package Controller;

import View.Graph.GraphGUI;
import View.LayoutGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CheckboxActionController extends AbstractAction {

    private LayoutGUI layoutGUI;


    public CheckboxActionController(String text,  LayoutGUI layoutGUI) {
        super(text);
        this.layoutGUI = layoutGUI;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBox cbLog = (JCheckBox) e.getSource();
        if (cbLog.isSelected()) {
            layoutGUI.changeView(true);
        } else {
            layoutGUI.changeView(false);
        }
    }
}
